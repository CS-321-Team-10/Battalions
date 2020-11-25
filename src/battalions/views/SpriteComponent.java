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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * A component that draws a sprite.
 * @author Scott
 */
public abstract class SpriteComponent extends JComponent
{
    /**
     * The size (in pixels) of each sprite.
     */
    public static final int SPRITE_SIZE = 32;

    /**
     * Whether this tile should be highlighted as selected.
     */
    private boolean _isSelected = false;

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(getImage(), 0, 0, SPRITE_SIZE, SPRITE_SIZE, null);

        if (_isSelected)
        {
            g.drawImage(getSelectedImage(), 0, 0, SPRITE_SIZE, SPRITE_SIZE, null);
        }
    }

    /**
     * Returns an image with the sprite corresponding to this component.
     * @return the specified sprite as an Image
     */
    protected abstract Image getImage();

    /**
     * Returns an image that can act as an overlay indicating a sprite is selected.
     * @return the overlay image indicating a sprite is selected
     */
    protected abstract Image getSelectedImage();

    /**
     * Returns the image with the specified pathname and filename.
     * @param pathName the path to the parent directory of the file
     * @param fileName the local name of the file
     * @return the specified image, if found; null, otherwise
     */
    protected static Image readImage(String pathName, String fileName)
    {
        // [TODO use platform-independent pathname]
        File f = new File(pathName + fileName);

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
     * Sets whether this tile should be highlighted as selected.
     * @param value true, if this tile should be highlighted; false, otherwise
     */
    public final void setIsSelected(boolean value)
    {
        _isSelected = value;
        repaint();
    }

    /**
     * Returns whether this sprite is selected.
     * @return true, if this sprite is selected; false, otherwise
     */
    public final boolean isSelected()
    {
        return _isSelected;
    }

    @Override
    public java.awt.Dimension getPreferredSize()
    {
        return new Dimension(SPRITE_SIZE, SPRITE_SIZE);
    }
}
