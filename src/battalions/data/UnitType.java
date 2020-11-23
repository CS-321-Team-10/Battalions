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

import battalions.util.LocationSets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Contains information about the base stats of each unit type.
 * @author Blocker
 * @author Scott
 */
public enum UnitType
{
    /**
     * The infantry unit type.
     */
    Infantry(/* HP */ 20, /* ATK */ 10, /* DEF */ 5, /* MATK */ 3, /* MDEF */ 4, /* SPD */ 5, /* LUCK */ 1,
        new HashSet<>(
            Arrays.asList(
                new Location[]
                { /* MOVES */
                    new Location(-2, -1), new Location(-2,  0), new Location(-2,  1),
                    new Location(-1, -2), new Location(-1, -1), new Location(-1,  0), new Location(-1,  1), new Location(-1,  2),
                    new Location( 0, -2), new Location( 0, -1), new Location( 0,  1), new Location( 0,  2),
                    new Location( 1, -2), new Location( 1, -1), new Location( 1,  0), new Location( 1,  1), new Location( 1,  2),
                    new Location( 2, -1), new Location( 2,  0), new Location( 2,  1)
                })),
        new HashSet<>(
            Arrays.asList(
                new Location[]
                { /* ACTIONS */
                    new Location(-1, -1), new Location(-1,  0), new Location(-1,  1),
                    new Location( 0, -1), new Location( 0,  0), new Location( 0,  1),
                    new Location( 1, -1), new Location( 1,  0), new Location( 1,  1)
                }))
    ),

    /**
     * The mage unit type.
     */
    Mage(/* HP */ 12, /* ATK */ 3, /* DEF */ 3, /* MATK */ 10, /* MDEF */ 5, /* SPD */ 4, /* LUCK */ 2,
        new HashSet<>(
            Arrays.asList(
                new Location[]
                { /* MOVES */
                    new Location(-2, -1), new Location(-2,  0), new Location(-2,  1),
                    new Location(-1, -2), new Location(-1, -1), new Location(-1,  0), new Location(-1,  1), new Location(-1,  2),
                    new Location( 0, -2), new Location( 0, -1), new Location( 0,  1), new Location( 0,  2),
                    new Location( 1, -2), new Location( 1, -1), new Location( 1,  0), new Location( 1,  1), new Location( 1,  2),
                    new Location( 2, -1), new Location( 2,  0), new Location( 2,  1)
                })),
        new HashSet<>(
            Arrays.asList(
                new Location[]
                { /* ACTIONS */
                    new Location(-2, -1), new Location(-2,  0), new Location(-2,  1),
                    new Location(-1, -2), new Location(-1, -1), new Location(-1,  0), new Location(-1,  1), new Location(-1,  2),
                    new Location( 0, -2), new Location( 0, -1), new Location( 0,  1), new Location( 0,  2),
                    new Location( 1, -2), new Location( 1, -1), new Location( 1,  0), new Location( 1,  1), new Location( 1,  2),
                    new Location( 2, -1), new Location( 2,  0), new Location( 2,  1)
                }))
    ),

    /**
     * The knight unit type.
     */
    Knight(/* HP */ 24, /* ATK */ 7, /* DEF */ 8, /* MATK */ 0, /* MDEF */ 3, /* SPD */ 2, /* LUCK */ 1,
        new HashSet<>(
            Arrays.asList(
                new Location[]
                { /* MOVES */
                    new Location(-1, -1), new Location(-1,  0), new Location(-1,  1),
                    new Location( 0, -1), new Location( 0,  1),
                    new Location( 1, -1), new Location( 1,  0), new Location( 1,  1)
                })),
        new HashSet<>(
            Arrays.asList(
                new Location[]
                { /* ACTIONS */
                    new Location(-1, -1), new Location(-1,  0), new Location(-1,  1),
                    new Location( 0, -1), new Location( 0,  0), new Location( 0,  1),
                    new Location( 1, -1), new Location( 1,  0), new Location( 1,  1)
                }))
    ),

    /**
     * The warlock unit type.
     */
    Warlock(/* HP */ 16, /* ATK */ 3, /* DEF */ 2, /* MATK */ 9, /* MDEF */ 9, /* SPD */ 3, /* LUCK */ 3,
        new HashSet<>(
            Arrays.asList(
                new Location[]
                { /* MOVES */
                    new Location(-1, -1), new Location(-1,  0), new Location(-1,  1),
                    new Location( 0, -1), new Location( 0,  1),
                    new Location( 1, -1), new Location( 1,  0), new Location( 1,  1)
                })),
        new HashSet<>(
            Arrays.asList(
                new Location[]
                { /* ACTIONS */
                    new Location(-2, -1), new Location(-2,  0), new Location(-2,  1),
                    new Location(-1, -2), new Location(-1, -1), new Location(-1,  0), new Location(-1,  1), new Location(-1,  2),
                    new Location( 0, -2), new Location( 0, -1), new Location( 0,  1), new Location( 0,  2),
                    new Location( 1, -2), new Location( 1, -1), new Location( 1,  0), new Location( 1,  1), new Location( 1,  2),
                    new Location( 2, -1), new Location( 2,  0), new Location( 2,  1)
                }))
    ),

    /**
     * The archer unit type.
     */
    Archer(/* HP */ 12, /* ATK */ 7, /* DEF */ 4, /* MATK */ 3, /* MDEF */ 5, /* SPD */ 4, /* LUCK */ 1,
        new HashSet<>(
            Arrays.asList(
                new Location[]
                { /* MOVES */
                    new Location(-2, -1), new Location(-2,  0), new Location(-2,  1),
                    new Location(-1, -2), new Location(-1, -1), new Location(-1,  0), new Location(-1,  1), new Location(-1,  2),
                    new Location( 0, -2), new Location( 0, -1), new Location( 0,  1), new Location( 0,  2),
                    new Location( 1, -2), new Location( 1, -1), new Location( 1,  0), new Location( 1,  1), new Location( 1,  2),
                    new Location( 2, -1), new Location( 2,  0), new Location( 2,  1)
                })),
        new HashSet<>(
            Arrays.asList(
                new Location[]
                { /* ACTIONS */
                    new Location(-3, -1), new Location(-3,  0), new Location(-3,  1),
                    new Location(-2, -2), new Location(-2, -1), new Location(-2,  0), new Location(-2,  1), new Location(-2,  2),
                    new Location(-1, -3), new Location(-1, -2), new Location(-1, -1), new Location(-1,  0), new Location(-1,  1), new Location(-1,  2), new Location(-1,  3),
                    new Location( 0, -3), new Location( 0, -2), new Location( 0, -1), new Location( 0,  1), new Location( 0,  2), new Location( 0,  3),
                    new Location( 1, -3), new Location( 1, -2), new Location( 1, -1), new Location( 1,  0), new Location( 1,  1), new Location( 1,  2), new Location( 1,  3),
                    new Location( 2, -2), new Location( 2, -1), new Location( 2,  0), new Location( 2,  1), new Location( 2,  2), new Location( 3, -1), new Location( 3,  0), new Location( 3,  1)
                }))
    ),

    /**
     * The healer unit type.
     */
    Healer(/* HP */ 10, /* ATK */ 2, /* DEF */ 2, /* MATK */ 8, /* MDEF */ 7, /* SPD */ 4, /* LUCK */ 1,
        new HashSet<>(
            Arrays.asList(
                new Location[]
                { /* MOVES */
                    new Location(-2, -1), new Location(-2,  0), new Location(-2,  1),
                    new Location(-1, -2), new Location(-1, -1), new Location(-1,  0), new Location(-1,  1), new Location(-1,  2),
                    new Location( 0, -2), new Location( 0, -1), new Location( 0,  1), new Location( 0,  2),
                    new Location( 1, -2), new Location( 1, -1), new Location( 1,  0), new Location( 1,  1), new Location( 1,  2),
                    new Location( 2, -1), new Location( 2,  0), new Location( 2,  1)
                })),
        new HashSet<>(
            Arrays.asList(
                new Location[]
                { /* ACTIONS */
                    new Location(-1, -1), new Location(-1,  0), new Location(-1,  1),
                    new Location( 0, -1), new Location( 0,  0), new Location( 0,  1),
                    new Location( 1, -1), new Location( 1,  0), new Location( 1,  1)
                }))
    );

    /**
     * The base amount of damage this unit type can sustain.
     */
    public final int baseHealth;

    /**
     * The base damage dealt by this unit type's physical attacks.
     */
    public final int baseAttack;

    /**
     * This unit type's base reduction to damage from physical attacks.
     */
    public final int baseDefense;

    /**
     * The base damage dealt by this unit type's magical attacks.
     */
    public final int baseMagicAttack;

    /**
     * This unit type's base reduction to damage from magical attacks.
     */
    public final int baseMagicDefense;

    /**
     * The speed of this unit type for determining action order.
     */
    public final int baseSpeed;

    /**
     * This unit type's base stat for the relative rarity of performing lucky actions.
     */
    public final int baseLuck;

    /**
     * The set of relative movement options for this unit.
     */
    private final Set<Location> _moveMask;

    /**
     * The set of relative action options for this unit.
     */
    private final Set<Location> _actionMask;

    /**
     * Returns whether this unit type can perform the specified relative movement.
     * @param relative the relative location to check
     * @return true, if the movement can be made; false, otherwise
     */
    public final boolean canMove(Location relative)
    {
        return _moveMask.contains(relative);
    }

    /**
     * Returns whether this unit type can perform the specified relative action.
     * @param relative the relative location to check
     * @return true, if the action can be made; false, otherwise
     */
    public final boolean canAct(Location relative)
    {
        return _actionMask.contains(relative);
    }

    /**
     * Returns a sequence of this unit type's valid movements that can be
     * used for aggregate operations.
     * @return a stream of this unit type's valid movements
     */
    public final Stream<Location> moves()
    {
        return _moveMask.stream();
    }

    /**
     * Returns a sequence of this unit type's valid action patterns that can be
     * used for aggregate operations.
     * @return a stream of this unit type's valid action patterns
     */
    public final Stream<Location> actions()
    {
        return _actionMask.stream();
    }

    /**
     * Initializes a new instance of the UnitType enumeration.
     * @param health the base health for this unit type
     * @param attack the base attack for this unit type
     * @param defense the base defense for this unit type
     * @param magicAttack the base magic attack for this unit type
     * @param magicDefense the base magic defense for this unit type
     * @param speed the base speed for this unit type
     * @param luck the base luck for this unit type
     * @param moves the valid relative locations to which this unit type can move
     * @param actions the valid relative locations at which this unit type can perform actions
     */
    private UnitType(int health, int attack, int defense,
        int magicAttack, int magicDefense,
        int speed, int luck,
        Set<Location> moves, Set<Location> actions)
    {
        assert health > 0;
        assert attack >= 0;
        assert defense >= 0;
        assert magicAttack >= 0;
        assert magicDefense >= 0;
        assert speed >= 0;
        assert luck >= 0;

        // Unit must be able to attack either physically or magically
        assert attack > 0 || magicAttack > 0;

        assert moves != null;
        assert actions != null;

        baseHealth = health;
        baseAttack = attack;
        baseDefense = defense;
        baseMagicAttack = magicAttack;
        baseMagicDefense = magicDefense;
        baseSpeed = speed;
        baseLuck = luck;

        _moveMask = moves;
        _actionMask = actions;
    }
}