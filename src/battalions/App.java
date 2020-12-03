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
    private Game _game;

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
        Map map = new Map(10, 10);
        // Map map2 = new Map(10, 10);

        // Add walls
        map.addTile(new Tile(map, new Location(4, 4), TileType.WallHorizontal, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(4, 5), TileType.WallVertical, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(7, 4), TileType.WallNorthWest, TileType.FieldLight));

        // Add dodge boost tiles
        map.addTile(new Tile(map, new Location(5, 4), TileType.Forest, TileType.FieldLight));
        map.addTile(new Tile(map, new Location(5, 3), TileType.Forest, TileType.FieldLight));

        // Add movement reduce tiles
        map.addTile(new Tile(map, new Location(7, 7), TileType.Sand));

        // Create players
        Player player = new Player();
        Player cpu = new Player();
        // Player player2 = new Player();
        // Player cpu2 = new Player();

        // Add friendly units
        player.addUnit(new Unit(player, map, new Location(5, 4), UnitType.Archer));
        player.addUnit(new Unit(player, map, new Location(2, 7), UnitType.Healer));

        // Add enemy units
        cpu.addUnit(new Unit(cpu, map, new Location(9, 3), UnitType.Knight));

        // Add all units to map
        player.getUnits().forEach(x -> map.addUnit(x));
        cpu.getUnits().forEach(x -> map.addUnit(x));

        // Testing save system
        // SaveSystem saving = new SaveSystem();
        // saving.Save(map);
        // saving.Load(map2, player2, cpu2);
        // _game = new Game(map2, player2, cpu2);

        // Create game
        _game = new Game(map, player, cpu);
    }

    /**
     * Initializes a Swing form as the GUI and allows the user to test several
     * actions in a game.
     */
    private void testGui()
    {
        GameView gameView = new GameView();

        Map map = _game.getMap();

        MapSelector mapSelector = new MapSelector(map);
        mapSelector.setCurrentPlayer(this._game.getPlayer());
        mapSelector.addPropertyChangeListener(_game);

        MapView mapView = gameView.getMapView();

        // [TODO] this should go in a controller and is just here temporarily
        mapView.addEndTurnButtonListener(
            new java.awt.event.ActionListener()
            {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent event)
                {
                    Player currentPlayer = mapSelector.getCurrentPlayer();
                    Player nextPlayer = currentPlayer.isCPU() ? _game.getPlayer() : _game.getCPU();

                    currentPlayer.endTurn();
                    nextPlayer.beginTurn();

                    mapSelector.setCurrentPlayer(nextPlayer);
                    mapSelector.deselect();
                }
            });

        MapSelectorView mapSelectorView = mapView.getMapSelectorView();
        MapSelectorController mapSelectorController = new MapSelectorController(mapSelector, mapSelectorView);

        gameView.setVisible(true);

        _game.start();
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
        return _game;
    }
}
