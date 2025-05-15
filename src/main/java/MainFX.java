import javafx.application.Application;
import javafx.stage.Stage;
import gameControl.GameManager;
import gameControl.GameState;
import gameControl.ScreenManager;


public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Yutnori -Team 13");

        GameManager gm = new GameManager(new GameState());
        ScreenManager sm = new ScreenManager(primaryStage, gm);
        gm.setScreenManager(sm);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
