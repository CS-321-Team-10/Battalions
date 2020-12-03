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

import battalions.App;
import battalions.models.Game;
import battalions.models.MapSelector;
import battalions.models.Player;
import battalions.models.SaveSystem;
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

        // Map selector model will update game model and game controller
        //  about properties
        this.model.getMapSelector().addPropertyChangeListener(this.model);
        this.model.getMapSelector().addPropertyChangeListener(this);

        // Game model will update this controller about properties
        this.model.addPropertyChangeListener(this);

        this.view.addEndTurnButtonListener(new EndTurnActionListener());
        this.view.addSaveButtonListener(new SaveActionListener());
        this.view.addLoadButtonListener(new LoadActionListener());
        this.view.addRestartButtonListener(new RestartActionListener());

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
        Object source = evt.getSource();
        String propertyName = evt.getPropertyName();

        update();

        if (source instanceof Game)
        {
            switch (propertyName)
            {
                case (Game.WINNER_PROPERTY):
                    // When a player has just won
                    Player winner = model.getWinner();
                    if (winner instanceof Player)
                    {
                        view.setWinner(winner);
                    }
                    break;
            }
        }
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
            App.main(new String[0]);
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
            SaveSystem.load(model);
            update();
            model.getMapSelector().deselect();
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
            SaveSystem.save(model);
            update();
        }
    }
}
