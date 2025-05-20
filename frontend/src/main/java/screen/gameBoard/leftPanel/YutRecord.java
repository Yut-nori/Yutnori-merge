package screen.gameBoard.leftPanel;


import gameControl.GameManager;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import util.UIConstants;

public class YutRecord extends Pane {

    private final int yutResult;
    private final boolean isEmpty;
    private final String resultText;

    // 생성자 1: 빈 레코드
    public YutRecord() {
        this.yutResult = 0;
        this.isEmpty = true;
        this.resultText = "";

        setPrefSize(80, 80);
        setStyle("-fx-background-color: transparent;");
        drawCircle();
    }

    // 생성자 2: 실제 결과 표시
    public YutRecord(GameManager gm, int yutResult, int count) {
        this.yutResult = yutResult;
        this.isEmpty = false;

        // [1] 결과 텍스트 설정
        String text = switch (yutResult) {
            case -1 -> "빽도";
            case 1 -> "도";
            case 2 -> "개";
            case 3 -> "걸";
            case 4 -> "윷";
            case 5 -> "모";
            default -> throw new IllegalStateException("Unexpected value: " + yutResult);
        };
        if (count > 1) text += "x" + count;
        this.resultText = text;

        setPrefSize(80, 80);
        setStyle("-fx-background-color: transparent;");
        drawCircle();

        // [2] 클릭 이벤트 등록
        this.setOnMouseClicked((MouseEvent e) -> {
            gm.clickYut(yutResult);
            // 화살표 기능 추가 가능 위치
        });

        // [3] 마우스 오버 효과
        this.setOnMouseEntered(e -> setCursor(Cursor.HAND));
        this.setOnMouseExited(e -> setCursor(Cursor.DEFAULT));
    }

    // 동그란 말판 그리기
    private void drawCircle() {
        getChildren().clear();

        // [1] 원 배경
        Circle circle = new Circle(40, 40, 35); // 중심 (40,40), 반지름 35
        circle.setFill(isEmpty ? Color.LIGHTGRAY : Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);

        getChildren().add(circle);

        // [2] 텍스트
        if (!isEmpty && !resultText.isEmpty()) {
            Text text = new Text(resultText);
            text.setFill(Color.BLACK);
            text.setFont(Font.font(UIConstants.YUT_RECORD_FONT, 20));

            // 중앙 정렬
            text.setLayoutX(40 - text.getLayoutBounds().getWidth() / 2);
            text.setLayoutY(40 + text.getLayoutBounds().getHeight() / 4);
            getChildren().add(text);
        }
    }
}
