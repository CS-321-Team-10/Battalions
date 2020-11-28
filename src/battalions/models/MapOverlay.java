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

/**
 * Provides a model for a player's selection of units and tiles on the map.
 * @author Scott
 */
public class MapOverlay
{
    /**
     * The selection mode allowing nothing to be selected.
     */
    public static final int SELECT_NONE_MODE = 0;

    /**
     * The selection mode flag allowing tiles to be selected.
     */
    public static final int SELECT_TILE_MODE = 1;

    /**
     * The selection mode flag allowing friendly units to be selected.
     */
    public static final int SELECT_FRIENDLY_UNIT_MODE = 1 << 1;

    /**
     * The selection mode flag allowing enemy units to be selected.
     */
    public static final int SELECT_ENEMY_UNIT_MODE = 1 << 2;

    /**
     * The selection mode allowing anything to be selected.
     */
    public static final int SELECT_ANY_MODE = SELECT_TILE_MODE | SELECT_FRIENDLY_UNIT_MODE | SELECT_ENEMY_UNIT_MODE;

    /**
     * The current selection mode flags.
     */
    private int _selectionMode = SELECT_ANY_MODE;

    /**
     * The map on which this overlay is based.
     */
    private final Map _map;

    /**
     * The currently selected tile, if one exists; null, otherwise.
     */
    private Tile _selectedTile;

    /**
     * The currently selected unit, if one exists; null, otherwise.
     */
    private Unit _selectedFriendlyUnit;

    /**
     * The currently selected enemy unit, if one exists; null, otherwise.
     */
    private Unit _selectedEnemyUnit;

    /**
     * Initializes a new instance of the MapOverlay class.
     * @param map the map on which this overlay is based
     */
    public MapOverlay(Map map)
    {
        assert map != null;
        _map = map;
    }

    /**
     * Selects a unit or tile (based on the current selection mode)
     * at the specified location. If one is already selected, deselects it.
     * @param l the location at which to select a unit or tile
     */
    public void toggleSelect(Location l)
    {
        Unit unit = _map.getUnitAt(l);
        Tile tile = _map.getTileAt(l);

        if ((_selectionMode & SELECT_FRIENDLY_UNIT_MODE) != 0
            && unit != null
            && unit.getPlayer().isCPU() == false)
        {
            _selectedFriendlyUnit = (_selectedFriendlyUnit != unit)
                ? unit
                : null;
        }
        else if ((_selectionMode & SELECT_ENEMY_UNIT_MODE) != 0
            && unit != null
            && unit.getPlayer().isCPU())
        {
            _selectedEnemyUnit = (_selectedEnemyUnit != unit)
                ? unit
                : null;
        }
        else if ((_selectionMode & SELECT_TILE_MODE) != 0
            && tile != null)
        {
            _selectedTile = (_selectedTile != tile)
                ? tile
                : null;
        }
    }

    /**
     * Deselects all current unit and tile selections.
     */
    public void clearSelection()
    {
        _selectedTile = null;
        _selectedFriendlyUnit = null;
        _selectedEnemyUnit = null;
    }

    /**
     * Returns the map on which this overlay is based.
     * @return the map on which this overlay is based
     */
    public final Map getMap()
    {
        return _map;
    }

    /**
     * Sets the current selection mode flags.
     * @param selectionMode the new selection mode flags
     */
    public final void setSelectionMode(int selectionMode)
    {
        _selectionMode = selectionMode;
    }

    /**
     * Returns the currently selected tile.
     * @return the currently selected tile, if one exists; null, otherwise
     */
    public final Tile getSelectedTile()
    {
        return _selectedTile;
    }

    /**
     * Returns the currently selected friendly unit.
     * @return the currently selected friendly unit, if one exists; null, otherwise
     */
    public final Unit getSelectedFriendlyUnit()
    {
        return _selectedFriendlyUnit;
    }

    /**
     * Returns the currently selected enemy unit.
     * @return the currently selected enemy unit, if one exists; null, otherwise
     */
    public final Unit getSelectedEnemyUnit()
    {
        return _selectedEnemyUnit;
    }
}
