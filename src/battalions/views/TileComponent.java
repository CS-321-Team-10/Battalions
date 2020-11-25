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
import battalions.util.Rng;
import java.awt.Image;

/**
 * A component that draws a tile sprite.
 * @author Scott
 */
public class TileComponent extends SpriteComponent
{
    /**
     * The overlay image that indicates that a tile is selected.
     */
    private static final Image SELECTED_IMAGE;

    /**
     * The tile model that this component draws.
     */
    private final Tile _tile;

    /**
     * The image to draw for this tile.
     */
    private final Image _image;

    static
    {
        SELECTED_IMAGE = readImage("src/images/ui/", "SelectTile.png");
        assert SELECTED_IMAGE != null;
    }

    /**
     * Initializes a new instance of the TileComponent class.
     * @param tile the tile to draw
     */
    public TileComponent(Tile tile)
    {
        assert tile != null;
        _tile = tile;

        // Assign image once to prevent re-calculating random
        //  variations (e.g. grass) upon each redraw.
        _image = getTileImage(_tile);
        assert _image != null;
    }

    /**
     * Returns an image with the sprite corresponding to the specified tile.
     * @param t the tile whose image to return
     * @return the specified tile sprite as an Image
     */
    private static Image getTileImage(Tile t)
    {
        String fileName = null;

        // Determine the given tile's type, then sets the appropriate PNG path
        switch (t.getType())
        {
            case Field:
                // Randomize which grass tile is chosen
                switch (Rng.getInt(0, 3))
                {
                    case 0:
                        fileName = "Grass1.png";
                        break;
                    case 1:
                        fileName = "Grass2.png";
                        break;
                    case 2:
                        fileName = "WheatGrass1.png";
                        break;
                    case 3:
                        fileName = "WheatGrass2.png";
                        break;
                }
                break;
            case Forest:
                fileName = "Forest.png";
                break;
            case Sand:
                fileName = "Sand.png";
                break;
            case Wall:
                switch (t.getOrientation())
                {
                    case Up:
                    case Down:
                        fileName = "WallVertical.png";
                        break;
                    case Right:
                    case Left:
                        fileName = "WallHorizontal.png";
                        break;
                    case UpLeft:
                        fileName = "WallCornerNW.png";
                        break;
                    case UpRight:
                        fileName = "WallCornerNE.png";
                        break;
                    case DownLeft:
                        fileName = "WallCornerSW.png";
                        break;
                    case DownRight:
                        fileName = "WallCornerSE.png";
                        break;
                }
                break;
        }

        if (fileName == null)
        {
            // If you end up here, it's probably a code error:
            //  e.g. a switch is missing a case
            assert false;
        }

        return readImage("src/images/tiles/", fileName);
    }

    @Override
    protected Image getImage()
    {
        return _image;
    }

    @Override
    protected Image getSelectedImage()
    {
        return SELECTED_IMAGE;
    }

    /**
     * Returns the tile model that this component draws.
     * @return the tile model that this component draws
     */
    public final Tile getTile()
    {
        return _tile;
    }
}