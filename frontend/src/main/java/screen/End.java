package screen;

import gameControl.GlobalButtonListener;
import gameControl.ScreenManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.ButtonUtil;
import util.ImageRenderer;

import java.util.List;
import java.util.Map;

import static util.File.getFileName;
import static util.File.imageLoading;

public class End extends Pane {

    private final String screenName = "end";
    private final Map<String, Image> images;

    public End(ScreenManager sm) {
        setPrefSize(1280, 720);

        // [1] 배경 이미지 로딩
        List<String> imageNames = getFileName(screenName);
        images = imageLoading(imageNames, screenName);

        // [2] 배경 이미지 설정
        if (images.containsKey("background.png")) {
            ImageView background = new ImageView(images.get("background.png"));
            background.setFitWidth(1280);
            background.setFitHeight(720);
            getChildren().add(background);
        }

        // [3] 승자 라벨 표시
        int winner = sm.getGameManager().getGameState().getCurrentPlayer() + 1;
        if (sm.getGameManager().getGameState().getYutResults().isEmpty()) {
            if (winner == 1)
                winner = sm.getGameManager().getGameState().getTotalPlayerNumber();
            else
                winner -= 1;
        }

        Label winnerLabel = new Label("Winner: " + winner);
        winnerLabel.setFont(new Font("Arial", 50));
        winnerLabel.setTextFill(Color.WHITE);
        winnerLabel.setLayoutX(520);
        winnerLabel.setLayoutY(180);
        getChildren().add(winnerLabel);

        // [4] 버튼 생성
        Button restartButton = ButtonUtil.createButtonIfExists(
                imageNames, "startButton.png", screenName, 400, 280,
                new GlobalButtonListener(sm, "setting"), images
        );

        Button exitButton = ButtonUtil.createButtonIfExists(
                imageNames, "exitButton.png", screenName, 470, 460,
                new GlobalButtonListener(sm, "exit"), images
        );

        getChildren().addAll(restartButton, exitButton);

        // [5] 이미지 추가 (Canvas 이용)
        Canvas canvas = new Canvas(1280, 720);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Object[][] imageData = {
                {"man.png", 50, 100},
                {"woman.png", 850, 100},
                {"title.png", 480, 30},
        };

        ImageRenderer.renderImages(gc, this, images, imageData);
        getChildren().add(canvas);
        canvas.setMouseTransparent(true);
    }
}

