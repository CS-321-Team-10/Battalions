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
     * The first player of the game.
     */
    private final Player player1;

    /**
     * The second player of the game.
     */
    private final Player player2;

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
     * @param player1 the first player for the game
     * @param player2 the second player for the game
     */
    public Game(MapSelector mapSelector, Player player1, Player player2)
    {
        assert mapSelector != null;
        assert player1 != null;
        assert player2 != null;

        this.mapSelector = mapSelector;
        this.map = mapSelector.getMap();
        this.player1 = player1;
        this.player2 = player2;

        this.mapSelector.setCurrentPlayer(this.player1);

        this.registerProperty(WINNER_PROPERTY, () -> this.getWinner());
    }

    /**
     * Clears all save data from this game.
     */
    public void clear()
    {
        map.clearUnits();
        player1.clearUnits();
        player2.clearUnits();
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
        if (player2.getLivingUnits().isEmpty())
        {
            setWinner(player1);
        }
        else if (player1.getLivingUnits().isEmpty())
        {
            setWinner(player2);
        }

        return winner;
    }

    /**
     * Returns the player whose turn it is currently not.
     * @return the player whose turn it is currently not
     */
    public Player nextPlayer()
    {
        return currentPlayer.isPlayer1() ? getPlayer2() : getPlayer1();
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
     * Returns the first player of the game.
     * @return the first player
     */
    public final Player getPlayer1()
    {
        return player1;
    }

    /**
     * Returns the second player of the game.
     * @return the second player
     */
    public final Player getPlayer2()
    {
        return player2;
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

            if (propertyName.equals(MapSelector.CURRENT_PLAYER_PROPERTY))
            {
                // If current player changed, update
                currentPlayer = (Player) newValue;
            }
            else if (propertyName.equals(MapSelector.MOVING_EVENT)
                || propertyName.equals(MapSelector.ATTACKING_EVENT)
                || propertyName.equals(MapSelector.ASSISTING_EVENT))
            {
                // If some action was just performed
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
