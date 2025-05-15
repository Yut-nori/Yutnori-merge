package gameBoard.rightPanel;

import gameControl.GameManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.UIConstants;

public class PlayerUnitPanel extends VBox {

    /**
     * 이후에 수정이 필요한 부분
     * 1. unitNumber의 경우 말판 위에 나가있는 개수만큼 빼줘야함
     */

    public PlayerUnitPanel(GameManager gm, String playerName, Color unitColor, int unitNumber) {

        // [1] VBox 기본 설정
        setSpacing(5);
        setPadding(new Insets(5));
        setPrefWidth(120);  // 예시값
        setStyle("-fx-background-color: transparent;");

        // [2] 플레이어 이름 라벨
        Label nameLabel = new Label(playerName);
        nameLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        nameLabel.setFont(Font.font(UIConstants.PLAYER_UNIT_TRACKER_FONT, 16));
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.setPrefSize(70, 40);

        // [3] Ready 상태 유닛들을 담는 FlowPane
        FlowPane circlePane = new FlowPane();
        circlePane.setHgap(10);
        circlePane.setVgap(5);
        circlePane.setPrefWrapLength(100);
        circlePane.setAlignment(Pos.CENTER);
        circlePane.setStyle("-fx-background-color: transparent;");

        // [4] 플레이어 인덱스 추출
        int playerIndex = switch (playerName) {
            case "Player 1" -> 0;
            case "Player 2" -> 1;
            case "Player 3" -> 2;
            case "Player 4" -> 3;
            default -> -1;
        };

        // [5] Ready 상태(말판에 나가지 않은 -1 위치)의 말들만 추가
        int[][] unitPositions = gm.getGameState().getUnitPosition();
        for (int i = 0; i < unitPositions[0].length; i++) {
            int position = unitPositions[playerIndex][i];
            if (position == -1) {
                UnitIcon icon = new UnitIcon(gm, unitColor, playerIndex, i, 1);
                circlePane.getChildren().add(icon);
            }
        }

        // [6] 구성 요소 VBox에 추가
        getChildren().addAll(nameLabel, circlePane);
    }
}
