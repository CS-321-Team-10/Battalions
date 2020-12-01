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
import battalions.data.Orientation;
import battalions.data.TileType;

/**
 * A single square on the map.
 * @author Scott
 * @author Bryant
 */
public class Tile implements IMapItem
{
    /**
     * The map in which this tile resides.
     */
    private final Map _map;

    /**
     * The location of this tile on the map.
     */
    private final Location _location;

    /**
     * The type of this tile, containing the effectFlags it has on a unit.
     */
    private final TileType _type;

    /**
     * The type of tile to display underneath this tile.
     */
    private final TileType _underlay;

    /**
     * The direction this tile faces.
     */
    private final Orientation _orientation;

    /**
     * Initializes a new instance of the Tile class.
     * @param map the map in which this tile resides
     * @param l the initial location for this tile
     * @param type the type of this tile, containing the effectFlags it has on a unit
     */
    public Tile(Map map, Location l, TileType type)
    {
        this(map, l, type, null, Orientation.NONE);
    }

    /**
     * Initializes a new instance of the Tile class.
     * @param map the map in which this tile resides
     * @param l the initial location for this tile
     * @param type the type of this tile, containing the effectFlags it has on a unit
     * @param underlay the type of tile to display underneath this tile
     */
    public Tile(Map map, Location l, TileType type, TileType underlay)
    {
        this(map, l, type, underlay, Orientation.NONE);
    }

    /**
     * Initializes a new instance of the Tile class.
     * @param map the map in which this tile resides
     * @param l the initial location for this tile
     * @param type the type of this tile, containing the effectFlags it has on a unit
     * @param underlay the type of tile to display underneath this tile
     * @param orientation the direction this tile faces
     */
    public Tile(Map map, Location l, TileType type, TileType underlay, Orientation orientation)
    {
        assert map != null;
        assert map.inBounds(l);
        assert type != null;

        _map = map;
        _location = l;

        _type = type;
        _underlay = underlay;
        _orientation = orientation;
    }

    /**
     * Returns the type of this tile, containing the effectFlags it has on a unit.
     * @return the type of this tile
     */
    public final TileType getType()
    {
        return _type;
    }

    /**
     * Returns the type of tile to display underneath this tile.
     * @return the type of the tile that displays underneath this tile
     */
    public final TileType getUnderlayType()
    {
        return _underlay;
    }

    @Override
    public final Map getMap()
    {
        return _map;
    }

    @Override
    public final Location getLocation()
    {
        return _location;
    }

    @Override
    public final Orientation getOrientation()
    {
        return _orientation;
    }

    @Override
    public String toString()
    {
        return "Tile " + _location.toString()
            + (_type.isImpassable() ? " [WALL]" : "")
            + (_type.boostsDodge() ? " [+DODGE]" : "")
            + (_type.reducesMovement() ? " [-MOVE]" : "");
    }
}
