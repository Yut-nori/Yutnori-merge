package swing;

import swing.util.UIConstants;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {

        setTitle("YutNori");
        setSize(UIConstants.FRAME_WIDTH, UIConstants.FRAME_HEIGHT + 30); // 30은 제목 표시줄 높이
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 중앙 정렬

        GameManager gm = new GameManager(new GameState());
        ScreenManager sm = new ScreenManager(this, gm);
        gm.setScreenManager(sm);

    }
}
