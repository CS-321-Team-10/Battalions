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
import battalions.models.MapOverlay;
import battalions.models.Tile;
import battalions.models.Unit;
import java.awt.Graphics;
import java.util.Set;
import javax.swing.JPanel;

/**
 * A panel that acts as a view, displaying a map model in the GUI.
 * @author Scott
 */
public final class MapPanel extends JPanel
{
    /**
     * The map and overlay model to display.
     */
    private MapOverlay _overlay;

    /**
     * Initializes a new instance of the MapPanel class.
     * This constructor exists so this class may be used with the Swing GUI Designer.
     */
    public MapPanel()
    {
    }

    /**
     * Sets the map model that this view displays.
     * @param overlay the map model to display
     */
    public void setMap(MapOverlay overlay)
    {
        assert overlay != null;
        _overlay = overlay;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // For drawing in the Swing Form Designer
        if (_overlay == null)
        {
            return;
        }

        final Map map = _overlay.getMap();

        final int height = map.getHeight();
        final int width = map.getWidth();
        final int spritePx = getSpriteSize();

        final Tile[][] tiles = map.getTiles();
        final Set<Unit> units = map.getUnits();

        final Tile selectedTile = _overlay.getSelectedTile();
        final Unit selectedUnit = _overlay.getSelectedFriendlyUnit();
        final Unit selectedEnemyUnit = _overlay.getSelectedEnemyUnit();

        // Draw tiles
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                Tile tile = tiles[y][x];
                TileType underlayType = tile.getUnderlayType();
                TileType mainType = tile.getType();

                int xPx = spritePx * x;
                int yPx = spritePx * y;

                // If main tile type is transparent, draw underlaid tile type
                if (underlayType != null)
                {
                    g.drawImage(Sprites.getImage(underlayType), xPx, yPx, spritePx, spritePx, this);
                }

                // Draw main tile type
                g.drawImage(Sprites.getImage(mainType), xPx, yPx, spritePx, spritePx, this);
            }
        }

        // Draw units
        units.forEach(unit ->
            {
                Location l = unit.getLocation();
                g.drawImage(Sprites.getImage(unit.getType(), !unit.getPlayer().isCPU()), spritePx * l.x, spritePx * l.y, spritePx, spritePx, this);
            });

        // Draw selected tile overlay
        if (selectedTile != null)
        {
            Location l = selectedTile.getLocation();
            g.drawImage(Sprites.SELECTED_MOVE, spritePx * l.x, spritePx * l.y, spritePx, spritePx, this);
        }

        // Draw selected friendly unit overlays
        if (selectedUnit != null)
        {
            Location l = selectedUnit.getLocation();

            // Draw available moves overlay
            selectedUnit.getValidMoves()
                .forEach(m -> g.drawImage(Sprites.AVAILABLE_MOVE, spritePx * m.x, spritePx * m.y, spritePx, spritePx, this));

            // Draw available attacks overlay
            selectedUnit.getValidAttacks()
                .forEach(a -> g.drawImage(Sprites.AVAILABLE_ATTACK, spritePx * a.x, spritePx * a.y, spritePx, spritePx, this));

            // Draw selection overlay
            g.drawImage(Sprites.SELECTED_UNIT, spritePx * l.x, spritePx * l.y, spritePx, spritePx, this);
        }

        // Draw selected enemy unit overlays
        if (selectedEnemyUnit != null)
        {
            Location l = selectedEnemyUnit.getLocation();

//            // Draw available moves overlay
//            selectedEnemyUnit.getValidMoves()
//                .forEach(m -> g.drawImage(Sprites.AVAILABLE_MOVE, spritePx * m.x, spritePx * m.y, spritePx, spritePx, this));
//
//            // Draw available attacks overlay
//            selectedEnemyUnit.getValidAttacks()
//                .forEach(a -> g.drawImage(Sprites.AVAILABLE_ASSIST, spritePx * a.x, spritePx * a.y, spritePx, spritePx, this));

            // Draw selection overlay
            g.drawImage(Sprites.SELECTED_ATTACK, spritePx * l.x, spritePx * l.y, spritePx, spritePx, this);
        }
    }

    /**
     * Calculates the size (in pixels) for the width and height of a tile.
     * @return the size (in pixels) for the width and height of a tile
     */
    private int getSpriteSize()
    {
        final Map map = _overlay.getMap();

        return Math.min(
            (int) (getWidth() / map.getWidth()),
            (int) (getHeight() / map.getHeight()));
    }

    /**
     * Converts a coordinate from pixels to a location on the map model.
     * @param x the x-position relative to the source component
     * @param y the y-position relative to the source component
     * @return the map model location in which the provided pixel coordinate lay
     */
    public Location toLocation(int x, int y)
    {
        final int spriteSize = getSpriteSize();
        return new Location(
            (int) (x / spriteSize),
            (int) (y / spriteSize));
    }
}
