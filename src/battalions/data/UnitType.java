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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Contains information about the base stats of each unit.
 * @author Blocker
 */
public enum UnitType
{
    //v Unit Name
           //v Base Stats: HP, Str, Def, MStr, MDef, Speed
    Infantry(20, 10, 5, 3, 4, 5, 2, 1,
        //v Movement Mask
        new HashSet<>(
            Arrays.asList(
                new Location[]{
                    new Location(-2, -1), new Location(-2,  0), new Location(-2,  1),
                    new Location(-1, -2), new Location(-1, -1), new Location(-1,  0), new Location(-1,  1), new Location(-1,  2),
                    new Location( 0, -2), new Location( 0, -1), new Location( 0,  1), new Location( 0,  2),
                    new Location( 1, -2), new Location( 1, -1), new Location( 1,  0), new Location( 1,  1), new Location( 1,  2),
                    new Location( 2, -1), new Location( 2,  0), new Location( 2,  1)
                }
            )
        ),
        //v Action Mask
        new HashSet<>(
            Arrays.asList(
                new Location[]{
                    new Location(-1, -1), new Location(-1,  0), new Location(-1,  1),
                    new Location( 0, -1), new Location( 0,  0), new Location( 0,  1),
                    new Location( 1, -1), new Location( 1,  0), new Location( 1,  1)
                }
            )
        )
    ),
    
    Mage(12, 3, 3, 10, 5, 4, 2, 2,
        new HashSet<>(
            Arrays.asList(
                new Location[]{
                    new Location(-2, -1), new Location(-2,  0), new Location(-2,  1),
                    new Location(-1, -2), new Location(-1, -1), new Location(-1,  0), new Location(-1,  1), new Location(-1,  2),
                    new Location( 0, -2), new Location( 0, -1), new Location( 0,  1), new Location( 0,  2),
                    new Location( 1, -2), new Location( 1, -1), new Location( 1,  0), new Location( 1,  1), new Location( 1,  2),
                    new Location( 2, -1), new Location( 2,  0), new Location( 2,  1)
                }
            )
        ),
        new HashSet<>(
            Arrays.asList(
                new Location[]{
                    new Location(-2, -1), new Location(-2,  0), new Location(-2,  1),
                    new Location(-1, -2), new Location(-1, -1), new Location(-1,  0), new Location(-1,  1), new Location(-1,  2),
                    new Location( 0, -2), new Location( 0, -1), new Location( 0,  1), new Location( 0,  2),
                    new Location( 1, -2), new Location( 1, -1), new Location( 1,  0), new Location( 1,  1), new Location( 1,  2),
                    new Location( 2, -1), new Location( 2,  0), new Location( 2,  1)
                }
            )
        )
    ),
    
    Knight(24, 7, 8, 0, 3, 2, 1, 1,
        new HashSet<>(
            Arrays.asList(
                new Location[]{
                    new Location(-1, -1), new Location(-1,  0), new Location(-1,  1),
                    new Location( 0, -1), new Location( 0,  1),
                    new Location( 1, -1), new Location( 1,  0), new Location( 1,  1)
                }
            )
        ),
        new HashSet<>(
            Arrays.asList(
                new Location[]{
                    new Location(-1, -1), new Location(-1,  0), new Location(-1,  1),
                    new Location( 0, -1), new Location( 0,  0), new Location( 0,  1),
                    new Location( 1, -1), new Location( 1,  0), new Location( 1,  1)
                }
            )
        )
    ),
    
    Warlock(16, 3, 2, 9, 9, 3, 1, 2,
        new HashSet<>(
            Arrays.asList(
                new Location[]{
                    new Location(-1, -1), new Location(-1,  0), new Location(-1,  1),
                    new Location( 0, -1), new Location( 0,  1),
                    new Location( 1, -1), new Location( 1,  0), new Location( 1,  1)
                }
            )
        ),
        new HashSet<>(
            Arrays.asList(
                new Location[]{
                    new Location(-2, -1), new Location(-2,  0), new Location(-2,  1),
                    new Location(-1, -2), new Location(-1, -1), new Location(-1,  0), new Location(-1,  1), new Location(-1,  2),
                    new Location( 0, -2), new Location( 0, -1), new Location( 0,  1), new Location( 0,  2),
                    new Location( 1, -2), new Location( 1, -1), new Location( 1,  0), new Location( 1,  1), new Location( 1,  2),
                    new Location( 2, -1), new Location( 2,  0), new Location( 2,  1)
                }
            )
        )
    ),
    
    Archer(12, 7, 4, 3, 5, 4, 2, 3,
        new HashSet<>(
            Arrays.asList(
                new Location[]{
                    new Location(-2, -1), new Location(-2,  0), new Location(-2,  1),
                    new Location(-1, -2), new Location(-1, -1), new Location(-1,  0), new Location(-1,  1), new Location(-1,  2),
                    new Location( 0, -2), new Location( 0, -1), new Location( 0,  1), new Location( 0,  2),
                    new Location( 1, -2), new Location( 1, -1), new Location( 1,  0), new Location( 1,  1), new Location( 1,  2),
                    new Location( 2, -1), new Location( 2,  0), new Location( 2,  1)
                }
            )
        ),
        new HashSet<>(
            Arrays.asList(
                new Location[]{
                    new Location(-3, -1), new Location(-3,  0), new Location(-3,  1),
                    new Location(-2, -2), new Location(-2, -1), new Location(-2,  0), new Location(-2,  1), new Location(-2,  2),
                    new Location(-1, -3), new Location(-1, -2), new Location(-1, -1), new Location(-1,  0), new Location(-1,  1), new Location(-1,  2), new Location(-1,  3), 
                    new Location( 0, -3), new Location( 0, -2), new Location( 0, -1), new Location( 0,  1), new Location( 0,  2), new Location( 0,  3),
                    new Location( 1, -3), new Location( 1, -2), new Location( 1, -1), new Location( 1,  0), new Location( 1,  1), new Location( 1,  2), new Location( 1,  3),
                    new Location( 2, -2), new Location( 2, -1), new Location( 2,  0), new Location( 2,  1), new Location( 2,  2), new Location( 3, -1), new Location( 3,  0), new Location( 3,  1)
                }
            )
        )
    ),
    
    Healer(10, 2, 2, 8, 7, 4, 2, 1,
        new HashSet<>(
            Arrays.asList(
                new Location[]{
                    new Location(-2, -1), new Location(-2,  0), new Location(-2,  1),
                    new Location(-1, -2), new Location(-1, -1), new Location(-1,  0), new Location(-1,  1), new Location(-1,  2),
                    new Location( 0, -2), new Location( 0, -1), new Location( 0,  1), new Location( 0,  2),
                    new Location( 1, -2), new Location( 1, -1), new Location( 1,  0), new Location( 1,  1), new Location( 1,  2),
                    new Location( 2, -1), new Location( 2,  0), new Location( 2,  1)
                }
            )
        ),
        new HashSet<>(
            Arrays.asList(
                new Location[]{
                    new Location(-1, -1), new Location(-1,  0), new Location(-1,  1),
                    new Location( 0, -1), new Location( 0,  0), new Location( 0,  1),
                    new Location( 1, -1), new Location( 1,  0), new Location( 1,  1)
                }
            )
        )
    );

    /**
     * Base States, Movement & Action Masks.
     */
    public final int baseHealth;
    public final int baseAttack;
    public final int baseDefense;
    public final int baseMagicAttack;
    public final int baseMagicDefense;
    public final int baseSpeed;
    public final int baseMovement;
    public final int baseRange;

    /**
     * Initializes a new value of the UnitType enumeration.
     */
    private UnitType(int baseHealth, int baseAttack, int baseDefense, int baseMagicAttack, int baseMagicDefense, int baseSpeed, int baseMovement, int baseRange, Set<Location> moveMask, Set<Location> attackMask)
    {
        assert baseHealth > 0;
        assert baseAttack >= 0;
        assert baseDefense >= 0;
        assert baseMagicAttack >= 0;
        assert baseMagicDefense >= 0;
        assert baseSpeed >= 0;
        assert baseMovement >= 0;
        assert baseRange >= 0;
        
        this.baseHealth = baseHealth;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseMagicAttack = baseMagicAttack;
        this.baseMagicDefense = baseMagicDefense;
        this.baseSpeed = baseSpeed;
        this.baseMovement = baseMovement;
        this.baseRange = baseRange;        
    }
}