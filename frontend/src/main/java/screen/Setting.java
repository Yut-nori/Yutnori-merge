package screen;

import gameControl.GameManager;
import gameControl.GlobalButtonListener;
import gameControl.ScreenManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import util.ComboBoxUtil;
import util.File;
import util.ImageRenderer;

import java.util.List;
import java.util.Map;

import static util.ButtonUtil.createButtonIfExists;
import static util.File.getFileName;

public class Setting extends Pane {

    private final String screenName = "setting";
    private final Map<String, Image> images;

    public Setting(ScreenManager sm) {
        setPrefSize(1280, 720);

        // [1] 배경 이미지 로딩
        List<String> imageNames = getFileName(screenName);
        images = File.imageLoading(imageNames, screenName);

        // [2] 배경 이미지 설정
        if (images.containsKey("background.png")) {
            ImageView background = new ImageView(images.get("background.png"));
            background.setFitWidth(1280);
            background.setFitHeight(720);
            getChildren().add(background);
        }

        // [3] 콤보박스 생성
        ComboBox<String> playerComboBox = ComboBoxUtil.createStyledComboBox(
                new String[]{"2명", "3명", "4명"}, 500, 215, 300, 70
        );
        ComboBox<String> unitComboBox = ComboBoxUtil.createStyledComboBox(
                new String[]{"2개", "3개", "4개", "5개"}, 500, 330, 300, 70
        );
        ComboBox<String> shapeComboBox = ComboBoxUtil.createStyledComboBox(
                new String[]{"사각형", "오각형", "육각형"}, 500, 490, 300, 70
        );
        ComboBox<String> testComboBox = ComboBoxUtil.createStyledComboBox(
                new String[]{"지정 윷", "랜덤 윷"}, 900, 330, 200, 70
        );

        getChildren().addAll(playerComboBox, unitComboBox, shapeComboBox, testComboBox);

        // [4] 시작 버튼
        Button startButton = createButtonIfExists(imageNames, "startButton.png", screenName, 500, 580
        ,e -> {
            int selectedPlayerNum = playerComboBox.getSelectionModel().getSelectedIndex() + 2;
            int selectedUnitNum = unitComboBox.getSelectionModel().getSelectedIndex() + 2;
            int selectedShapeNum = shapeComboBox.getSelectionModel().getSelectedIndex() + 4;
            boolean selectedTest = testComboBox.getSelectionModel().getSelectedIndex() == 0;

            GameManager gm = sm.getGameManager();
            gm.getGameState().initiateState(selectedPlayerNum, selectedUnitNum, selectedShapeNum, selectedTest);
            gm.apiSetOption(selectedPlayerNum, selectedUnitNum, selectedShapeNum, selectedTest);

            sm.gameBoard(); // 예: 다음 화면으로 전환
        }, images);

        // [5] 종료 버튼
        Button exitButton = createButtonIfExists(imageNames, "exitButton.png", screenName, 1030, 570,
                new GlobalButtonListener(sm, "exit"), images);

        getChildren().addAll(startButton, exitButton);

        // [6] 텍스트 이미지들
        Canvas canvas = new Canvas(1280, 720);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Object[][] imageData = {
                {"screenName.png", 250, 30},
                {"playerNum.png", 150, 210},
                {"unitNum.png", 150, 330},
                {"boardShape.png", 130, 500}
        };
        ImageRenderer.renderImages(gc, this, images, imageData);
        getChildren().add(canvas);
        canvas.setMouseTransparent(true);
    }
}
