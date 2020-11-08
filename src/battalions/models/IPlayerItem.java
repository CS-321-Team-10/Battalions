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
 * Represents an object that is owned by a specific player.
 * @author Scott
 */
public interface IPlayerItem
{
    /**
     * Returns the player who owns this object.
     * @return the parent player of this object
     */
    Player getPlayer();
}
