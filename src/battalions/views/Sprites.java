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
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Static class allowing access to all project images.
 * @author Scott
 */
public class Sprites
{
    public static final Image GRASS_1_TILE = readImage("/images/tiles/Grass1.png");
    public static final Image GRASS_2_TILE = readImage("/images/tiles/Grass2.png");
    public static final Image WHEAT_GRASS_1_TILE = readImage("/images/tiles/WheatGrass1.png");
    public static final Image WHEAT_GRASS_2_TILE = readImage("/images/tiles/WheatGrass2.png");
    public static final Image FOREST_TILE = readImage("/images/tiles/Forest.png");
    public static final Image SAND_TILE = readImage("/images/tiles/Sand.png");
    public static final Image VERTICAL_WALL_TILE = readImage("/images/tiles/WallVertical.png");
    public static final Image HORIZONTAL_WALL_TILE = readImage("/images/tiles/WallHorizontal.png");
    public static final Image NORTHWEST_WALL_TILE = readImage("/images/tiles/WallCornerNW.png");
    public static final Image NORTHEAST_WALL_TILE = readImage("/images/tiles/WallCornerNE.png");
    public static final Image SOUTHWEST_WALL_TILE = readImage("/images/tiles/WallCornerSW.png");
    public static final Image SOUTHEAST_WALL_TILE = readImage("/images/tiles/WallCornerSE.png");

    public static final Image INFANTRY_UNIT = readImage("/images/units/FriendlyInfantry.png");
    public static final Image HEALER_UNIT = readImage("/images/units/FriendlyHealer.png");
    public static final Image ARCHER_UNIT = readImage("/images/units/FriendlyArcher.png");
    public static final Image KNIGHT_UNIT = readImage("/images/units/FriendlyKnight.png");
    public static final Image MAGE_UNIT = readImage("/images/units/FriendlyMage.png");
    public static final Image WARLOCK_UNIT = readImage("/images/units/FriendlyWarlock.png");

    public static final Image ENEMY_INFANTRY_UNIT = readImage("/images/units/EnemyInfantry.png");
    public static final Image ENEMY_HEALER_UNIT = readImage("/images/units/EnemyHealer.png");
    public static final Image ENEMY_ARCHER_UNIT = readImage("/images/units/EnemyArcher.png");
    public static final Image ENEMY_KNIGHT_UNIT = readImage("/images/units/EnemyKnight.png");
    public static final Image ENEMY_MAGE_UNIT = readImage("/images/units/EnemyMage.png");
    public static final Image ENEMY_WARLOCK_UNIT = readImage("/images/units/EnemyWarlock.png");

    public static final Image SELECTED_TILE = readImage("/images/ui/SelectedTile.png");
    public static final Image SELECTED_UNIT = readImage("/images/ui/SelectedFriendlyUnit.png");
    public static final Image SELECTED_ENEMY_UNIT = readImage("/images/ui/SelectedEnemyUnit.png");
    public static final Image SELECTED_FRIENDLY_UNIT_RANGE = readImage("/images/ui/SelectedFriendlyUnitRange.png");
    public static final Image SELECTED_ENEMY_UNIT_RANGE = readImage("/images/ui/SelectedEnemyUnitRange.png");

    /**
     * Reads an image with the specified path.
     * @param path the path to the file, including the file name and extension
     * @return the image that was read, if one was found; null, otherwise
     */
    public static Image readImage(String path)
    {
        URL url = Sprites.class.getResource(path);

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

        if (tile.getLocation().equals(new battalions.data.Location(7, 4)))
        {
            System.out.println(tile);
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
