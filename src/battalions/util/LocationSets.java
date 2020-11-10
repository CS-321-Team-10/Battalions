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

import battalions.data.Location;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility class for performing set operations involving location masks.
 * @author Scott
 */
public final class LocationSets
{
    /**
     * Returns a new set of locations, each of which are offset from the
     * provided set by the specified offset.
     * @param locations the set of relative locations
     * @param offset the offset to apply to each relative location
     * @return a set of absolute locations produced by offsetting each relative location
     */
    public static Set<Location> fromOffset(Set<Location> locations, Location offset)
    {
        Set<Location> result = new HashSet<>();

        locations.forEach(x -> result.add(x.fromOffset(offset)));

        return result;
    }

    /**
     * Returns a new set (shallow copy) with the same elements as the provided set.
     * @param <T> the type of elements in the set
     * @param original the original set to clone
     * @return a shallow copy of the original set
     */
    public static <T> Set<T> clone(Set<T> original)
    {
        Set<T> result = new HashSet<>();

        original.forEach(x -> result.add(x));

        return result;
    }

    /**
     * Returns a new set formed by an intersection operation between the
     * two provided sets.
     * @param <T> the type of elements in the set
     * @param left the left operand of the intersection operation
     * @param right the right operand of the intersection operation
     * @return a new set that is the intersection of the provided sets
     */
    public static <T> Set<T> and(Set<T> left, Set<T> right)
    {
        Set<T> result = new HashSet<>();

        left.stream()
            .filter(x -> (right.contains(x)))
            .forEach(x -> result.add(x));

        return result;
    }

    /**
     * Returns a new set formed by a union operation between the
     * two provided sets.
     * @param <T> the type of elements in the set
     * @param left the left operand of the union operation
     * @param right the right operand of the union operation
     * @return a new set that is the union of the provided sets
     */
    public static <T> Set<T> or(Set<T> left, Set<T> right)
    {
        Set<T> result = new HashSet<>();

        left.forEach(x -> result.add(x));
        right.forEach(x -> result.add(x));

        return result;
    }
}
