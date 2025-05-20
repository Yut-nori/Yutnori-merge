package screen.gameBoard.leftPanel;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YutGroup extends HBox {

    public YutGroup(int result) {
        setSpacing(0);
        setAlignment(Pos.CENTER_LEFT);
        setPrefHeight(250);
        setStyle("-fx-background-color: transparent;");

        // [1] 이미지 로딩 및 크기 지정
        Image headImg = new Image(getClass().getResource("/GameBoard/head.png").toExternalForm());
        Image tailImg = new Image(getClass().getResource("/GameBoard/tail.png").toExternalForm());
        Image backImg = new Image(getClass().getResource("/GameBoard/back.png").toExternalForm());

        // [2] 결과 해석 후 이미지 추가
        int[] sequence = generateRandomSequence(result); // 0: head, 1: tail, 2: back

        for (int state : sequence) {
            ImageView imageView = new ImageView();
            imageView.setFitWidth(80);
            imageView.setFitHeight(240);
            switch (state) {
                case 0 -> imageView.setImage(headImg);
                case 1 -> imageView.setImage(tailImg);
                case 2 -> imageView.setImage(backImg);
            }
            getChildren().add(imageView);
        }
    }

    private int[] generateRandomSequence(int result) {
        int[] resultArray = switch (result) {
            case -1 -> new int[]{0, 0, 0, 2};     // 빽도
            case 1 -> new int[]{0, 0, 0, 1};     // 도
            case 2 -> new int[]{0, 0, 1, 1};     // 개
            case 3 -> new int[]{0, 1, 1, 1};     // 걸
            case 4 -> new int[]{1, 1, 1, 1};     // 윷
            case 5 -> new int[]{0, 0, 0, 0};     // 모
            default -> throw new IllegalArgumentException("결과값은 -1, 1~5 사이여야 합니다.");
        };

        List<Integer> tempList = new ArrayList<>();
        for (int val : resultArray) tempList.add(val);
        Collections.shuffle(tempList);

        for (int i = 0; i < 4; i++) resultArray[i] = tempList.get(i);
        return resultArray;
    }
}
