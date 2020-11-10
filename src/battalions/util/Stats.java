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

import battalions.data.ActionType;

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
     * The maximum random variation that can occur for a heal.
     */
    public static final int HEAL_VARIATION = 2;

    /**
     * The maximum random variation that can occur for an attack assist.
     */
    public static final int ASSIST_VARIATION = 2;

    /**
     * The maximum random variation that can occur for a magic attack assist.
     */
    public static final int MAGIC_ASSIST_VARIATION = 2;

    /**
     * The maximum random variation that can occur for a defend.
     */
    public static final int DEFEND_VARIATION = 1;

    /**
     * Luck stat values must be between 0 and this value (exclusive).
     */
    public static final int LUCK_RANGE = 10;

    /**
     * The multiplicative boost to ATK or MATK stats when lucky.
     */
    public static final float LUCKY_ATTACK_MULTIPLIER = 1.5f;

    /**
     * The multiplicative boost to ATK or MATK assists when lucky.
     */
    public static final float LUCKY_ASSIST_MULTIPLIER = 2.0f;

    /**
     * The maximum boosted defense when lucky
     */
    public static final int LUCKY_DEFEND = 100;

    /**
     * Returns a small random variation on the provided base stat.
     * @param action the type of action being performed
     * @param stat the base stat corresponding to the type of action
     * @param luck the base luck stat
     * @return a randomly varied stat
     */
    public static int randomVariation(ActionType action, int stat, int luck)
    {
        int base = stat;
        int random = 0;
        float multiplier = 1.0f;

        switch (action)
        {
            case Attack:
                random = Rng.getInt(0, ATTACK_VARIATION);
                if (isLucky(luck))
                {
                    multiplier = LUCKY_ATTACK_MULTIPLIER;
                }
                break;
            case MagicAttack:
                random = Rng.getInt(0, MAGIC_ATTACK_VARIATION);
                if (isLucky(luck))
                {
                    multiplier = LUCKY_ATTACK_MULTIPLIER;
                }
                break;
            case Heal:
                random = Rng.getInt(0, HEAL_VARIATION);
                break;
            case Defend:
                random = Rng.getInt(0, DEFEND_VARIATION);
                if (isLucky(luck))
                {
                    base = LUCKY_DEFEND;
                }
                break;
            case AttackAssist:
                random = Rng.getInt(0, ASSIST_VARIATION);
                if (isLucky(luck))
                {
                    multiplier = LUCKY_ASSIST_MULTIPLIER;
                }
                break;
            case MagicAttackAssist:
                random = Rng.getInt(0, MAGIC_ASSIST_VARIATION);
                if (isLucky(luck))
                {
                    multiplier = LUCKY_ASSIST_MULTIPLIER;
                }
                break;
        }

        return (int)(multiplier * (base + random));
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
