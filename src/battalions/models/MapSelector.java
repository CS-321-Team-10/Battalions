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
 *
 * @author Scott
 */
public class MapSelector extends PropertyChangeNotifier
{
    public static final String MOVING_EVENT = "moving";

    public static final String ATTACKING_EVENT = "attacking";

    public static final String ASSISTING_EVENT = "assisting";

    public static final String CURRENT_PLAYER_PROPERTY = "currentPlayer";

    public static final String SELECTABLE_UNITS_PROPERTY = "selectableUnits";

    public static final String SELECTED_UNIT_PROPERTY = "selectedUnit";

    public static final String CURSOR_LOCATION_PROPERTY = "cursorLocation";

    private final Map map;

    private Player currentPlayer;

    private Set<Unit> selectableUnits;

    private Unit selectedUnit;

    private Location cursorLocation;

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
                acted = tryMoveTo(l);
                if (!acted)
                {
                    deselect();
                    return;
                }
            }
            else
            {
                deselect();
                return;
            }
        }
        else if (target.equals(selectedUnit))
        {
            deselect();
            return;
        }
        else if (currentPlayer.owns(target))
        {
            if (selectedUnit instanceof Unit)
            {
                acted = tryAssist(target);
                if (!acted && !trySelect(target))
                {
                    deselect();
                    return;
                }
            }
            else if (!trySelect(target))
            {
                deselect();
                return;
            }
        }
        else if (selectedUnit instanceof Unit)
        {
            acted = tryAttack(target);
            if (!acted && !trySelect(target))
            {
                deselect();
                return;
            }
        }
        else if (!trySelect(target))
        {
            deselect();
            return;
        }

        // If still selected
        if (selectedUnit instanceof Unit)
        {
            if (acted && !selectedUnit.hasAvailableOptions())
            {
                // Used up last option for this turn
                deselect();
                return;
            }
            else
            {
                // Hide selectable units
                setSelectableUnits(Collections.EMPTY_SET);
            }
        }
    }

    public void deselect()
    {
        setSelectedUnit(null);
        setSelectableUnits(currentPlayer instanceof Player
            ? currentPlayer.getAvailableUnits()
            : Collections.EMPTY_SET);
    }

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

    private boolean tryAttack(Unit target)
    {
        boolean attacking = selectedUnit.tryAttack(target);

        if (attacking)
        {
            propertyChangeSupport.firePropertyChange(ATTACKING_EVENT, selectedUnit, target);
        }

        return attacking;
    }

    private boolean tryAssist(Unit target)
    {
        boolean assisting = selectedUnit.tryAttack(target);

        if (assisting)
        {
            propertyChangeSupport.firePropertyChange(ASSISTING_EVENT, selectedUnit, target);
        }

        return assisting;
    }

    public final Map getMap()
    {
        return map;
    }

    public final Player getCurrentPlayer()
    {
        return currentPlayer;
    }

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

    public final Set<Unit> getSelectableUnits()
    {
        return Collections.unmodifiableSet(selectableUnits);
    }

    private void setSelectableUnits(Set<Unit> value)
    {
        assert value instanceof Set;

        Set<Unit> oldValue = selectableUnits;
        selectableUnits = value;

        propertyChangeSupport.firePropertyChange(SELECTABLE_UNITS_PROPERTY, oldValue, selectableUnits);
    }

    public final Unit getSelectedUnit()
    {
        return selectedUnit;
    }

    private void setSelectedUnit(Unit value)
    {
        Unit oldValue = selectedUnit;
        selectedUnit = value;

        propertyChangeSupport.firePropertyChange(SELECTED_UNIT_PROPERTY, oldValue, selectedUnit);
    }

    public final Location getCursorLocation()
    {
        return cursorLocation;
    }

    public void setCursorLocation(Location value)
    {
        Location oldValue = cursorLocation;
        Location newValue = map.inBounds(value) ? value : null;
        cursorLocation = newValue;

        propertyChangeSupport.firePropertyChange(CURSOR_LOCATION_PROPERTY, oldValue, cursorLocation);
    }

    public Tile getHighlightedTile()
    {
        return cursorLocation instanceof Location
            && map.inBounds(cursorLocation)
                ? map.getTileAt(cursorLocation)
                : null;
    }

    public Unit getHighlightedUnit()
    {
        return cursorLocation instanceof Location
            && map.inBounds(cursorLocation)
                ? map.getUnitAt(cursorLocation)
                : null;
    }
}
