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
                g.drawImage(Sprites.getImage(tiles[y][x]), spritePx * x, spritePx * y, spritePx, spritePx, this);
            }
        }

        // Draw units
        units.forEach(
            x ->
            {
                Location l = x.getLocation();
                g.drawImage(Sprites.getImage(x), spritePx * l.x, spritePx * l.y, spritePx, spritePx, this);
            });

        // Draw selection overlays
        if (selectedTile != null)
        {
            Location l = selectedTile.getLocation();
            g.drawImage(Sprites.SELECTED_TILE, spritePx * l.x, spritePx * l.y, spritePx, spritePx, this);
        }

        if (selectedUnit != null)
        {
            Location l = selectedUnit.getLocation();
            g.drawImage(Sprites.SELECTED_UNIT, spritePx * l.x, spritePx * l.y, spritePx, spritePx, this);
        }

        if (selectedEnemyUnit != null)
        {
            Location l = selectedEnemyUnit.getLocation();
            g.drawImage(Sprites.SELECTED_ENEMY_UNIT, spritePx * l.x, spritePx * l.y, spritePx, spritePx, this);
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
