package swing.screen;

import swing.GameState;
import swing.ScreenManager;
import swing.util.GlobalButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static swing.util.Button.createButtonIfExists;
import static swing.util.File.getFileName;
import static swing.util.File.imageLoading;
import static swing.util.ImageRenderer.renderImages;

public class End extends JPanel {

    // ** 멤버 변수 **
    private final String screenName = "end";
    private Map<String, BufferedImage> images;

    // ** Constructor **
    public End(ScreenManager sm) {

        // [1] 레이아웃을 null로 설정
        setLayout(null);

        // [2] 배경 이미지 로딩
        List<String> imageNames = getFileName(screenName);
        images = imageLoading(imageNames, screenName);

        JLabel winnerLabel = new JLabel();
        int winner = sm.getGm().getGameState().getCurrentPlayer() + 1;
        GameState gameState = sm.getGm().getGameState();
        if(sm.getGm().getGameState().getYutResults().isEmpty()) {
            if(winner == 1)
                winner = gameState.getTotalPlayerNumber();
            else
                winner -= 1;

        }
        winnerLabel.setText("Winner: " + winner);
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 50));
        winnerLabel.setForeground(Color.WHITE); // 또는 Color.YELLOW 등
        winnerLabel.setBounds(new Rectangle(520, 180, 300, 100));
        add(winnerLabel);

        // [3] 게임 시작, 게임 종료 버튼 생성
        JButton reStartButton;
        JButton exitButton;
        reStartButton = createButtonIfExists(
                imageNames, "startButton.png", "end", 400, 280,
                new GlobalButtonListener(sm, "settingPage")
        );
        exitButton = createButtonIfExists(
                imageNames, "exitButton.png", "end", 470, 460,
                new GlobalButtonListener(sm, "exit")
        );

        // [4] 패널에 버튼 추가
        if (reStartButton != null) add(reStartButton);
        if (exitButton != null) add(exitButton);

    }

    // 패널이 그려질 때 호출되는 메서드
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // [1] 배경 이미지 그리기
        if (images.get("background.png") != null) {
            g.drawImage(images.get("background.png"), 0, 0, getWidth(), getHeight(), this);
        }

        // [2] 이미지 이름, x, y 좌표 정의
        Object[][] imageData = {
                {"man.png", 50, 100},
                {"woman.png", 850, 100},
                {"title.png", 480, 30},
//                {"winner.png", 450, 180}
        };

        // [3] 이미지 그리기
        renderImages(g, this, images, imageData);

    }
}
