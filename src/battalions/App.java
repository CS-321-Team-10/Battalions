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
     * The view used by this App.
     */
    private GameView gameView;

    /**
     * Initializes static members of this class.
     */
    static
    {
        _instance = new App();
    }

    /**
     * The entry point of the program.
     * @param args unused command line arguments
     */
    public static void main(String[] args)
    {
        _instance.setUpGame();
        _instance.setUpGUI();
    }

    /**
     * Sets up the game, map, and players.
     */
    private void setUpGame()
    {
        // Create map
        Map map = new Map(30, 15);

        // Forest
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
        map.addTile(new Tile(map, new Location(6, 11), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(7, 11), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(8, 11), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(20, 12), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(20, 11), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(20, 10), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(21, 10), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(21, 11), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(21, 12), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(22, 11), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(22, 12), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(10,3), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(10,4), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(10,5), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(23,1), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(24,2), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(25,2), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(16,6), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(26,3), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(27,4), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(27,5), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(7,12), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(8,12), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(1,13), TileType.Forest, TileType.FieldLight));
        
        // Add walls
        // Fort 1
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
        // Fort 2
        map.addTile(new Tile(map, new Location(29, 7), TileType.WallNorthEast, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(29, 8), TileType.WallVertical, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(29, 9), TileType.Wall3WayWest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(29, 10), TileType.WallVertical, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(29, 11), TileType.WallVertical, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(29, 12), TileType.WallVertical, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(29, 13), TileType.WallVertical, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(29, 14), TileType.WallSouthEast, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(28, 14), TileType.WallHorizontal, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(27, 14), TileType.WallHorizontal, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(26, 14), TileType.Wall3WayNorth, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(25, 14), TileType.WallHorizontal, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(24, 14), TileType.WallHorizontal, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(23, 14), TileType.WallHorizontal, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(22, 14), TileType.WallSouthWest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(28, 9), TileType.WallHorizontal, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(27, 9), TileType.WallHorizontal, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(25, 9), TileType.WallNorthWest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(26, 9), TileType.Wall4Way, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(26, 7), TileType.WallNorthWest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(26, 8), TileType.WallVertical, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(26, 10), TileType.WallVertical, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(26, 11), TileType.WallVertical, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(26, 13), TileType.WallVertical, TileType.FieldLight));
        
        // Rivers
        map.addTile(new Tile(map, new Location(15, 0), TileType.WaterVertical, TileType.Sand));
        map.addTile(new Tile(map, new Location(15, 1), TileType.WaterVertical, TileType.Sand));
        map.addTile(new Tile(map, new Location(15, 2), TileType.WaterVertical, TileType.Sand));
        map.addTile(new Tile(map, new Location(15, 3), TileType.Water3WayNorth, TileType.Sand));
        map.addTile(new Tile(map, new Location(14, 3), TileType.WaterNorthWest, TileType.Sand));
        map.addTile(new Tile(map, new Location(16, 3), TileType.WaterHorizontal, TileType.Sand));
        map.addTile(new Tile(map, new Location(17, 3), TileType.WaterNorthEast, TileType.Sand));
        map.addTile(new Tile(map, new Location(17, 4), TileType.WaterSouthWest, TileType.Sand));
        map.addTile(new Tile(map, new Location(18, 4), TileType.WaterNorthEast, TileType.Sand));
        map.addTile(new Tile(map, new Location(18, 5), TileType.WaterSouthWest, TileType.Sand));
        map.addTile(new Tile(map, new Location(19, 5), TileType.WaterNorthEast, TileType.Sand));
        map.addTile(new Tile(map, new Location(19, 6), TileType.WaterVertical, TileType.Sand));
        map.addTile(new Tile(map, new Location(19, 7), TileType.WaterVertical, TileType.Sand));
        map.addTile(new Tile(map, new Location(19, 7), TileType.WaterSouthEast, TileType.Sand));
        map.addTile(new Tile(map, new Location(18, 7), TileType.WaterNorthWest, TileType.Sand));
        map.addTile(new Tile(map, new Location(18, 8), TileType.WaterVertical, TileType.Sand));
        map.addTile(new Tile(map, new Location(18, 9), TileType.WaterVertical, TileType.Sand));
        map.addTile(new Tile(map, new Location(18, 10), TileType.WaterSouthEast, TileType.Sand));
        map.addTile(new Tile(map, new Location(17, 10), TileType.WaterHorizontal, TileType.Sand));
        map.addTile(new Tile(map, new Location(16, 10), TileType.BridgeVertical, TileType.WaterHorizontal));
        map.addTile(new Tile(map, new Location(15, 10), TileType.WaterHorizontal, TileType.Sand));
        map.addTile(new Tile(map, new Location(14, 4), TileType.WaterVertical, TileType.Sand));
        map.addTile(new Tile(map, new Location(14, 5), TileType.WaterVertical, TileType.Sand));
        map.addTile(new Tile(map, new Location(14, 6), TileType.WaterVertical, TileType.Sand));
        map.addTile(new Tile(map, new Location(14, 7), TileType.WaterVertical, TileType.Sand));
        map.addTile(new Tile(map, new Location(14, 8), TileType.WaterVertical, TileType.Sand));
        map.addTile(new Tile(map, new Location(14, 9), TileType.WaterVertical, TileType.Sand));
        map.addTile(new Tile(map, new Location(14, 10), TileType.Water3WayEast, TileType.Sand));
        map.addTile(new Tile(map, new Location(14, 11), TileType.WaterVertical, TileType.Sand));
        map.addTile(new Tile(map, new Location(14, 12), TileType.WaterSouthEast, TileType.Sand));
        map.addTile(new Tile(map, new Location(13, 12), TileType.WaterNorthWest, TileType.Sand));
        map.addTile(new Tile(map, new Location(13, 13), TileType.WaterVertical, TileType.Sand));
        map.addTile(new Tile(map, new Location(13, 14), TileType.WaterSouthEast, TileType.Sand));
        map.addTile(new Tile(map, new Location(12, 14), TileType.WaterNorthWest, TileType.Sand));
        
        // Sand
        map.addTile(new Tile(map, new Location(15, 4), TileType.Sand, TileType.Sand));
        map.addTile(new Tile(map, new Location(16, 4), TileType.Sand, TileType.Sand));
        map.addTile(new Tile(map, new Location(18, 3), TileType.SandNorthEast, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(19, 4), TileType.SandNorthEast, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(16, 2), TileType.SandNorthEast, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(15, 9), TileType.SandNorthEast, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(14, 2), TileType.SandNorthWest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(17, 9), TileType.SandNorthWest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(12, 13), TileType.SandNorthWest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(13, 11), TileType.SandNorthWest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(11, 14), TileType.SandNorthWest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(19, 8), TileType.SandSouthEast, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(15, 11), TileType.SandSouthEast, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(14, 13), TileType.SandSouthEast, TileType.FieldLight));
        
        // Bridges
        map.addTile(new Tile(map, new Location(14, 8), TileType.BridgeHorizontal, TileType.WaterVertical));
        map.addTile(new Tile(map, new Location(16, 10), TileType.BridgeVertical, TileType.WaterHorizontal));
        
        // Towers
        map.addTile(new Tile(map, new Location(25, 5), TileType.BrokenTower, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(4, 11), TileType.BrokenTower, TileType.FieldLight));

        // Create players
        Player player = new Player();
        Player cpu = new Player();

        // Add friendly units
        player.addUnit(new Unit(player, map, new Location(7, 6), UnitType.Knight));
        player.addUnit(new Unit(player, map, new Location(2, 8), UnitType.Healer));
        player.addUnit(new Unit(player, map, new Location(3, 6), UnitType.Mage));
        player.addUnit(new Unit(player, map, new Location(4, 12), UnitType.Warlock));
        player.addUnit(new Unit(player, map, new Location(13, 8), UnitType.Infantry));
        player.addUnit(new Unit(player, map, new Location(7, 10), UnitType.Archer));

        // Add enemy units
        cpu.addUnit(new Unit(cpu, map, new Location(26, 12), UnitType.Knight));
        cpu.addUnit(new Unit(cpu, map, new Location(28, 8), UnitType.Healer));
        cpu.addUnit(new Unit(cpu, map, new Location(28, 13), UnitType.Mage));
        cpu.addUnit(new Unit(cpu, map, new Location(24, 5), UnitType.Warlock));
        cpu.addUnit(new Unit(cpu, map, new Location(16, 11), UnitType.Infantry));
        cpu.addUnit(new Unit(cpu, map, new Location(21, 11), UnitType.Archer));

        // Add all units to map
        player.getUnits().forEach(x -> map.addUnit(x));
        cpu.getUnits().forEach(x -> map.addUnit(x));

        // Create game
        MapSelector mapSelector = new MapSelector(map);
        game = new Game(mapSelector, player, cpu);
    }

    /**
     * Initializes a Swing form as the GUI and allows the user to test several
     * actions in a game.
     */
    private void setUpGUI()
    {
        if (gameView instanceof GameView)
        {
            gameView.dispose();
        }

        gameView = new GameView();
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
