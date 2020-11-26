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

import battalions.models.Map;
import battalions.views.MapView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        _view.setMap(_model);
    }

    private class UpdateListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
        }
    }

}
