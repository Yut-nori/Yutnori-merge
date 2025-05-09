package swing.gameBoard.topPanel;

import swing.GameManager;
import swing.util.UIConstants;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {

    // ** 생성자 **
    public TopPanel(GameManager gm) {

        // [1] 패널 기본 설정
        setLayout(null);
        setOpaque(false);

        // [2] 패널의 위치와 크기 설정
        setBounds(UIConstants.TOP_PANEL_START_X, UIConstants.TOP_PANEL_START_Y, UIConstants.TOP_PANEL_WIDTH, UIConstants.TOP_PANEL_HEIGHT );

        // [3] 텍스트 라벨 설정
        int currentPlayerNum = gm.getGameState().getCurrentPlayer() + 1;
        JLabel turnLabel = new JLabel("Player " + currentPlayerNum + "'s turn : " + gm.getGameState().getEvent());
        turnLabel.setFont(new Font(UIConstants.DEFAULT_FONT, Font.BOLD, 30));
        turnLabel.setForeground(Color.WHITE);

        // Locate label to center
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        turnLabel.setVerticalAlignment(SwingConstants.CENTER);
        turnLabel.setBounds(0, 0, 660, 60);

        // Set panel and label to transparent
        setOpaque(false);

        add(turnLabel);
    }
}