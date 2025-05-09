package swing.gameBoard.rightPanel;

import swing.GameManager;

import javax.swing.*;
import java.awt.*;

class PlayerUnitTracker extends JPanel {
    public PlayerUnitTracker(GameManager gm) {

        setLayout(null);
        setOpaque(false);

        // Title Label
        JLabel titleLabel = new JLabel("Player Unit Tracker");
        titleLabel.setFont(new Font("돋움", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);

        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(0, 0, 310, 140);

        // Player Unit Tracker
        TrackerPanel trackerPanel = new TrackerPanel(gm, gm.getGameState().getTotalPlayerNumber(), gm.getGameState().getUnitNumberPerPlayer());
        trackerPanel.setBounds(0, 140, 310, 440);

        add(titleLabel);
        add(trackerPanel);

    }
}