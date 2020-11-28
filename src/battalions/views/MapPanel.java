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
import battalions.models.Tile;
import battalions.models.Unit;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;
import javax.swing.JPanel;

/**
 * A panel that acts as a view, displaying a map model in the GUI.
 * @author Scott
 */
public final class MapPanel extends JPanel
{
    /**
     * The selection mode allowing nothing to be selected.
     */
    public static final int SELECT_NONE_MODE = 0;

    /**
     * The selection mode flag allowing tiles to be selected.
     */
    public static final int SELECT_TILE_MODE = 1;

    /**
     * The selection mode flag allowing friendly units to be selected.
     */
    public static final int SELECT_FRIENDLY_UNIT_MODE = 1 << 1;

    /**
     * The selection mode flag allowing enemy units to be selected.
     */
    public static final int SELECT_ENEMY_UNIT_MODE = 1 << 2;

    /**
     * The selection mode allowing anything to be selected.
     */
    public static final int SELECT_ANY_MODE = SELECT_TILE_MODE | SELECT_FRIENDLY_UNIT_MODE | SELECT_ENEMY_UNIT_MODE;

    /**
     * The map model to display.
     */
    private Map _map;

    /**
     * The current selection mode flags.
     */
    private int _selectionMode = SELECT_ANY_MODE;

    /**
     * The currently selected tile, if one exists; null, otherwise.
     */
    private Tile _selectedTile;

    /**
     * The currently selected unit, if one exists; null, otherwise.
     */
    private Unit _selectedFriendlyUnit;

    /**
     * The currently selected enemy unit, if one exists; null, otherwise.
     */
    private Unit _selectedEnemyUnit;

    /**
     * Initializes a new instance of the MapPanel class.
     * This constructor exists so this class may be used with the Swing GUI Designer.
     */
    public MapPanel()
    {
    }

    /**
     * Sets the map model that this view displays.
     * @param map the map model to display
     */
    public void setMap(Map map)
    {
        assert map != null;
        _map = map;

        this.addMouseListener(new MapPanelMouseAdapter());
    }

    /**
     * Selects a unit or tile at the specified location.
     * @param l the location at which to select a unit or tile
     */
    public void select(Location l)
    {
        Unit unit = _map.getUnitAt(l);
        Tile tile = _map.getTileAt(l);

        if ((_selectionMode & SELECT_FRIENDLY_UNIT_MODE) != 0
            && unit != null
            && unit.getPlayer().isCPU() == false)
        {
            _selectedFriendlyUnit = (_selectedFriendlyUnit != unit)
                ? unit
                : null;
        }
        else if ((_selectionMode & SELECT_ENEMY_UNIT_MODE) != 0
            && unit != null
            && unit.getPlayer().isCPU())
        {
            _selectedEnemyUnit = (_selectedEnemyUnit != unit)
                ? unit
                : null;
        }
        else if ((_selectionMode & SELECT_TILE_MODE) != 0
            && tile != null)
        {
            _selectedTile = (_selectedTile != tile)
                ? tile
                : null;
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // For drawing in the Swing Form Designer
        if (_map == null)
        {
            return;
        }

        final int height = _map.getHeight();
        final int width = _map.getWidth();
        final int spritePx = getSpriteSize();

        final Tile[][] tiles = _map.getTiles();
        final Set<Unit> units = _map.getUnits();

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
        if (_selectedTile != null)
        {
            Location l = _selectedTile.getLocation();
            g.drawImage(Sprites.SELECTED_TILE, spritePx * l.x, spritePx * l.y, spritePx, spritePx, this);
        }

        if (_selectedFriendlyUnit != null)
        {
            Location l = _selectedFriendlyUnit.getLocation();
            g.drawImage(Sprites.SELECTED_UNIT, spritePx * l.x, spritePx * l.y, spritePx, spritePx, this);
        }

        if (_selectedEnemyUnit != null)
        {
            Location l = _selectedEnemyUnit.getLocation();
            g.drawImage(Sprites.SELECTED_ENEMY_UNIT, spritePx * l.x, spritePx * l.y, spritePx, spritePx, this);
        }
    }

    /**
     * Calculates the size (in pixels) for the width and height of a tile.
     * @return the size (in pixels) for the width and height of a tile
     */
    private int getSpriteSize()
    {
        return Math.min(
            (int) (getWidth() / _map.getWidth()),
            (int) (getHeight() / _map.getHeight()));
    }

    /**
     * Converts a coordinate from pixels to a location on the map model.
     * @param x the x-position relative to the source component
     * @param y the y-position relative to the source component
     * @return the map model location in which the provided pixel coordinate lay
     */
    private Location toLocation(int x, int y)
    {
        final int spriteSize = getSpriteSize();
        return new Location(
            (int) (x / spriteSize),
            (int) (y / spriteSize));
    }

    /**
     * Defines the response of a MapPanel to mouse events.
     */
    private final class MapPanelMouseAdapter extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            // Calculate map model location and select unit/tile there
            select(getLocation(e));
            repaint();
        }

        /**
         * Gets the coordinate at which a mouse event occurred and
         * converts it from pixels to a location on the map model.
         * @param e the mouse event
         * @return the map model location at which the mouse event occurred
         */
        private Location getLocation(MouseEvent e)
        {
            return toLocation(e.getX(), e.getY());
        }
    }

    /**
     * Sets the current selection mode flags.
     * @param selectionMode the new selection mode flags
     */
    public final void setSelectionMode(int selectionMode)
    {
        _selectionMode = selectionMode;
    }

    /**
     * Returns the currently selected tile.
     * @return the currently selected tile, if one exists; null, otherwise
     */
    public final Tile getSelectedTile()
    {
        return _selectedTile;
    }

    /**
     * Returns the currently selected friendly unit.
     * @return the currently selected friendly unit, if one exists; null, otherwise
     */
    public final Unit getSelectedFriendlyUnit()
    {
        return _selectedFriendlyUnit;
    }

    /**
     * Returns the currently selected enemy unit.
     * @return the currently selected enemy unit, if one exists; null, otherwise
     */
    public final Unit getSelectedEnemyUnit()
    {
        return _selectedEnemyUnit;
    }
}
