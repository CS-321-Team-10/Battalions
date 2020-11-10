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
import battalions.models.Tile;
import battalions.util.Rng;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

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
     * Instantiates a new instance of the MapView class.
     */
    public MapView()
    {
        initComponents();
    }

    /**
     * Displays the provided text on this view.
     * @param t the text to display
     */
    public void setMapText(String t)
    {
        mapText.setText(t);
    }

    /**
     * Adds an action listener that will be used to perform updates upon
     * clicking the Update button.
     * @param l the listener for the Update button
     */
    public void addUpdateButtonListener(ActionListener l)
    {
        updateButton.addActionListener(l);
    }

    /**
     * Displays each tile in the 2D array of tiles.
     * @param tiles the 2D array of tiles to be displayed
     */
    public void displayMap(Tile[][] tiles)
    {
        final int rows = tiles.length;
        assert rows > 0;

        final int cols = tiles[0].length;
        assert cols > 0;

        // Remove all child tiles
        tileMap.removeAll();
        tileMap.setLayout(new GridLayout(rows, cols));

        // Traverse X on the inner loop so that the map is drawn row-wise,
        //  which is required for adding children to a GridLayout
        for (int y = 0; y < tiles[0].length; y++)
        {
            for (int x = 0; x < tiles.length; x++)
            {
                // Current tile
                Tile t = tiles[y][x];

                // Create tile ImageIcon and insert into JLabel
                JLabel label = new JLabel(getImage(t));
                label.setPreferredSize(new Dimension(25, 25));

                // Add each child tile
                tileMap.add(label);
            }
        }

        // Validate size and paint once all child tiles are added
        tileMap.validate();
    }

    /**
     * Return an image with the sprite corresponding to the specified tile.
     * @param t the tile whose image to return
     * @return the specified tile sprite as an ImageIcon
     */
    private ImageIcon getImage(Tile t)
    {
        // Path to tiles directory
        String fileName = null;

        // Determine the given tile's type, then sets the appropriate PNG path
        switch (t.getType())
        {
            case Field:
                // Randomize which grass tile is chosen
                switch (Rng.getInt(0, 3))
                {
                    case 0:
                        fileName = "Grass1.png";
                        break;
                    case 1:
                        fileName = "Grass2.png";
                        break;
                    case 2:
                        fileName = "WheatGrass1.png";
                        break;
                    case 3:
                        fileName = "WheatGrass2.png";
                        break;
                }
                break;
            case Forest:
                fileName = "Forest.png";
                break;
            case Sand:
                fileName = "Sand.png";
                break;
            case Wall:
                switch (t.getOrientation())
                {
                    case Up:
                    case Down:
                        fileName = "WallVertical.png";
                        break;
                    case Right:
                    case Left:
                        fileName = "WallHorizontal.png";
                        break;
                    case UpLeft:
                        fileName = "WallCornerNW.png";
                        break;
                    case UpRight:
                        fileName = "WallCornerNE.png";
                        break;
                    case DownLeft:
                        fileName = "WallCornerSW.png";
                        break;
                    case DownRight:
                        fileName = "WallCornerSE.png";
                        break;
                }
                break;
        }

        if (fileName == null)
        {
            // If you end up here, it's probably a code error:
            //  e.g. a switch is missing a case
            assert false;
        }

        return new ImageIcon("src/images/tiles/" + fileName);
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
