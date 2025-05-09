package swing.gameBoard.rightPanel;

import swing.GameManager;

import javax.swing.*;

public class RightPanel extends JPanel {
    public RightPanel(GameManager gm) {
        setOpaque(false);
        setLayout(null);
        setBounds(970, 0, 310, 720);

        // 플레이어 유닛 트래커 패널 생성
        PlayerUnitTracker playerUnitTracker = new PlayerUnitTracker(gm);
        playerUnitTracker.setBounds(0, 0, 310, 720);

        // 메뉴 패널 생성
        MenuPanel menuPanel = new MenuPanel(gm);
        menuPanel.setBounds(0, 580, 310, 140);


        add(playerUnitTracker);
        add(menuPanel);
    }

}