package swing.gameBoard.leftPanel;

import swing.GameManager;
import swing.util.UIConstants;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    
    // ** 생성자 **
    public LeftPanel(GameManager gm) {

        // [1] 패널 기본 설정
        setLayout(null);
        setOpaque(false);
        
        // [2] 패널의 크기와 위치 설정
        setBounds(0, 0, UIConstants.LEFT_PANEL_WDITH, UIConstants.FRAME_HEIGHT);

        // [3] 윷 결과 패널 생성
        YutResult yutResult = new YutResult(gm.getGameState().getLastResult());
        yutResult.setBounds(0, 0, UIConstants.LEFT_PANEL_WDITH, UIConstants.YUT_RESULT_PANEL_HEIGHT);

        // [4] 윷 던지기 버튼 패널 생성
        ThrowControl throwControl = new ThrowControl(gm);
        throwControl.setBounds(0, UIConstants.THROW_CONTROL_START_Y, UIConstants.LEFT_PANEL_WDITH, UIConstants.THROW_CONTROL_HEIGHT);

        // [5] 패널에 추가
        add(yutResult);
        add(throwControl);
    }

}

