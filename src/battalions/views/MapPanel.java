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
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 * A panel that draws a 2D array of tiles.
 * @author Scott
 */
public class MapPanel extends JPanel
{
    /**
     * The map to be drawn by this panel.
     */
    private Map _map;

    /**
     * The tile that is currently highlighted as selected.
     */
    private TileComponent _selectedTile;

    /**
     * The unit that is currently highlighted as selected.
     */
    private UnitComponent _selectedUnit;

    /**
     * Initializes a new instance of the MapPanel class.
     * This empty constructor is present to allow this class to be used in the Swing Designer.
     */
    public MapPanel()
    {
    }

    /**
     * Sets the map to be drawn by this panel.
     * @param map the map to be drawn by this panel
     */
    public void setMap(Map map)
    {
        assert map != null;
        _map = map;

        draw();
    }

    /**
     * Selects the specified tile and deselects the previously selected tile.
     * @param tc the new tile to select
     */
    public void selectTile(TileComponent tc)
    {
        if (tc == _selectedTile)
        {
            // Toggle selection if already selected
            _selectedTile.setIsSelected(_selectedTile.isSelected() ^ true);
            _selectedTile = null;
        }
        else
        {
            if (_selectedTile != null)
            {
                // Deselect old tile
                _selectedTile.setIsSelected(false);
            }

            // Select new tile
            _selectedTile = tc;
            _selectedTile.setIsSelected(true);
        }
    }

    public void selectUnit(UnitComponent uc)
    {
        if (uc == _selectedUnit)
        {
            // Toggle selection if already selected
            _selectedUnit.setIsSelected(_selectedUnit.isSelected() ^ true);
            _selectedUnit = null;
        }
        else
        {
            if (_selectedUnit != null)
            {
                // Deselect old unit
                _selectedUnit.setIsSelected(false);
            }

            // Select new unit
            _selectedUnit = uc;
            _selectedUnit.setIsSelected(true);
        }
    }

    /**
     * When an event is triggered by a subcomponent of this MapPanel, it may
     * use this method to dispatch the event up the chain.
     * @param e the event to dispatch
     */
    private void passEvent(InputEvent e)
    {
        this.dispatchEvent(e);
    }

    /**
     * Draws this component.
     */
    private void draw()
    {
        removeAll();
        setLayout(new FlowLayout());

        final int y = _map.getHeight();
        final int x = _map.getWidth();

        Tile[][] tiles = _map.getTiles();

        TileComponent[][] tileComponents = new TileComponent[y][x];
        UnitComponent[][] unitComponents = new UnitComponent[y][x];

        // Create sprites for all tiles
        for (int i = 0; i < y; i++)
        {
            for (int j = 0; j < x; j++)
            {
                tileComponents[i][j] = newTileSprite(tiles[i][j]);
            }
        }

        // Create sprites for all units
        _map.getUnits()
            .forEach(u ->
            {
                Location l = u.getLocation();
                unitComponents[l.y][l.x] = newUnitSprite(u);
            });

        JPanel tileGrid = new GridPanel<>(tileComponents);
        JPanel unitGrid = new GridPanel<>(unitComponents);

        add(tileGrid);
        add(unitGrid);

        revalidate();
    }

    /**
     * Creates a new tile sprite with a mouse listener.
     * @param t the tile model for the sprite
     * @return a new tile sprite with a mouse listener
     */
    private TileComponent newTileSprite(Tile t)
    {
        TileComponent tc = new TileComponent(t);
        tc.addMouseListener(
            new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    super.mouseClicked(e);

                    // When a tile is clicked, update the parent MapPanel
                    selectTile(tc);
                    passEvent(e);
                }
            });
        return tc;
    }

    /**
     * Creates a new unit sprite with a mouse listener.
     * @param u the unit model for the sprite
     * @return a new unit sprite with a mouse listener
     */
    private UnitComponent newUnitSprite(Unit u)
    {
        UnitComponent uc = new UnitComponent(u);
        uc.addMouseListener(
            new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    super.mouseClicked(e);

                    // When a unit is clicked, update the parent MapPanel
                    selectUnit(uc);
                    passEvent(e);
                }
            });
        return uc;
    }

    /**
     * A panel that can display components in a grid with no overlap.
     * @param <T> the type of component to display
     */
    private class GridPanel<T extends Component> extends JPanel
    {
        /**
         * Initializes a new instance of the GridPanel class.
         * @param array the components to be displayed in the grid
         */
        public GridPanel(T[][] array)
        {
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(0, 0, 0, 0);
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.fill = GridBagConstraints.BOTH;

            for (int y = 0; y < array[0].length; y++)
            {
                // Set y-location in layout
                gbc.gridy = y;

                for (int x = 0; x < array.length; x++)
                {
                    // Set x-location in layout
                    gbc.gridx = x;

                    T element = array[y][x];
                    if (element != null)
                    {
                        // Add current element
                        add(element, gbc);
                    }
                }
            }
        }
    }

    /**
     * Returns the tile that is currently selected.
     * @return the tile that is currently selected, if one is selected; null, otherwise
     */
    public final Tile getSelectedTile()
    {
        return (_selectedTile == null)
            ? null
            : _selectedTile.getTile();
    }

    /**
     * Returns the unit that is currently selected.
     * @return the unit that is currently selected, if one is selected; null, otherwise
     */
    public final Unit getSelectedUnit()
    {
        return (_selectedUnit == null)
            ? null
            : _selectedUnit.getUnit();
    }
}