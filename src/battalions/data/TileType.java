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
package battalions.data;

/**
 * Contains information about the type of a tile and its effectFlags on a unit.
 * @author Scott
 */
public enum TileType
{
    Field(TileEffectFlags.NONE),
    Forest(TileEffectFlags.BOOSTS_DODGE),
    Sand(TileEffectFlags.REDUCES_MOVEMENT),
    Wall(TileEffectFlags.IMPASSABLE);

    /**
     * Bit-flags representing the effects this tile has on a unit.
     */
    public final int effectFlags;

    /**
     * Initializes a new value of the TileType enumeration.
     * @param effectFlags bit-flags representing the effects this tile has on a unit
     */
    private TileType(int effectFlags)
    {
        this.effectFlags = effectFlags;
    }

    /**
     * Returns whether or not this tile type is impassable.
     * @return true, if this tile type is impassable; false, otherwise
     */
    public boolean isImpassable()
    {
        return (effectFlags & TileEffectFlags.IMPASSABLE) == TileEffectFlags.IMPASSABLE;
    }

    /**
     * Returns whether or not this tile type boosts a unit's chance of dodging an attack.
     * @return true, if this tile type boosts dodge chance; false, otherwise
     */
    public boolean boostsDodge()
    {
        return (effectFlags & TileEffectFlags.BOOSTS_DODGE) == TileEffectFlags.BOOSTS_DODGE;
    }

    /**
     * Returns whether or not this tile type reduces a unit's movement range.
     * @return true, if this tile type reduces movement range; false, otherwise
     */
    public boolean reducesMovement()
    {
        return (effectFlags & TileEffectFlags.REDUCES_MOVEMENT) == TileEffectFlags.REDUCES_MOVEMENT;
    }
}