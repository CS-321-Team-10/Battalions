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
 * Represents an object that has consistent behavior related to beginning
 * and ending each turn.
 * @author Scott
 */
public interface ITurnBased
{
    /**
     * Prepares this object (and any children) to begin the current turn.
     */
    void beginTurn();

    /**
     * Cleans up this object (and any children) after completing the current turn.
     */
    void endTurn();
}
