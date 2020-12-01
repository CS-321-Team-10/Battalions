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
public class Game implements ITurnBased
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
     * The human player for the game.
     */
    private final Player _player;

    /**
     * The CPU player for the game.
     */
    private final Player _cpu;

    /**
     * Initializes a new instance of the Game class.
     */
    public Game()
    {
        _map = new Map(MAP_WIDTH, MAP_HEIGHT);
        _player = new Player();
        _cpu = new Player();
    }

    /**
     * Returns the main map, containing a grid of tiles and all units.
     * @return the main map
     */
    public final Map getMap()
    {
        return _map;
    }

    /**
     * Returns the human player of the game.
     * @return the human player
     */
    public final Player getPlayer()
    {
        return _player;
    }

    /**
     * Returns the CPU player of the game.
     * @return the CPU player
     */
    public final Player getCpu()
    {
        return _cpu;
    }

    /**
     * Begins the game.
     */
    public void start()
    {
        beginTurn();
    }

    /**
     * Ends the current turn and begins the next turn.
     */
    public void nextTurn()
    {
        endTurn();
        beginTurn();
    }

    @Override
    public void beginTurn()
    {
        _player.beginTurn();
        _cpu.beginTurn();
    }

    @Override
    public void endTurn()
    {
        _player.endTurn();
        _cpu.endTurn();
    }
    
    /**
     * Checks each player's to determine if either has lost all of their units.
     * If either player does not have any remaining units, the other player is declared the winner.
     * Otherwise, the winner is undetermined, or null.
     * @author Blocker
     */
    public Player getWinner()
    {
        if(_cpu.getLivingUnits().size() == 0)
        {
            return _player;
        }
        
        else if(_player.getLivingUnits().size() == 0)
        {
            return _cpu;
        }
        
        else
            return null;
    }
}
