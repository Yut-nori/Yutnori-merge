package swing.gameBoard;

import swing.ScreenManager;
import swing.gameBoard.centerPanel.MainBoard;
import swing.gameBoard.rightPanel.RightPanel;
import swing.gameBoard.leftPanel.LeftPanel;
import swing.gameBoard.topPanel.TopPanel;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.awt.*;
import java.util.Map;

import static swing.util.File.getFileName;
import static swing.util.File.imageLoading;

public class GameBoard extends JPanel {

    // ** 멤버 변수 **
    private final String screenName = "GameBoard";
    private final Map<String, BufferedImage> images;

    // ** Constructor **
    public GameBoard(ScreenManager sm) {

        // [1] 레이아웃을 null 로 설정
        setLayout(null);

        // [2] 게임 내에서 모든 패널들을 붙이는 전체 container 역할 패널 세팅 (gameBoard 화면에서)
        sm.getGm().setPanelContainer(this);

        // [3] 배경 이미지 로딩
        List<String> imageNames = getFileName(screenName);
        images = imageLoading(imageNames, screenName);

        // [4] 패널 생성
        MainBoard mainBoard = new MainBoard(sm.getGm());
        LeftPanel leftPanel = new LeftPanel(sm.getGm());
        RightPanel rightPanel = new RightPanel(sm.getGm());
        TopPanel topPanel = new TopPanel(sm.getGm());

        // [5] 패널 추가
        add(mainBoard);
        add(leftPanel);
        add(rightPanel);
        add(topPanel);
    }

    // 패널이 그려질 때 호출되는 메서드
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 배경 이미지 그리기
        if (images.get("background.png") != null) {
            g.drawImage(images.get("background.png"), 0, 0, getWidth(), getHeight(), this);
        }
    }

}







