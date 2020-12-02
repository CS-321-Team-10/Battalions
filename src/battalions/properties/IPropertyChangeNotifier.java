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
package battalions.properties;

import java.beans.PropertyChangeListener;

/**
 * Notifies listeners when property values have changed.
 * @author Scott
 */
public interface IPropertyChangeNotifier
{
    /**
     * Adds a new listener to be notified when any property value has changed.
     * @param listener the new listener to add
     */
    void addPropertyChangeListener(PropertyChangeListener listener);

    /**
     * Removes a listener, who will no longer be notified when
     * any property value has changed.
     * @param listener the listener to remove
     */
    void removePropertyChangeListener(PropertyChangeListener listener);
}
