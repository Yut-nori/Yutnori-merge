package swing.screen;

import swing.ScreenManager;
import swing.util.GlobalButtonListener;
import swing.util.ImageRenderer;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

import static swing.util.File.getFileName;
import static swing.util.File.imageLoading;
import static swing.util.Button.createButtonIfExists;

public class Start extends JPanel {

    // ** 멤버 변수 **
    private final String screenName = "start";
    private Map<String, BufferedImage> images;

    // ** Constructor **
    public Start(ScreenManager sm) {

        // [1] 레이아웃을 null로 설정
        setLayout(null);

        // [2] 배경 이미지 로딩
        List<String> imageNames = getFileName(screenName);
        this.images = imageLoading(imageNames, screenName);

        // [3] 게임 시작, 게임 종료 버튼 생성
        JButton startButton = null;
        JButton exitButton = null;
        startButton = createButtonIfExists(
                imageNames, "startButton.png", "start", 820, 280,
                new GlobalButtonListener(sm, "settingPage")
        );
        exitButton  = createButtonIfExists(
                imageNames, "exitButton.png", "start", 850, 460,
                new GlobalButtonListener(sm, "exit")
        );

        // [4] 패널에 버튼 추가
        if (startButton != null) add(startButton);
        if (exitButton != null) add(exitButton);
    }

    // 패널이 그려질 때 호출되는 메서드
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // [1] 배경 이미지 그리기
        if (images.get("background.png") != null) {
            g.drawImage(images.get("background.png"), 0, 0, getWidth(), getHeight(), this);
        }

        // [2] 이미지 이름, x, y 좌표 정의
        Object[][] imageData = {
                {"gameBoard.png", 50, 170},
                {"yut.png", 100, 40},
                {"mascort.png", 520, 300},
                {"mascortAdd.png", 1040, 60},
                {"gameName.png", 700, 60}
        };

        // [3] 이미지 그리기
        ImageRenderer.renderImages(g, this, images, imageData);
    }

}
