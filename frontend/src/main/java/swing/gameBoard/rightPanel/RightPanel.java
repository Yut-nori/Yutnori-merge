package swing.gameBoard.rightPanel;

import swing.GameManager;
import swing.util.UIConstants;

import javax.swing.*;

public class RightPanel extends JPanel {

    // ** Constructor **
    public RightPanel(GameManager gm) {

        // [1] 패널 기본 설정
        setLayout(null);
        setOpaque(false);

        // [2] 패널의 위치와 크기 설정
        setBounds(UIConstants.RIGHT_PANEL_START_X, UIConstants.RIGHT_PANEL_START_Y, UIConstants.RIGHT_PANEL_WIDTH, UIConstants.RIGHT_PANEL_HEIGHT);

        // [3] 플레이어 유닛 tracker 패널 생성 및 위치와 크기 설정
        PlayerUnitTrackerPanel playerUnitTrackerPanel = new PlayerUnitTrackerPanel(gm);
        playerUnitTrackerPanel.setBounds(0, 0, UIConstants.RIGHT_PANEL_WIDTH, UIConstants.RIGHT_PANEL_HEIGHT);

        // [4] 메뉴 패널 생성
        MenuPanel menuPanel = new MenuPanel(gm);
        menuPanel.setBounds(0, UIConstants.MENU_PANEL_START_Y, UIConstants.RIGHT_PANEL_WIDTH, UIConstants.MENU_PANEL_HEIGHT);

        // [5] 패널에 붙이기
        add(playerUnitTrackerPanel);
        add(menuPanel);
    }

}