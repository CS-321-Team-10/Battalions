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

import battalions.data.TileType;
import battalions.models.Player;
import battalions.models.Tile;
import battalions.models.Unit;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Provides a view for the entire game.
 * @author Scott
 */
public class GameView extends javax.swing.JFrame
{
    /**
     * Creates new form GameView
     */
    public GameView()
    {
        initComponents();
    }

    /**
     * Displays info about the specified highlighted tile.
     * @param tile the tile whose info to display
     */
    public void setHighlightedTileInfo(Tile tile)
    {
        if (tile instanceof Tile)
        {
            TileType type = tile.getType();
            tileTypeLabel.setText(type.toString());

            tileLocationLabel.setText(tile.getLocation().toString());

            tileAttributesLabel.setText(
                "[ " + (type.isImpassable() ? "IMPASSABLE " : "")
                + (type.boostsDodge() ? "BOOSTS DODGE CHANCE " : "")
                + (type.reducesMovement() ? "REDUCES MOVEMENT RANGE " : "")
                + "]");
        }
        else
        {
            tileTypeLabel.setText("-");
            tileLocationLabel.setText("-");
            tileAttributesLabel.setText("-");
        }
    }

    /**
     * Displays info about the specified highlighted unit.
     * @param unit the unit whose info to display
     */
    public void setHighlightedUnit(Unit unit)
    {
        if (unit instanceof Unit)
        {
            highlightedUnitTypeLabel.setText(unit.getType().toString());

            highlightedUnitPlayerLabel.setText(unit.getPlayer().isCPU() ? "Player 2" : "Player 1");

            highlightedUnitLocationLabel.setText(unit.getLocation().toString());

            highlightedUnitStatsLabel.setText(
                (unit.isAlive() ? " [" : " [DEAD] [")
                + unit.getHealth() + " HP] [" + unit.getSpeed() + " SPEED] [" + unit.getLuck() + " LUCK] ["
                + unit.getAttack() + " ATK] [" + unit.getDefense() + " DEF] ["
                + unit.getMagicAttack() + " MATK] [" + unit.getMagicDefense() + " MDEF]");
        }
        else
        {
            highlightedUnitTypeLabel.setText("-");
            highlightedUnitPlayerLabel.setText("-");
            highlightedUnitLocationLabel.setText("-");
            highlightedUnitStatsLabel.setText("-");
        }
    }

    /**
     * Displays info about the specified selected unit.
     * @param unit the unit whose info to display
     */
    public void setSelectedUnit(Unit unit)
    {
        if (unit instanceof Unit)
        {
            selectedUnitTypeLabel.setText(unit.getType().toString());

            selectedUnitPlayerLabel.setText(unit.getPlayer().isCPU() ? "Player 2" : "Player 1");

            selectedUnitLocationLabel.setText(unit.getLocation().toString());

            selectedUnitStatsLabel.setText(
                (unit.isAlive() ? " [" : " [DEAD] [")
                + unit.getHealth() + " HP] [" + unit.getSpeed() + " SPEED] [" + unit.getLuck() + " LUCK] ["
                + unit.getAttack() + " ATK] [" + unit.getDefense() + " DEF] ["
                + unit.getMagicAttack() + " MATK] [" + unit.getMagicDefense() + " MDEF]");
        }
        else
        {
            selectedUnitTypeLabel.setText("-");
            selectedUnitPlayerLabel.setText("-");
            selectedUnitLocationLabel.setText("-");
            selectedUnitStatsLabel.setText("-");
        }
    }

    /**
     * Indicates that the specified player has won the game.
     * @param player the winner of the game
     */
    public void setWinner(Player player)
    {
        JOptionPane.showMessageDialog(this, "Player " + (player.isCPU() ? "2" : "1") + " wins!",  "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Adds an action listener that will be used to end the current turn
     * upon clicking the End Turn button.
     * @param l the listener for the End Turn button
     */
    public void addEndTurnButtonListener(ActionListener l)
    {
        endTurnButton.addActionListener(l);
    }

    /**
     * Adds an action listener that will be used to restart
     * upon clicking the Restart button.
     * @param l the listener for the Restart button
     */
    public void addRestartButtonListener(ActionListener l)
    {
        restartButton.addActionListener(l);
    }

    /**
     * Adds an action listener that will be used to load a save file
     * upon clicking the Load button.
     * @param l the listener for the End Turn button
     */
    public void addLoadButtonListener(ActionListener l)
    {
        loadButton.addActionListener(l);
    }

    /**
     * Adds an action listener that will be used to save to a save file
     * upon clicking the Save button.
     * @param l the listener for the End Turn button
     */
    public void addSaveButtonListener(ActionListener l)
    {
        saveButton.addActionListener(l);
    }

    /**
     * Returns the map view that is inside this frame.
     * @return the map view inside this frame
     */
    public final MapSelectorView getMapSelectorView()
    {
        return mapSelectorView;
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

        mapBorder = new javax.swing.JPanel();
        mapSelectorView = new battalions.views.MapSelectorView();
        actionsBorder = new javax.swing.JPanel();
        endTurnButton = new javax.swing.JButton();
        restartButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        highlightedTileBorder = new javax.swing.JPanel();
        tileTypeLabel = new javax.swing.JLabel();
        tileLocationLabel = new javax.swing.JLabel();
        tileAttributesLabel = new javax.swing.JLabel();
        highlightedUnitBorder = new javax.swing.JPanel();
        highlightedUnitPlayerLabel = new javax.swing.JLabel();
        highlightedUnitStatsLabel = new javax.swing.JLabel();
        highlightedUnitLocationLabel = new javax.swing.JLabel();
        highlightedUnitTypeLabel = new javax.swing.JLabel();
        selectedUnitBorder = new javax.swing.JPanel();
        selectedUnitPlayerLabel = new javax.swing.JLabel();
        selectedUnitStatsLabel = new javax.swing.JLabel();
        selectedUnitLocationLabel = new javax.swing.JLabel();
        selectedUnitTypeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mapBorder.setBorder(javax.swing.BorderFactory.createTitledBorder("Map"));

        javax.swing.GroupLayout mapSelectorViewLayout = new javax.swing.GroupLayout(mapSelectorView);
        mapSelectorView.setLayout(mapSelectorViewLayout);
        mapSelectorViewLayout.setHorizontalGroup(
            mapSelectorViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mapSelectorViewLayout.setVerticalGroup(
            mapSelectorViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 493, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout mapBorderLayout = new javax.swing.GroupLayout(mapBorder);
        mapBorder.setLayout(mapBorderLayout);
        mapBorderLayout.setHorizontalGroup(
            mapBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mapBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mapSelectorView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        mapBorderLayout.setVerticalGroup(
            mapBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mapBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mapSelectorView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        actionsBorder.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));

        endTurnButton.setText("End Turn");

        restartButton.setText("Restart");

        loadButton.setText("Load");

        saveButton.setText("Save");

        javax.swing.GroupLayout actionsBorderLayout = new javax.swing.GroupLayout(actionsBorder);
        actionsBorder.setLayout(actionsBorderLayout);
        actionsBorderLayout.setHorizontalGroup(
            actionsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionsBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(actionsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(actionsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(endTurnButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(restartButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(loadButton)
                    .addComponent(saveButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        actionsBorderLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {endTurnButton, loadButton, restartButton, saveButton});

        actionsBorderLayout.setVerticalGroup(
            actionsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionsBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(endTurnButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(restartButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loadButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        highlightedTileBorder.setBorder(javax.swing.BorderFactory.createTitledBorder("Highlighted Tile"));

        tileTypeLabel.setText("-");
        tileTypeLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("Tile Type"));

        tileLocationLabel.setText("-");
        tileLocationLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("Location"));

        tileAttributesLabel.setText("-");
        tileAttributesLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("Attributes"));

        javax.swing.GroupLayout highlightedTileBorderLayout = new javax.swing.GroupLayout(highlightedTileBorder);
        highlightedTileBorder.setLayout(highlightedTileBorderLayout);
        highlightedTileBorderLayout.setHorizontalGroup(
            highlightedTileBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(highlightedTileBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(highlightedTileBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tileTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tileLocationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(tileAttributesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        highlightedTileBorderLayout.setVerticalGroup(
            highlightedTileBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(highlightedTileBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tileTypeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tileLocationLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tileAttributesLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        highlightedTileBorderLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tileAttributesLabel, tileLocationLabel, tileTypeLabel});

        highlightedUnitBorder.setBorder(javax.swing.BorderFactory.createTitledBorder("Highlighted Unit"));

        highlightedUnitPlayerLabel.setText("-");
        highlightedUnitPlayerLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("Team"));

        highlightedUnitStatsLabel.setText("-");
        highlightedUnitStatsLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("Stats"));

        highlightedUnitLocationLabel.setText("-");
        highlightedUnitLocationLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("Location"));

        highlightedUnitTypeLabel.setText("-");
        highlightedUnitTypeLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("Unit Type"));

        javax.swing.GroupLayout highlightedUnitBorderLayout = new javax.swing.GroupLayout(highlightedUnitBorder);
        highlightedUnitBorder.setLayout(highlightedUnitBorderLayout);
        highlightedUnitBorderLayout.setHorizontalGroup(
            highlightedUnitBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(highlightedUnitBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(highlightedUnitBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(highlightedUnitPlayerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                    .addComponent(highlightedUnitLocationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(highlightedUnitStatsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(highlightedUnitTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        highlightedUnitBorderLayout.setVerticalGroup(
            highlightedUnitBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(highlightedUnitBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(highlightedUnitTypeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(highlightedUnitPlayerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(highlightedUnitLocationLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(highlightedUnitStatsLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        highlightedUnitBorderLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {highlightedUnitLocationLabel, highlightedUnitPlayerLabel, highlightedUnitStatsLabel, highlightedUnitTypeLabel});

        selectedUnitBorder.setBorder(javax.swing.BorderFactory.createTitledBorder("Selected Unit"));

        selectedUnitPlayerLabel.setText("-");
        selectedUnitPlayerLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("Team"));

        selectedUnitStatsLabel.setText("-");
        selectedUnitStatsLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("Stats"));

        selectedUnitLocationLabel.setText("-");
        selectedUnitLocationLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("Location"));

        selectedUnitTypeLabel.setText("-");
        selectedUnitTypeLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("Unit Type"));

        javax.swing.GroupLayout selectedUnitBorderLayout = new javax.swing.GroupLayout(selectedUnitBorder);
        selectedUnitBorder.setLayout(selectedUnitBorderLayout);
        selectedUnitBorderLayout.setHorizontalGroup(
            selectedUnitBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectedUnitBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(selectedUnitBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectedUnitPlayerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                    .addComponent(selectedUnitLocationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selectedUnitStatsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selectedUnitTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        selectedUnitBorderLayout.setVerticalGroup(
            selectedUnitBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectedUnitBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectedUnitTypeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectedUnitPlayerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectedUnitLocationLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectedUnitStatsLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mapBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(highlightedTileBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(highlightedUnitBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectedUnitBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(actionsBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mapBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(highlightedTileBorder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(highlightedUnitBorder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(actionsBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selectedUnitBorder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info
                    : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(GameView.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(GameView.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(GameView.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(GameView.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new GameView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionsBorder;
    private javax.swing.JButton endTurnButton;
    private javax.swing.JPanel highlightedTileBorder;
    private javax.swing.JPanel highlightedUnitBorder;
    private javax.swing.JLabel highlightedUnitLocationLabel;
    private javax.swing.JLabel highlightedUnitPlayerLabel;
    private javax.swing.JLabel highlightedUnitStatsLabel;
    private javax.swing.JLabel highlightedUnitTypeLabel;
    private javax.swing.JButton loadButton;
    private javax.swing.JPanel mapBorder;
    private battalions.views.MapSelectorView mapSelectorView;
    private javax.swing.JButton restartButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JPanel selectedUnitBorder;
    private javax.swing.JLabel selectedUnitLocationLabel;
    private javax.swing.JLabel selectedUnitPlayerLabel;
    private javax.swing.JLabel selectedUnitStatsLabel;
    private javax.swing.JLabel selectedUnitTypeLabel;
    private javax.swing.JLabel tileAttributesLabel;
    private javax.swing.JLabel tileLocationLabel;
    private javax.swing.JLabel tileTypeLabel;
    // End of variables declaration//GEN-END:variables
}
