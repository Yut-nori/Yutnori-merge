package swing.gameBoard.rightPanel;

import swing.GameManager;
import swing.util.GlobalButtonListener;
import swing.util.UIConstants;

import javax.swing.*;
import java.awt.*;

class MenuPanel extends JPanel {

    // ** Constructor **
    public MenuPanel(GameManager gm) {

        // [1] 투명도 설정
        setOpaque(false);

        // [2] 레이아웃을 null 로 설정
        setLayout(null);

        // [3] 게임 종료 버튼 생성
        JButton exitButton = new JButton("게임 종료");
        exitButton.setFont(new Font(UIConstants.BUTTON_FONT, Font.BOLD, 20));
        exitButton.setBounds(30, 35, 250, 85);
        exitButton.setBackground(Color.LIGHT_GRAY);
        exitButton.setForeground(Color.BLACK);
        exitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // [4] 버튼 리스너 할당
        exitButton.addActionListener(new GlobalButtonListener(gm, "exit"));

        // [5] 패널에 추가
        add(exitButton);
    }

}