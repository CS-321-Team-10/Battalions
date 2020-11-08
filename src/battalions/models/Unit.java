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

import battalions.util.Stats;

/**
 * A single troop who has particular stat values and can attack and be attacked.
 * @author Bryant
 * @author Scott
 */
public class Unit
{
    /**
     * The location of this unit on the x-axis.
     */
    private int _x;

    /**
     * The location of this unit on the y-axis.
     */
    private int _y;

    /**
     * The amount of damage this unit can sustain.
     */
    private int _health;

    /**
     * The damage dealt by this unit's physical attacks.
     */
    private final int _attack;

    /**
     * This unit's reduction to damage from physical attacks.
     */
    private final int _defense;

    /**
     * The damage dealt by this unit's magical attacks.
     */
    private final int _magicAttack;

    /**
     * This unit's reduction to damage from magical attacks.
     */
    private final int _magicDefense;

    /**
     * The speed of this unit for determining action order.
     */
    private final int _speed;

    /**
     * This unit's stat for the relative rarity of performing lucky actions.
     */
    private final int _luck;
    
    /**
     * The amount of tiles this unit can move across
     */
    private final int _movement;
    
    /**
     * The amount of tiles this unit can attack another Unit across
     */
    private final int _range;

    /**
     * Whether or not this unit is alive.
     */
    private boolean _isAlive;
    
    /**
     * Whether or not the unit has moved.
     */
    private boolean _hasMoved;
    
    /**
     * Whether or not the unit has performed an action.
     */
    private boolean _hasActed;

    /**
     * Initializes a new instance of the Unit class.
     * @param x the initial x-coordinate for this unit
     * @param y the initial y-coordinate for this unit
     * @param health the base HP stat for this unit
     * @param attack the base ATK stat for this unit
     * @param defense the base DEF stat for this unit
     * @param magicAttack the base MATK stat for this unit
     * @param magicDefense the base MDEF stat for this unit
     * @param speed the base SPEED stat for this unit
     * @param luck the base LUCK stat for this unit
     * @param movement the base MOVEMENT stat for this unit
     * @param range the base RANGE stat for this unit
     */
    public Unit(int x, int y,
        int health, int attack, int defense,
        int magicAttack, int magicDefense,
        int speed, int luck, int movement, int range)
    {
        assert x >= 0;
        assert y >= 0;

        assert health > 0;
        assert attack >= 0;
        assert defense >= 0;
        assert magicAttack >= 0;
        assert magicDefense >= 0;
        assert speed >= 0;
        assert luck >= 0;
        assert movement >= 0;
        assert range >= 0;

        // Unit must be able to attack either physically or magically
        assert attack > 0 || magicAttack > 0;

        _x = x;
        _y = y;

        _health = health;
        _attack = attack;
        _defense = defense;
        _magicAttack = magicAttack;
        _magicDefense = magicDefense;
        _speed = speed;
        _luck = luck;
        _movement = movement;
        _range = range;

        _isAlive = true;
        _hasMoved = false;
        _hasActed = false;
    }

    /**
     * Attacks the specified unit with a physical attack.
     * @param enemy the enemy to be attacked
     */
    public void attack(Unit enemy)
    {
        int attack = Stats.randomAttack(_attack, _luck);
        enemy.takeDamage(attack);
    }

    /**
     * Attacks the specified unit with a magical attack.
     * @param enemy the enemy to be attacked
     */
    public void magicAttack(Unit enemy)
    {
        int magicAttack = Stats.randomMagicAttack(_magicAttack, _luck);
        enemy.takeMagicDamage(magicAttack);
    }

    /**
     * Takes damage from a physical attack.
     * @param attack the ATK stat of the attacker
     */
    public void takeDamage(int attack)
    {
        assert attack >= 0;

        int damage = Math.max(0, attack - _defense);
        _health = Math.max(0, _health - damage);

        if (_health == 0)
        {
            _isAlive = false;
        }
    }

    /**
     * Takes damage from a magical attack.
     * @param magicAttack the MATK stat of the attacker
     */
    public void takeMagicDamage(int magicAttack)
    {
        assert magicAttack >= 0;

        int damage = Math.max(0, magicAttack - _magicDefense);
        _health = Math.max(0, _health - damage);

        if (_health == 0)
        {
            _isAlive = false;
        }
    }

    /**
     * Gets the current x-coordinate of this unit.
     * @return the current x-coordinate of this unit
     */
    public int getX()
    {
        return _x;
    }
    
    /**
     * Sets the current x-coordinate of this unit.
     * @param x The new x-coordinate of the unit.
     */
    public void setX(int x)
    {
        _x = x;
    }

    /**
     * Gets the current y-coordinate of this unit.
     * @return the current y-coordinate of this unit.
     */
    public int getY()
    {
        return _y;
    }
    
    /**
     * Sets the current y-coordinate of this unit.
     * @param y The new y-coordinate of the unit.
     */
    public void setY(int y)
    {
        _y = y;
    }

    /**
     * Gets the current HP stat of this unit.
     * @return the current HP stat of this unit
     */
    public int getHealth()
    {
        return _health;
    }

    /**
     * Gets the current ATK stat of this unit.
     * @return the current ATK stat of this unit
     */
    public int getAttack()
    {
        return _attack;
    }

    /**
     * Gets the current DEF stat of this unit.
     * @return the current DEF stat of this unit
     */
    public int getDefense()
    {
        return _defense;
    }

    /**
     * Gets the current MATK stat of this unit.
     * @return the current MATK stat of this unit
     */
    public int getMagicAttack()
    {
        return _magicAttack;
    }

    /**
     * Gets the current MDEF stat of this unit.
     * @return the current MDEF stat of this unit
     */
    public int getMagicDefense()
    {
        return _magicDefense;
    }

    /**
     * Gets the current SPEED stat of this unit.
     * @return the current SPEED stat of this unit
     */
    public int getSpeed()
    {
        return _speed;
    }

    /**
     * Gets the current LUCK stat of this unit.
     * @return the current LUCK stat of this unit
     */
    public int getLuck()
    {
        return _luck;
    }
    
    /**
     * Gets the current MOVEMENT stat of this unit.
     * @return the current MOVEMENT stat of this unit
     */
    public int getMovement()
    {
        return _movement;
    }
    
    /**
     * Gets the current RANGE stat of this unit.
     * @return the current RANGE stat of this unit
     */
    public int getRange()
    {
        return _range;
    }

    /**
     * Determines whether or not this unit is alive.
     * @return true, if this unit is alive; false, otherwise
     */
    public boolean isAlive()
    {
        return _isAlive;
    }
    
    /**
     * Determines whether or not this unit has moved for the turn.
     * @return true, if this unit has moved; false, otherwise
     */
    public boolean gethasMoved()
    {
        return _hasMoved;
    }
    
    /**
    * Sets the _hasMoved variable to true or false
    * @param trueorfalse a boolean that is true if the Unit has moved
    */
    public void sethasMoved(boolean trueorfalse)
    {
        _hasMoved = trueorfalse;
    }
    
    /**
    * Determines whether or not this unit has acted for the turn.
    * @return true, if this unit has acted; false, otherwise
    */
    public boolean gethasActed()
    {
        return _hasActed;
    }
    
    /**
    * Sets the _hasActed variable to true or false
    * @param trueorfalse a boolean that is true if the Unit has acted
    */
    public void sethasActed(boolean trueorfalse)
    {
        _hasActed = trueorfalse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        String format =
            (_isAlive ? "Live " : "Dead ")
            + "Unit @(%d, %d)\n"
            + "  [%d HP] [%d SPEED] [%d LUCK]\n"
            + "  [%d ATK] [%d DEF] [%d MATK] [%d MDEF]";

        return String.format(format + '\n', _x, _y, _health, _attack, _defense,
            _magicAttack, _magicDefense, _speed, _luck);
    }
}
