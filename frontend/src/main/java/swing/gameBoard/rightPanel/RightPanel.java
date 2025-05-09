package swing.gameBoard.rightPanel;

import swing.GameManager;
import swing.util.UIConstants;

import javax.swing.*;

public class RightPanel extends JPanel {

    // ** Constructor **
    public RightPanel(GameManager gm) {

        // [1] 투명도
        setOpaque(false);

        // [2] 레이아웃을 null 로 설정
        setLayout(null);

        // [3] 패널의 위치와 크기 설정
        setBounds(UIConstants.RIGHT_PANEL_START_X, UIConstants.RIGHT_PANEL_START_Y, UIConstants.RIGHT_PANEL_WIDTH, UIConstants.RIGHT_PANEL_HEIGHT);

        // [4] 플레이어 유닛 tracker 패널 생성 및 위치와 크기 설정
        PlayerUnitTracker playerUnitTracker = new PlayerUnitTracker(gm);
        playerUnitTracker.setBounds(0, 0, UIConstants.RIGHT_PANEL_WIDTH, UIConstants.RIGHT_PANEL_HEIGHT);

        // [5] 메뉴 패널 생성
        MenuPanel menuPanel = new MenuPanel(gm);
        menuPanel.setBounds(0, MENU_PANEL_START_Y, UIConstants.RIGHT_PANEL_WIDTH, UIConstants.MENU_PANEL_HEIGHT);

        // [6] 패널에 붙이기
        add(playerUnitTracker);
        add(menuPanel);
    }

}