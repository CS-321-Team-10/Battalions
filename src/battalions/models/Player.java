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

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a player of the game.
 * @author Scott
 */
public class Player implements ITurnBased
{
    /**
     * A counter that holds the unique ID of the last instantiated player.
     */
    private static int _currentId = 0;

    /**
     * The unique ID of this player.
     */
    private final int _uid;

    /**
     * A set of units owned by this player.
     */
    private final Set<Unit> _units;

    /**
     * Initializes a new instance of the Player class.
     */
    public Player()
    {
        // Increment static UID and assign to this player
        _currentId++;
        _uid = _currentId;

        _units = new HashSet<>();
    }

    /**
     * Adds a unit as a child of this player.
     * @param u the unit that this player will now own
     */
    public void addUnit(Unit u)
    {
        Player p = u.getPlayer();

        // Ensure unit is not owned by another player
        assert p == this || p == null;

        _units.add(u);
    }

    /**
     * Returns the unique ID of this player.
     * @return the unique ID of this player
     */
    public final int getUid()
    {
        return _uid;
    }

    @Override
    public void beginTurn()
    {
        for (Unit u : _units)
        {
            u.beginTurn();
        }
    }

    @Override
    public void endTurn()
    {
        for (Unit u : _units)
        {
            u.endTurn();
        }
    }
}
