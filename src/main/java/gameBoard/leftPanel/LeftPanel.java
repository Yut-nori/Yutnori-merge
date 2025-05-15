package gameBoard.leftPanel;


import gameControl.GameManager;
import javafx.scene.layout.Pane;
import util.UIConstants;

public class LeftPanel extends Pane {

    // ** 생성자 **
    public LeftPanel(GameManager gm) {

        // [1] 패널 기본 설정
        setStyle("-fx-background-color: transparent;");

        // [2] 패널의 크기와 위치 설정
        setLayoutX(0);
        setLayoutY(0);
        setPrefSize(UIConstants.LEFT_PANEL_WDITH, UIConstants.FRAME_HEIGHT);

        // [3] 윷 결과 패널 생성
        YutResult yutResult = new YutResult(gm.getGameState().getLastResult());
        yutResult.setLayoutX(0);
        yutResult.setLayoutY(0);
        yutResult.setPrefSize(UIConstants.LEFT_PANEL_WDITH, UIConstants.YUT_RESULT_PANEL_HEIGHT);

        // [4] 윷 던지기 버튼 패널 생성
        ThrowControl throwControl = new ThrowControl(gm);
        throwControl.setLayoutX(0);
        throwControl.setLayoutY(UIConstants.THROW_CONTROL_START_Y);
        throwControl.setPrefSize(UIConstants.LEFT_PANEL_WDITH, UIConstants.THROW_CONTROL_HEIGHT);

        // [5] 패널에 추가
        getChildren().addAll(yutResult, throwControl);
    }
}
