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
import battalions.data.Orientation;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Bryant
 */
public class SaveSystem
{
    private final File save_data = new File("SaveData.txt");
    
    public void SaveAndQuit(Map map)
    {
        try
        {
            save_data.createNewFile();
            FileWriter writer = new FileWriter("SaveData.txt");
            
            for(int i = 0; i < map.getWidth(); i++)
            {
                for(int j = 0; i < map.getHeight(); j++)
                {
                    Location l = new Location(i, j);
                    Tile t = map.getTileAt(l);
                    TileType type = t.getType();
                    TileType underlay = t.getUnderlayType();
                    Orientation o = t.getOrientation();
                    
                    writer.write("x" + i + " ");
                    writer.write("y" + j + " ");
                    writer.write("type" + type + " ");
                    writer.write("underlay" + underlay + " ");
                    writer.write("orientation" + o + " \n");
                }
            }
        }
        catch(IOException exception)
        {
            System.out.println("Error.");
        }
    }
    
    public void Load()
    {
        
    }
}
