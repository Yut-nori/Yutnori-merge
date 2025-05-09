package swing.screen;

import swing.MainFrame;
import swing.ScreenManager;
import swing.util.GlobalButtonListener;
import swing.util.ImageRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static swing.util.Button.createButtonIfExists;
import static swing.util.Button.createImageButton;
import static swing.util.File.getFileName;
import static swing.util.File.imageLoading;
import static swing.util.ImageRenderer.renderImages;

public class End extends JPanel {
    private final String screenName = "end";

    private Map<String, BufferedImage> images = new HashMap<>();

    public End(ScreenManager sm) {
        setLayout(null);

        // 배경 이미지 로딩
        List<String> imageNames = getFileName(screenName);
        images = imageLoading(imageNames, screenName);

        JButton startButton = null;
        JButton exitButton = null;
        startButton = createButtonIfExists(
                imageNames, "startButton.png", "end", 400, 280,
                new GlobalButtonListener(sm, "settingPage")
        );

        exitButton = createButtonIfExists(
                imageNames, "exitButton.png", "end", 470, 460,
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

        Object[][] imageData = {
                {"man.png", 50, 100},
                {"woman.png", 850, 100},
                {"title.png", 480, 30},
                {"winner.png", 450, 180}
        };

        renderImages(g, this, images, imageData);

    }
}
