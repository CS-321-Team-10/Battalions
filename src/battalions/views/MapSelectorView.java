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

import battalions.data.Location;
import battalions.data.Orientation;
import battalions.data.TileType;
import battalions.models.Map;
import battalions.models.MapSelector;
import battalions.models.Player;
import battalions.models.Tile;
import battalions.models.Unit;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Set;
import javax.swing.JPanel;

/**
 * A view for a map and highlights/selections.
 * @author Scott
 */
public final class MapSelectorView extends JPanel implements PropertyChangeListener
{
    /**
     * The canvas for the map.
     */
    private Image canvas;

    /**
     * The width of the map, in tiles.
     */
    private int mapWidth = 1;

    /**
     * The height of the map, in tiles.
     */
    private int mapHeight = 1;

    /**
     * The sprite size, in pixels.
     */
    private int spriteSize = 1;

    /**
     * The canvas width, in pixels.
     */
    private int canvasWidth = 1;

    /**
     * The canvas height, in pixels.
     */
    private int canvasHeight = 1;

    /**
     * The canvas left offset, in pixels.
     */
    private int canvasOffsetX;

    /**
     * The canvas top offset, in pixels.
     */
    private int canvasOffsetY;

    /**
     * The current cursor x position, in pixels.
     */
    private int cursorX;

    /**
     * The current cursor y position, in pixels.
     */
    private int cursorY;

    /**
     * The cursor left and top offset, in pixels.
     */
    private int cursorOffset;

    /**
     * Creates a MapSelectorView form.
     */
    public MapSelectorView()
    {
        canvas = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
        clearImage(canvas);

        // Make windows 32x32px cursor transparent so we can draw at a custom size
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
            new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "sprite cursor"));
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Draw canvas, centered inside panel
        g.drawImage(canvas, canvasOffsetX, canvasOffsetY, this);

        // Draw custom sized cursor
        g.drawImage(Sprites.CURSOR, cursorX - cursorOffset, cursorY - cursorOffset, spriteSize, spriteSize, this);
    }

    /**
     * Draws the tiles to the canvas before the screen is painted.
     * @param tiles the tiles to draw
     */
    public void drawTiles(Tile[][] tiles)
    {
        Graphics g = canvas.getGraphics();

        for (Tile[] row : tiles)
        {
            for (Tile tile : row)
            {
                drawSprite(g, tile);
            }
        }
    }

    /**
     * Draws the units to the canvas before the screen is painted.
     * @param units the units to draw
     */
    public void drawUnits(Set<Unit> units)
    {
        Graphics g = canvas.getGraphics();

        units.forEach(unit -> drawSprite(g, unit));
    }

    /**
     * Draws the selectable unit highlights to the canvas before the screen is painted.
     * @param selectableUnits the units to highlight
     */
    public void drawSelectableUnits(Set<Unit> selectableUnits)
    {
        Graphics g = canvas.getGraphics();

        selectableUnits.forEach(unit -> drawSprite(g, Sprites.SELECTABLE_UNIT, unit.getLocation()));
    }

    /**
     * Draws a tile highlight to the canvas before the screen is painted.
     * @param highlightedTile the tile to highlight
     */
    public void drawHighlighted(Tile highlightedTile)
    {
        Graphics g = canvas.getGraphics();

        if (highlightedTile instanceof Tile)
        {
            drawSprite(g, Sprites.HIGHLIGHTED_TILE, highlightedTile.getLocation());
        }
    }

    /**
     * Draws a unit highlight and available moves/actions to the canvas before the screen is painted
     * @param highlightedUnit the unit to highlight
     * @param drawUnit whether to draw the unit highlight
     * @param drawMoves whether to draw the move highlights
     * @param drawAttacks whether to draw the attack highlights
     * @param drawAssists whether to draw the assist highlights
     */
    public void drawHighlighted(Unit highlightedUnit, boolean drawUnit, boolean drawMoves, boolean drawAttacks, boolean drawAssists)
    {
        Graphics g = canvas.getGraphics();

        if (highlightedUnit instanceof Unit)
        {
            if (drawUnit)
            {
                // Draw unit overlay
                drawSprite(g, Sprites.HIGHLIGHTED_UNIT, highlightedUnit.getLocation());
            }

            if (drawMoves)
            {
                // Draw available moves
                highlightedUnit.getValidMoves().forEach(move ->
                    drawSprite(g, Sprites.HIGHLIGHTED_MOVE, move.getLocation()));
            }

            if (drawAssists)
            {
                highlightedUnit.getValidAssists().forEach(assist ->
                    drawSprite(g, Sprites.HIGHLIGHTED_ASSIST, assist.getLocation()));
            }

            if (drawAttacks)
            {
                highlightedUnit.getValidAttacks().forEach(attack ->
                    drawSprite(g, Sprites.HIGHLIGHTED_ATTACK, attack.getLocation()));
            }
        }
    }

    /**
     * Draws a selected unit highlight and available moves/actions to the canvas before the screen is painted.
     * @param selectedUnit the unit to highlight
     * @param drawUnit whether to draw the unit highlight
     * @param drawMoves whether to draw the move highlights
     * @param drawAttacks whether to draw the attack highlights
     * @param drawAssists whether to draw the assist highlights
     */
    public void drawSelected(Unit selectedUnit, boolean drawUnit, boolean drawMoves, boolean drawAttacks, boolean drawAssists)
    {
        Graphics g = canvas.getGraphics();

        if (selectedUnit instanceof Unit)
        {
            if (drawUnit)
            {
                // Draw unit overlay
                drawSprite(g, Sprites.SELECTED_UNIT, selectedUnit.getLocation());
            }

            if (drawMoves)
            {
                // Draw available moves
                selectedUnit.getValidMoves().forEach(move ->
                    drawSprite(g, Sprites.SELECTABLE_MOVE, move.getLocation()));
            }

            if (drawAssists)
            {
                // Draw available assists
                selectedUnit.getValidAssists().forEach(assist ->
                    drawSprite(g, Sprites.SELECTABLE_ASSIST, assist.getLocation()));
            }

            if (drawAttacks)
            {
                // Draw available attacks
                selectedUnit.getValidAttacks().forEach(attack ->
                    drawSprite(g, Sprites.SELECTABLE_ATTACK, attack.getLocation()));
            }
        }
    }

    /**
     * Draws the cursor location to the canvas.
     * @param cursorLocation the location of the cursor
     * @param cursorType whether the cursor is over a tile, friendly, or enemy unit
     */
    public void drawCursorLocation(Location cursorLocation, Boolean cursorType)
    {
        Graphics g = canvas.getGraphics();

        if (cursorLocation instanceof Location)
        {
            drawSprite(g, (cursorType == null)
                ? Sprites.CURSOR_TILE
                : cursorType
                    ? Sprites.CURSOR_FRIENDLY
                    : Sprites.CURSOR_ENEMY, cursorLocation);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent event)
    {
        Object source = event.getSource();
        String propertyName = event.getPropertyName();
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (source instanceof Map)
        {
            switch (propertyName)
            {
                case Map.SIZE_PROPERTY:
                    updateMapSize((Location) newValue);
                    break;
            }
        }
    }

    /**
     * Recalculates various size values.
     */
    public void onResize()
    {
        int width = getWidth();
        int height = getHeight();

        spriteSize = Math.max(Math.min(
            width / mapWidth,
            height / mapHeight), 1);

        canvasWidth = mapWidth * spriteSize;
        canvasHeight = mapHeight * spriteSize;

        canvasOffsetX = (width - canvasWidth) / 2;
        canvasOffsetY = (height - canvasHeight) / 2;

        cursorOffset = (int) (spriteSize * 0.35);

        canvas = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB);
    }

    /**
     * Updates the map size property and resizes the canvas.
     * @param value the new value of the property
     */
    private void updateMapSize(Location value)
    {
        if (value instanceof Location)
        {
            mapWidth = Math.max(value.x, 1);
            mapHeight = Math.max(value.y, 1);

            onResize();
            repaint();
        }
    }

    /**
     * Converts a pixel location to a map location
     * @param xPx the x location in pixels
     * @param yPx the y location in pixels
     * @return a map location
     */
    public Location toLocation(int xPx, int yPx)
    {
        return new Location(
            (xPx - canvasOffsetX) / spriteSize,
            (yPx - canvasOffsetY) / spriteSize);
    }

    /**
     * Draws a unit sprite to an image.
     * @param g the graphics context
     * @param unit the unit to draw
     */
    private void drawSprite(Graphics g, Unit unit)
    {
        drawSprite(g, Sprites.getImage(unit.getType(), unit.getPlayer().isPlayer1()), unit.getLocation(), unit.getOrientation());
    }

    /**
     * Draws a tile sprite to an image.
     * @param g the graphics context
     * @param tile the tile to draw
     */
    private void drawSprite(Graphics g, Tile tile)
    {
        Location l = tile.getLocation();
        TileType underlayType = tile.getUnderlayType();

        Orientation orientation = tile.getOrientation();

        // If main tile type is transparent, draw underlaid tile type
        if (underlayType instanceof TileType)
        {
            drawSprite(g, Sprites.getImage(underlayType), l, orientation);
        }

        // Draw main tile type
        drawSprite(g, Sprites.getImage(tile.getType()), l, orientation);
    }

    /**
     * Draws a sprite to an image at a specified location.
     * @param g the graphics context
     * @param sprite the sprite to draw
     * @param l the location to draw it at
     */
    private void drawSprite(Graphics g, Image sprite, Location l)
    {
        drawSprite(g, sprite, l, Orientation.NONE);
    }

    /**
     * Draws a sprite to an image at a specified location and with specified orientation.
     * @param g the graphics context
     * @param sprite the sprite to draw
     * @param l the location to draw it at
     * @param orientation the orientation of the sprite
     */
    private void drawSprite(Graphics g, Image sprite, Location l, Orientation orientation)
    {
        int x = l.x * spriteSize;
        int y = l.y * spriteSize;

        if (orientation.flipHorizontal)
        {
            g.drawImage(sprite, x + spriteSize, y, -spriteSize, spriteSize, this);
        }
        else
        {
            g.drawImage(sprite, x, y, spriteSize, spriteSize, this);
        }
    }

    /**
     * Clears an image to transparent.
     * @param image the image to clear
     * @return the image's graphics context
     */
    private Graphics clearImage(Image image)
    {
        if (image == null)
        {
            return null;
        }

        Graphics g = image.getGraphics();
        g.clearRect(0, 0, image.getWidth(this), image.getHeight(this));
        return g;
    }

    /**
     * Sets the current cursor location.
     * @param xPx the x location in pixels
     * @param yPx the y location in pixels
     */
    public void setCursorLocation(int xPx, int yPx)
    {
        cursorX = xPx;
        cursorY = yPx;
    }

    /**
     * Returns the map with in tiles.
     * @return the map width
     */
    public final int getMapWidth()
    {
        return mapWidth;
    }

    /**
     * Returns the map height in tiles.
     * @return the map height
     */
    public final int getMapHeight()
    {
        return mapHeight;
    }

    /**
     * Returns the sprite size in pixels.
     * @return the sprite size
     */
    public final int getSpriteSize()
    {
        return spriteSize;
    }

    /**
     * Returns the canvas width in pixels.
     * @return the canvas width
     */
    public final int getCanvasWidth()
    {
        return canvasWidth;
    }

    /**
     * Returns the canvas height in pixels.
     * @return the canvas height
     */
    public final int getCanvasHeight()
    {
        return canvasHeight;
    }

    /**
     * Returns the canvas left offset in pixels.
     * @return the canvas left offset
     */
    public final int getCanvasOffsetX()
    {
        return canvasOffsetX;
    }

    /**
     * Returns the canvas top offset in pixels
     * @return the canvas top offset
     */
    public final int getCanvasOffsetY()
    {
        return canvasOffsetY;
    }
}
