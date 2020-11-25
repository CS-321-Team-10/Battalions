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
 * Constructor for a forest tile. Subclass of Tile.
 * @author Bryant Terry
 */
public class TileForest extends Tile
{
    public TileForest(Map map, Location l, Orientation orientation)
    {
        super(map, l, orientation);
        
        _type = TileType.Forest;
    }

}