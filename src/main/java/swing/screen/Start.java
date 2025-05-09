package swing.screen;


import swing.ScreenManager;
import swing.util.GlobalButtonListener;
import swing.util.ImageRenderer;

import java.util.HashMap;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

import static swing.util.File.getFileName;
import static swing.util.File.imageLoading;
import static swing.util.Button.createButtonIfExists;

public class Start extends JPanel {
    private final String screenName = "start";

    private Map<String, BufferedImage> images;

    public Start(ScreenManager sm) {
        setLayout(null);

        // 배경 이미지 로딩
        List<String> imageNames = getFileName(screenName);
        images = imageLoading(imageNames, screenName);

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

        if (startButton != null) add(startButton);
        if (exitButton != null) add(exitButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (images.get("background.png") != null) {
            g.drawImage(images.get("background.png"), 0, 0, getWidth(), getHeight(), this);
        }

        // 이미지 이름, x, y 좌표 정의
        Object[][] imageData = {
                {"gameBoard.png", 50, 170},
                {"yut.png", 100, 40},
                {"mascort.png", 520, 300},
                {"mascortAdd.png", 1040, 60},
                {"gameName.png", 700, 60}
        };

        ImageRenderer.renderImages(g, this, images, imageData);
    }

}
