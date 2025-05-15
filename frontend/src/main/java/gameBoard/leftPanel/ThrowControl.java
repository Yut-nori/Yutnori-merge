package gameBoard.leftPanel;

import gameControl.GameManager;
import gameControl.GlobalButtonListener;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import util.ComboBoxUtil;

public class ThrowControl extends Pane {

    public ThrowControl(GameManager gm) {
        setPrefSize(310, 250); // Swing의 setBounds와 유사

        // [1] 기본 설정 완료
        setStyle("-fx-background-color: transparent;");

        // [2] case에 따라 윷 던지기 버튼을 만들어야 함

        // case1: 지정 윷 던지기
        if (gm.getGameState().isTest()) {

            // [2.1] 지정할 윷을 선택하는 콤보박스 생성
            ComboBox<String> yutComboBox = ComboBoxUtil.createStyledComboBox(
                    new String[]{"빽도", "도", "개", "걸", "윷", "모"},
                    20, 240, 150, 70 // ✅ 폭 150 이상 추천
            );

            // [2.2] 윷 던지기 버튼 생성 및 이벤트 리스너 추가
            Button designatedYutThrowBtn = createYutButton("지정 윷", 150, 240, 150);
            designatedYutThrowBtn.setOnAction(e -> {
                int yutResult = yutComboBox.getSelectionModel().getSelectedIndex();
                if (yutResult == 0) yutResult = -1; // 빽도는 -1로 처리

                System.out.println("지정 윷 던지기 버튼 + " + yutResult);

                // 윷 던지기 로직 실행
                gm.throwYut(yutResult);
            });

            // [2.3] 패널에 추가
            getChildren().addAll(yutComboBox, designatedYutThrowBtn);
        }

        // case2: 랜덤 윷 던지기
        else {
            Button randomYutThrowBtn = createYutButton("랜덤 윷", 20, 240, 270);
            randomYutThrowBtn.setOnAction(e -> new GlobalButtonListener(gm, "randomThrow").handle(e));
            getChildren().add(randomYutThrowBtn);
        }

        // [3] 윷 결과 패널 생성
        /**
         * 이제 이 Record를 버튼을 누를 때마다 클릭했을 때 보이는게 달라지도록 만들어져야함!
         * gm을 통해서 만들 수 있을듯!
         */
        int recordSpace = 20;
        int recordRadius = 80;
        int showResults = gm.getGameState().getCountClickedButton();

        YutRecord[] yutRecords = new YutRecord[6];
        int[] countYutResults = new int[6];

        for (int i = 0; i < showResults; i++) {
            int result = gm.getGameState().getYutResults().get(i);
            if (result == -1) result = 0;
            countYutResults[result]++;
        }

        for (int i = 0; i < 6; i++) {
            int yutResult = (i == 0) ? -1 : i;
            if (countYutResults[i] == 0) {
                yutRecords[i] = new YutRecord();
            } else {
                yutRecords[i] = new YutRecord(gm, yutResult, countYutResults[i]);
            }

            // 위치 설정 (3열 정렬)
            double x = 16 + recordRadius * (i % 3) + recordSpace * (i % 3);
            double y = 30 + 100 * (i / 3);
            yutRecords[i].setLayoutX(x);
            yutRecords[i].setLayoutY(y);
            yutRecords[i].setPrefSize(recordRadius, recordRadius);

            getChildren().add(yutRecords[i]);
        }
    }

    private Button createYutButton(String label, double x, double y, double width) {
        Button button = new Button(label + "\n던지기");
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPrefWidth(width);
        button.setPrefHeight(90);
        button.setStyle("-fx-font-size: 16px; -fx-background-color: cyan; -fx-border-color: black; -fx-border-width: 2px;");
        return button;
    }
}
