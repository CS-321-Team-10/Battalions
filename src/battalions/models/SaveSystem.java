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
    private final File save_data = new File("SaveData.txt");
    
    /*
     * Gets the tile and units and writes their information into a file.
     * @param map the map being read from
    */
    public void Save(Map map)
    {
        try
        {
            save_data.createNewFile();
            FileWriter filewriter = new FileWriter(save_data);
            BufferedWriter writer = new BufferedWriter(filewriter);
            
            /*
             * Copies map data to text file.
            */
            writer.write(map.getWidth() + "\n");
            writer.write(map.getHeight() + "\n");
            for(int i = 0; i < map.getWidth(); i++)
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
                    //writer.write("orientation" + o.flipHorizontal + " \n");
                }
            }
            
            /*
             * Copies unit data to text file.
            */
            Set<Unit> unitlist = map.getUnits();
            int size = unitlist.size();
            writer.write(size + "\n");
            for(Unit unit: unitlist)
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
        catch(IOException exception)
        {
            System.out.println("Error: File not found.");
        }
    }
    /*
     * Reads data from "SaveData.txt" and updates game accordingly.
     * @param map the map being written to
     * @param player1 the first player being written to
     * @param player2 the second player being written to
    */
    public void Load(Map map, Player player1, Player player2)
    {
       try
       {
            Scanner reader = new Scanner(save_data);
            String widthstring = reader.nextLine();
            String heightstring = reader.nextLine();
            int width = Integer.parseInt(widthstring);
            int height = Integer.parseInt(heightstring);
            
            /*
             * Loading the map data.
            */
            for(int i = 0; i < (width * height); i++)
            {
                String data = reader.nextLine();
                String[] split_data = data.split(" ");
                TileType type;
                TileType underlay;
                int x = Integer.parseInt(split_data[0]);
                int y = Integer.parseInt(split_data[1]);
                Location l = new Location(x, y);
                type = findTileType(split_data[2]);
                underlay = findTileType(split_data[3]);
                
                Tile t = new Tile(map, l, type, underlay);
                map.addTile(t);
            }
            
            /*
             * Loading the unit data.
            */
            String sizestring = reader.nextLine();
            int size = Integer.parseInt(sizestring);
            for(int i = 0; i < size; i++)
            {
                String data = reader.nextLine();
                String[] split_data = data.split(" ");
                int player_uid = Integer.parseInt(split_data[0]);
                int x = Integer.parseInt(split_data[1]);
                int y = Integer.parseInt(split_data[2]);
                Location l = new Location(x, y);
                UnitType type = findUnitType(split_data[3]);
                int health = Integer.parseInt(split_data[4]);
                boolean move = Boolean.parseBoolean(split_data[5]);
                boolean act = Boolean.parseBoolean(split_data[6]);
                
                /*
                 * Determines wheter unit is attributed to player1 or 
                 * player2
                */
                Unit unit;
                if(player_uid == 1)
                {
                    unit = new Unit(player1, map, l, type);
                    unit.sethasMoved(move);
                    unit.sethasActed(act);
                    unit.setHealth(health);
                    player1.addUnit(unit);
                }
                else
                {
                    unit = new Unit(player2, map, l, type);
                    unit.sethasMoved(move);
                    unit.sethasActed(act);
                    unit.setHealth(health);
                    player2.addUnit(unit);
                }
            }
            
            /*
             * Add all the units to the map.
            */
            player1.getUnits().forEach(x -> map.addUnit(x));
            player2.getUnits().forEach(x -> map.addUnit(x));
       }
       catch(FileNotFoundException exception)
       {
           System.out.println("Error: File not found.");
       }
    }
    
    /*
     * Translates a string to to TileType.
     * @param string the string to be translated to a TileType.
    */
    public TileType findTileType(String string)
    {
        TileType type = TileType.FieldLight;
        
        switch(string)
                {
                    case "FieldLight":
                        type = TileType.FieldLight;
                        break;
                    case "FieldDark":
                        type = TileType.FieldDark;
                        break;
                    case "GrassDark":
                        type = TileType.GrassDark;    
                        break;
                    case "GrassLight":
                        type = TileType.GrassLight;
                        break;
                    case "BridgeHorizontal":
                        type = TileType.BridgeHorizontal;
                        break;
                    case "BridgeVertical":
                        type = TileType.BridgeVertical;    
                        break;
                    case "Forest":
                        type = TileType.Forest;
                        break;
                    case "Sand":
                        type = TileType.Sand;
                        break;     
                    case "SandNorthEast":
                        type = TileType.SandNorthEast;    
                        break;
                    case "SandNorthWest":
                        type = TileType.SandNorthWest;
                        break;
                    case "SandSouthEast":
                        type = TileType.SandSouthEast;
                        break;         
                    case "SandSouthWest":
                        type = TileType.SandSouthWest;    
                        break;
                    case "SandInwardNorthEast":
                        type = TileType.SandInwardNorthEast;
                        break;
                    case "SandInwardNorthWest":
                        type = TileType.SandInwardNorthWest;
                        break;
                    case "SandInwardSouthEast":
                        type = TileType.SandInwardSouthEast;    
                        break;
                    case "SandInwardSouthWest":
                        type = TileType.SandInwardSouthWest;
                        break;
                    case "WallHorizontal":
                        type = TileType.WallHorizontal;
                        break;
                    case "WallVertical":
                        type = TileType.WallVertical;   
                        break;
                    case "WallNorthEast":
                        type = TileType.WallNorthEast;
                        break;
                    case "WallNorthWest":
                        type = TileType.WallNorthWest;
                        break;     
                    case "WallSouthEast":
                        type = TileType.WallSouthEast;    
                        break;
                    case "WallSouthWest":
                        type = TileType.WallSouthWest;
                        break;
                    case "Wall3WayEast":
                        type = TileType.Wall3WayEast;
                        break;         
                    case "Wall3WayWest":
                        type = TileType.Wall3WayWest;    
                        break;
                    case "Wall3WayNorth":
                        type = TileType.Wall3WayNorth;
                        break;
                    case "Wall3WaySouth":
                        type = TileType.Wall3WaySouth;
                        break;     
                    case "Wall4Way":
                        type = TileType.Wall4Way;   
                        break;
                    case "WaterHorizontal":
                        type = TileType.WaterHorizontal;
                        break;
                    case "WaterVertical":
                        type = TileType.WaterVertical;
                        break;         
                    case "WaterNorthEast":
                        type = TileType.WaterNorthEast;    
                        break;
                    case "WaterNorthWest":
                        type = TileType.WaterNorthWest;    
                        break;
                    case "WaterSouthEast":
                        type = TileType.WaterSouthEast;
                        break;
                    case "WaterSouthWest":
                        type = TileType.WaterSouthWest;
                        break;     
                    case "Water3WayEast":
                        type = TileType.Water3WayEast;    
                        break;
                    case "Water3WayWest":
                        type = TileType.Water3WayWest;
                        break;
                    case "Water3WayNorth":
                        type = TileType.Water3WayNorth;
                        break;         
                    case "Water3WaySouth":
                        type = TileType.Water3WaySouth;    
                        break;
                    case "Water4Way":
                        type = TileType.Water4Way;
                        break;
                    case "BrokenBuilding":
                        type = TileType.BrokenBuilding;
                        break;         
                    case "BrokenTower":
                        type = TileType.BrokenTower;    
                        break;
                    case "null":
                        type = null;    
                        break;
                }
        return type;
    }
    
    /*
     * Translates a string to a UnitType.
     * @param string the string to be translated to a UnitType.
    */
    public UnitType findUnitType(String string)
    {
        UnitType type = UnitType.Infantry;
        
        switch(string)
                {
                    case "Infantry":
                        type = UnitType.Infantry;
                        break;
                    case "Mage":
                        type = UnitType.Mage;
                        break;
                    case "Knight":
                        type = UnitType.Knight;    
                        break;
                    case "Warlock":
                        type = UnitType.Warlock;
                        break;
                    case "Archer":
                        type = UnitType.Archer;
                        break;
                    case "Healer":
                        type = UnitType.Healer;    
                        break;
        }
        
        return type;
    }
}
