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
import java.awt.*;
import javax.swing.*;
import battalions.models.Map;
import battalions.models.Tile;
import battalions.util.Rng;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
/**
 * Provides a view for the Map class, controlled by the MapController class.
 * @author Guess
 * @author Scott
 */
public class MapView extends javax.swing.JPanel
{
    // A MapController object to refer to when drawing map tiles, in order to follow MVC
    // An ImageObserver needed to draw map tiles
    ImageObserver observer;

    /**
     * Creates new form MapView
     */
    public MapView()
    {
        initComponents();
    }

    public void setMapText(String t)
    {
        mapText.setText(t);
    }

    public void addUpdateButtonListener(ActionListener l)
    {
        updateButton.addActionListener(l);
    }

    public void drawATestPanel(Color color, int width, int height, int x, int y)
    {
        JPanel testPanel = new JPanel();

        testPanel.setSize(width, height);
        testPanel.setLocation(x, y);
        testPanel.setBackground(color);
        testPanel.setVisible(true);

        tileMap.add(testPanel);

        tileMap.repaint();
    }

    // A method that reads through the map data to display, calling the method to display each tile
    public void displayMap(Map mapMod) throws IOException
    {
        // A temporary copy of the 2D tile array retrieved from the model
        Tile[][] tiles = mapMod.getTiles();

        // for statements to traverse the copy array
        for (int x = 0; x < tiles.length; x++)
        {
            for (int y = 0; y < tiles[0].length; y++)
            {
                // calls method to display the tile at this x y location
                displayMapTile(tiles[x][y]);
            }
        }
    }
    // A method to display a tile image corresponding to the passed tile
    public void displayMapTile(Tile tile) throws IOException
    {
        // Path to tiles directory
        String fileName = "\\Battalions\\src\\tiles\\";

        // Determine the given tile's type, then sets the appropriate PNG path
	switch (tile.getType())
	{
            case Field:
                // Randomize which grass tile is chosen
                switch (Rng.getInt(0, 3))
                {
                    case 0:
                        fileName += "Grass1.png";
                        break;
                    case 1:
                        fileName += "Grass2.png";
                        break;
                    case 2:
                        fileName += "WheatGrass1.png";
                        break;
                    case 3:
                        fileName += "WheatGrass2.png";
                        break;
                }
                break;
            case Forest:
                fileName += "Forest.png";
                break;
            case Sand:
                fileName += "Sand.png";
                break;
            case Wall:
                switch (tile.getOrientation())
                {
                    case Up:
                    case Down:
                        fileName += "WallVertical.png";
                        break;
                    case Right:
                    case Left:
                        fileName += "WallHorizontal.png";
                        break;
                    case UpLeft:
                        fileName += "WallCornerNW.png";
                        break;
                    case UpRight:
                        fileName += "WallCornerNE.png";
                        break;
                    case DownLeft:
                        fileName += "WallCornerSW.png";
                        break;
                    case DownRight:
                        fileName += "WallCornerSE.png";
                        break;
                }
                break;
            default:
                // If you end up here, it's probably a code error:
                //  e.g. the switch is missing a case
                assert false;
	}

	// Tile image and image object to hold it
	File file;
	BufferedImage tileImage = null;
	try
	{
		file = new File(fileName);
		tileImage = ImageIO.read(file);
	}
	catch (FileNotFoundException e)
	{
		System.out.println("FileNotFoundException: file \"" + fileName + "\" was not found. " + e);
		return;
	}

        Graphics g = tileImage.getGraphics();

        // Draw the image within the map view
        g.drawImage(tileImage, tile.getLocation().x, tile.getLocation().y, observer);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        showButtonGroup = new javax.swing.ButtonGroup();
        sourceSelectButtonGroup = new javax.swing.ButtonGroup();
        destinationSelectButtonGroup = new javax.swing.ButtonGroup();
        mapBorder = new javax.swing.JPanel();
        mapTextScrollPanel = new javax.swing.JScrollPane();
        mapText = new javax.swing.JTextArea();
        updateButton = new javax.swing.JButton();
        showBorder = new javax.swing.JPanel();
        showUnitsRadio = new javax.swing.JRadioButton();
        showMovesRadio = new javax.swing.JRadioButton();
        showAttacksRadio = new javax.swing.JRadioButton();
        showAllRadio = new javax.swing.JRadioButton();
        sourceSelectBorder = new javax.swing.JPanel();
        deselectRadio = new javax.swing.JRadioButton();
        selectUnitRadio = new javax.swing.JRadioButton();
        selectTileRadio = new javax.swing.JRadioButton();
        sourceSelectX = new javax.swing.JTextField();
        sourceSelectY = new javax.swing.JTextField();
        tileMap = new javax.swing.JPanel();
        destinationSelectBorder = new javax.swing.JPanel();
        destinationDeselectRadio = new javax.swing.JRadioButton();
        desinationSelectUnitRadio = new javax.swing.JRadioButton();
        destinationSelectTileRadio = new javax.swing.JRadioButton();
        destinationSelectX = new javax.swing.JTextField();
        destinationSelectY = new javax.swing.JTextField();

        mapBorder.setBorder(javax.swing.BorderFactory.createTitledBorder("Map"));

        mapText.setColumns(20);
        mapText.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        mapText.setRows(5);
        mapText.setText("<Map>");
        mapTextScrollPanel.setViewportView(mapText);

        updateButton.setText("Update");

        showBorder.setBorder(javax.swing.BorderFactory.createTitledBorder("Map Layer"));

        showButtonGroup.add(showUnitsRadio);
        showUnitsRadio.setText("Show Available Units");

        showButtonGroup.add(showMovesRadio);
        showMovesRadio.setText("Show Valid Moves");

        showButtonGroup.add(showAttacksRadio);
        showAttacksRadio.setText("Show Valid Attacks");

        showButtonGroup.add(showAllRadio);
        showAllRadio.setText("Show All");

        javax.swing.GroupLayout showBorderLayout = new javax.swing.GroupLayout(showBorder);
        showBorder.setLayout(showBorderLayout);
        showBorderLayout.setHorizontalGroup(
            showBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(showBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(showAttacksRadio)
                    .addComponent(showMovesRadio)
                    .addComponent(showUnitsRadio)
                    .addComponent(showAllRadio))
                .addContainerGap())
        );
        showBorderLayout.setVerticalGroup(
            showBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(showAllRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showUnitsRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showMovesRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showAttacksRadio)
                .addContainerGap())
        );

        sourceSelectBorder.setBorder(javax.swing.BorderFactory.createTitledBorder("Source"));

        sourceSelectButtonGroup.add(deselectRadio);
        deselectRadio.setText("Deselect");

        sourceSelectButtonGroup.add(selectUnitRadio);
        selectUnitRadio.setText("Select Unit");

        sourceSelectButtonGroup.add(selectTileRadio);
        selectTileRadio.setText("Select Tile");

        javax.swing.GroupLayout sourceSelectBorderLayout = new javax.swing.GroupLayout(sourceSelectBorder);
        sourceSelectBorder.setLayout(sourceSelectBorderLayout);
        sourceSelectBorderLayout.setHorizontalGroup(
            sourceSelectBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sourceSelectBorderLayout.createSequentialGroup()
                .addGroup(sourceSelectBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sourceSelectBorderLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(sourceSelectBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selectUnitRadio)
                            .addComponent(selectTileRadio)
                            .addComponent(deselectRadio)
                            .addComponent(sourceSelectY, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(sourceSelectBorderLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(sourceSelectX, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        sourceSelectBorderLayout.setVerticalGroup(
            sourceSelectBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sourceSelectBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(deselectRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectTileRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectUnitRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sourceSelectBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sourceSelectY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sourceSelectX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tileMap.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout tileMapLayout = new javax.swing.GroupLayout(tileMap);
        tileMap.setLayout(tileMapLayout);
        tileMapLayout.setHorizontalGroup(
            tileMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 284, Short.MAX_VALUE)
        );
        tileMapLayout.setVerticalGroup(
            tileMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        destinationSelectBorder.setBorder(javax.swing.BorderFactory.createTitledBorder("Destination"));

        destinationSelectButtonGroup.add(destinationDeselectRadio);
        destinationDeselectRadio.setText("Deselect");

        destinationSelectButtonGroup.add(desinationSelectUnitRadio);
        desinationSelectUnitRadio.setText("Select Unit");

        destinationSelectButtonGroup.add(destinationSelectTileRadio);
        destinationSelectTileRadio.setText("Select Tile");

        javax.swing.GroupLayout destinationSelectBorderLayout = new javax.swing.GroupLayout(destinationSelectBorder);
        destinationSelectBorder.setLayout(destinationSelectBorderLayout);
        destinationSelectBorderLayout.setHorizontalGroup(
            destinationSelectBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(destinationSelectBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(destinationSelectBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(desinationSelectUnitRadio)
                    .addComponent(destinationSelectTileRadio)
                    .addComponent(destinationDeselectRadio)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, destinationSelectBorderLayout.createSequentialGroup()
                        .addComponent(destinationSelectX, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(destinationSelectY, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        destinationSelectBorderLayout.setVerticalGroup(
            destinationSelectBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(destinationSelectBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(destinationDeselectRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(destinationSelectTileRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desinationSelectUnitRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(destinationSelectBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(destinationSelectY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(destinationSelectX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mapBorderLayout = new javax.swing.GroupLayout(mapBorder);
        mapBorder.setLayout(mapBorderLayout);
        mapBorderLayout.setHorizontalGroup(
            mapBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mapBorderLayout.createSequentialGroup()
                .addGroup(mapBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mapBorderLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mapBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateButton)
                            .addGroup(mapBorderLayout.createSequentialGroup()
                                .addComponent(showBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sourceSelectBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(destinationSelectBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 342, Short.MAX_VALUE))
                    .addGroup(mapBorderLayout.createSequentialGroup()
                        .addComponent(tileMap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mapTextScrollPanel)))
                .addContainerGap())
        );
        mapBorderLayout.setVerticalGroup(
            mapBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mapBorderLayout.createSequentialGroup()
                .addGroup(mapBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mapBorderLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mapTextScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
                    .addComponent(tileMap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mapBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(destinationSelectBorder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(showBorder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sourceSelectBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mapBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mapBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton deselectRadio;
    private javax.swing.JRadioButton desinationSelectUnitRadio;
    private javax.swing.JRadioButton destinationDeselectRadio;
    private javax.swing.JPanel destinationSelectBorder;
    private javax.swing.ButtonGroup destinationSelectButtonGroup;
    private javax.swing.JRadioButton destinationSelectTileRadio;
    private javax.swing.JTextField destinationSelectX;
    private javax.swing.JTextField destinationSelectY;
    private javax.swing.JPanel mapBorder;
    private javax.swing.JTextArea mapText;
    private javax.swing.JScrollPane mapTextScrollPanel;
    private javax.swing.JRadioButton selectTileRadio;
    private javax.swing.JRadioButton selectUnitRadio;
    private javax.swing.JRadioButton showAllRadio;
    private javax.swing.JRadioButton showAttacksRadio;
    private javax.swing.JPanel showBorder;
    private javax.swing.ButtonGroup showButtonGroup;
    private javax.swing.JRadioButton showMovesRadio;
    private javax.swing.JRadioButton showUnitsRadio;
    private javax.swing.JPanel sourceSelectBorder;
    private javax.swing.ButtonGroup sourceSelectButtonGroup;
    private javax.swing.JTextField sourceSelectX;
    private javax.swing.JTextField sourceSelectY;
    private javax.swing.JPanel tileMap;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
