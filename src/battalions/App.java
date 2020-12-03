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
    private Game game;

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
        _instance.testGui();
    }

    /**
     * Sets up the game, map, and players for testing.
     */
    private void testSetup()
    {
        // Create map
        Map map = new Map(30, 15);
        //Map map2 = new Map(30, 15);
        
        // Add forest
        map.addTile(new Tile(map, new Location(0,0), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(0,1), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(0,2), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(0,3), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(0,4), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(0,5), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(1,0), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(2,0), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(3,0), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(4,0), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(5,0), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(1,1), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(1,2), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(1,3), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(1,4), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(2,1), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(2,2), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(2,3), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(3,1), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(3,2), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(4,1), TileType.Forest, TileType.FieldLight));
        
        // Add walls
        map.addTile(new Tile(map, new Location(1, 4), TileType.WallNorthWest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(1, 5), TileType.WallVertical, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(1, 6), TileType.WallVertical, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(1, 7), TileType.WallVertical, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(1, 8), TileType.WallVertical, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(1, 9), TileType.WallSouthWest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(2, 9), TileType.WallHorizontal, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(3, 9), TileType.WallHorizontal, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(4, 9), TileType.WallHorizontal, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(5, 9), TileType.WallHorizontal, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(6, 9), TileType.WallSouthEast, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(6, 8), TileType.WallNorthWest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(7, 8), TileType.WallSouthEast, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(7, 7), TileType.WallVertical, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(7, 5), TileType.WallVertical, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(7, 4), TileType.WallVertical, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(7, 3), TileType.WallNorthEast, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(6, 3), TileType.WallHorizontal, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(5, 3), TileType.WallHorizontal, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(4, 3), TileType.WallHorizontal, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(3, 3), TileType.WallNorthWest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(3, 4), TileType.WallSouthEast, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(4, 6), TileType.WallVertical, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(4, 7), TileType.WallSouthEast, TileType.FieldLight));
        

        // Create players
        Player player = new Player();
        Player cpu = new Player();
        //Player player2 = new Player();
        //Player cpu2 = new Player();

        // Add friendly units
        player.addUnit(new Unit(player, map, new Location(7, 6), UnitType.Knight));
        player.addUnit(new Unit(player, map, new Location(2, 8), UnitType.Healer));

        // Add enemy units
        cpu.addUnit(new Unit(cpu, map, new Location(9, 3), UnitType.Knight));

        // Add all units to map
        player.getUnits().forEach(x -> map.addUnit(x));
        cpu.getUnits().forEach(x -> map.addUnit(x));

        // Testing save system
        //SaveSystem saving = new SaveSystem();
        //saving.Save(map);
        //saving.Load(map2, player2, cpu2);
        //_game = new Game(map2, player2, cpu2);

        // Create game
        MapSelector mapSelector = new MapSelector(map);
        game = new Game(mapSelector, player, cpu);
    }

    /**
     * Initializes a Swing form as the GUI and allows the user to test several
     * actions in a game.
     */
    private void testGui()
    {
        GameView gameView = new GameView();
        GameController gameController = new GameController(game, gameView);

        MapSelector mapSelector = game.getMapSelector();
        MapSelectorView mapSelectorView = gameView.getMapSelectorView();
        MapSelectorController mapSelectorController = new MapSelectorController(mapSelector, mapSelectorView);

        gameView.setVisible(true);
        game.start();
    }


    /**
     * Returns the static singleton instance of the App class.
     * @return the static singleton instance of the App class
     */
    public static final App getInstance()
    {
        return _instance;
    }

    /**
     * Returns the current game used by this App.
     * @return the current game
     */
    public final Game getGame()
    {
        return game;
    }
}
