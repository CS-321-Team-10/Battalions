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
 * A struct that specifies the direction an object faces.
 * @author Scott
 */
public class Orientation
{
    /**
     * The object will be displayed without any flipping.
     */
    public static final Orientation NONE = new Orientation(false);

    /**
     * Whether this object should be flipped horizontally.
     */
    public final boolean flipHorizontal;

    /**
     * Initializes a new instance of the Orientation class.
     * @param flipHorizontal whether this object should be flipped horizontally
     */
    public Orientation(boolean flipHorizontal)
    {
        this.flipHorizontal = flipHorizontal;
    }
}