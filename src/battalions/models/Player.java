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

import battalions.App;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    /**
     * Returns whether this player is a CPU player.
     * @return true, if this player is a CPU player; false, otherwise
     */
    public boolean isCPU()
    {
        // [TODO Make this method of determining CPU more robust]
        return getUid() == App.getInstance().getGame().getCPU().getUid();
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

    public boolean owns(Unit unit)
    {
        assert unit instanceof Unit;

        return this._units.contains(unit);
    }

    /**
     * Returns the set of all units owned by this player.
     * @return a copy of the set of all units owned by this player
     */
    public Set<Unit> getUnits()
    {
        Set<Unit> result = new HashSet<>();

        _units.stream()
            .forEach(x -> result.add(x));

        return result;
    }

    /**
     * Returns the set of all living units owned by this player.
     * @return a copy of the set of all units owned by this player
     * who are still alive
     */
    public Set<Unit> getLivingUnits()
    {
        Set<Unit> living = new HashSet<>();

        getUnits().stream()
            .filter(x -> x.isAlive())
            .forEach(x -> living.add(x));

        return living;
    }

    /**
     * Returns whether this player has at least one unit who has a valid
     * movement or action option.
     * @return true, if this player has a unit who can move or act; false, otherwise
     */
    public boolean hasAvailableUnits()
    {
        return getAvailableUnits().size() >= 1;
    }

    /**
     * Returns the set of all units owned by this player who have at least
     * one valid movement or action available.
     * @return a copy of the set of units available to move or attack
     */
    public Set<Unit> getAvailableUnits()
    {
        return this._units
            .stream()
            .filter(unit -> unit.hasAvailableOptions())
            .collect(Collectors.toUnmodifiableSet());
    }
}
