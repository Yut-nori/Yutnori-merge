package swing.gameBoard.leftPanel;

import swing.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class YutRecord extends JPanel {

    private final String resultText;
    private final boolean isEmpty;
    private final int YutResult;

    public YutRecord() {
        this.resultText = "";
        this.isEmpty = true;
        this.YutResult = 0;
        setPreferredSize(new Dimension(80, 80)); // 원 사이즈
        setOpaque(false); // 투명 배경
    }

    public YutRecord(GameManager gameManager, int YutResult, int count) {
        String resultText1;
        this.YutResult = YutResult;
        this.isEmpty = false;
        resultText1 = switch (YutResult) {
            case -1 -> "빽도";
            case 1 -> "도";
            case 2 -> "개";
            case 3 -> "걸";
            case 4 -> "윷";
            case 5 -> "모";
            default -> throw new IllegalStateException("Unexpected value: " + YutResult);
        };
        if(count > 1)
            resultText1 += "x" + count;

        this.resultText = resultText1;
        setPreferredSize(new Dimension(80, 80)); // 원 사이즈
        setOpaque(false); // 투명 배경

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicked on: " + resultText);
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



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 그래픽 설정
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 원 그리기 (조금 padding 넣어 중심 정렬)
        int padding = 5;
        int diameter = 70; // 80 - padding*2
        g2.setColor(isEmpty? Color.LIGHT_GRAY : Color.WHITE);
        g2.fillOval(padding, padding, diameter, diameter);

        // 테두리
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(padding, padding, diameter, diameter);

        // 글씨 그리기 (중앙 정렬)
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("맑은 고딕", Font.BOLD, 20)); // 사이즈 조정
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(resultText);
        int textHeight = fm.getAscent();

        int centerX = padding + diameter / 2 - textWidth / 2;
        int centerY = padding + diameter / 2 + textHeight / 2 - 4;

        g2.drawString(resultText, centerX, centerY);
    }
}