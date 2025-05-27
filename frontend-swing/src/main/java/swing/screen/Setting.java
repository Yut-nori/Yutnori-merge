package swing.screen;

import swing.GameManager;
import swing.ScreenManager;
import swing.util.GlobalButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static swing.util.Button.createButtonIfExists;
import static swing.util.ComboBox.createStyledComboBox;
import static swing.util.File.getFileName;
import static swing.util.File.imageLoading;
import static swing.util.ImageRenderer.renderImages;

public class Setting extends JPanel {

    // ** 멤버 변수 **
    private final String screenName = "setting";
    private Map<String, BufferedImage> images;

    // ** Constructor **
    public Setting(ScreenManager sm) {
        
        // [1] 레이아웃을 null로 설정
        setLayout(null);

        // [2] 배경 이미지 로딩
        List<String> imageNames = getFileName(screenName);
        this.images = imageLoading(imageNames, screenName);

        // [3] 게임 옵션을 선택하는 콤보박스 생성
        JComboBox<String> playerComboBox = createStyledComboBox(
                new String[]{"2명", "3명", "4명"}, 500, 215, 300, 70
        );
        JComboBox<String> unitComboBox = createStyledComboBox(
                new String[]{"2개", "3개", "4개", "5개"}, 500, 330, 300, 70
        );
        JComboBox<String> shapeComboBox = createStyledComboBox(
                new String[]{"사각형", "오각형", "육각형"}, 500, 490, 300, 70
        );
        JComboBox<String> testComboBox = createStyledComboBox(
                new String[]{"지정 윷", "랜덤 윷"}, 900, 330, 200, 70
        );
        
        // [4] 패널에 콤보박스 추가
        add(playerComboBox);
        add(unitComboBox);
        add(shapeComboBox);
        add(testComboBox);

        // [5] 게임 시작, 게임 종료 버튼 생성
        JButton startButton;
        JButton exitButton;
        startButton = createButtonIfExists(
                imageNames, "startButton.png", "setting", 500, 580,
                e -> {

                    // [5.1] 선택된 옵션을 가져오기
                    int selectedPlayerNum = playerComboBox.getSelectedIndex() + 2;
                    int selectedUnitNum = unitComboBox.getSelectedIndex() + 2;
                    int selectedShapeNum = shapeComboBox.getSelectedIndex() + 4;
                    boolean selectedTest = testComboBox.getSelectedIndex() == 0;

                    // [5.2] 선택된 옵션을 GameManager 에게 전달하여 GameState 초기화
                    GameManager gm = sm.getGm();
                    gm.getGameState().initiateState(selectedPlayerNum, selectedUnitNum, selectedShapeNum, selectedTest);

                    // [5.3] GameManager 에게 API 호출 요청
                    gm.apiSetOption(selectedPlayerNum, selectedUnitNum, selectedShapeNum, selectedTest);

                    // [5.4] 게임 시작 화면으로 전환
                    sm.gameBoard();
                }
        );
        exitButton = createButtonIfExists(
                imageNames, "exitButton.png", "setting", 1030, 570,
                new GlobalButtonListener(sm, "exit")
        );

        // [6] 패널에 버튼 추가
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
                {"screenName.png", 250, 30},
                {"playerNum.png", 150, 210},
                {"unitNum.png", 150, 330},
                {"boardShape.png", 130, 500}
        };

        // [3] 이미지 그리기
        renderImages(g, this, images, imageData);

    }

}
