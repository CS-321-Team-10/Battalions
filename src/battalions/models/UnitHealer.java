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

import battalions.data.Location;
import battalions.data.ActionType;
import battalions.data.UnitType;
import battalions.util.Stats;
import java.util.HashSet;
import java.util.Set;

/**
 * Constructor for a healer unit. Subclass of Unit.
 * @author Bryant Terry
 */
public class UnitHealer extends Unit
{
    public UnitHealer(Player player, Map map, Location l)
    {
        super(player, map, l, UnitType.Healer);
    }
}
