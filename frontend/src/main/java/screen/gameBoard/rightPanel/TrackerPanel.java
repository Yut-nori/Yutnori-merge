package screen.gameBoard.rightPanel;


import gameControl.GameManager;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import util.UIConstants;

public class TrackerPanel extends VBox {

    // ** Constructor **
    public TrackerPanel(GameManager gm, int playerNumber, int unitNumber) {

        // [1] VBox 기본 설정
        setSpacing(10);
        setPrefWidth(UIConstants.RIGHT_PANEL_WIDTH);
        setStyle("-fx-background-color: transparent;");
        setFillWidth(true);

        // [2] 플레이어별 PlayerUnitPanel 생성 및 추가
        for (int i = 0; i < playerNumber; i++) {
            String playerName = "Player " + (i + 1);

            // [3] 플레이어별 색상 설정
            Color playerColor = switch (i + 1) {
                case 1 -> UIConstants.PLAYER1_UNIT_COLOR;
                case 2 -> UIConstants.PLAYER2_UNIT_COLOR;
                case 3 -> UIConstants.PLAYER3_UNIT_COLOR;
                case 4 -> UIConstants.PLAYER4_UNIT_COLOR;
                default -> throw new IllegalStateException("Unexpected value: " + (i + 1));
            };

            // [4] 개별 PlayerUnitPanel 추가
            PlayerUnitPanel panel = new PlayerUnitPanel(gm, playerName, playerColor, unitNumber);
            getChildren().add(panel);
        }
    }
}
