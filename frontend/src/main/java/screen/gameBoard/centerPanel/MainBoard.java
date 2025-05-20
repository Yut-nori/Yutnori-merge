package screen.gameBoard.centerPanel;

import screen.gameBoard.rightPanel.UnitIcon;
import gameControl.GameManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static screen.gameBoard.centerPanel.UnitPosition.*;
import static util.File.getFileName;
import static util.File.imageLoading;

public class MainBoard extends Pane {

    private final String screenName = "GameBoard";
    private final Map<String, Image> images = new HashMap<>();
    private final int shape;
    private final GameManager gm;
    private final Canvas canvas;

    public MainBoard(GameManager gm) {
        this.gm = gm;
        this.shape = gm.getGameState().getShape();

        switch (shape) {
            case 4 -> {
                setLayoutX(310);
                setLayoutY(60);
                setPrefSize(660, 640);
            }
            case 5 -> {
                setLayoutX(315);
                setLayoutY(70);
                setPrefSize(690, 690);
            }
            case 6 -> {
                setLayoutX(320);
                setLayoutY(60);
                setPrefSize(660, 660);
            }
        }

        List<String> imageNames = getFileName(screenName);
        images.putAll(imageLoading(imageNames, screenName));

        // [1] 캔버스 생성 및 배경 이미지 그리기
        canvas = new Canvas(getPrefWidth(), getPrefHeight());
        drawBoardBackground();
        getChildren().add(canvas);

        // [2] 말 추가
        int[][] unitPosition = gm.getGameState().getUnitPosition();
        int[][] unitGrouped = gm.getGameState().getUnitNumberPerPosition();

        for (int i = 0; i < unitPosition.length; i++) {
            Color color = switch (i + 1) {
                case 1 -> Color.RED;
                case 2 -> Color.BLUE;
                case 3 -> Color.GREEN;
                case 4 -> Color.YELLOW;
                default -> throw new IllegalStateException("Unexpected value: " + i);
            };

            for (int j = 0; j < unitPosition[i].length; j++) {
                if (unitPosition[i][j] == -1 || unitGrouped[i][j] == 0) continue;

                int x, y;
                switch (shape) {
                    case 4 -> {
                        x = rectangleUnitPositions[unitPosition[i][j]][0];
                        y = rectangleUnitPositions[unitPosition[i][j]][1];
                    }
                    case 5 -> {
                        x = pentagonUnitPositions[unitPosition[i][j]][0];
                        y = pentagonUnitPositions[unitPosition[i][j]][1];
                    }
                    case 6 -> {
                        x = hexagonUnitPositions[unitPosition[i][j]][0];
                        y = hexagonUnitPositions[unitPosition[i][j]][1];
                    }
                    default -> throw new IllegalStateException("Unexpected shape: " + shape);
                }

                addUnit(i, j, x, y, unitGrouped[i][j], color);
            }
        }
    }

    private void addUnit(int playerNum, int unitNum, int x, int y, int groupedNum, Color color) {
        UnitIcon unit = new UnitIcon(gm, color, playerNum, unitNum, groupedNum);
        unit.setLayoutX(x);
        unit.setLayoutY(y);
        getChildren().add(unit);
    }

    private void drawBoardBackground() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image bg = switch (shape) {
            case 4 -> images.get("rectangle.png");
            case 5 -> images.get("pentagon.png");
            case 6 -> images.get("hexagon.png");
            default -> null;
        };

        if (bg != null) {
            gc.drawImage(bg, 0, 0, canvas.getWidth(), canvas.getHeight());
        }
    }
}
