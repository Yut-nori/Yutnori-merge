package swing.gameBoard.RightPanel;

import swing.GameManager;

import javax.swing.*;
import java.awt.*;

class PlayerUnitPanel extends JPanel {

    /**
     * 이후에 수정이 필요한 부분
     * 1. unitNumber의 경우 말판 위에 나가있는 개수만큼 빼줘야함
     */
    public PlayerUnitPanel(GameManager gm, String playerName, Color unitColor, int unitNumber) {
        setLayout(new BorderLayout());
        setOpaque(false);

        JLabel nameLabel = new JLabel(playerName);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        nameLabel.setPreferredSize(new Dimension(70, 40));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel circlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        circlePanel.setOpaque(false);

        int playerNum = switch (playerName) {
            case "Player 1" -> 0;
            case "Player 2" -> 1;
            case "Player 3" -> 2;
            case "Player 4" -> 3;
            default -> -1;
        };
        /**
         * player의 back에서 아직 출발하지 않은 Ready 상태의 개수에 있는 것만 출력
         */
        int[][] unitPositions = gm.getGameState().getUnitPosition();
        for (int i = 0; i < unitPositions.length; i++) {
            int position = gm.getGameState().getUnitPosition()[playerNum][i];
            if(position == -1)
                circlePanel.add(new UnitIcon(gm, unitColor, playerNum, i, 1));
        }

        add(nameLabel, BorderLayout.NORTH);
        add(circlePanel, BorderLayout.CENTER);
    }
}