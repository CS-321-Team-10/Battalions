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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Set;

/**
 * Manages turns and controls the flow of the game.
 * @author Blocker
 * @author Bryant
 * @author Scott
 */
public class Game implements ITurnBased, PropertyChangeListener
{
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
    public Game(Map map, Player player, Player cpu)
    {
        assert map != null;
        assert player != null;
        assert cpu != null;

        _map = map;
        _player = player;
        _cpu = cpu;
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

    /**
     * If a player has lost all units, returns the opposing player as the winner.
     * If neither player has lost all units, returns null.
     * @return the winning player, if a player has won; null, otherwise
     */
    public Player getWinner()
    {
        if (_cpu.getLivingUnits().isEmpty())
        {
            return _player;
        }

        if (_player.getLivingUnits().isEmpty())
        {
            return _cpu;
        }

        return null;
    }

    public void winGame(Player winner)
    {
        // [TODO] Indicate that the game is over
        System.out.println("Player " + (winner.isCPU() ? "2" : "1") + " wins the game!");
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
    public final Player getCPU()
    {
        return _cpu;
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

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        Object source = evt.getSource();
        String propertyName = evt.getPropertyName();

        if (source instanceof MapSelector)
        {
            MapSelector mapSelector = (MapSelector) source;

            // If some action was just performed
            if (propertyName.equals(MapSelector.MOVING_EVENT)
                || propertyName.equals(MapSelector.ATTACKING_EVENT)
                || propertyName.equals(MapSelector.ASSISTING_EVENT))
            {
                Player currentPlayer = mapSelector.getCurrentPlayer();

                Player winner = getWinner();
                if (winner instanceof Player)
                {
                    winGame(winner);
                }
                else if (currentPlayer.getAvailableUnits().isEmpty())
                {
                    Player nextPlayer = currentPlayer.isCPU() ? _player : _cpu;

                    currentPlayer.endTurn();
                    nextPlayer.beginTurn();

                    mapSelector.setCurrentPlayer(nextPlayer);
                    mapSelector.deselect();
                }
            }
        }
    }
}
