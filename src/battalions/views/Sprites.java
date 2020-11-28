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

import battalions.models.Tile;
import battalions.models.Unit;
import battalions.util.Rng;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Static class allowing access to all project images.
 * @author Scott
 */
public class Sprites
{
    private static final String TILES_DIRECTORY = "src/images/tiles/";
    private static final String UNITS_DIRECTORY = "src/images/units/";
    private static final String UI_DIRECTORY = "src/images/ui/";

    private static final Image GRASS_1_TILE = readImage(TILES_DIRECTORY + "Grass1.png");
    private static final Image GRASS_2_TILE = readImage(TILES_DIRECTORY + "Grass2.png");
    private static final Image WHEAT_GRASS_1_TILE = readImage(TILES_DIRECTORY + "WheatGrass1.png");
    private static final Image WHEAT_GRASS_2_TILE = readImage(TILES_DIRECTORY + "WheatGrass2.png");
    private static final Image FOREST_TILE = readImage(TILES_DIRECTORY + "Forest.png");
    private static final Image SAND_TILE = readImage(TILES_DIRECTORY + "Sand.png");
    private static final Image VERTICAL_WALL_TILE = readImage(TILES_DIRECTORY + "WallVertical.png");
    private static final Image HORIZONTAL_WALL_TILE = readImage(TILES_DIRECTORY + "WallHorizontal.png");
    private static final Image NORTHWEST_WALL_TILE = readImage(TILES_DIRECTORY + "WallCornerNW.png");
    private static final Image NORTHEAST_WALL_TILE = readImage(TILES_DIRECTORY + "WallCornerNE.png");
    private static final Image SOUTHWEST_WALL_TILE = readImage(TILES_DIRECTORY + "WallCornerSW.png");
    private static final Image SOUTHEAST_WALL_TILE = readImage(TILES_DIRECTORY + "WallCornerSE.png");

    private static final Image INFANTRY_UNIT = readImage(UNITS_DIRECTORY + "FriendlyInfantry.png");
    private static final Image HEALER_UNIT = readImage(UNITS_DIRECTORY + "FriendlyHealer.png");
    private static final Image ARCHER_UNIT = readImage(UNITS_DIRECTORY + "FriendlyArcher.png");
    private static final Image KNIGHT_UNIT = readImage(UNITS_DIRECTORY + "FriendlyKnight.png");
    private static final Image MAGE_UNIT = readImage(UNITS_DIRECTORY + "FriendlyMage.png");
    private static final Image WARLOCK_UNIT = readImage(UNITS_DIRECTORY + "FriendlyWarlock.png");

    private static final Image ENEMY_INFANTRY_UNIT = readImage(UNITS_DIRECTORY + "EnemyInfantry.png");
    private static final Image ENEMY_HEALER_UNIT = readImage(UNITS_DIRECTORY + "EnemyHealer.png");
    private static final Image ENEMY_ARCHER_UNIT = readImage(UNITS_DIRECTORY + "EnemyArcher.png");
    private static final Image ENEMY_KNIGHT_UNIT = readImage(UNITS_DIRECTORY + "EnemyKnight.png");
    private static final Image ENEMY_MAGE_UNIT = readImage(UNITS_DIRECTORY + "EnemyMage.png");
    private static final Image ENEMY_WARLOCK_UNIT = readImage(UNITS_DIRECTORY + "EnemyWarlock.png");

    public static final Image SELECTED_TILE = readImage(UI_DIRECTORY + "SelectedTile.png");
    public static final Image SELECTED_UNIT = readImage(UI_DIRECTORY + "SelectedFriendlyUnit.png");
    public static final Image SELECTED_ENEMY_UNIT = readImage(UI_DIRECTORY + "SelectedEnemyUnit.png");

    /**
     * Reads an image with the specified path.
     * @param path the path to the file, including the file name and extension
     * @return the image that was read, if one was found; null, otherwise
     */
    public static Image readImage(String path)
    {
        File f = new File(path);

        try
        {
            return ImageIO.read(f);
        }
        catch (java.io.IOException ex)
        {
            return null;
        }
    }

    /**
     * Returns the sprite corresponding to the provided tile.
     * @param tile the tile whose corresponding sprite to return
     * @return the sprite corresponding to the provided tile, if one exists; null, otherwise
     */
    public static Image getImage(Tile tile)
    {
        switch (tile.getType())
        {
            case Field:
                // Randomize which grass tile is chosen
                switch (Rng.getInt(0, 3))
                {
                    case 0:
                        return GRASS_1_TILE;
                    case 1:
                        return GRASS_2_TILE;
                    case 2:
                        return WHEAT_GRASS_1_TILE;
                    case 3:
                        return WHEAT_GRASS_2_TILE;
                }
            case Forest:
                return FOREST_TILE;
            case Sand:
                return SAND_TILE;
            case Wall:
                switch (tile.getOrientation())
                {
                    case Up:
                    case Down:
                        return VERTICAL_WALL_TILE;
                    case Right:
                    case Left:
                        return HORIZONTAL_WALL_TILE;
                    case UpLeft:
                        return NORTHWEST_WALL_TILE;
                    case UpRight:
                        return NORTHEAST_WALL_TILE;
                    case DownLeft:
                        return SOUTHWEST_WALL_TILE;
                    case DownRight:
                        return SOUTHEAST_WALL_TILE;
                }
        }

        return null;
    }

    /**
     * Returns the sprite corresponding to the provided unit.
     * @param unit the unit whose corresponding sprite to return
     * @return the sprite corresponding to the provided unit, if one exists; null, otherwise
     */
    public static Image getImage(Unit unit)
    {
        final boolean friendly = !unit.getPlayer().isCPU();

        switch (unit.getType())
        {
            case Archer:
                return friendly ? ARCHER_UNIT : ENEMY_ARCHER_UNIT;
            case Healer:
                return friendly ? HEALER_UNIT : ENEMY_HEALER_UNIT;
            case Infantry:
                return friendly ? INFANTRY_UNIT : ENEMY_INFANTRY_UNIT;
            case Knight:
                return friendly ? KNIGHT_UNIT : ENEMY_KNIGHT_UNIT;
            case Mage:
                return friendly ? MAGE_UNIT : ENEMY_MAGE_UNIT;
            case Warlock:
                return friendly ? WARLOCK_UNIT : ENEMY_WARLOCK_UNIT;
        }

        return null;
    }
}
