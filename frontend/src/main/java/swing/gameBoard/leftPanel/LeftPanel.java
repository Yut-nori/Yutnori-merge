package swing.gameBoard.leftPanel;

import swing.GameManager;
import swing.util.UIConstants;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    public LeftPanel(GameManager gm) {
        setLayout(null);
        setOpaque(false);
        setBounds(0, 0, UIConstants.LEFT_PANEL_WDITH, UIConstants.FRAME_HEIGHT);

        // 윷 결과 패널 생성
        YutResult yutResult = new YutResult(gm.getGameState().getLastResult());
        yutResult.setBounds(0, 0, UIConstants.LEFT_PANEL_WDITH, 470);

        // 던지기 패널 생성
        ThrowControl throwControl = new ThrowControl(gm);
        throwControl.setBounds(0, 380, UIConstants.LEFT_PANEL_WDITH, 340);



        add(yutResult);
        add(throwControl);
    }
}

