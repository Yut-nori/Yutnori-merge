package swing.gameBoard.rightPanel;

import swing.GameManager;
import swing.util.UIConstants;

import javax.swing.*;
import java.awt.*;

class PlayerUnitTrackerPanel extends JPanel {
    
    // ** Constructor **
    public PlayerUnitTrackerPanel(GameManager gm) {

        // [1] 패널 기본 설정
        setLayout(null);
        setOpaque(false);

        // [2] 타이틀 라벨 생성
        JLabel titleLabel = new JLabel("Player Unit Tracker");
        titleLabel.setFont(new Font(UIConstants.DEFAULT_FONT, Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(0, 0, UIConstants.RIGHT_PANEL_WIDTH, 140);

        // [3] PlayerUnitPanel을 감싸는 패널 생성
        TrackerPanel trackerPanel = new TrackerPanel(gm, gm.getGameState().getTotalPlayerNumber(), gm.getGameState().getUnitNumberPerPlayer());
        trackerPanel.setBounds(0, 140, UIConstants.RIGHT_PANEL_WIDTH, 440);

        // [4] 패널에 추가
        add(titleLabel);
        add(trackerPanel);
    }

}