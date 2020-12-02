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
import battalions.views.MapSelectorView;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Scott
 */
public class MapSelectorController implements PropertyChangeListener
{
    private final MapSelector model;

    private final MapSelectorView view;

    public MapSelectorController(MapSelector model, MapSelectorView view)
    {
        assert model instanceof MapSelector;
        assert view instanceof MapSelectorView;

        this.model = model;
        this.view = view;

        Map map = model.getMap();

        map.addPropertyChangeListener(view);
        model.addPropertyChangeListener(view);

        map.addPropertyChangeListener(this);
        model.addPropertyChangeListener(this);

        MapSelectorViewMouseListener mouseListener = new MapSelectorViewMouseListener();
        view.addMouseListener(mouseListener);
        view.addMouseMotionListener(mouseListener);
        view.addKeyListener(new MapSelectorViewKeyListener());
        view.addComponentListener(new MapSelectorViewComponentListener());
    }

    private void draw()
    {
        Map map = model.getMap();

        view.drawTiles(map.getTiles());
        view.drawHighlighted(model.getHighlightedTile());
        view.drawUnits(map.getUnits());
        view.drawSelectableUnits(model.getSelectableUnits());

        boolean drawHighlighted = model.getSelectedUnit() == null;
        view.drawHighlighted(model.getHighlightedUnit(), true, drawHighlighted, drawHighlighted, drawHighlighted);
        view.drawSelected(model.getSelectedUnit(), true, true, true, true);
        view.drawCursor(model.getCursorLocation());

        view.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        // Whenever the model updates, draw the view
        draw();
    }

    private class MapSelectorViewMouseListener implements MouseListener, MouseMotionListener
    {
        private MouseEvent pressedEvent;

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
            pressedEvent = e;
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            Location pressed = toLocation(pressedEvent);
            Location released = toLocation(e);

            if (released.equals(pressed)
                && (e.getX() != pressedEvent.getX() || e.getY() != pressedEvent.getY()))
            {
                mouseClicked(e);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
        }

        @Override
        public void mouseDragged(MouseEvent e)
        {
            Location dragged = toLocation(e);
            model.setCursorLocation(dragged);
        }

        @Override
        public void mouseMoved(MouseEvent e)
        {
            Location moved = toLocation(e);
            model.setCursorLocation(moved);
        }
    }

    private class MapSelectorViewKeyListener extends KeyAdapter
    {
        @Override
        public void keyTyped(KeyEvent e)
        {
            super.keyTyped(e);

            switch (e.getKeyCode())
            {
                case (KeyEvent.VK_ESCAPE):
                    model.deselect();
                    break;
            }
        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            super.keyPressed(e);

            switch (e.getKeyCode())
            {
                case (KeyEvent.VK_ESCAPE):
                    model.deselect();
                    break;
            }
        }
    }

    private class MapSelectorViewComponentListener extends ComponentAdapter
    {
        @Override
        public void componentResized(ComponentEvent e)
        {
            super.componentResized(e);

            view.onResize();
            draw();
        }
    }
}
