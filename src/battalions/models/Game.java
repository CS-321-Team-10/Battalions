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

import battalions.properties.PropertyChangeNotifier;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Manages turns and controls the flow of the game.
 * @author Blocker
 * @author Bryant
 * @author Scott
 */
public class Game extends PropertyChangeNotifier implements PropertyChangeListener
{
    /**
     * The property indicating the player who has won the game.
     */
    public static final String WINNER_PROPERTY = "winner";

    /**
     * The map selector for the game, keeping track of highlights and selections.
     */
    private final MapSelector mapSelector;

    /**
     * The main map, containing a grid of tiles and all units.
     */
    private final Map map;

    /**
     * The human player for the game.
     */
    private final Player player;

    /**
     * The CPU player for the game.
     */
    private final Player cpu;

    /**
     * The current player whose turn it is.
     */
    private Player currentPlayer;

    /**
     * The winner of the game.
     */
    private Player winner;

    /**
     * Initializes a new instance of the Game class.
     * @param mapSelector the map selector for the game
     * @param player the first player for the game
     * @param cpu the second player for the game
     */
    public Game(MapSelector mapSelector, Player player, Player cpu)
    {
        assert mapSelector != null;
        assert player != null;
        assert cpu != null;

        this.mapSelector = mapSelector;
        this.map = mapSelector.getMap();
        this.player = player;
        this.cpu = cpu;

        this.mapSelector.setCurrentPlayer(this.player);

        this.registerProperty(WINNER_PROPERTY, () -> this.getWinner());
    }

    /**
     * Begins the game.
     */
    public void start()
    {
        currentPlayer.beginTurn();
    }

    /**
     * Ends the current turn and begins the next turn.
     */
    public void nextTurn()
    {
        currentPlayer.endTurn();

        Player nextPlayer = nextPlayer();
        nextPlayer.beginTurn();

        currentPlayer = nextPlayer;

        mapSelector.setCurrentPlayer(currentPlayer);
        mapSelector.deselect();
    }

    /**
     * If a player has lost all units, returns the opposing player as the winner.
     * If neither player has lost all units, returns null.
     * @return the winning player, if a player has won; null, otherwise
     */
    public Player checkWinGame()
    {
        if (cpu.getLivingUnits().isEmpty())
        {
            setWinner(player);
        }
        else if (player.getLivingUnits().isEmpty())
        {
            setWinner(cpu);
        }

        return winner;
    }

    /**
     * Returns the player whose turn it is currently not.
     * @return the player whose turn it is currently not
     */
    public Player nextPlayer()
    {
        return currentPlayer.isCPU() ? getPlayer() : getCPU();
    }

    /**
     * Returns the map selector, which keeps track of highlights and selections.
     * @return the map selector
     */
    public final MapSelector getMapSelector()
    {
        return mapSelector;
    }

    /**
     * Returns the main map, containing a grid of tiles and all units.
     * @return the main map
     */
    public final Map getMap()
    {
        return map;
    }

    /**
     * Returns the human player of the game.
     * @return the human player
     */
    public final Player getPlayer()
    {
        return player;
    }

    /**
     * Returns the CPU player of the game.
     * @return the CPU player
     */
    public final Player getCPU()
    {
        return cpu;
    }

    /**
     * Returns the winner of the game.
     * @return the player who has won the game, if one has; null, otherwise
     */
    public final Player getWinner()
    {
        checkWinGame();
        return winner;
    }

    /**
     * Sets the value of the winner property and may fire a property change notification.
     * @param value the new value of the property
     */
    private void setWinner(Player value)
    {
        Player oldValue = winner;
        winner = value;

        this.propertyChangeSupport.firePropertyChange(WINNER_PROPERTY, oldValue, winner);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        Object source = evt.getSource();
        String propertyName = evt.getPropertyName();
        Object newValue = evt.getNewValue();

        if (source instanceof MapSelector)
        {
            MapSelector selector = (MapSelector) source;

            // If some action was just performed
            if (propertyName.equals(MapSelector.CURRENT_PLAYER_PROPERTY))
            {
                currentPlayer = (Player) newValue;
            }
            else if (propertyName.equals(MapSelector.MOVING_EVENT)
                || propertyName.equals(MapSelector.ATTACKING_EVENT)
                || propertyName.equals(MapSelector.ASSISTING_EVENT))
            {
                currentPlayer = selector.getCurrentPlayer();

                // Check for win conditions
                if (checkWinGame() instanceof Player)
                {
                }
                else if (!currentPlayer.hasAvailableUnits())
                {
                    // End turns and set next player
                    nextTurn();
                }
            }
        }
    }
}
