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
 * Represents an object that is located at a coordinate on a map.
 * @author Scott
 */
public interface IMapItem
{
    /**
     * Returns the map in which this object resides and
     * of which this object is a child.
     * @return the parent map of this object
     */
    Map getMap();
    
    /**
     * Returns the current location of this object on the map.
     * @return the current location of this object on the map
     */
    Location getLocation();
}
