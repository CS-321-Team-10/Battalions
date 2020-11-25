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

import battalions.data.*;
import battalions.controllers.*;
import battalions.models.*;
import battalions.views.*;
import java.util.HashSet;

/**
 * Serves as the entry point of the program.
 * @author Bryant
 * @author Scott
 */
public class App
{
    /**
     * The static instance of this singleton class.
     */
    private static final App _instance;

    /**
     * The game used by this App.
     */
    private Game _game;

    /**
     * The human player used by this App.
     */
    private Player _player;

    /**
     * The CPU player used by this App.
     */
    private Player _cpu;

    /**
     * Initializes static members of this class.
     */
    static
    {
        _instance = new App();
    }

    /**
     * Initializes a new instance of the App class.
     */
    private App()
    {
        testSetup();
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

    /**
     * Sets up the game, map, and players for testing.
     */
    private void testSetup()
    {
        // Create game
        _game = new Game();
        Map map = _game.getMap();

        _player = new Player();
        _cpu = new Player();

        // Add walls
        map.addTile(new TileWall(map, new Location(4, 4), Orientation.Right));
        map.addTile(new TileWall(map, new Location(4, 5), Orientation.Up));
        map.addTile(new TileWall(map, new Location(7, 4), Orientation.UpLeft));

        // Add dodge boost tiles
        map.addTile(new TileForest(map, new Location(5, 4), Orientation.Up));
        map.addTile(new TileForest(map, new Location(5, 3), Orientation.Up));

        // Add movement reduce tiles
        map.addTile(new TileSand(map, new Location(7, 7), Orientation.Up));

        map.addUnit(new Unit(_player, map, new Location(5, 4), UnitType.Archer));
        map.addUnit(new Unit(_player, map, new Location(2, 7), UnitType.Healer));
    }

    /**
     * Tests unit movements.
     */
    private void testMovement()
    {
        // [TODO fix move tests]
//        _map.moveUnitCalculations(5, 4, 5, 7); // Should work
//        _map.moveUnitCalculations(2, 7, 1, 1); // Exceeds movement stat
//        _map.moveUnitCalculations(2, 7, 5, 6); // Should work
    }

    /**
     * Tests unit attacks.
     */
    private void testAttacks()
    {
        // [TODO fix attack tests]
//        _map.runAttackSequence(5, 7, 5, 6, true); // Turn already used;
//        _map.runAttackSequence(5, 7, 5, 6, true); // These shouldn't work.
//        _map.runAttackSequence(5, 7, 5, 6, true);
//        _map.runAttackSequence(5, 7, 5, 6, true);
    }

    /**
     * Tests a sequence of several actions in a game using the console as the UI.
     */
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

    /**
     * Prints useful game-state information to the console.
     */
    private void printConsole()
    {
        Map map = _game.getMap();

        // Display map
        System.out.println(map);

        // Display all unit stats
        for (Unit unit : map.getUnits())
        {
            System.out.println(unit);
        }
    }

    /**
     * Initializes a Swing form as the GUI and allows the user to test several
     * actions in a game.
     */
    private void testGui()
    {
        GameView gameView = new GameView();

        Map map = _game.getMap();
        MapView mapView = gameView.getMapView();
        MapController mapController = new MapController(map, mapView);

        gameView.setVisible(true);
    }


    /**
     * Returns the static singleton instance of the App class.
     * @return the static singleton instance of the App class
     */
    public static final App getInstance()
    {
        return _instance;
    }
}
