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

import java.util.ArrayList;

/**
 * Contains a grid of tiles.
 * @author Bryant
 * @author Scott
 */
public class Map
{
    /**
     * The number of tiles along the direction of the x-axis.
     */
    private final int _width;

    /**
     * The number of tiles along the direction of the y-axis.
     */
    private final int _height;

    /**
     * The grid of tiles that composes this map.
     */
    private final Tile[][] _tiles;

    /**
     * The list of units on the map.
     */
    private final ArrayList<Unit> _units;

    /**
     * Initializes a new instance of the Map class.
     * @param width the width for this map
     * @param height the height for this map
     */
    public Map(int width, int height)
    {
        _width = width;
        _height = height;

        _tiles = new Tile[_width][_height];
        for (int x = 0; x < _width; x++)
        {
            for (int y = 0; y < _height; y++)
            {
                _tiles[x][y] = new Tile(x, y, false, false, false);
            }
        }

        _units = new ArrayList<>();
    }

    /**
     * Returns the width of this map.
     * @return the number of tiles along the direction of the x-axis
     */
    public int getWidth()
    {
        return _width;
    }

    /**
     * Returns the height of this map.
     * @return the number of tiles along the direction of the y-axis
     */
    public int get_height()
    {
        return _height;
    }

    /**
     * Returns the tile positioned at the specified (x, y) coordinate on this map.
     * @param x the x-coordinate of the desired tile
     * @param y the y-coordinate of the desired tile
     * @return the tile at the specified coordinate
     */
    public Tile getTileAt(int x, int y)
    {
        return _tiles[x][y];
    }

    /**
     * Returns the tile on which the specified unit is positioned.
     * @param unit the unit whose tile to return
     * @return the tile at the same (x, y) coordinate of the specified unit
     */
    public Tile getTileUnder(Unit unit)
    {
        return getTileAt(unit.getX(), unit.getY());
    }
}
