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
 * Specifies the direction an object faces.
 * @author Scott
 */
public enum Orientation
{
    /**
     * The object faces up.
     */
    Up,

    /**
     * The object faces up and right.
     */
    UpRight,

    /**
     * The object faces right.
     */
    Right,

    /**
     * The object faces down and right.
     */
    DownRight,

    /**
     * The object faces down.
     */
    Down,

    /**
     * The object faces down and left.
     */
    DownLeft,

    /**
     * The object faces left.
     */
    Left,

    /**
     * The object faces up and left.
     */
    UpLeft,
}