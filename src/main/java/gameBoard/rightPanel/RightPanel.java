package gameBoard.rightPanel;


import gameControl.GameManager;
import javafx.scene.layout.Pane;
import util.UIConstants;

public class RightPanel extends Pane {

    // ** Constructor **
    public RightPanel(GameManager gm) {

        // [1] 패널 기본 설정
        setStyle("-fx-background-color: transparent;");

        // [2] 패널 위치 및 크기 설정
        setLayoutX(UIConstants.RIGHT_PANEL_START_X);
        setLayoutY(UIConstants.RIGHT_PANEL_START_Y);
        setPrefSize(UIConstants.RIGHT_PANEL_WIDTH, UIConstants.RIGHT_PANEL_HEIGHT);

        // [3] 플레이어 유닛 tracker 패널 생성
        PlayerUnitTrackerPanel playerUnitTrackerPanel = new PlayerUnitTrackerPanel(gm);
        playerUnitTrackerPanel.setLayoutX(0);
        playerUnitTrackerPanel.setLayoutY(0);
        playerUnitTrackerPanel.setPrefSize(UIConstants.RIGHT_PANEL_WIDTH, UIConstants.RIGHT_PANEL_HEIGHT);

        // [4] 메뉴 패널 생성
        MenuPanel menuPanel = new MenuPanel(gm);
        menuPanel.setLayoutX(0);
        menuPanel.setLayoutY(UIConstants.MENU_PANEL_START_Y);
        menuPanel.setPrefSize(UIConstants.RIGHT_PANEL_WIDTH, UIConstants.MENU_PANEL_HEIGHT);

        //[5] 하위 패널 추가
        getChildren().addAll(playerUnitTrackerPanel, menuPanel);
    }
}
