package swing.gameBoard.rightPanel;

import swing.GameManager;
import swing.util.GlobalButtonListener;

import javax.swing.*;
import java.awt.*;

class MenuPanel extends JPanel {

    // ** Constructor **
    public MenuPanel(GameManager gm) {
        setLayout(null);
        setOpaque(false);

        // Exit Button
        JButton exitButton = new JButton("게임 종료");
        exitButton.setFont(new Font("굴림", Font.BOLD, 20));
        exitButton.setBounds(30, 35, 250, 85);
        exitButton.setBackground(Color.LIGHT_GRAY);
        exitButton.setForeground(Color.BLACK);
        exitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        exitButton.addActionListener(new GlobalButtonListener(gm, "exit"));

        add(exitButton);

    }
}