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
 * Bit-flags representing the effects a tile has on a unit standing on it.
 * @author Scott
 */
public class TileEffectFlags
{
    /**
     * This tile includes no effect on a unit standing on it.
     */
    public static final int NONE = 0;

    /**
     * This tile prevents a unit from standing on or crossing it.
     */
    public static final int IMPASSABLE = 1;

    /**
     * This tile boosts a unit's chance of dodging an attack.
     */
    public static final int BOOSTS_DODGE = 1 << 1;

    /**
     * This tile reduces a unit's movement range.
     */
    public static final int REDUCES_MOVEMENT = 1 << 2;

    /**
     * Returns whether the provided combination of effects includes
     * the specified effect.
     * @param effectFlags a combination of tile effects
     * @param effectFlag a single tile effect to be checked
     * @return true, if the combination includes the specified effect; false, otherwise
     */
    public static boolean includes(int effectFlags, int effectFlag)
    {
        return (effectFlags & effectFlag) == effectFlag;
    }
}
