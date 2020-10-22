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

/**
 * A single square on the map.
 * @author Scott
 * @author Bryant
 */
public class Tile
{
    /**
     * The location of the tile on the x-axis.
     */
    private final int _x;

    /**
     * The location of the tile on the y-axis.
     */
    private final int _y;

    /**
     * Whether or not this tile is impassible.
     */
    private final boolean _isWall;

    /**
     * Whether or not this tile boosts a unit's chance of dodging an attack.
     */
    private final boolean _boostsDodge;

    /**
     * Whether or not this tile reduces a unit's movement range.
     */
    private final boolean _reducesMovement;

    /**
     * Initializes a new instance of the Tile class.
     * @param x the location of this tile on the x-axis
     * @param y the location of this tile on the y-axis
     * @param isWall whether or not this tile is impassible
     * @param boostsDodge whether or not this tile boosts a unit's chance of dodging an attack
     * @param reducesMovement whether or not this tile reduces a unit's movement range
     */
    public Tile(int x, int y, boolean isWall, boolean boostsDodge, boolean reducesMovement)
    {
        _x = x;
        _y = y;
        _isWall = isWall;
        _boostsDodge = boostsDodge;
        _reducesMovement = reducesMovement;
    }

    /**
     * Gets the x-coordinate of this tile.
     * @return the location of this tile on the x-axis
     */
    public int getX()
    {
        return _x;
    }

    /**
     * Gets the y-coordinate of this tile.
     * @return the location of this tile on the y-axis
     */
    public int getY()
    {
        return _y;
    }

    /**
     * Returns whether or not this tile is impassible.
     * @return true, if this tile is impassible; false, otherwise
     */
    public boolean isWall()
    {
        return _isWall;
    }

    /**
     * Returns whether or not this tile boosts a unit's chance of dodging an attack.
     * @return true, if this tile boosts dodge chance; false, otherwise
     */
    public boolean boostsDodge()
    {
        return _boostsDodge;
    }

    /**
     * Returns whether or not this tile reduces a unit's movement range.
     * @return true, if this tile reduces movement range; false, otherwise
     */
    public boolean reducesMovement()
    {
        return _reducesMovement;
    }
}
