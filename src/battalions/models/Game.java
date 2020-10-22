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
 * Manages turns and controls the flow of the game.
 * @author Bryant
 * @author Scott
 */
public class Game
{
    /**
     * The width of the main map.
     */
    public static final int MAP_WIDTH = 10;

    /**
     * The height of the main map.
     */
    public static final int MAP_HEIGHT = 10;

    /**
     * The main map, containing a grid of tiles and all units.
     */
    private final Map _map;

    /**
     * Initializes a new instance of the Game class.
     */
    public Game()
    {
        _map = new Map(MAP_WIDTH, MAP_HEIGHT);
    }

    /**
     * Returns the main map, containing a grid of tiles and all units.
     * @return the main map
     */
    public Map getMap()
    {
        return _map;
    }
}
