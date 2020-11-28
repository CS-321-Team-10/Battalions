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
import battalions.models.Game;
import battalions.models.Map;
import battalions.models.MapOverlay;
import battalions.models.Tile;
import battalions.models.Unit;
import battalions.views.MapPanel;
import battalions.views.MapView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Scott
 */
public class MapController
{
    private final Game _game;
    private final Map _map;
    private final MapOverlay _overlay;

    private final MapView _view;
    private final MapPanel _mapPanel;

    public MapController(Game game, Map map, MapView view)
    {
        assert game != null;
        assert map != null;
        assert view != null;

        _game = game;
        _map = map;
        _overlay = new MapOverlay(map);

        _view = view;
        _mapPanel = _view.getMapPanel();

        _view.setMap(_overlay);

        _view.addMapMouseListener(new MapPanelMouseAdapter());
        _view.addMoveButtonListener(new MoveListener());
        _view.addAttackButtonListener(new AttackListener());
        _view.addEndTurnButtonListener(new EndTurnListener());

        update();
    }

    private void update()
    {
        _mapPanel.repaint();

        _view.showSelectedTile(_overlay.getSelectedTile());
        _view.showSelectedFriendlyUnit(_overlay.getSelectedFriendlyUnit());
        _view.showSelectedEnemyUnit(_overlay.getSelectedEnemyUnit());

        Tile tile = _overlay.getSelectedTile();
        Unit unit = _overlay.getSelectedFriendlyUnit();
        Unit enemy = _overlay.getSelectedEnemyUnit();

        _view.setMoveButtonEnabled(
            unit != null && tile != null
            && unit.canMoveTo(tile.getLocation()));
        _view.setAttackButtonEnabled(
            unit != null && enemy != null
            && unit.canAttack(enemy));
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
            Location l = getLocation(e);
            if (_map.inBounds(l))
            {
                _overlay.toggleSelect(l);
            }

            update();
        }

        /**
         * Gets the coordinate at which a mouse event occurred and
         * converts it from pixels to a location on the map model.
         * @param e the mouse event
         * @return the map model location at which the mouse event occurred
         */
        private Location getLocation(MouseEvent e)
        {
            return _mapPanel.toLocation(e.getX(), e.getY());
        }
    }

    private class MoveListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Unit unit = _overlay.getSelectedFriendlyUnit();
            Tile destination = _overlay.getSelectedTile();

            if (unit != null && destination != null)
            {
                unit.tryMoveTo(destination.getLocation());
            }

            update();
        }
    }

    private class AttackListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Unit attacker = _overlay.getSelectedFriendlyUnit();
            Unit enemy = _overlay.getSelectedEnemyUnit();

            if (attacker != null && enemy != null)
            {
                attacker.tryAttack(enemy);

                // If enemy dies, deselect it
                if (enemy.isAlive() == false)
                {
                    _overlay.selectEnemyUnit(null);
                }
            }

            update();
        }
    }

    private class EndTurnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            _overlay.clearSelection();
            _game.nextTurn();

            update();
        }
    }
}
