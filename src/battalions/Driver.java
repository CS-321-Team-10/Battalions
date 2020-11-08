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

import battalions.controllers.*;
import battalions.models.*;
import battalions.views.*;
import java.awt.FlowLayout;

/**
 * Serves as the entry point of the program.
 * @author Bryant
 * @author Scott
 */
public class Driver
{
    private static Driver _instance = new Driver();

    private Game _game;
    private Map _map;

    private Driver()
    {
        testSetup();
    }

    public static final Driver getInstance()
    {
        return _instance;
    }

    /**
     * The entry point of the program.
     * @param args unused command line arguments
     */
    public static void main(String[] args)
    {
//        _instance.testConsole();
        _instance.testGui();
    }

    private void testSetup()
    {
        // Create game
        _game = new Game();
        _map = _game.getMap();

        // Add walls
        _map.addTile(new Tile(4, 4, true, false, false));
        _map.addTile(new Tile(4, 5, true, false, false));
        _map.addTile(new Tile(7, 4, true, false, false));

        // Add dodge boost tiles
        _map.addTile(new Tile(5, 4, false, true, false));
        _map.addTile(new Tile(5, 3, false, true, false));

        // Add movement reduce tiles
        _map.addTile(new Tile(7, 7, false, false, true));

        // Add units
        _map.addUnit(new Unit(5, 4, 15, 2, 1, 3, 2, 5, 3, 5, 1));
        _map.addUnit(new Unit(2, 7, 10, 3, 2, 2, 1, 4, 5, 5, 1));
    }

    private void testMovement()
    {
        _map.moveUnitCalculations(5, 4, 5, 7); // Should work
        _map.moveUnitCalculations(2, 7, 1, 1); // Exceeds movement stat
        _map.moveUnitCalculations(2, 7, 5, 6); // Should work
    }

    private void testAttacks()
    {
        _map.runAttackSequence(5, 7, 5, 6, true); // Turn already used;
        _map.runAttackSequence(5, 7, 5, 6, true); // These shouldn't work.
        _map.runAttackSequence(5, 7, 5, 6, true);
        _map.runAttackSequence(5, 7, 5, 6, true);
    }

    private void testConsole()
    {
        System.out.println("Battalions & Besiegement!\n");

        System.out.println("# = Unit");
        System.out.println("| = Wall Tile");
        System.out.println("~ = Tile Boosts Dodge Chance");
        System.out.println("_ = Tile Reduces Movement Range\n");

        printConsole();

        testMovement();
        printConsole();

        testAttacks();
        printConsole();
    }

    private void printConsole()
    {
        // Display map
        System.out.println(_map);

        // Display all unit stats
        for (Unit unit : _map.getUnits())
        {
            System.out.println(unit);
        }
    }

    private void testGui()
    {
        GameView gameView = new GameView();

        MapController mapController = new MapController(_map, gameView.getMapView());

        gameView.setVisible(true);
    }

}
