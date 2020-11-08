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

import java.util.Random;

/**
 * Provides extension methods for performing random number generation.
 * @author Scott
 */
public final class Rng
{
    /**
     * The instance used for performing random number generation.
     */
    private static final Random _random = new Random();

    /**
     * Generates a random int between the lower and upper bounds (inclusive).
     * @param lower the lower bound (inclusive)
     * @param upper the upper bound (inclusive)
     * @return a random int between the lower and upper bounds (inclusive)
     */
    public static int getInt(int lower, int upper)
    {
        assert lower < upper;

        int range = upper - lower + 1;

        return _random.nextInt(range) + lower;
    }
}
