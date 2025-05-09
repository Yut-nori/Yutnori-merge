package swing.gameBoard.topPanel;

import swing.GameManager;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {

    // ** Constructor **
    public TopPanel(GameManager gm) {

        // [1] 레이아웃을 null 로 설정
        setLayout(null);

        // [2] 패널의 위치와 크기 설정
        setBounds(310, 0, 660, 60);

        // [3] 텍스트 라벨 설정
        JLabel turnLabel = new JLabel("Player " + gm.getGameState().getCurrentPlayer() + 1 + "'s turn : " + gm.getGameState().getEvent());
        turnLabel.setFont(new Font("Arial", Font.BOLD, 30));
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