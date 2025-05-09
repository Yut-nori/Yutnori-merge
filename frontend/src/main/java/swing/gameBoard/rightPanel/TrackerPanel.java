package swing.gameBoard.rightPanel;

import swing.GameManager;
import swing.util.UIConstants;

import javax.swing.*;
import java.awt.*;

class TrackerPanel extends JPanel {

    // ** Constructor **
    public TrackerPanel(GameManager gm, int playerNumber, int unitNumber) {

        // [1] 패널 기본 설정
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        // [2] 플레이어별 말 개수를 나타내는 패널을 생성
        PlayerUnitPanel[] playerUnitPanels = new PlayerUnitPanel[playerNumber];

        // [3] 플레이어별 텍스트와 말의 색상 설정
        for(int i = 0; i < playerNumber; i++) {
            String playerName = "Player " + (i + 1);
            Color playerColor = switch(i + 1) {
                case 1 -> UIConstants.PLAYER1_UNIT_COLOR;
                case 2 -> UIConstants.PLAYER2_UNIT_COLOR;
                case 3 -> UIConstants.PLAYER3_UNIT_COLOR;
                case 4 -> UIConstants.PLAYER4_UNIT_COLOR;
                default -> throw new IllegalStateException("Unexpected value: " + i + 1);
            };
            playerUnitPanels[i] = new PlayerUnitPanel(gm, playerName, playerColor, unitNumber);
        }

        // [4] 컴포넌트 가운데 정렬 후 패널을 TrackerPanel에 추가
        for (PlayerUnitPanel p : playerUnitPanels) {
            p.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(p);
        }
    }

}