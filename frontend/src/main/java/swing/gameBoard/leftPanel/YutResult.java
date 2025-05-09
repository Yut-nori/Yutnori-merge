package swing.gameBoard.leftPanel;

import swing.GameManager;
import swing.util.UIConstants;

import javax.swing.*;
import java.awt.*;

class YutResult extends JPanel {

    // ** 생성자 **
    public YutResult(int result) {

        // [1] 기본 설정
        setLayout(null);
        setOpaque(false);

        // [2] 윷 결과 라벨 생성
        JLabel resultLabel = new JLabel();
        switch (result) {
            case -1 -> resultLabel.setText("빽 도!");
            case 1 -> resultLabel.setText("도");
            case 2 -> resultLabel.setText("개");
            case 3 -> resultLabel.setText("걸");
            case 4 -> resultLabel.setText("윷");
            case 5 -> resultLabel.setText("모");
            default -> resultLabel.setText("No Result");
        }
        resultLabel.setFont(new Font(UIConstants.YUT_RESULT_LABEL, Font.BOLD, 90));
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setVerticalAlignment(SwingConstants.CENTER);
        resultLabel.setBounds(70, 85, 200, 85);
        resultLabel.setOpaque(false);

        // [3] 윷 결과 그룹 패널 생성
        YutGroup yutGroup = new YutGroup(result);
        yutGroup.setBounds(0, 195, 320, 250);
        
        // [4] 패널에 추가
        add(resultLabel);
        add(yutGroup);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 여기에 그리기 작업을 추가하세요.
    }
}