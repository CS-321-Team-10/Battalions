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

import battalions.data.Location;
import battalions.data.Orientation;
import battalions.data.TileType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Contains a grid of tiles.
 * @author Bryant
 * @author Scott
 */
public class Map
{
    /**
     * The size of this map (in tiles) along the x-axis.
     */
    private final int _width;

    /**
     * The size of this map (in tiles) along the y-axis.
     */
    private final int _height;

    /**
     * The grid of tiles that composes this map.
     */
    private final Tile[][] _tiles;

    /**
     * The set of units on the map.
     */
    private final java.util.Map<Location, Unit> _units;

    /**
     * Initializes a new instance of the Map class at the specified size.
     * @param width the size of this map (in tiles) along the x-axis
     * @param height the size of this map (in tiles) along the y-axis
     */
    public Map(int width, int height)
    {
        assert width > 0;
        assert height > 0;

        _width = width;
        _height = height;

        _tiles = new Tile[_height][_width];
        for (int x = 0; x < _width; x++)
        {
            for (int y = 0; y < _height; y++)
            {
                _tiles[x][y] = new Tile(this, new Location(x, y), TileType.Field, Orientation.Up);
            }
        }

        _units = new HashMap<>();
    }

    /**
     * Returns the width of this map.
     * @return the number of tiles along the direction of the x-axis
     */
    public final int getWidth()
    {
        return _width;
    }

    /**
     * Returns the height of this map.
     * @return the number of tiles along the direction of the y-axis
     */
    public final int getHeight()
    {
        return _height;
    }

    /**
     * Returns the tile positioned at the specified location on this map.
     * @param l the location of the desired tile
     * @return the tile at the specified location
     */
    public Tile getTileAt(Location l)
    {
        assert inBounds(l);

        return _tiles[l.x][l.y];
    }

    /**
     * Returns the unit at the specified location on this map.
     * @param l the location of the desired unit
     * @return the unit at the specified location, if one exists; null, otherwise
     */
    public Unit getUnitAt(Location l)
    {
        assert inBounds(l);

        return _units.get(l);
    }

    /**
     * Returns the tile positioned under the specified unit on this map.
     * @param u the unit positioned on the desired tile
     * @return the tile whose location matches that of the specified unit
     */
    public Tile getTileUnder(Unit u)
    {
        assert _units.containsValue(u);

        return getTileAt(u.getLocation());
    }

    /**
     * Adds a tile to this map.
     * @param t the tile to add to this map
     */
    public void addTile(Tile t)
    {
        assert t != null;

        Location l = t.getLocation();
        assert inBounds(l);

        // Cannot place wall on top of unit
        assert getUnitAt(l) == null;

        _tiles[l.x][l.y] = t;
    }

    /**
     * Adds a unit to this map.
     * @param u the unit to add to the map
     */
    public void addUnit(Unit u)
    {
        assert u != null;

        Location l = u.getLocation();
        assert inBounds(l);

        // Cannot place unit on top of wall
        assert getTileAt(l).getType().isImpassable() == false;

        _units.put(l, u);
    }

    /**
     * Returns whether it is possible for the specified unit to move to
     * the specified location.
     * @param u the unit performing the hypothetical movement
     * @param l the hypothetical destination location
     * @return true, if the movement is possible; false, otherwise
     */
    public boolean canMoveTo(Unit u, Location l)
    {
        // Unit must be a child of this map
        if (_units.containsValue(u) == false)
        {
            return false;
        }

        // Location must be in bounds
        if (inBounds(l) == false)
        {
            return false;
        }

        // Location must not contain a wall
        Tile tile = getTileAt(l);
        if (tile.getType().isImpassable())
        {
            return false;
        }

        // [TODO] Pathfinding code to make sure path doesn't
        //        teleport over walls or other players

        return true;
    }

    /**
     * Returns whether it is possible for the former specified unit
     * to attack the latter.
     * @param attacker the unit performing the hypothetical attack
     * @param defender the unit being hypothetically attacked
     * @return true, if the attack is possible; false, otherwise
     */
    public boolean canAttack(Unit attacker, Unit defender)
    {
        // Both units must be children of this map
        if ((_units.containsValue(attacker) == false)
            || (_units.containsValue(defender) == false))
        {
            return false;
        }

        // Units must be owned by opposing players
        if (attacker.getPlayer() == defender.getPlayer())
        {
            return false;
        }

        // [TODO] Pathfinding code to make sure path doesn't
        //        teleport over walls or other players

        return true;
    }

    /**
     * Returns whether it is possible for the former specified unit
     * to assist the latter.
     * @param assister the unit performing the hypothetical assist
     * @param assisted the unit being hypothetically assisted
     * @return true, if the assist is possible; false, otherwise
     */
    public boolean canAssist(Unit assister, Unit assisted)
    {
        // Both units must be children of this map
        if (_units.containsValue(assister) == false
            || _units.containsValue(assisted) == false)
        {
            return false;
        }

        // Units must be owned by the same player
        if (assister.getPlayer() != assister.getPlayer())
        {
            return false;
        }

        // [TODO] Pathfinding code to make sure path doesn't
        //        teleport over walls or other players

        return true;
    }

    /**
     * Returns whether the specified location is within the bounds of this map.
     * @param l the location in question
     * @return whether the location is a valid location on this map
     */
    public boolean inBounds(Location l)
    {
        return (l.x >= 0) && (l.x < _width)
            && (l.y >= 0) && (l.y < _height);
    }

    /**
     * Returns a copy of all tiles on the map, as a 2D array.
     * @return a shallow copy of all tiles on the map, as a 2D array
     */
    public Tile[][] getTiles()
    {
        Tile[][] tiles = new Tile[_height][_width];

        for (int x = 0; x < _width; x++)
        {
            for (int y = 0; y < _height; y++)
            {
                tiles[y][x] = _tiles[y][x];
            }
        }

        return tiles;
    }

    /**
     * Returns the set of all units on this map.
     * @return a copy of the set of all units on this map
     */
    public Set<Unit> getUnits()
    {
        Set<Unit> result = new HashSet<>();

        _units.entrySet().forEach(x -> result.add(x.getValue()));

        return result;
    }

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
                Location l = new Location(x, y);

                sb.append("|");

                char tileChar = ' ';
                Tile tile = getTileAt(l);

                if (tile.getType().isImpassable())
                {
                    tileChar = '|';
                }
                else if (tile.getType().boostsDodge())
                {
                    tileChar = '~';
                }
                else if (tile.getType().reducesMovement())
                {
                    tileChar = '_';
                }

                sb.append(tileChar);

                Unit unit = getUnitAt(l);
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
