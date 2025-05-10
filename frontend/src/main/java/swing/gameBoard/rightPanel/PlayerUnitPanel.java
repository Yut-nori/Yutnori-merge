package swing.gameBoard.rightPanel;

import swing.GameManager;
import swing.util.UIConstants;

import javax.swing.*;
import java.awt.*;

class PlayerUnitPanel extends JPanel {

    /**
     * 이후에 수정이 필요한 부분
     * 1. unitNumber의 경우 말판 위에 나가있는 개수만큼 빼줘야함
     */

    // ** Constructor **
    public PlayerUnitPanel(GameManager gm, String playerName, Color unitColor, int unitNumber) {

        // [1] 패널 기본 설정
        setLayout(new BorderLayout());
        setOpaque(false);

        // [2] 플레이어 정보 텍스트 라벨 생성
        JLabel nameLabel = new JLabel(playerName);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font(UIConstants.PLAYER_UNIT_TRACKER_FONT, Font.BOLD, 16));
        nameLabel.setPreferredSize(new Dimension(70, 40));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // [3] Ready 중인 플레이어의 말을 그리는 패널
        JPanel circlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        circlePanel.setOpaque(false);

        // [4] 텍스트에 따라 플레이어 인덱스 설정
        int playerIndex = switch (playerName) {
            case "Player 1" -> 0;
            case "Player 2" -> 1;
            case "Player 3" -> 2;
            case "Player 4" -> 3;
            default -> -1;
        };

        /**
         * player의 back에서 아직 출발하지 않은 Ready 상태의 개수에 있는 것만 출력
         */
        // [5] 현재 상태의 말들의 위치에 따라 해당 위치 정보에 따라 그리기
        int[][] unitPositions = gm.getGameState().getUnitPosition();
        int[][] unitNumPerPositions = gm.getGameState().getUnitNumberPerPosition();
        for (int i = 0; i < unitPositions[0].length; i++) {
            // [5.1] 위치 정보 index 가져오기
            int position = unitPositions[playerIndex][i];
            // [5.2] Ready 상태의 말들을 추가
            if(position == -1)  circlePanel.add(new UnitIcon(gm, unitColor, playerIndex, i, 1));
        }

        // [6] 패널에 주가
        add(nameLabel, BorderLayout.NORTH);
        add(circlePanel, BorderLayout.CENTER);
    }

}