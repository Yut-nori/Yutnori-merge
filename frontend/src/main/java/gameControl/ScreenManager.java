package gameControl;

import gameBoard.GameBoard;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import screen.End;
import screen.Setting;
import screen.Start;

public class ScreenManager {
    private final Stage stage;
    private final GameManager gm;

    // ** Constructor **
    public ScreenManager(Stage stage, GameManager gm) {
        this.stage = stage;
        this.gm = gm;
        start();
    }

    // ** Scene 전환 메서드 **
    private void switchScene(Pane root) {
        Scene scene = new Scene(root, 1280, 720); // 원하는 크기로 조정 가능
        stage.setScene(scene);
        stage.show();
    }

    // (0)게임 시작 -> (1)메인 화면
    public void start() {
        switchScene(new Start(this));
    }

    // (1)메인 화면 -> (2)세팅
    public void setting() {
        switchScene(new Setting(this));
    }

    // (2)세팅 -> (3)윷놀이 시작
    public void gameBoard() {
        switchScene(new GameBoard(this));
    }

    // (3)윷놀이 시작 -> (4)게임 종료
    public void end() {
        switchScene(new End(this));
    }

    public GameManager getGameManager() {
        return gm;
    }
}
