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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * An adapter class that implements basic property change notifications.
 * @author Scott
 */
public abstract class PropertyChangeNotifier implements IPropertyChangeNotifier
{
    /**
     * The support object for firing property change notifications.
     */
    protected final PropertyChangeSupport propertyChangeSupport;

    /**
     * The getters for the registered properties.
     */
    protected final Map<String, Supplier<Object>> propertyGetters;

    /**
     * Initializes a new instance of the PropertyChangeNotifier class.
     */
    public PropertyChangeNotifier()
    {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        this.propertyGetters = new HashMap<>();
    }

    /**
     * Registers a property with this property change notifier.
     * @param propertyName the name of the property
     * @param getter the getter function for the property
     */
    protected final void registerProperty(String propertyName, Supplier<Object> getter)
    {
        assert propertyName instanceof String;
        assert getter instanceof Map;

        this.propertyGetters.putIfAbsent(propertyName, getter);
        this.propertyChangeSupport.firePropertyChange(propertyName, null, getter.get());
    }

    /**
     * Fires a property change event for the specified property.
     * @param propertyName the name of the property
     */
    protected void firePropertyChange(String propertyName)
    {
        assert this.propertyGetters.containsKey(propertyName);

        Object value = this.propertyGetters.get(propertyName).get();
        this.propertyChangeSupport.firePropertyChange(propertyName, null, value);
    }

    /**
     * Fires all property change events to a listener.
     * @param listener the listener to receive the events
     */
    private void firePropertyChanges(PropertyChangeListener listener)
    {
        this.propertyGetters.entrySet().forEach(entry ->
        {
            String propertyName = entry.getKey();
            Object value = entry.getValue().get();
            listener.propertyChange(new PropertyChangeEvent(this, propertyName, null, value));
        });
    }

    @Override
    public final void addPropertyChangeListener(PropertyChangeListener listener)
    {
        this.propertyChangeSupport.addPropertyChangeListener(listener);

        // Fire all property changes to give listener initial values
        firePropertyChanges(listener);
    }

    @Override
    public final void removePropertyChangeListener(PropertyChangeListener listener)
    {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
