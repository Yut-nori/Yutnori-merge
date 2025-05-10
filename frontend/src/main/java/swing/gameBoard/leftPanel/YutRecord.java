package swing.gameBoard.leftPanel;

import swing.GameManager;
import swing.util.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class YutRecord extends JPanel {

    // ** 멤버 변수 **
    private final String resultText;
    private final boolean isEmpty;
    private final int YutResult;

    // ** 생성자 1 **
    public YutRecord() {
        this.resultText = "";
        this.isEmpty = true;
        this.YutResult = 0;

        setPreferredSize(new Dimension(80, 80)); // 원 사이즈
        setOpaque(false); // 투명 배경
    }

    //  ** 생성자 2 **
    public YutRecord(GameManager gameManager, int YutResult, int count) {

        // [1] 상태 값 글자 설정
        this.YutResult = YutResult;
        this.isEmpty = false;
        String resultText = switch (YutResult) {
            case -1 -> "빽도";
            case 1 -> "도";
            case 2 -> "개";
            case 3 -> "걸";
            case 4 -> "윷";
            case 5 -> "모";
            default -> throw new IllegalStateException("Unexpected value: " + YutResult);
        };
        
        // [2] 같은 윷 결과가 2번 이상 나왔을 경우 x2 과 같이 표시
        if(count > 1)   resultText += "x" + count;

        // [3] 결과 텍스트를 설정
        this.resultText = resultText;
        setPreferredSize(new Dimension(80, 80)); // 원 사이즈
        setOpaque(false); // 투명 배경

        // [4] 이벤트 리스너 설정
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameManager.clickYut(YutResult);
                //이때, 화살표 기능 추가..?
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    // 패널이 그려질 때 호출되는 메서드
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // [1] 그래픽 설정
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // [2] 원 그리기 (padding 값을 주어 중심 정렬)
        int padding = 5;
        int diameter = 70; // 80 - padding*2
        g2.setColor(isEmpty? Color.LIGHT_GRAY : Color.WHITE);
        g2.fillOval(padding, padding, diameter, diameter);

        // [3] 테두리 설정
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(padding, padding, diameter, diameter);

        // [4] 글자 쓰고 가운데 정렬
        g2.setColor(Color.BLACK);
        g2.setFont(new Font(UIConstants.YUT_RECORD_FONT, Font.BOLD, 20));
        FontMetrics fm = g2.getFontMetrics();

        int textWidth = fm.stringWidth(resultText);
        int textHeight = fm.getAscent();

        int centerX = padding + diameter / 2 - textWidth / 2;
        int centerY = padding + diameter / 2 + textHeight / 2 - 4;

        g2.drawString(resultText, centerX, centerY);
    }

}