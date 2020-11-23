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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * A component that draws a tile sprite.
 * @author Scott
 */
public class TileComponent extends JComponent
{
    /**
     * The size (in pixels) of each tile.
     */
    public static final int TILE_SIZE = 32;

    /**
     * The overlay image that indicates that a tile is selected.
     */
    private static final Image SELECTED_IMAGE;

    /**
     * The tile model that this component draws.
     */
    private final Tile _tile;

    /**
     * Whether this tile should be highlighted as selected.
     */
    private boolean _isSelected = false;

    /**
     * The image to draw for this tile.
     */
    private final Image _image;

    static
    {
        SELECTED_IMAGE = getSelectedImage();
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
        _image = getImage(tile);
        assert _image != null;

        addMouseListener(
            new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    super.mouseClicked(e);
                    System.out.println("clicked on " + _tile);
                    setIsSelected(_isSelected ^ true);
                }
            });
    }

    /**
     * Sets whether this tile should be highlighted as selected.
     * @param value true, if this tile should be highlighted; false, otherwise
     */
    public final void setIsSelected(boolean value)
    {
        _isSelected = value;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(_image, 0, 0, TILE_SIZE, TILE_SIZE, null);

        if (_isSelected)
        {
            g.drawImage(SELECTED_IMAGE, 0, 0, TILE_SIZE, TILE_SIZE, null);
        }
    }

    /**
     * Returns an image with the sprite corresponding to the specified tile.
     * @param t the tile whose image to return
     * @return the specified tile sprite as an Image
     */
    private static Image getImage(Tile t)
    {
        // Path to tiles directory
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

        // [TODO use platform-independent pathname]
        File f = new File("src/images/tiles/" + fileName);

        try
        {
            return ImageIO.read(f);
        }
        catch (IOException ex)
        {
            return null;
        }
    }

    /**
     * Returns an image that can act as an overlay indicating a tile is selected.
     * @return the overlay image indicating a tile is selected
     */
    private static Image getSelectedImage()
    {
        File f = new File("src/images/ui/SelectTile.png");

        try
        {
            return ImageIO.read(f);
        }
        catch (IOException ex)
        {
            return null;
        }
    }

    /**
     * Returns the tile model that this component draws.
     * @return the tile model that this component draws
     */
    public final Tile getTile()
    {
        return _tile;
    }

    @Override
    public java.awt.Dimension getPreferredSize()
    {
        return new Dimension(TILE_SIZE, TILE_SIZE);
    }
}