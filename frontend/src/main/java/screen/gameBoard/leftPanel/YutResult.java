package screen.gameBoard.leftPanel;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.UIConstants;

public class YutResult extends Pane {

    public YutResult(int result) {
        setPrefSize(320, 470);

        // [1] 윷 결과 라벨
        Label resultLabel = new Label();
        resultLabel.setText(switch (result) {
            case -1 -> "빽도";
            case 1 -> "도";
            case 2 -> "개";
            case 3 -> "걸";
            case 4 -> "윷";
            case 5 -> "모";
            default -> "No Result";
        });

        resultLabel.setFont(Font.font(UIConstants.YUT_RESULT_LABEL, 90));
        resultLabel.setTextFill(Color.WHITE);
        resultLabel.setLayoutX(70);
        resultLabel.setLayoutY(85);
        resultLabel.setPrefSize(240, 85);

        // [2] YutGroup 하단 추가
        YutGroup yutGroup = new YutGroup(result);
        yutGroup.setLayoutX(0);
        yutGroup.setLayoutY(195);
        yutGroup.setPrefSize(320, 250);

        // [3] 구성 요소 추가
        getChildren().addAll(resultLabel, yutGroup);
    }
}
