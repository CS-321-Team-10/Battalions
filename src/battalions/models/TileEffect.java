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

import java.util.EnumSet;

/**
 * Specifies the effect of a tile.
 * @author Scott
 */
public enum TileEffect
{
    /**
     * This tile prevents units from standing on or crossing it.
     */
    Impassable(1),

    /**
     * This tile boosts a unit's chance of dodging an attack.
     */
    BoostsDodgeChance(1 << 1),

    /**
     * This tile reduces a unit's movement range.
     */
    ReducesMovementRange(1 << 2);

    /**
     * The underlying value of this TileEffect.
     */
    private final int _value;

    /**
     * Initializes a new instance of the TileEffect class.
     * @param value the underlying value of this TileEffectFlag
     */
    private TileEffect(int value)
    {
        _value = value;
    }

    /**
     * Returns an EnumSet representing this TileEffect value, which can be
     * used to check which individual bit-flags are set.
     * @return an EnumSet representing this TileEffect value
     */
    public EnumSet<TileEffect> getEffects()
    {
        EnumSet<TileEffect> effects = EnumSet.noneOf(TileEffect.class);

        for (TileEffect effect : TileEffect.values())
        {
             if ((effect._value & this._value) == this._value)
             {
                 effects.add(effect);
             }
        }

        return effects;
    }
}
