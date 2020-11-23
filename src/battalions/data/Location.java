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
package battalions.data;

/**
 * Stores an ordered (x, y) pair representing a location.
 * @author Scott
 */
public class Location
{
    /**
     * The x-value of this location.
     */
    public final int x;

    /**
     * The y-value of this location.
     */
    public final int y;

    /**
     * Initializes a new instance of the Location class.
     * @param x the x-value for this location
     * @param y the y-value for this location
     */
    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns a new location that is offset from this location by the specified amount.
     * @param l the offset between this location and the desired return location
     * @return a new location offset from this location by the specified amount
     */
    public Location plus(Location l)
    {
        return new Location(x + l.x, y + l.y);
    }

    /**
     * Returns a new location which is the relative offset to transform
     * the specified location into this location.
     * @param l the source location that, when added to the returned offset,
     * would produce this location
     * @return a new location which is the relative offset between the two locations
     */
    public Location minus(Location l)
    {
        return new Location(x - l.x, y - l.y);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this)
        {
            return true;
        }

        if ((o instanceof Location) == false)
        {
            return false;
        }

        Location other = (Location)o;
        return other.x == x && other.y == y;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 73 * hash + this.x;
        hash = 73 * hash + this.y;
        return hash;
    }

    @Override
    public String toString()
    {
        return "(" + x + " " + y + ")";
    }
}