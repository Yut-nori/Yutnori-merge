package swing.gameBoard.rightPanel;

import swing.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UnitIcon extends JComponent {
    private final Color color;
    private final int diameter;
    private final int thickness;
    private final int groupedUnitNum;

    public UnitIcon(GameManager gm, Color color, int playerNum, int unitNum, int groupedUnitNum) {
        this.color = color;
        this.diameter = 28;
        this.thickness = 2;
        this.groupedUnitNum = groupedUnitNum;

        int fullSize = diameter + thickness;
        setPreferredSize(new Dimension(fullSize, fullSize));
        setOpaque(false);

        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setToolTipText("말 클릭");

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("말 클릭됨!");
                gm.clickUnit(playerNum, unitNum);
            }
        });
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int offset = thickness / 2;
        g2.setColor(color);
        g2.fillOval(offset, offset, diameter, diameter);

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(thickness));
        g2.drawOval(offset, offset, diameter, diameter);

        // 그룹 숫자 추가 (2 이상일 때만)
        if (groupedUnitNum >= 2) {
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 14));
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