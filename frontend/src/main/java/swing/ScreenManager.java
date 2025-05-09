package swing;

import swing.gameBoard.GameBoard;
import swing.screen.End;
import swing.screen.Setting;
import swing.screen.Start;

import javax.swing.*;
import java.awt.*;

public class ScreenManager {
    private MainFrame frame;
    private Container container;
    private final GameManager gm;

    // ** Constructor **
    public ScreenManager(MainFrame frame, GameManager gm) {
        this.frame = frame;
        this.gm = gm;
        this.container = frame.getContentPane();
        start();
    }

    private void switchPanel(JPanel newPanel) {
        container.removeAll();
        container.add(newPanel);
        container.revalidate();
        container.repaint();
    }


    // (0)게임 시작 -> (1) 메인 화면
    public void start() {
        switchPanel(new Start(this));
    }

    // (1)게임 시작 -> (2)세팅
    public void setting() {
        switchPanel(new Setting(this));
    }

    // (2)세팅 -> (3)윷놀이 시작
    public void gameBoard() {
        switchPanel(new GameBoard(this));
    }
    
    // (3)윷놀이 시작 -> (4)게임 종료 
    public void end() {
        switchPanel(new End(this));
    }

    // ** Getters and Setters **
    public GameManager getGm() {
        return gm;
    }
}
