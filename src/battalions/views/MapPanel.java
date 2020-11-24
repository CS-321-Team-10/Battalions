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
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Predicate;
import javax.swing.JPanel;

/**
 * A panel that draws a 2D array of tiles.
 * @author Scott
 */
public class MapPanel extends JPanel
{
    /**
     * The tiles to be drawn by this panel.
     */
    private Tile[][] _tiles;

    /**
     * The tile that is currently highlighted as selected.
     */
    private TileComponent _selectedTile;

    /**
     * The number of rows in this panel's 2D array of tiles.
     */
    private int _rows;

    /**
     * The number of columns in this panel's 2D array of tiles.
     */
    private int _cols;

    /**
     * Initializes a new instance of the MapPanel class.
     * This empty constructor is present to allow this class to be used in the Swing Designer.
     */
    public MapPanel()
    {
    }

    /**
     * Sets the 2D array of tiles to be drawn by this panel.
     * @param tiles the tiles to be drawn by this panel
     */
    public void drawTiles(Tile[][] tiles)
    {
        assert tiles != null;

        final int rows = tiles.length;
        assert rows > 0;

        final int cols = tiles[0].length;
        assert cols > 0;

        _tiles = tiles;
        _rows = rows;
        _cols = cols;

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
            _selectedTile.setIsSelected(_selectedTile.isSelected() ^ true);
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

        // Inside inner anonymous panel, so that outer panel can stretch
        //  without stretching the inner panel and creating gaps between tiles.
        // There should be a better way to deal with resizing, but I haven't
        //  figured it out yet.
        JPanel innerPanel =
            new JPanel()
            {
                { /* Anonymous Constructor */
                    setLayout(new GridBagLayout());

                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(0, 0, 0, 0);
                    gbc.weightx = 1;
                    gbc.weighty = 1;
                    gbc.fill = GridBagConstraints.BOTH;

                    for (int y = 0; y < _tiles[0].length; y++)
                    {
                        // Set Y location in layout
                        gbc.gridy = y;

                        for (int x = 0; x < _tiles.length; x++)
                        {
                            // Set X location in layout
                            gbc.gridx = x;

                            // Add current tile
                            TileComponent tc = new TileComponent(_tiles[y][x]);
                            tc.addMouseListener(
                                new MouseAdapter()
                                {
                                    @Override
                                    public void mouseClicked(MouseEvent e)
                                    {
                                        // When a tile is clicked, update the parent MapPanel
                                        super.mouseClicked(e);
                                        selectTile(tc);
                                        passEvent(e);
                                    }
                                });
                            add(tc, gbc);
                        }
                    }
                }
            };
        add(innerPanel);

        revalidate();
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
}