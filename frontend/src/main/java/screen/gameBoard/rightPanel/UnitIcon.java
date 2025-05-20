package screen.gameBoard.rightPanel;

import gameControl.GameManager;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import util.UIConstants;

public class UnitIcon extends Group {

    private final int diameter = 28;
    private final int thickness = 2;

    public UnitIcon(GameManager gm, Color color, int playerNum, int unitNum, int groupedUnitNum) {
        double radius = diameter / 2.0;

        // [1] 바탕 원
        Circle circle = new Circle(radius + thickness / 2.0, radius + thickness / 2.0, radius);
        circle.setFill(color);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(thickness);

        // [2] 그룹 숫자가 있을 경우 표시
        Label label = null;
        if (groupedUnitNum > 1) {
            label = new Label("x" + groupedUnitNum);
            label.setTextFill(Color.WHITE);
            label.setFont(Font.font(UIConstants.DEFAULT_FONT, 14));

            // 라벨을 중앙 정렬
            label.setLayoutX(radius + thickness / 2.0 - 10); // 대략적인 가운데 정렬
            label.setLayoutY(radius + thickness / 2.0 - 8);
        }

        // [3] 클릭 리스너 등록
        this.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("말 클릭됨!");
            gm.clickUnit(playerNum, unitNum);
        });

        // [4] 마우스 커서 모양 (기본 핸드 커서)
        this.setCursor(javafx.scene.Cursor.HAND);

        // [5] 구성 요소 추가
        this.getChildren().add(circle);
        if (label != null) {
            this.getChildren().add(label);
        }

    }
}
