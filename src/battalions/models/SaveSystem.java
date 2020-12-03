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
import battalions.data.TileType;
import battalions.data.UnitType;
import battalions.data.Orientation;
import java.util.Set;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Responsible for saving and loading the game.
 * @author Bryant
 */
public class SaveSystem
{
    /**
     * The save file with the current status of the game.
     */
    private static final File SAVE_DATA = new File("SaveData.txt");

    /**
     * Gets the tile and units and writes their information into a file.
     * @param game the game to save
    */
    public static void save(Game game)
    {
        Map map = game.getMap();

        try (FileWriter filewriter = new FileWriter(SAVE_DATA);
            BufferedWriter writer = new BufferedWriter(filewriter))
        {
            SAVE_DATA.createNewFile();

            // Writes turn state
            writer.write(game.getMapSelector().getCurrentPlayer().getUid() + "\n");

            // Copies map data to text file
            writer.write(map.getWidth() + "\n");
            writer.write(map.getHeight() + "\n");
            for (int i = 0; i < map.getWidth(); i++)
            {
                for(int j = 0; j < map.getHeight(); j++)
                {
                    Location l = new Location(i, j);
                    Tile t = map.getTileAt(l);
                    TileType type = t.getType();
                    TileType underlay = t.getUnderlayType();
                    Orientation o = t.getOrientation();

                    writer.write(i + " ");
                    writer.write(j + " ");
                    writer.write(type + " ");
                    writer.write(underlay + " \n");
                }
            }

            // Copies unit data to text file
            Set<Unit> unitlist = map.getUnits();
            int size = unitlist.size();
            writer.write(size + "\n");
            for (Unit unit: unitlist)
            {
                Player player = unit.getPlayer();
                Location l = unit.getLocation();
                UnitType type = unit.getType();
                int health = unit.getHealth();

                writer.write(player.getUid() + " ");
                writer.write(l.x + " ");
                writer.write(l.y + " ");
                writer.write(type + " ");
                writer.write(health + " ");
                writer.write(unit.hasMoved() + " ");
                writer.write(unit.hasActed() + " \n");
            }

            writer.close();
            filewriter.close();
        }
        catch (IOException exception)
        {
            System.out.println("Error: File not found.");
        }
    }

    /**
     * Reads data from "SaveData.txt" and updates game accordingly.
     * @param game the game being written to
    */
    public static void load(Game game)
    {
        Map map = game.getMap();
        MapSelector mapSelector = game.getMapSelector();
        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();

        game.clear();

        try (Scanner reader = new Scanner(SAVE_DATA))
        {
            // Reads turn state
            int uid = Integer.parseInt(reader.nextLine());
            mapSelector.setCurrentPlayer(uid == 0 ? player1 : player2);

            String widthstring = reader.nextLine();
            String heightstring = reader.nextLine();
            int width = Integer.parseInt(widthstring);
            int height = Integer.parseInt(heightstring);

            // Loading the map data
            for (int i = 0; i < (width * height); i++)
            {
                String data = reader.nextLine();
                String[] split_data = data.split(" ");
                TileType type;
                TileType underlay;
                int x = Integer.parseInt(split_data[0]);
                int y = Integer.parseInt(split_data[1]);
                Location l = new Location(x, y);
                String typeString = split_data[2];
                type = typeString.equals("null") ? null : TileType.valueOf(typeString);
                typeString = split_data[3];
                underlay = typeString.equals("null") ? null : TileType.valueOf(typeString);

                Tile t = new Tile(map, l, type, underlay);
                map.addTile(t);
            }

            // Loading the unit data
            String sizestring = reader.nextLine();
            int size = Integer.parseInt(sizestring);
            for (int i = 0; i < size; i++)
            {
                String data = reader.nextLine();
                String[] split_data = data.split(" ");
                int player_uid = Integer.parseInt(split_data[0]);
                int x = Integer.parseInt(split_data[1]);
                int y = Integer.parseInt(split_data[2]);
                Location l = new Location(x, y);
                String typeString = split_data[3];
                UnitType type = typeString.equals("null") ? null : UnitType.valueOf(typeString);
                int health = Integer.parseInt(split_data[4]);
                boolean move = Boolean.parseBoolean(split_data[5]);
                boolean act = Boolean.parseBoolean(split_data[6]);

                // Determines wheter unit is attributed to player1 or player2
                Unit unit;
                if(player_uid == 1)
                {
                    unit = new Unit(player1, map, l, type);
                    unit.setHasMoved(move);
                    unit.setHasActed(act);
                    unit.setHealth(health);
                    player1.addUnit(unit);
                }
                else
                {
                    unit = new Unit(player2, map, l, type);
                    unit.setHasMoved(move);
                    unit.setHasActed(act);
                    unit.setHealth(health);
                    player2.addUnit(unit);
                }
            }

            // Add all the units to the map
            player1.getUnits().forEach(x -> map.addUnit(x));
            player2.getUnits().forEach(x -> map.addUnit(x));

            reader.close();
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("Error.");
        }
    }
}
