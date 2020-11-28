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

import battalions.data.Location;
import battalions.data.ActionType;
import battalions.data.UnitType;
import battalions.util.Stats;
import java.util.HashSet;
import java.util.Set;

/**
 * A single troop who has particular stat values and can attack and be attacked.
 * @author Bryant
 * @author Scott
 */
public class Unit implements ITurnBased, IMapItem, IPlayerItem
{
    /**
     * The player who owns this unit.
     */
    private final Player _player;

    /**
     * The map in which this unit resides.
     */
    private final Map _map;

    /**
     * The location of this unit on the map.
     */
    private Location _location;

    /**
     * The type of this unit.
     */
    private final UnitType _type;

    /**
     * Whether this unit has performed a move this turn.
     */
    private boolean _hasMoved;

    /**
     * Whether this unit has performed an action this turn.
     */
    private boolean _hasActed;

    /**
     * Whether this unit is alive.
     */
    private boolean _isAlive;

    /**
     * The maximum amount of damage this unit can sustain.
     */
    private final int _maxHealth;

    /**
     * The amount of damage this unit can sustain.
     */
    private int _health;

    /**
     * The damage dealt by this unit's physical attacks.
     */
    private int _attack;

    /**
     * This unit's reduction to damage from physical attacks.
     */
    private int _defense;

    /**
     * The damage dealt by this unit's magical attacks.
     */
    private int _magicAttack;

    /**
     * This unit's reduction to damage from magical attacks.
     */
    private int _magicDefense;

    /**
     * The speed of this unit for determining action order.
     */
    private int _speed;

    /**
     * This unit's stat for the relative rarity of performing lucky actions.
     */
    private int _luck;

    /**
     * Initializes a new instance of the Unit class.
     * @param player the player who owns this unit
     * @param map the map in which this unit resides
     * @param l the initial location for this unit
     * @param type the type of this unit
     */
    public Unit(Player player, Map map, Location l, UnitType type)
    {
        assert player != null;
        assert map != null;
        assert map.inBounds(l);

        _player = player;
        _map = map;
        _location = l;
        _type = type;

        _health = type.baseHealth;
        _maxHealth = _health;

        _attack = type.baseAttack;
        _defense = type.baseDefense;
        _magicAttack = type.baseMagicAttack;
        _magicDefense = type.baseMagicDefense;
        _speed = type.baseSpeed;
        _luck = type.baseLuck;

        _isAlive = true;
        _hasMoved = false;
        _hasActed = false;
    }

    /**
     * Returns whether this unit can move to the specified location.
     * @param l the hypothetical location to move to
     * @return whether moving to the specified location is possible
     */
    public boolean canMoveTo(Location l)
    {
        // Check if has not already moved or acted
        //  and within valid movement range
        //  and move logic is valid
        return (_hasMoved == false) && (_hasActed == false)
            && _type.canMove(l.minus(_location))
            && _map.canMoveTo(this, l);
    }

    /**
     * Returns whether this unit can attack the specified unit.
     * @param target the hypothetical enemy to be attacked
     * @return whether an attack on the specified unit is possible
     */
    public boolean canAttack(Unit target)
    {
        Location l = target.getLocation();

        // Check if has not already acted
        //  and within valid attack range
        //  and attack logic is valid
        return (_hasActed == false)
            && _type.canAct(l.minus(_location))
            && _map.canAttack(this, target);
    }

    /**
     * Returns whether this unit can assist the specified unit.
     * @param target the hypothetical enemy to be attacked
     * @return whether an attack on the specified unit is possible
     */
    public boolean canAssist(Unit target)
    {
        Location l = target.getLocation();

        // Check if has not already acted
        //  and within valid assist range
        //  and assist logic is valid
        return (_hasActed == false)
            && _type.canAct(l.minus(_location))
            && _map.canAssist(this, target);
    }

    /**
     * If possible, moves to the specified location.
     * @param l the location to be moved to
     * @return whether the move was successfully performed
     */
    public boolean tryMoveTo(Location l)
    {
        // Check if move is impossible
        if (canMoveTo(l) == false)
        {
            return false;
        }

        // Move to specified location
        setLocation(l);

        _hasMoved = true;
        return true;
    }

    /**
     * If possible, attacks the specified enemy unit with a physical attack.
     * @param target the enemy unit to be attacked
     * @return whether the attack was successfully performed
     */
    public boolean tryAttack(Unit target)
    {
        // Check if attack is impossible
        if (canAttack(target) == false)
        {
            return false;
        }

        // Attack enemy unit
        int actual = Stats.randomVariation(ActionType.Attack, _attack, _luck);
        target.takeDamage(actual);

        _hasActed = true;
        return true;
    }

    /**
     * If possible, attacks the specified enemy unit with a magical attack.
     * @param target the enemy unit to be attacked
     * @return whether the attack was successfully performed
     */
    public boolean tryMagicAttack(Unit target)
    {
        // Check if attack is impossible
        if (canAttack(target) == false)
        {
            return false;
        }

        // Attack enemy unit
        int actual = Stats.randomVariation(ActionType.MagicAttack, _magicAttack, _luck);
        target.takeMagicDamage(actual);

        _hasActed = true;
        return true;
    }

    /**
     * If possible, heals the specified friendly unit.
     * @param target the friendly unit to be healed
     * @return whether the heal was successfully performed
     */
    public boolean tryHeal(Unit target)
    {
        // Check if assist is impossible
        if (canAssist(target) == false)
        {
            return false;
        }

        // Heal friendly unit
        int actual = Stats.randomVariation(ActionType.Heal, Math.max(_attack, _magicAttack), _luck);
        target.healBy(actual);

        _hasActed = true;
        return true;
    }

    /**
     * If possible, assists the specified unit by boosting its physical attack.
     * @param target the friendly unit to be assisted
     * @return whether the assist was successfully performed
     */
    public boolean tryAttackAssist(Unit target)
    {
        // Check if assist is impossible
        if (canAssist(target) == false)
        {
            return false;
        }

        // Assist friendly unit
        int actual = Stats.randomVariation(ActionType.AttackAssist, _attack, _luck);
        target.boostAttackBy(actual);

        _hasActed = true;
        return true;
    }

    /**
     * If possible, assists the specified unit by boosting its magical attack.
     * @param target the friendly unit to be assisted
     * @return whether the assist was successfully performed
     */
    public boolean tryMagicAttackAssist(Unit target)
    {
        // Check if assist is impossible
        if (canAssist(target) == false)
        {
            return false;
        }

        // Assist friendly unit
        int actual = Stats.randomVariation(ActionType.MagicAttackAssist, _magicAttack, _luck);
        target.boostMagicAttackBy(actual);

        _hasActed = true;
        return true;
    }

    /**
     * If possible, this unit boosts its own defense.
     * @return whether defense was successfully boosted
     */
    public boolean tryDefend()
    {
        // Check if defend is impossible
        if (_hasActed)
        {
            return false;
        }

        // Perform defend
        int actual = Stats.randomVariation(ActionType.Defend, _defense, _luck);
        boostDefenseBy(actual);

        _hasActed = true;
        return true;
    }

    /**
     * Uses this unit's movement pattern mask and the current map state
     * to return its possible moves.
     * @return the set of absolute move options for this unit
     */
    public Set<Location> getValidMoves()
    {
        Set<Location> valid = new HashSet<>();

        _type.moves()
            .filter(x -> _map.canMoveTo(this, x))
            .forEach(x -> valid.add(x));

        return valid;
    }

    /**
     * Uses this unit's attack pattern mask and the current map state
     * to return its possible attacks.
     * @return the set of absolute attack options for this unit
     */
    public Set<Location> getValidAttacks()
    {
        Set<Location> valid = new HashSet<>();

        _type.actions()
            .filter(x -> _map.canAttack(this, _map.getUnitAt(x)))
            .forEach(x -> valid.add(x));

        return valid;
    }

    /**
     * Takes damage from a physical attack.
     * @param damage the ATK stat of the attacker
     */
    public void takeDamage(int damage)
    {
        assert damage >= 0;

        // Ensure damage taken is non-negative and at least 1
        int actualDamage = Math.max(1, damage - _defense);

        // Ensure health remains >= 0
        _health = Math.max(0, _health - actualDamage);

        if (_health <= 0)
        {
            die();
        }
    }

    /**
     * Takes damage from a magical attack.
     * @param damage the MATK stat of the attacker
     */
    public void takeMagicDamage(int damage)
    {
        assert damage >= 0;

        // Ensure damage taken is non-negative
        int actualDamage = Math.max(0, damage - _magicDefense);

        // Ensure health remains >= 0
        _health = Math.max(0, _health - actualDamage);

        if (_health <= 0)
        {
            die();
        }
    }

    /**
     * Restores the specified amount of health, or heals until maximum
     * health has been reached.
     * @param amount the amount of HP to restore
     */
    public void healBy(int amount)
    {
        assert amount >= 0;

        // Cap health at max
        _health = Math.min(_maxHealth, _health + amount);
    }

    /**
     * Boosts this unit's attack stat by the specified amount.
     * @param amount the amount by which to increase the ATK stat
     */
    public void boostAttackBy(int amount)
    {
        assert amount >= 0;

        _attack += amount;
    }

    /**
     * Boosts this unit's magical attack stat by the specified amount.
     * @param amount the amount by which to increase the MATK stat
     */
    public void boostMagicAttackBy(int amount)
    {
        assert amount >= 0;

        _magicAttack += amount;
    }

    /**
     * Boosts this unit's defense stat by the specified amount.
     * @param amount the amount by which to increase the DEF stat
     */
    public void boostDefenseBy(int amount)
    {
        assert amount >= 0;

        _defense += amount;
    }

    @Override
    public void beginTurn()
    {
        _hasMoved = false;
        _hasActed = false;

        // Clear attack/defense boosts
        _attack = _type.baseAttack;
        _defense = _type.baseDefense;
        _magicAttack = _type.baseMagicAttack;
        _magicDefense = _type.baseMagicDefense;
    }

    @Override
    public void endTurn()
    {

    }

    @Override
    public final Player getPlayer()
    {
        return _player;
    }

    @Override
    public final Map getMap()
    {
        return _map;
    }

    @Override
    public final Location getLocation()
    {
        return _location;
    }

    /**
     * Returns the type of this unit, containing the base stats and
     * movement/attack patterns of this unit.
     * @return the type of this unit
     */
    public final UnitType getType()
    {
        return _type;
    }

    /**
     * Sets the location of this unit on the map.
     * @param l the new location to move to
     */
    private void setLocation(Location l)
    {
        _location = l;
    }

    /**
     * Returns whether this unit has used its move for this turn.
     * @return true, if this unit has moved; false, otherwise
     */
    public final boolean hasMoved()
    {
        return _hasMoved;
    }

    /**
    * Returns whether this unit has used its action for this turn.
    * @return true, if this unit has acted; false, otherwise
    */
    public final boolean hasActed()
    {
        return _hasActed;
    }

    /**
     * Gets the current HP stat of this unit.
     * @return the current HP stat of this unit
     */
    public final int getHealth()
    {
        return _health;
    }

    /**
     * Gets the current ATK stat of this unit.
     * @return the current ATK stat of this unit
     */
    public final int getAttack()
    {
        return _attack;
    }

    /**
     * Gets the current DEF stat of this unit.
     * @return the current DEF stat of this unit
     */
    public final int getDefense()
    {
        return _defense;
    }

    /**
     * Gets the current MATK stat of this unit.
     * @return the current MATK stat of this unit
     */
    public final int getMagicAttack()
    {
        return _magicAttack;
    }

    /**
     * Gets the current MDEF stat of this unit.
     * @return the current MDEF stat of this unit
     */
    public final int getMagicDefense()
    {
        return _magicDefense;
    }

    /**
     * Gets the current SPEED stat of this unit.
     * @return the current SPEED stat of this unit
     */
    public final int getSpeed()
    {
        return _speed;
    }

    /**
     * Gets the current LUCK stat of this unit.
     * @return the current LUCK stat of this unit
     */
    public final int getLuck()
    {
        return _luck;
    }

    /**
     * Determines whether or not this unit is alive.
     * @return true, if this unit is alive; false, otherwise
     */
    public final boolean isAlive()
    {
        return _isAlive;
    }

    /**
     * Set this unit's alive status to false and removes it from the map.
     */
    private void die()
    {
        _isAlive = false;
        _map.removeUnit(this);
    }

    @Override
    public String toString()
    {
        return _type + " " + _location.toString()
            + (_isAlive ? "" : " [DEAD]")
            + "\n  [" + _health + " HP] [" + _speed + " SPEED] [" + _luck + " LUCK]"
            + "\n  [" + _attack + " ATK] [" + _defense + " DEF] [" + _magicAttack + " MATK] [" + _magicDefense + " MDEF]";
    }
}
