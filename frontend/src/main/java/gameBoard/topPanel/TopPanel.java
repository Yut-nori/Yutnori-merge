package gameBoard.topPanel;

import gameControl.GameManager;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.UIConstants;

public class TopPanel extends Pane {

    public TopPanel(GameManager gm) {
        setPrefSize(UIConstants.TOP_PANEL_WIDTH, UIConstants.TOP_PANEL_HEIGHT);
        setLayoutX(UIConstants.TOP_PANEL_START_X);
        setLayoutY(UIConstants.TOP_PANEL_START_Y);
        setStyle("-fx-background-color: transparent;");

        // 현재 턴 텍스트 설정
        int currentPlayerNum = gm.getGameState().getCurrentPlayer() + 1;
        String turnText = "Player " + currentPlayerNum + "'s turn";
        String eventText = gm.getGameState().getEvent() == null ? "" : " / Last: " + gm.getGameState().getEvent();

        Label turnLabel = new Label(turnText + eventText);
        turnLabel.setFont(Font.font(UIConstants.DEFAULT_FONT, 30));
        turnLabel.setTextFill(Color.WHITE);
        turnLabel.setPrefSize(UIConstants.TOP_PANEL_WIDTH, UIConstants.TOP_PANEL_HEIGHT);
        turnLabel.setAlignment(Pos.CENTER);

        getChildren().add(turnLabel);
    }
}
