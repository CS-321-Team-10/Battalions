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

/**
 * Specifies the type of a unit action.
 * @author Scott
 */
public enum ActionType
{
    /**
     * Performs a physical attack.
     */
    Attack,

    /**
     * Performs a magical attack.
     */
    MagicAttack,

    /**
     * Restores an ally unit's health stat.
     */
    Heal,

    /**
     * Boosts a unit's own defense stat.
     */
    Defend,

    /**
     * Boosts an ally unit's attack stat.
     */
    AttackAssist,

    /**
     * Boosts an ally unit's magic attack stat.
     */
    MagicAttackAssist,
}
