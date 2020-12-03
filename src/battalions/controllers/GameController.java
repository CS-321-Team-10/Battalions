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

import battalions.models.Game;
import battalions.models.MapSelector;
import battalions.views.GameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Scott
 */
public class GameController implements PropertyChangeListener
{
    /**
     * The game model.
     */
    private final Game model;

    /**
     * The game view.
     */
    private final GameView view;

    /**
     * Initializes a new instance of the GameController class.
     * @param model the game model
     * @param view the game view
     */
    public GameController(Game model, GameView view)
    {
        assert model instanceof Game;
        assert view instanceof GameView;

        this.model = model;
        this.view = view;

        this.model.getMapSelector().addPropertyChangeListener(this.model);
        this.model.getMapSelector().addPropertyChangeListener(this);

        this.view.addEndTurnButtonListener(new EndTurnActionListener());

        update();
    }

    /**
     * Updates info on the view.
     */
    public final void update()
    {
        MapSelector mapSelector = model.getMapSelector();
        view.setHighlightedTileInfo(mapSelector.getHighlightedTile());
        view.setHighlightedUnit(mapSelector.getHighlightedUnit());
        view.setSelectedUnit(mapSelector.getSelectedUnit());

        view.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        update();
    }

    /**
     * Ends the current turn when End Turn button is clicked.
     */
    private class EndTurnActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            model.nextTurn();
            update();
        }
    }

    /**
     * Restarts the game when the Restart button is clicked.
     */
    private class RestartActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {

        }
    }

    /**
     * Loads a save file when the Load button is clicked.
     */
    private class LoadActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {

        }
    }

    /**
     * Saves to a save file when the Load button is clicked.
     */
    private class SaveActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {

        }
    }
}
