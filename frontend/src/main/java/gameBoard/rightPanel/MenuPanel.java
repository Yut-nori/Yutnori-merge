package gameBoard.rightPanel;

import gameControl.GameManager;
import gameControl.GlobalButtonListener;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import util.UIConstants;

public class MenuPanel extends Pane {

    // ** Constructor **
    public MenuPanel(GameManager gm) {

        // [1] 패널 기본 설정
        setStyle("-fx-background-color: transparent;");
        setPrefSize(300, 150); // 예시값, 상위에서 조정 가능

        // [2] 게임 종료 버튼 생성
        Button exitButton = new Button("게임 종료");
        exitButton.setLayoutX(30);
        exitButton.setLayoutY(35);
        exitButton.setPrefSize(250, 85);

        exitButton.setFont(new Font(UIConstants.BUTTON_FONT, 20));
        exitButton.setStyle(
                "-fx-background-color: lightgray;" +
                        "-fx-text-fill: black;" +
                        "-fx-border-color: black;" +
                        "-fx-border-width: 2px;"
        );

        // [3] 버튼 리스너 연결
        exitButton.setOnAction(e -> new GlobalButtonListener(gm, "exit").handle(e));

        // [4] 패널에 추가
        getChildren().add(exitButton);
    }
}
