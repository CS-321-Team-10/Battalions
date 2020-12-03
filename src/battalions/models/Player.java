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
     * Removes a unit from this player.
     * @param u the unit to remove from this player
     */
    public void removeUnit(Unit u)
    {
        _units.remove(u);
    }

    /**
     * Removes all units from this player.
     */
    public void clearUnits()
    {
        _units.clear();
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
     * Returns whether this player is the first player of the game.
     * @return true, if this player is the first player; false, otherwise
     */
    public boolean isPlayer1()
    {
        return getUid() == App.getInstance().getGame().getPlayer1().getUid();
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

    /**
     * Returns whether this player owns a unit.
     * @param unit the unit to check if it is a child of this player
     * @return true, if the unit is owned by this player; false, otherwise
     */
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
        return _units.stream()
            .collect(Collectors.toSet());
    }

    /**
     * Returns the set of all living units owned by this player.
     * @return a copy of the set of all units owned by this player
     * who are still alive
     */
    public Set<Unit> getLivingUnits()
    {
        return getUnits().stream()
            .filter(x -> x.isAlive())
            .collect(Collectors.toSet());
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
            .collect(Collectors.toSet());
    }
}
