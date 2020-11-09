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
package battalions.models;

/**
 * A single square on the map.
 * @author Scott
 * @author Bryant
 */
public class Tile implements IMapItem
{
    /**
     * Contains bit-flags for tile effects.
     */
    public class Effects
    {
        /**
         * This tile has no special effects on units.
         */
        public static final int NONE = 0;
        
        /**
        * This tile prevents units from standing on or crossing it.
        */
       public static final int IMPASSABLE = 1;

       /**
        * This tile boosts a unit's chance of dodging an attack.
        */
       public static final int BOOSTS_DODGE = 1 << 1;

       /**
        * This tile reduces a unit's movement range.
        */
       public static final int REDUCES_MOVEMENT = 1 << 2;
    }

    // Adding an enum for the type of a tile, used for determining its sprite
    public enum tileType
    {
        field, forest, wallHoriz, wallVert, wallCornerNW, wallCornerNE, wallCornerSW, wallCornerSE;
    }
    
    /**
     * The map in which this tile resides.
     */
    private final Map _map;

    /**
     * The location of this tile on the map.
     */
    private final Location _location;

    /**
     * The effects this tile has when a unit stands on it.
     */
    private final int _effects;

    // The type of the tile on the map
    private final tileType _type;
    
    /**
     * Initializes a new instance of the Tile class.
     * @param map the map in which this tile resides
     * @param l the initial location for this tile
     * @param effectFlags the effect bit-flags for this tile
     */
    public Tile(Map map, Location l, int effectFlags, tileType type)
    {
        assert map != null;
        assert map.inBounds(l);
        
        _type = type;
        _map = map;
        _location = l;
        _effects = effectFlags;
    }
    
    @Override
    public final Map getMap()
    {
        return _map;
    }
    
    @Override
    public final Location getLocation()
    {
        return _location;
    }

    // A simple return method for getting tile type
    public final tileType getTileType()
    {
        return _type;
    }
    
    /**
     * Returns whether or not this tile is impassable.
     * @return true, if this tile is impassable; false, otherwise
     */
    public boolean isImpassable()
    {
        return (_effects & Effects.IMPASSABLE) == Effects.IMPASSABLE;
    }

    /**
     * Returns whether or not this tile boosts a unit's chance of dodging an attack.
     * @return true, if this tile boosts dodge chance; false, otherwise
     */
    public boolean boostsDodge()
    {
        return (_effects & Effects.BOOSTS_DODGE) == Effects.BOOSTS_DODGE;
    }

    /**
     * Returns whether or not this tile reduces a unit's movement range.
     * @return true, if this tile reduces movement range; false, otherwise
     */
    public boolean reducesMovement()
    {
        return (_effects & Effects.REDUCES_MOVEMENT) == Effects.REDUCES_MOVEMENT;
    }

    @Override
    public String toString()
    {
        return "Tile " + _location.toString()
            + (isImpassable() ? " [WALL]" : "")
            + (boostsDodge() ? " [+DODGE]" : "")
            + (reducesMovement() ? " [-MOVE]" : "");
    }
}
