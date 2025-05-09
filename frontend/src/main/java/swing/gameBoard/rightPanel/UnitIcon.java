package swing.gameBoard.rightPanel;

import swing.GameManager;
import swing.util.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UnitIcon extends JComponent {
    
    // ** 멤버 변수 **
    private final Color color;
    private final int diameter;
    private final int thickness;
    private final int groupedUnitNum;

    // ** 생성자 **
    public UnitIcon(GameManager gm, Color color, int playerNum, int unitNum, int groupedUnitNum) {
        
        // [1] 멤버 변수 초기화
        this.color = color;
        this.diameter = 28;
        this.thickness = 2;
        this.groupedUnitNum = groupedUnitNum;

        // [2] 아이콘 사이즈 설정
        int fullSize = diameter + thickness;
        setPreferredSize(new Dimension(fullSize, fullSize));
        setOpaque(false);

        // [3] 클릭 가능하게 처리
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setToolTipText("말 클릭");

        // [4] 클릭 이벤트 처리
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("말 클릭됨!");
                gm.clickUnit(playerNum, unitNum);
            }
        });
    }

    // 아이콘이 그려질 때 호출되는 메서드
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // [1] 그래픽 객체 생성
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // [2] 원의 크기 설정
        int offset = thickness / 2;
        g2.setColor(color);
        g2.fillOval(offset, offset, diameter, diameter);

        // [3] 원의 색상, 스트로크 바탕으로 원 그리기
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(thickness));
        g2.drawOval(offset, offset, diameter, diameter);

        // [4] 2 이상일 때, 그룹 숫자 라벨을 원 위에 추가
        if (groupedUnitNum >= 2) {
            g2.setColor(Color.WHITE);
            g2.setFont(new Font(UIConstants.DEFAULT_FONT, Font.BOLD, 14));
            String text = "x" + String.valueOf(groupedUnitNum);
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getAscent();
            int centerX = offset + diameter / 2 - textWidth / 2;
            int centerY = offset + diameter / 2 + textHeight / 4;
            g2.drawString(text, centerX, centerY);
        }
    }

}