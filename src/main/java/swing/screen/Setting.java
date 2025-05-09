package swing.screen;

import swing.GameManager;
import swing.MainFrame;
import swing.ScreenManager;
import swing.util.ComboBox;
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
import static swing.util.ComboBox.createStyledComboBox;
import static swing.util.File.getFileName;
import static swing.util.File.imageLoading;
import static swing.util.ImageRenderer.renderImages;

public class Setting extends JPanel {
    private final String screenName = "setting";

    private Map<String, BufferedImage> images = new HashMap<>();

    public Setting(ScreenManager sm) {
        setLayout(null);

        // 배경 이미지 로딩
        List<String> imageNames = getFileName(screenName);
        images = imageLoading(imageNames, screenName);

        JComboBox<String> playerComboBox = createStyledComboBox(
                new String[]{"2명", "3명", "4명"}, 500, 215, 300, 70
        );
        add(playerComboBox);

        JComboBox<String> unitComboBox = createStyledComboBox(
                new String[]{"2개", "3개", "4개", "5개"}, 500, 330, 300, 70
        );
        add(unitComboBox);

        JComboBox<String> shapeComboBox = createStyledComboBox(
                new String[]{"사각형", "오각형", "육각형"}, 500, 490, 300, 70
        );
        add(shapeComboBox);

        JComboBox<String> testComboBox = createStyledComboBox(
                new String[]{"지정 윷", "랜덤 윷"}, 900, 330, 200, 70
        );
        add(testComboBox);

        JButton startButton = null;
        JButton exitButton = null;
        startButton = createButtonIfExists(
                imageNames, "startButton.png", "setting", 500, 580,
                e -> {
                    int selectedPlayerNum = playerComboBox.getSelectedIndex() + 2;
                    int selectedUnitNum = unitComboBox.getSelectedIndex() + 2;
                    int selectedShapeNum = shapeComboBox.getSelectedIndex() + 4;
                    boolean selectedTest = testComboBox.getSelectedIndex() == 0 ? true : false;

                    GameManager gm = sm.getGm();
                    gm.getGameState().initiateState(selectedPlayerNum, selectedUnitNum, selectedShapeNum, selectedTest);

                    // api 호출
                    gm.apiSetOption(selectedPlayerNum, selectedUnitNum, selectedShapeNum, selectedTest);

                    sm.gameBoard();
                }
        );

        exitButton = createButtonIfExists(
                imageNames, "exitButton.png", "setting", 1030, 570,
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
                {"screenName.png", 250, 30},
                {"playerNum.png", 150, 210},
                {"unitNum.png", 150, 330},
                {"boardShape.png", 130, 500}
        };

        renderImages(g, this, images, imageData);

    }

}
