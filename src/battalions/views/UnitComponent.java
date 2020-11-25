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

import battalions.models.Unit;
import java.awt.Image;

/**
 * A component that draws a unit sprite.
 * @author Scott
 */
public class UnitComponent extends SpriteComponent
{
    /**
     * The overlay image that indicates that a unit is selected.
     */
    private static final Image SELECTED_IMAGE;

    /**
     * The unit model that this component draws.
     */
    private final Unit _unit;

    /**
     * The image to draw for this unit.
     */
    private final Image _image;

    static
    {
        SELECTED_IMAGE = readImage("src/images/ui/", "SelectUnit.png");
        assert SELECTED_IMAGE != null;
    }

    /**
     * Initializes a new instance of the UnitComponent class.
     * @param unit the unit to draw
     */
    public UnitComponent(Unit unit)
    {
        assert unit != null;
        _unit = unit;

        // Assign image once to prevent re-calculating random
        //  variations upon each redraw.
        _image = getUnitImage(unit);
        assert _image != null;
    }

    /**
     * Returns an image with the sprite corresponding to the specified unit.
     * @param t the unit whose image to return
     * @return the specified unit sprite as an Image
     */
    private static Image getUnitImage(Unit u)
    {
        // Determine whether CPU or human player
        String fileName = (u.getPlayer().isCPU())
            ? "Enemy"
            : "Friendly";

        // Determine the given unit's type, then sets the appropriate PNG path
        switch (u.getType())
        {
            case Archer:
                fileName += "Infantry.png";
                break;
            case Healer:
                fileName += "Healer.png";
                break;
            case Infantry:
                fileName += "Infantry.png";
                break;
            case Knight:
                fileName += "Knight.png";
                break;
            case Mage:
                fileName += "Mage.png";
                break;
            case Warlock:
                fileName += "Warlock.png";
                break;
            default:
                // If you end up here, it's probably a code error:
                //  e.g. a switch is missing a case
                assert false;
        }

        return readImage("src/images/units/", fileName);
    }

    @Override
    protected Image getImage()
    {
        return _image;
    }

    @Override
    protected Image getSelectedImage()
    {
        return SELECTED_IMAGE;
    }

    /**
     * Returns the unit model that this component draws.
     * @return the unit model that this component draws
     */
    public final Unit getUnit()
    {
        return _unit;
    }
}