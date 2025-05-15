package gameBoard;


import gameBoard.centerPanel.MainBoard;
import gameBoard.leftPanel.LeftPanel;
import gameBoard.rightPanel.RightPanel;
import gameBoard.topPanel.TopPanel;
import gameControl.ScreenManager;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.Map;

import static util.File.getFileName;
import static util.File.imageLoading;

public class GameBoard extends Pane {

    // ** 멤버 변수 **
    private final String screenName = "GameBoard";
    private final Map<String, Image> images;
    private final Canvas canvas;
    private BorderPane rootPane = new BorderPane();


    // ** Constructor **
    public GameBoard(ScreenManager sm) {
        setPrefSize(1280, 720); // 전체 게임 보드 크기
        setStyle("-fx-background-color: transparent;");

        // [1] 배경 이미지 로딩
        List<String> imageNames = getFileName(screenName);
        images = imageLoading(imageNames, screenName);

        // [2] 캔버스를 통한 배경 이미지 그리기
        canvas = new Canvas(1280, 720);
        drawBackground();
        getChildren().add(canvas);

        // [3] GameManager에 현재 Pane 참조 설정
        sm.getGameManager().setRootPane(rootPane);

        // [4] 패널 생성
        rootPane.setLeft(new LeftPanel(sm.getGameManager()));
        rootPane.setRight(new RightPanel(sm.getGameManager()));
        rootPane.setTop(new TopPanel(sm.getGameManager()));
        rootPane.setCenter(new MainBoard(sm.getGameManager()));


        // [5] 패널 추가
        getChildren().add(rootPane);
    }

    // 배경 이미지 그리기
    private void drawBackground() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image bg = images.get("background.png");
        if (bg != null) {
            gc.drawImage(bg, 0, 0, canvas.getWidth(), canvas.getHeight());
        }
    }
}
