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
package battalions.controllers;

import battalions.data.Location;
import battalions.models.Map;
import battalions.models.MapSelector;
import battalions.models.Unit;
import battalions.views.MapSelectorView;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Controller that delegates between a map model and view.
 * @author Scott
 */
public class MapController implements PropertyChangeListener
{
    /**
     * The map selector model that wraps a map with selection and highlight abilities.
     */
    private final MapSelector model;

    /**
     * The map selector view.
     */
    private final MapSelectorView view;

    /**
     * Initializes new instance of the MapController class.
     * @param model the map selector model that wraps a map with
     * selection and highlight abilities
     * @param view the map selector view
     */
    public MapController(MapSelector model, MapSelectorView view)
    {
        assert model instanceof MapSelector;
        assert view instanceof MapSelectorView;

        this.model = model;
        this.view = view;

        Map map = model.getMap();

        // Map and selector will notify view of certain property changes
        map.addPropertyChangeListener(view);
        model.addPropertyChangeListener(view);

        // Map and selector will notify this controller to repaint view upon
        // property changes
        map.addPropertyChangeListener(this);
        model.addPropertyChangeListener(this);

        MapSelectorViewMouseListener mouseListener = new MapSelectorViewMouseListener();
        view.addMouseListener(mouseListener);
        view.addMouseMotionListener(mouseListener);
        view.addComponentListener(new MapSelectorViewComponentListener());
    }

    /**
     * Prepares each layer of the view, then paints it to the screen.
     */
    public void draw()
    {
        Map map = model.getMap();

        // Prepare all layers
        view.drawTiles(map.getTiles());
        view.drawUnits(map.getUnits());
        view.drawHighlighted(model.getHighlightedTile());
        view.drawSelectableUnits(model.getSelectableUnits());

        Unit selectedUnit = model.getSelectedUnit();
        Unit highlightedUnit = model.getHighlightedUnit();
        boolean emptySelection = selectedUnit == null;
        boolean emptyHighlighted = highlightedUnit == null;

        view.drawCursorLocation(model.getCursorLocation(), emptyHighlighted
            ? null
            : model.getCurrentPlayer().owns(highlightedUnit));

        view.drawHighlighted(highlightedUnit, false, emptySelection, emptySelection, false);
        view.drawSelected(model.getSelectedUnit(), true, true, true, false);

        // Draw layers
        view.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        // Whenever the model updates, draw the view
        draw();
    }

    /**
     * A listener for mouse clicks and movements to update the map view.
     */
    private class MapSelectorViewMouseListener extends MouseAdapter implements MouseMotionListener
    {
        /**
         * The last mouse press event, used to treat a press + release as a click.
         */
        private MouseEvent pressedEvent;

        /**
         * Converts a mouse event's position to a map location.
         * @param e the mouse event whose location to convert
         * @return the a map location in which the mouse event occurred
         */
        private Location toLocation(MouseEvent e)
        {
            return view.toLocation(e.getX(), e.getY());
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            // Calculate map model location and select unit/tile there
            Location clicked = toLocation(e);
            model.select(clicked);
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            // Record mouse press
            pressedEvent = e;
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            Location pressed = toLocation(pressedEvent);
            Location released = toLocation(e);

            // If pressed + released on the same map location, but is not
            //  automatically counted as a mouse click, manually invoke a
            //  mouse click event for that location
            if (released.equals(pressed)
                && (e.getX() != pressedEvent.getX() || e.getY() != pressedEvent.getY()))
            {
                mouseClicked(e);
            }
        }

        @Override
        public void mouseDragged(MouseEvent e)
        {
            Location dragged = toLocation(e);
            model.setCursorLocation(dragged);

            view.setCursorLocation(e.getX(), e.getY());
            draw();
        }

        @Override
        public void mouseMoved(MouseEvent e)
        {
            Location moved = toLocation(e);
            model.setCursorLocation(moved);

            view.setCursorLocation(e.getX(), e.getY());
            draw();
        }
    }

    /**
     * A listener to repaint the map view on resize events.
     */
    private class MapSelectorViewComponentListener extends ComponentAdapter
    {
        @Override
        public void componentResized(ComponentEvent e)
        {
            view.onResize();
            draw();
        }
    }
}
