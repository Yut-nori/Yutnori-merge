package swing.gameBoard.leftPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class YutGroup extends JPanel {

    public YutGroup(int result) {

        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setOpaque(false);

        // 윷 이미지 로드
        ImageIcon headImg = new ImageIcon(getClass().getResource("/GameBoard/head.png"));
        ImageIcon tailImg = new ImageIcon(getClass().getResource("/GameBoard/tail.png"));
        ImageIcon backImg = new ImageIcon(getClass().getResource("/GameBoard/back.png"));

        Image scaledHeadImg = headImg.getImage().getScaledInstance(80, 240, Image.SCALE_SMOOTH);
        Image scaledTailImg = tailImg.getImage().getScaledInstance(80, 240, Image.SCALE_SMOOTH);
        Image scaledBackImg = backImg.getImage().getScaledInstance(80, 240, Image.SCALE_SMOOTH);

        ImageIcon headImgIcon = new ImageIcon(scaledHeadImg);
        ImageIcon tailImgIcon = new ImageIcon(scaledTailImg);
        ImageIcon backImgIcon = new ImageIcon(scaledBackImg);


        int[] Result = generateRandomSequence(result); // 0: head, 1: tail, 2: back

        for (int j : Result) {
            JLabel label = new JLabel();

            switch (j) {
                case 0:
                    label.setIcon(headImgIcon);
                    break;
                case 1:
                    label.setIcon(tailImgIcon);
                    break;
                case 2:
                    label.setIcon(backImgIcon);
                    break;
            }
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setOpaque(false);

            add(label);
        }
    }

    private int[] generateRandomSequence(int result) {
        int[] resultArray = switch (result) {
            case -1 -> // 빽도
                    new int[]{0, 0, 0, 2};
            case 1 -> // 도
                    new int[]{0, 0, 0, 1};
            case 2 -> // 개
                    new int[]{0, 0, 1, 1};
            case 3 -> // 걸
                    new int[]{0, 1, 1, 1};
            case 4 -> // 윷
                    new int[]{1, 1, 1, 1};
            case 5 -> // 모
                    new int[]{0, 0, 0, 0};
            default -> throw new IllegalArgumentException("결과값은 -1, 1~5 사이여야 합니다.");
        };

        // 기본 설정

        // 배열을 List로 변환 후 섞기
        List<Integer> tempList = new ArrayList<>();
        for (int val : resultArray) {
            tempList.add(val);
        }
        Collections.shuffle(tempList);

        // 다시 배열로 변환
        for (int i = 0; i < 4; i++) {
            resultArray[i] = tempList.get(i);
        }

        return resultArray;
    }
}
