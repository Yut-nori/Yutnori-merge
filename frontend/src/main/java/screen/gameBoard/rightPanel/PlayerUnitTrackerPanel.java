package screen.gameBoard.rightPanel;


import gameControl.GameManager;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.UIConstants;

public class PlayerUnitTrackerPanel extends Pane {

    public PlayerUnitTrackerPanel(GameManager gm) {

        // [1] 기본 패널 설정
        setStyle("-fx-background-color: transparent;");
        setPrefSize(UIConstants.RIGHT_PANEL_WIDTH, UIConstants.RIGHT_PANEL_HEIGHT);

        // [2] 타이틀 라벨 생성
        Label titleLabel = new Label("Player Unit Tracker");
        titleLabel.setFont(Font.font(UIConstants.DEFAULT_FONT, 30));
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setLayoutX(0);
        titleLabel.setLayoutY(0);
        titleLabel.setPrefWidth(UIConstants.RIGHT_PANEL_WIDTH);
        titleLabel.setPrefHeight(140);
        titleLabel.setStyle("-fx-alignment: center;"); // text alignment style

        // [3] TrackerPanel 생성 (플레이어 수 및 유닛 수 기반)
        int totalPlayers = gm.getGameState().getTotalPlayerNumber();
        int unitsPerPlayer = gm.getGameState().getUnitNumberPerPlayer();
        TrackerPanel trackerPanel = new TrackerPanel(gm, totalPlayers, unitsPerPlayer);
        trackerPanel.setLayoutX(0);
        trackerPanel.setLayoutY(140);
        trackerPanel.setPrefSize(UIConstants.RIGHT_PANEL_WIDTH, 440);

        // [4] 패널에 추가
        getChildren().addAll(titleLabel, trackerPanel);
    }
}
