package swing.gameBoard.RightPanel;

import swing.GameManager;

import javax.swing.*;
import java.awt.*;

class TrackerPanel extends JPanel {
    public TrackerPanel(GameManager gm, int playerNumber, int unitNumber) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        PlayerUnitPanel[] playerUnitPanels = new PlayerUnitPanel[playerNumber];

        for(int i = 0; i < playerNumber; i++) {
            String playerName = "Player " + (i + 1);
            Color playerColor = switch(i + 1) {
                case 1 -> Color.RED;
                case 2 -> Color.BLUE;
                case 3 -> Color.GREEN;
                case 4 -> Color.YELLOW;
                default -> throw new IllegalStateException("Unexpected value: " + i + 1);
            };
            playerUnitPanels[i] = new PlayerUnitPanel(gm, playerName, playerColor, unitNumber);
        }

        for (PlayerUnitPanel p : playerUnitPanels) {
            p.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(p);
        }
    }
}