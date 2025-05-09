package swing.util;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Button {
    public static JButton createImageButton(ImageIcon icon, ActionListener action) {
        JButton button = new JButton(icon);

        // 버튼을 이미지처럼 보이게 설정
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);

        // 이벤트 추가
        button.addActionListener(action);

        return button;
    }
    /**
     * 이미지 파일명을 기반으로 버튼을 생성하는 정적 메서드
     * @param imageName 버튼 이미지 파일명 (예: "startButton.png")
     * @param folderName 리소스 폴더명 (예: "start", "end", "setting" 등)
     * @param x X 위치
     * @param y Y 위치
     * @param listener 버튼에 연결할 ActionListener
     * @return 생성된 JButton (이미지가 없으면 null)
     */
    public static JButton createButtonIfExists(java.util.List<String> imageNames, String imageName,
                                               String folderName, int x, int y, ActionListener listener) {
        if (!imageNames.contains(imageName)) return null;

        ImageIcon icon = new ImageIcon(Button.class.getResource("/" + folderName + "/" + imageName));
        JButton button = createImageButton(icon, listener);
        button.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        return button;
    }

}
