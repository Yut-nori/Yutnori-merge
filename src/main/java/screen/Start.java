package screen;

import gameControl.GlobalButtonListener;
import gameControl.ScreenManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import util.*;

import java.util.List;
import java.util.Map;

public class Start extends Pane {

    private final String screenName = "start";
    private Map<String, Image> images;

    public Start(ScreenManager sm) {
        setPrefSize(1280, 720);

        // [1] 이미지 로딩
        List<String> imageNames = File.getFileName(screenName);
        images = File.imageLoading(imageNames, screenName);

        // [2] 배경 이미지
        Image bg = images.get("background.png");
        if (bg != null) {
            ImageView bgView = new ImageView(bg);
            bgView.setFitWidth(1280);
            bgView.setFitHeight(720);
            getChildren().add(bgView);
        }

        // [3] 캔버스에 기타 이미지 그리기
        Canvas canvas = new Canvas(1280, 720);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Object[][] imageData = {
                {"gameBoard.png", 50, 170},
                {"yut.png", 100, 40},
                {"mascort.png", 520, 300},
                {"mascortAdd.png", 1040, 60},
                {"gameName.png", 700, 60}
        };
        ImageRenderer.renderImages(gc, this, images, imageData);
        getChildren().add(canvas);

        // [4] 버튼 추가 (유틸 메서드 활용)
        Button startBtn = ButtonUtil.createButtonIfExists(
                imageNames, "startButton.png", screenName, 820, 280,
                new GlobalButtonListener(sm, "settingPage"), images);
        Button exitBtn = ButtonUtil.createButtonIfExists(
                imageNames, "exitButton.png", screenName, 850, 460,
                new GlobalButtonListener(sm, "exit"), images);

        if (startBtn != null) getChildren().add(startBtn);
        if (exitBtn != null) getChildren().add(exitBtn);
    }
}

