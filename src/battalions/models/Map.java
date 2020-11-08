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

import java.util.ArrayList;

/**
 * Contains a grid of tiles.
 * @author Bryant
 * @author Scott
 */
public class Map
{
    /**
     * The number of tiles along the direction of the x-axis.
     */
    private final int _width;

    /**
     * The number of tiles along the direction of the y-axis.
     */
    private final int _height;

    /**
     * The grid of tiles that composes this map.
     */
    private final Tile[][] _tiles;

    /**
     * The list of player units on the map.
     */
    private final ArrayList<Unit> _units;

    /**
     * Initializes a new instance of the Map class.
     * @param width the width for this map
     * @param height the height for this map
     */
    public Map(int width, int height)
    {
        assert width > 0;
        assert height > 0;

        _width = width;
        _height = height;

        _tiles = new Tile[_width][_height];
        for (int x = 0; x < _width; x++)
        {
            for (int y = 0; y < _height; y++)
            {
                _tiles[x][y] = new Tile(x, y, false, false, false);
            }
        }

        _units = new ArrayList<>();
    }

    /**
     * Returns the width of this map.
     * @return the number of tiles along the direction of the x-axis
     */
    public int getWidth()
    {
        return _width;
    }

    /**
     * Returns the height of this map.
     * @return the number of tiles along the direction of the y-axis
     */
    public int getHeight()
    {
        return _height;
    }

    /**
     * Returns the tile positioned at the specified (x, y) coordinate on this map.
     * @param x the x-coordinate of the desired tile
     * @param y the y-coordinate of the desired tile
     * @return the tile at the specified coordinate
     */
    public Tile getTileAt(int x, int y)
    {
        assert x >= 0 && x < _width;
        assert y >= 0 && y < _height;

        return _tiles[x][y];
    }

    /**
     * Replaces an existing tile with the specified tile.
     * @param tile the new tile
     */
    public void addTile(Tile tile)
    {
        assert tile != null;

        int x = tile.getX();
        int y = tile.getY();

        // Cannot place wall on top of unit
        assert getUnitAt(x, y) == null;

        _tiles[x][y] = tile;
    }

    /**
     * Returns the tile on which the specified unit is positioned.
     * @param unit the unit whose tile to return
     * @return the tile at the same (x, y) coordinate of the specified unit
     */
    public Tile getTileUnder(Unit unit)
    {
        return getTileAt(unit.getX(), unit.getY());
    }

    /**
     * Returns the unit at the specified (x, y) coordinate, if one exists.
     * @param x the x-coordinate to check for a unit
     * @param y the y-coordinate to check for a unit
     * @return the unit at (x, y), if one exists; null, otherwise
     */
    public Unit getUnitAt(int x, int y)
    {
        // TODO [re-structure]
        // Improve this implementation by re-structuring how units are stored

        for (Unit unit : _units)
        {
            if (unit.getX() == x && unit.getY() == y)
            {
                return unit;
            }
        }

        return null;
    }

    /**
     * Adds the specified unit to the map.
     * @param unit the unit to add to the map
     */
    public void addUnit(Unit unit)
    {
        // TODO [re-structure]
        // This method signature should be altered, maybe to include (x, y) params

        // Cannot add unit on top of wall
        assert getTileAt(unit.getX(), unit.getY()).isWall() == false;

        _units.add(unit);
    }
    
    /**
     *  Allows the unit to move across the map. This the actual calculation that
     *  should be performed after the program checks to ensure that the unit
     *  is being moved to an empty tile, within its movement range.
     * @param old_x the x-coordinate of the unit being moved.
     * @param old_y the y-coordinate of the unit being moved.
     * @param new_x the x-coordinate the unit is being moved to.
     * @param new_y the y-coordinate the unit is being moved to.
     */
    public void moveUnit(int old_x, int old_y, int new_x, int new_y)
    {   
        for (Unit unit : _units)
        {
            if (unit.getX() == old_x && unit.getY() == old_y)
            {
                unit.setX(new_x);
                unit.setY(new_y);
                unit.sethasMoved(true);
            }
        }
    }
    
    /**
     * Performs calculations and checks to determine whether or not the 
     * new coordinates for a Unit being moved are valid. Checks the Unit's
     * movement stat, and checks the new Tile for Units and walls.
     * Returns true if the new coordinates are valid, and otherwise returns
     * false.
     * @param old_x the x-coordinate of the unit being moved.
     * @param old_y the y-coordinate of the unit being moved.
     * @param new_x the x-coordinate the unit is being moved to.
     * @param new_y the y-coordinate the unit is being moved to.
     */
    public boolean moveUnitCalculations(int old_x, int old_y, int new_x, int new_y)
    {
        // TODO [re-structure]
        // Improve this implementation by re-working movement calculations
        
        // Get absolute value of the distance between new and old coordinates
        int x_difference = Math.abs(old_x - new_x);
        int y_difference = Math.abs(old_y - new_y);
        if(getUnitAt(old_x, old_y).getMovement() < (x_difference + y_difference))
        {
            return false;
        }
        
        if(getUnitAt(old_x, old_y).gethasMoved() || getUnitAt(new_x, new_y) != null || getTileAt(new_x, new_y).isWall())
        {
            return false;
        }
        
        moveUnit(old_x, old_y, new_x, new_y);
        return true;
    }
    
    /**
     * Allows a unit to attack another unit. Checks to ensure that the unit
     * hasn't already attacked, and that they're actually attacking another
     * unit.
     * @param old_x the x-coordinate of the attacking unit.
     * @param old_y the y-coordinate of the attacking unit.
     * @param new_x the x-coordinate the unit is being attacked.
     * @param new_y the y-coordinate the unit is being attacked.
     */
    public boolean runAttackSequence(int old_x, int old_y, int new_x, int new_y, boolean normal_attack)
    {
        // TODO [re-structure]
        // Improve this implementation by re-working range calculations
        
        int x_difference = Math.abs(old_x - new_x);
        int y_difference = Math.abs(old_y - new_y);
        
        // End the function if the targeted unit is outside of range,
        if((getUnitAt(old_x, old_y).getRange() < (x_difference + y_difference)))
        {
            return false;
        }
        
        if(!getUnitAt(old_x, old_y).gethasActed() && getUnitAt(new_x, new_y) != null && getUnitAt(new_x, new_y).isAlive())
        {
            for (Unit unit: _units)
            {
                if (unit.getX() == old_x && unit.getY() == old_y)
                {
                    unit.sethasActed(true);
                }
            }
            
            for (Unit unit : _units)
            {
                if (unit.getX() == new_x && unit.getY() == new_y)
                {
                    if(normal_attack)
                    {
                        getUnitAt(old_x, old_y).attack(unit);
                        return true;
                    }
                    else
                    {
                        getUnitAt(old_x, old_y).magicAttack(unit);
                        return true;
                    }
                }
            }
        }
        
        return false;
    }

    /**
     * Returns a collection of all units on this map.
     * @return a copy of the collection of all units on this map
     */
    public Unit[] getUnits()
    {
        return _units.toArray(new Unit[_units.size()]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        final int capacity = (2 + _height * 2) * (5 + _width * 4);
        java.lang.StringBuilder sb = new java.lang.StringBuilder(capacity);

        // Top x-numbering
        sb.append("  ");
        for (int x = 0; x < _width; x++)
        {
            sb.append("   ");
            sb.append(x % 10);
        }
        sb.append('\n');

        // Main map
        for (int y = 0; y < _height; y++)
        {
            // Top of row
            sb.append("   ");
            for (int x = 0; x < _width; x++)
            {
                sb.append("·---");
            }
            sb.append("·\n");

            // Left y-numbering
            sb.append(" ");
            sb.append(y % 10);
            sb.append(" ");

            // Each column in row
            for (int x = 0; x < _width; x++)
            {
                sb.append("|");

                char tileChar = ' ';
                Tile tile = getTileAt(x, y);

                if (tile.isWall())
                {
                    tileChar = '|';
                }
                else if (tile.boostsDodge())
                {
                    tileChar = '~';
                }
                else if (tile.reducesMovement())
                {
                    tileChar = '_';
                }

                sb.append(tileChar);

                Unit unit = getUnitAt(x, y);
                if (unit != null && unit.isAlive())
                {
                    sb.append('#');
                }
                else
                {
                    sb.append(tileChar);
                }

                sb.append(tileChar);
            }

            // Right side of last column in row
            sb.append("|\n");
        }

        // Bottom of last row
        sb.append("   ");
        for (int x = 0; x < _width; x++)
        {
            sb.append("·---");
        }
        sb.append("·\n");

        return sb.toString();
    }
}
