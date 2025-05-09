package swing.util;

import javax.swing.*;
import java.awt.*;

public class ComboBox {
    public static JComboBox<String> createStyledComboBox(String[] items, int x, int y, int width, int height) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setBounds(x, y, width, height);
        styleComboBox(comboBox);
        return comboBox;
    }

    private static void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("나눔손글씨 붓", Font.PLAIN, 30));
        comboBox.setBackground(new Color(193, 144, 94));
        comboBox.setForeground(Color.WHITE);
        comboBox.setFocusable(false);
        comboBox.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                c.setBackground(isSelected ? new Color(160, 120, 80) : new Color(193, 144, 94));
                c.setForeground(Color.WHITE);
                setFont(new Font("나눔손글씨 붓", Font.PLAIN, 18));
                return c;
            }
        });
    }
}
