/*
 * School:         University of Alabama in Huntsville
 * Course Title:   Object-Oriented Programming in Java
 * Instructor:     Dr. Dan Rochowiak
 *
 * Course Number:  CS 321
 * Course Section: 01
 * Term:           Fall 2020
 *
 * Team:           10
 * Team Members:   Scott Clarke
 *                 Guess Crow
 *                 Blocker Griffin
 *                 Thomas Lemmons
 *                 Bryant Terry
 */
package battalions.models;

import battalions.data.Location;
import battalions.properties.PropertyChangeNotifier;
import java.util.Collections;
import java.util.Set;

/**
 * Wraps a map with the ability for a player to highlight/select/move/attack/assist
 * with units/tiles.
 * @author Scott
 */
public class MapSelector extends PropertyChangeNotifier
{
    /**
     * The event that is fired when a unit has moved.
     */
    public static final String MOVING_EVENT = "moving";

    /**
     * The event that is fired when a unit has attacked.
     */
    public static final String ATTACKING_EVENT = "attacking";

    /**
     * The event that is fired when a unit has assisted a friendly unit.
     */
    public static final String ASSISTING_EVENT = "assisting";

    /**
     * The property for the current player whose turn it is.
     */
    public static final String CURRENT_PLAYER_PROPERTY = "currentPlayer";

    /**
     * The property for the currently selectable units.
     */
    public static final String SELECTABLE_UNITS_PROPERTY = "selectableUnits";

    /**
     * The property for the currently selected unit.
     */
    public static final String SELECTED_UNIT_PROPERTY = "selectedUnit";

    /**
     * The property for the current cursor location on the map.
     */
    public static final String CURSOR_LOCATION_PROPERTY = "cursorLocation";

    /**
     * The wrapped map.
     */
    private final Map map;

    /**
     * The current player whose turn it is.
     */
    private Player currentPlayer;

    /**
     * The currently selectable units.
     */
    private Set<Unit> selectableUnits;

    /**
     * The currently selected unit.
     */
    private Unit selectedUnit;

    /**
     * The current cursor location on the map.
     */
    private Location cursorLocation;

    /**
     * Initializes a new instance of the MapSelector, wrapping the provided map,
     * @param map the map to wrap with the ability to make selections/moves/actions
     */
    public MapSelector(Map map)
    {
        assert map instanceof Map;
        this.map = map;

        selectableUnits = Collections.EMPTY_SET;
        deselect();

        registerProperty(CURRENT_PLAYER_PROPERTY, () -> getCurrentPlayer());
        registerProperty(SELECTABLE_UNITS_PROPERTY, () -> getSelectableUnits());
        registerProperty(SELECTED_UNIT_PROPERTY, () -> getSelectedUnit());
        registerProperty(CURSOR_LOCATION_PROPERTY, () -> getCursorLocation());
    }

    /**
     * Toggles the selection of the unit at the specified map location
     * or performs a move/attack/assist.
     * @param l the location to select or perform a move/attack/assist on
     */
    public void select(Location l)
    {
        if (!map.inBounds(l))
        {
            return;
        }

        Unit target = map.getUnitAt(l);

        boolean acted = false;

        if (target == null)
        {
            if (selectedUnit instanceof Unit)
            {
                // Try to move; otherwise, deselect
                acted = tryMoveTo(l);
                if (!acted)
                {
                    deselect();
                    return;
                }
            }
            else
            {
                // Target and selected unit are null, update selectable units
                deselect();
                return;
            }
        }
        else if (target.equals(selectedUnit))
        {
            // Toggle selection if clicked on already selected unit
            deselect();
            return;
        }
        else if (currentPlayer.owns(target))
        {
            if (selectedUnit instanceof Unit)
            {
                // Try to assist; otherwise, deselect
                acted = tryAssist(target);
                if (!acted && !trySelect(target))
                {
                    deselect();
                    return;
                }
            }
            else if (!trySelect(target))
            {
                // If no unit could be selected, update selectable units
                deselect();
                return;
            }
        }
        else if (selectedUnit instanceof Unit)
        {
            // Try to attack; otherwise, deselect
            acted = tryAttack(target);
            if (!acted && !trySelect(target))
            {
                deselect();
                return;
            }
        }
        else if (!trySelect(target))
        {
            // If no unit could be selected, update selectable units
            deselect();
            return;
        }

        // If a unit is still selected
        if (selectedUnit instanceof Unit)
        {
            if (acted && !selectedUnit.hasAvailableOptions())
            {
                // If it used up its last option for this turn
                deselect();
                return;
            }
            else
            {
                // Hide other selectable units if one is already selected
                setSelectableUnits(Collections.EMPTY_SET);
            }
        }
    }

    /**
     * Deselects a unit and updates the currently selectable units.
     */
    public final void deselect()
    {
        setSelectedUnit(null);
        setSelectableUnits(currentPlayer instanceof Player
            ? currentPlayer.getAvailableUnits()
            : Collections.EMPTY_SET);
    }

    /**
     * Tries to select a unit.
     * @param unit the unit to try to select
     * @return true, if the unit was selected; false, otherwise
     */
    private boolean trySelect(Unit unit)
    {
        boolean selected = currentPlayer.owns(unit)
            && unit.hasAvailableOptions();

        if (selected)
        {
            setSelectedUnit(unit);
        }

        return selected;
    }

    /**
     * Tries to move the selected unit to a location
     * @param location the location to try to move to
     * @return true, if the selected unit moved; false, otherwise
     */
    private boolean tryMoveTo(Location location)
    {
        Tile source = map.getTileUnder(selectedUnit);
        boolean moving = selectedUnit.tryMoveTo(location);

        if (moving)
        {
            Tile destination = map.getTileUnder(selectedUnit);
            propertyChangeSupport.firePropertyChange(MOVING_EVENT, source, destination);
        }

        return moving;
    }

    /**
     * Tries to attack a target unit with the selected unit.
     * @param target the target unit to attack
     * @return true, if the target unit was attacked; false, otherwise
     */
    private boolean tryAttack(Unit target)
    {
        boolean attacking = selectedUnit.tryAttack(target);

        if (attacking)
        {
            propertyChangeSupport.firePropertyChange(ATTACKING_EVENT, selectedUnit, target);
        }

        return attacking;
    }

    /**
     * Tries to assist a target unit with the selected unit.
     * @param target the target unit to assist
     * @return true, if the target unit was assisted; false, otherwise
     */
    private boolean tryAssist(Unit target)
    {
        boolean assisting = selectedUnit.tryAttack(target);

        if (assisting)
        {
            propertyChangeSupport.firePropertyChange(ASSISTING_EVENT, selectedUnit, target);
        }

        return assisting;
    }

    /**
     * Returns the map wrapped by this selector.
     * @return the map wrapped by this selector
     */
    public final Map getMap()
    {
        return map;
    }

    /**
     * Returns the current player whose turn it is.
     * @return the current player whose turn it is
     */
    public final Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    /**
     * Sets the current player and may fire a property change event.
     * @param value the new value of the property
     */
    public void setCurrentPlayer(Player value)
    {
        assert value instanceof Player;

        Player oldValue = currentPlayer;
        currentPlayer = value;

        propertyChangeSupport.firePropertyChange(CURRENT_PLAYER_PROPERTY, oldValue, currentPlayer);

        if (selectedUnit == null)
        {
            deselect();
        }
    }

    /**
     * Returns the set of currently selectable units.
     * @return the set of currently selectable units
     */
    public final Set<Unit> getSelectableUnits()
    {
        return Collections.unmodifiableSet(selectableUnits);
    }

    /**
     * Sets the currently selectable units and may fire a property change event.
     * @param value the new value of the property
     */
    private void setSelectableUnits(Set<Unit> value)
    {
        assert value instanceof Set;

        Set<Unit> oldValue = selectableUnits;
        selectableUnits = value;

        propertyChangeSupport.firePropertyChange(SELECTABLE_UNITS_PROPERTY, oldValue, selectableUnits);
    }

    /**
     * Returns the currently selected unit
     * @return the currently selected unit
     */
    public final Unit getSelectedUnit()
    {
        return selectedUnit;
    }

    /**
     * Sets the currently selected unit and may fire a property change event.
     * @param value the new value of the property
     */
    private void setSelectedUnit(Unit value)
    {
        Unit oldValue = selectedUnit;
        selectedUnit = value;

        propertyChangeSupport.firePropertyChange(SELECTED_UNIT_PROPERTY, oldValue, selectedUnit);
    }

    /**
     * Returns the current cursor location on the map
     * @return the current cursor location
     */
    public final Location getCursorLocation()
    {
        return cursorLocation;
    }

    /**
     * Sets the current cursor location on the map.
     * @param value the new value of the property
     */
    public void setCursorLocation(Location value)
    {
        Location oldValue = cursorLocation;
        Location newValue = map.inBounds(value) ? value : null;
        cursorLocation = newValue;

        propertyChangeSupport.firePropertyChange(CURSOR_LOCATION_PROPERTY, oldValue, cursorLocation);
    }

    /**
     * Returns the currently highlighted tile under the cursor.
     * @return the currently highlighted tile
     */
    public Tile getHighlightedTile()
    {
        return cursorLocation instanceof Location
            && map.inBounds(cursorLocation)
                ? map.getTileAt(cursorLocation)
                : null;
    }

    /**
     * Returns the currently highlighted unit under the cursor.
     * @return the currently highlighted unit
     */
    public Unit getHighlightedUnit()
    {
        return cursorLocation instanceof Location
            && map.inBounds(cursorLocation)
                ? map.getUnitAt(cursorLocation)
                : null;
    }
}
