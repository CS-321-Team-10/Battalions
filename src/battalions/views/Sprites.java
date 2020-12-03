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
package battalions.views;

import battalions.data.TileType;
import battalions.data.UnitType;
import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * Static class allowing access to all project images.
 * @author Scott
 */
public class Sprites
{
    /**
     * A transparent color used for image backgrounds.
     */
    public static final Color TRANSPARENT = new Color(0xFF, 0xFF, 0xFF, 0x00);

    /**
     * A sprite acting as the user's cursor.
     */
    public static final Image CURSOR;

    /**
     * A sprite overlay representing the tile location where the cursor points.
     */
    public static final Image CURSOR_TILE;

    /**
     * A sprite overlay representing a friendly unit at which the cursor points.
     */
    public static final Image CURSOR_FRIENDLY;

    /**
     * A sprite overlay representing an enemy unit at which the cursor points.
     */
    public static final Image CURSOR_ENEMY;

    /**
     * A sprite overlay representing an available unit.
     */
    public static final Image SELECTABLE_UNIT;

    /**
     * A sprite overlay representing a selected unit.
     */
    public static final Image SELECTED_UNIT;

    /**
     * A sprite overlay representing an available move target for a selected unit.
     */
    public static final Image SELECTABLE_MOVE;

    /**
     * A sprite overlay representing an available attack target for a selected unit.
     */
    public static final Image SELECTABLE_ATTACK;

    /**
     * A sprite overlay representing an available assist target for a selected unit.
     */
    public static final Image SELECTABLE_ASSIST;

    /**
     * A sprite overlay representing a highlighted tile.
     */
    public static final Image HIGHLIGHTED_TILE;

    /**
     * A sprite overlay representing a highlighted unit.
     */
    public static final Image HIGHLIGHTED_UNIT;

    /**
     * A sprite overlay representing an available move target for a highlighted unit.
     */
    public static final Image HIGHLIGHTED_MOVE;

    /**
     * A sprite overlay representing an available attack target for a highlighted unit.
     */
    public static final Image HIGHLIGHTED_ATTACK;

    /**
     * A sprite overlay representing an available assist target for a highlighted unit.
     */
    public static final Image HIGHLIGHTED_ASSIST;

    /**
     * A sprite indicating a unit is attacking.
     */
    public static final Image UI_ATTACKING;

    /**
     * A sprite indicating a unit is defending against an attack.
     */
    public static final Image UI_DEFENDING;

    /**
     * A sprite indicating a unit has died.
     */
    public static final Image UI_DEAD;

    /**
     * A sprite indicating a player has won.
     */
    public static final Image UI_SPARKLES;

    /**
     * A mapping from tile types to sprites.
     */
    private static final Map<TileType, Image> TILES;

    /**
     * A mapping from friendly unit types to sprites.
     */
    private static final Map<UnitType, Image> FRIENDLY_UNITS;

    /**
     * A mapping from enemy unit types to sprites.
     */
    private static final Map<UnitType, Image> ENEMY_UNITS;

    /**
     * Initializes static members of this class.
     */
    static
    {
        // Initialize tile images
        Map<TileType, Image> tiles = new HashMap<>();

        tiles.put(TileType.FieldDark, readImage("/images/tiles/FieldDark.png"));
        tiles.put(TileType.FieldLight, readImage("/images/tiles/FieldLight.png"));
        tiles.put(TileType.GrassDark, readImage("/images/tiles/GrassDark.png"));
        tiles.put(TileType.GrassLight, readImage("/images/tiles/GrassLight.png"));

        tiles.put(TileType.BridgeHorizontal, readImage("/images/tiles/BridgeHorizontal.png"));
        tiles.put(TileType.BridgeVertical, readImage("/images/tiles/BridgeVertical.png"));

        tiles.put(TileType.Forest, readImage("/images/tiles/Forest.png"));

        tiles.put(TileType.Sand, readImage("/images/tiles/Sand.png"));
        tiles.put(TileType.SandNorthEast, readImage("/images/tiles/SandNorthEast.png"));
        tiles.put(TileType.SandNorthWest, readImage("/images/tiles/SandNorthWest.png"));
        tiles.put(TileType.SandSouthEast, readImage("/images/tiles/SandSouthEast.png"));
        tiles.put(TileType.SandSouthWest, readImage("/images/tiles/SandSouthWest.png"));
        tiles.put(TileType.SandInwardNorthEast, readImage("/images/tiles/SandInwardNorthEast.png"));
        tiles.put(TileType.SandInwardNorthWest, readImage("/images/tiles/SandInwardNorthWest.png"));
        tiles.put(TileType.SandInwardSouthEast, readImage("/images/tiles/SandInwardSouthEast.png"));
        tiles.put(TileType.SandInwardSouthWest, readImage("/images/tiles/SandInwardSouthWest.png"));

        tiles.put(TileType.WallHorizontal, readImage("/images/tiles/WallHorizontal.png"));
        tiles.put(TileType.WallVertical, readImage("/images/tiles/WallVertical.png"));
        tiles.put(TileType.WallNorthEast, readImage("/images/tiles/WallNorthEast.png"));
        tiles.put(TileType.WallNorthWest, readImage("/images/tiles/WallNorthWest.png"));
        tiles.put(TileType.WallSouthEast, readImage("/images/tiles/WallSouthEast.png"));
        tiles.put(TileType.WallSouthWest, readImage("/images/tiles/WallSouthWest.png"));

        tiles.put(TileType.Wall3WayEast, readImage("/images/tiles/Wall3WayEast.png"));
        tiles.put(TileType.Wall3WayNorth, readImage("/images/tiles/Wall3WayNorth.png"));
        tiles.put(TileType.Wall3WaySouth, readImage("/images/tiles/Wall3WaySouth.png"));
        tiles.put(TileType.Wall3WayWest, readImage("/images/tiles/Wall3WayWest.png"));
        tiles.put(TileType.Wall4Way, readImage("/images/tiles/Wall4Way.png"));

        tiles.put(TileType.WaterHorizontal, readImage("/images/tiles/WaterHorizontal.png"));
        tiles.put(TileType.WaterVertical, readImage("/images/tiles/WaterVertical.png"));
        tiles.put(TileType.WaterNorthEast, readImage("/images/tiles/WaterNorthEast.png"));
        tiles.put(TileType.WaterNorthWest, readImage("/images/tiles/WaterNorthWest.png"));
        tiles.put(TileType.WaterSouthEast, readImage("/images/tiles/WaterSouthEast.png"));
        tiles.put(TileType.WaterSouthWest, readImage("/images/tiles/WaterSouthWest.png"));

        tiles.put(TileType.Water3WayEast, readImage("/images/tiles/Water3WayEast.png"));
        tiles.put(TileType.Water3WayNorth, readImage("/images/tiles/Water3WayNorth.png"));
        tiles.put(TileType.Water3WaySouth, readImage("/images/tiles/Water3WaySouth.png"));
        tiles.put(TileType.Water3WayWest, readImage("/images/tiles/Water3WayWest.png"));
        tiles.put(TileType.Water4Way, readImage("/images/tiles/Water4Way.png"));

        tiles.put(TileType.BrokenBuilding, readImage("/images/tiles/BrokenBuilding.png"));
        tiles.put(TileType.BrokenTower, readImage("/images/tiles/BrokenTower.png"));

        TILES = Collections.unmodifiableMap(tiles);

        // Initialize friendly unit images
        Map<UnitType, Image> friendlyUnits = new HashMap<>();

        friendlyUnits.put(UnitType.Archer, readImage("/images/units/FriendlyArcher.png"));
        friendlyUnits.put(UnitType.Healer, readImage("/images/units/FriendlyHealer.png"));
        friendlyUnits.put(UnitType.Infantry, readImage("/images/units/FriendlyInfantry.png"));
        friendlyUnits.put(UnitType.Knight, readImage("/images/units/FriendlyKnight.png"));
        friendlyUnits.put(UnitType.Mage, readImage("/images/units/FriendlyMage.png"));
        friendlyUnits.put(UnitType.Warlock, readImage("/images/units/FriendlyWarlock.png"));

        FRIENDLY_UNITS = Collections.unmodifiableMap(friendlyUnits);

        // Initialize enemy unit images
        Map<UnitType, Image> enemyUnits = new HashMap<>();

        enemyUnits.put(UnitType.Archer, readImage("/images/units/EnemyArcher.png"));
        enemyUnits.put(UnitType.Healer, readImage("/images/units/EnemyHealer.png"));
        enemyUnits.put(UnitType.Infantry, readImage("/images/units/EnemyInfantry.png"));
        enemyUnits.put(UnitType.Knight, readImage("/images/units/EnemyKnight.png"));
        enemyUnits.put(UnitType.Mage, readImage("/images/units/EnemyMage.png"));
        enemyUnits.put(UnitType.Warlock, readImage("/images/units/EnemyWarlock.png"));

        ENEMY_UNITS = Collections.unmodifiableMap(enemyUnits);

        // Initialize cursor overlays
        CURSOR = readImage("/images/ui/Cursor1.png");
        CURSOR_TILE = readImage("/images/ui/GreySelect.png");
        CURSOR_FRIENDLY = readImage("/images/ui/BlueSelect.png");
        CURSOR_ENEMY = readImage("/images/ui/RedSelect.png");

        // Initialize highlighted overlays
        HIGHLIGHTED_TILE = readImage("/images/ui/GreyHighlight.png");
        HIGHLIGHTED_UNIT = readImage("/images/ui/GreyHighlight.png");
        HIGHLIGHTED_MOVE = readImage("/images/ui/YellowHighlight.png");
        HIGHLIGHTED_ATTACK = readImage("/images/ui/RedHighlight.png");
        HIGHLIGHTED_ASSIST = readImage("/images/ui/GreenHighlight.png");

        // Initialize selection overlays
        SELECTABLE_UNIT = readImage("/images/ui/BlueHighlight.png");
        SELECTED_UNIT = readImage("/images/ui/GreenSelect.png");
        SELECTABLE_MOVE = readImage("/images/ui/YellowHighlight.png");
        SELECTABLE_ATTACK = readImage("/images/ui/RedHighlight.png");
        SELECTABLE_ASSIST = readImage("/images/ui/GreenHighlight.png");

        // Initialize UI sprites
        UI_ATTACKING = readImage("/images/ui/CrossedSwords.png");
        UI_DEFENDING = readImage("/images/ui/Shield.png");
        UI_DEAD = readImage("/images/ui/Skull.png");
        UI_SPARKLES = readImage("/images/ui/Sparkles1.png");
    }

    /**
     * Reads an image from resources with the specified path.
     * @param path the path to the resource
     * @return the image that was read, if one was found; null, otherwise
     */
    public static Image readImage(String path)
    {
        // Load image from resources
        URL url = Sprites.class.getResource(path);

        if (url == null)
        {
            return null;
        }

        try
        {
            return ImageIO.read(url);
        }
        catch (java.io.IOException ex)
        {
            return null;
        }
    }

    /**
     * Returns the sprite corresponding to the provided tile type.
     * @param type the tile type whose sprite to return
     * @return the sprite for the provided tile type, if one exists; null, otherwise
     */
    public static Image getImage(TileType type)
    {
        return TILES.get(type);
    }

    /**
     * Returns the sprite corresponding to the provided unit type.
     * @param type the unit type whose sprite to return
     * @param isFriendly whether the unit is a friendly or enemy unit
     * @return the sprite for the provided unit type, if one exists; null, otherwise
     */
    public static Image getImage(UnitType type, boolean isFriendly)
    {
        return isFriendly
            ? FRIENDLY_UNITS.get(type)
            : ENEMY_UNITS.get(type);
    }
}
