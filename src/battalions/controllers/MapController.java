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

import battalions.models.Location;
import battalions.models.Map;
import battalions.models.Tile.tileType;
import battalions.views.MapView;
import java.awt.Color;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Scott
 */
public class MapController
{
    private final Map _model;
    private final MapView _view;

    public MapController(Map model, MapView view)
    {
        _model = model;
        _view = view;

        _view.addUpdateButtonListener((ActionListener) new UpdateListener());
    }

    private class UpdateListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            _view.setMapText(_model.toString());

            // A test method to draw a panel
            _view.drawATestPanel(Color.red, 32, 32, 10, 10);
            _view.drawATestPanel(Color.blue, 50, 50, 40, 40);

            // Tries to display the map from the passed model whenever the update button is pressed
            try
            {
                _view.displayMap(_model);
            }
            catch (IOException ex)
            {
                System.out.println("Map unable to draw" + ex);
            return;
            }

        }
    }

}
