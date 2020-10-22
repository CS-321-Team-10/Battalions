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
package battalions;

import battalions.models.*;

/**
 * Serves as the entry point of the program.
 * @author Bryant
 * @author Scott
 */
public class Driver
{
    /**
     * The entry point of the program.
     * @param args unused command line arguments
     */
    public static void main(String[] args)
    {
        System.out.println("Battalions & Besiegement!\n");

        System.out.println("# = Unit");
        System.out.println("| = Wall Tile");
        System.out.println("~ = Tile Boosts Dodge Chance");
        System.out.println("_ = Tile Reduces Movement Range\n");

        Game game = new Game();
        Map map = game.getMap();

        // Add walls
        map.addTile(new Tile(4, 4, true, false, false));
        map.addTile(new Tile(4, 5, true, false, false));
        map.addTile(new Tile(7, 4, true, false, false));
        
        // Add dodge boost tiles
        map.addTile(new Tile(5, 4, false, true, false));
        map.addTile(new Tile(5, 3, false, true, false));
        
        // Add movement reduce tiles
        map.addTile(new Tile(7, 7, false, false, true));

        // Add units
        map.addUnit(new Unit(5, 4, 15, 2, 1, 3, 2, 5, 3));
        map.addUnit(new Unit(2, 7, 10, 3, 2, 2, 1, 4, 5));

        // Display map
        System.out.println(map);
        
        // Display all unit stats
        for (Unit unit : map.getUnits())
        {
            System.out.println(unit);
        }
    }

}
