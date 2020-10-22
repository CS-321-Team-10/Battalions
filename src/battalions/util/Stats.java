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
package battalions.util;

/**
 * Used for calculating stat variations.
 * @author Scott
 */
public final class Stats
{
    /**
     * The maximum random variation that can occur for a physical attack.
     */
    public static final int ATTACK_VARIATION = 1;

    /**
     * The maximum random variation that can occur for a magical attack.
     */
    public static final int MAGIC_ATTACK_VARIATION = 2;

    /**
     * Luck stat values must be between 0 and this value (exclusive).
     */
    public static final int LUCK_RANGE = 10;

    /**
     * The multiplicative boost to ATK or MATK stats when lucky.
     */
    public static final double LUCKY_ATTACK_MULTIPLIER = 1.5;

    /**
     * Returns a small random variation on the provided base ATK stat.
     * @param attack the base ATK stat
     * @param luck the base LUCK stat
     * @return a randomly varied ATK stat
     */
    public static int randomAttack(int attack, int luck)
    {
        int base = attack + Rng.getInt(0, ATTACK_VARIATION);
        return isLucky(luck) ? (int)(base * LUCKY_ATTACK_MULTIPLIER) : base;
    }

    /**
     * Returns a small random variation on the provided base MATK stat.
     * @param magicAttack the base MATK stat
     * @param luck the base LUCK stat
     * @return a randomly varied MATK stat
     */
    public static int randomMagicAttack(int magicAttack, int luck)
    {
        int base = magicAttack + Rng.getInt(0, MAGIC_ATTACK_VARIATION);
        return isLucky(luck) ? (int)(base * LUCKY_ATTACK_MULTIPLIER) : base;
    }

    /**
     * Determines whether or not a lucky action occurs.
     * @param luck a unit's LUCK stat
     * @return true, if the action is lucky; false, otherwise
     */
    private static boolean isLucky(int luck)
    {
        assert luck < LUCK_RANGE;

        return Rng.getInt(0, LUCK_RANGE - 1) < luck;
    }
}
