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
import battalions.data.TileType;
import battalions.models.Map;
import battalions.models.MapSelector;
import battalions.models.Player;
import battalions.models.Tile;
import battalions.models.Unit;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Set;
import javax.swing.JPanel;

/**
 *
 * @author Scott
 */
public final class MapSelectorView extends JPanel implements PropertyChangeListener
{
    private Image canvas;

    private int mapWidth = 1;

    private int mapHeight = 1;

    private int spriteSize = 1;

    private int canvasWidth = 1;

    private int canvasHeight = 1;

    private int canvasOffsetX;

    private int canvasOffsetY;

    public MapSelectorView()
    {
        canvas = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB);
        clearImage(canvas);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Draw canvas, centered inside panel
        g.drawImage(canvas, canvasOffsetX, canvasOffsetY, this);
    }

    private void onMoving(Tile source, Tile destination)
    {

    }

    private void onAttacking(Unit attacker, Unit target)
    {

    }

    private void onAssisting(Unit assister, Unit target)
    {

    }

    private void onCurrentPlayerChange(Player oldValue, Player newValue)
    {

    }

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

    public void drawUnits(Set<Unit> units)
    {
        Graphics g = canvas.getGraphics();

        units.forEach(unit -> drawSprite(g, unit));
    }

    public void drawSelectableUnits(Set<Unit> selectableUnits)
    {
        Graphics g = canvas.getGraphics();

        selectableUnits.forEach(unit -> drawSprite(g, Sprites.SELECTABLE_UNIT, unit.getLocation()));
    }

    public void drawHighlighted(Tile highlightedTile)
    {
        Graphics g = canvas.getGraphics();

        if (highlightedTile instanceof Tile)
        {
            drawSprite(g, Sprites.HIGHLIGHTED_TILE, highlightedTile.getLocation());
        }
    }

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

            if (drawAttacks)
            {
                highlightedUnit.getValidAttacks().forEach(attack ->
                    drawSprite(g, Sprites.HIGHLIGHTED_ATTACK, attack.getLocation()));
            }

            if (drawAssists)
            {
                highlightedUnit.getValidAssists().forEach(assist ->
                    drawSprite(g, Sprites.HIGHLIGHTED_ASSIST, assist.getLocation()));
            }
        }
    }

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

            if (drawAttacks)
            {
                // Draw available attacks
                selectedUnit.getValidAttacks().forEach(attack ->
                    drawSprite(g, Sprites.SELECTABLE_ATTACK, attack.getLocation()));
            }

            if (drawAssists)
            {
                // Draw available assists
                selectedUnit.getValidAssists().forEach(assist ->
                    drawSprite(g, Sprites.SELECTABLE_ASSIST, assist.getLocation()));
            }
        }
    }

    public void drawCursor(Location cursorLocation)
    {
        Graphics g = canvas.getGraphics();

        if (cursorLocation instanceof Location)
        {
            drawSprite(g, Sprites.CURSOR, cursorLocation);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent event)
    {
        Object source = event.getSource();
        String propertyName = event.getPropertyName();
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (source instanceof MapSelector)
        {
            switch (propertyName)
            {
                case MapSelector.MOVING_EVENT:
                    onMoving((Tile) oldValue, (Tile) newValue);
                    break;
                case MapSelector.ATTACKING_EVENT:
                    onAttacking((Unit) oldValue, (Unit) newValue);
                    break;
                case MapSelector.ASSISTING_EVENT:
                    onAssisting((Unit) oldValue, (Unit) newValue);
                    break;
                case MapSelector.CURRENT_PLAYER_PROPERTY:
                    onCurrentPlayerChange((Player) oldValue, (Player) newValue);
                    break;
            }
        }
        else if (source instanceof Map)
        {
            switch (propertyName)
            {
                case Map.SIZE_PROPERTY:
                    updateMapSize((Location) newValue);
                    break;
            }
        }
    }

    public void onResize()
    {
        int width = getWidth();
        int height = getHeight();

        spriteSize = Math.min(
            width / mapWidth,
            height / mapHeight);

        canvasWidth = mapWidth * spriteSize;
        canvasHeight = mapHeight * spriteSize;

        canvasOffsetX = (width - canvasWidth) / 2;
        canvasOffsetY = (height - canvasHeight) / 2;

        canvas = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB);
    }

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

    public Location toLocation(int xPx, int yPx)
    {
        return new Location(
            (xPx - canvasOffsetX) / spriteSize,
            (yPx - canvasOffsetY) / spriteSize);
    }

    private void drawSprite(Graphics g, Unit unit)
    {
        drawSprite(g, Sprites.getImage(unit.getType(), !unit.getPlayer().isCPU()), unit.getLocation());
    }

    private void drawSprite(Graphics g, Image sprite, Location l)
    {
        g.drawImage(sprite, l.x * spriteSize, l.y * spriteSize, spriteSize, spriteSize, this);
    }

    private void drawSprite(Graphics g, Tile tile)
    {
        Location l = tile.getLocation();
        TileType underlayType = tile.getUnderlayType();

        // If main tile type is transparent, draw underlaid tile type
        if (underlayType instanceof TileType)
        {
            drawSprite(g, Sprites.getImage(underlayType), l);
        }

        // Draw main tile type
        drawSprite(g, Sprites.getImage(tile.getType()), l);
    }

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

    public final int getMapWidth()
    {
        return mapWidth;
    }

    public final int getMapHeight()
    {
        return mapHeight;
    }

    public final int getSpriteSize()
    {
        return spriteSize;
    }

    public final int getCanvasWidth()
    {
        return canvasWidth;
    }

    public final int getCanvasHeight()
    {
        return canvasHeight;
    }

    public final int getCanvasOffsetX()
    {
        return canvasOffsetX;
    }

    public final int getCanvasOffsetY()
    {
        return canvasOffsetY;
    }
}
