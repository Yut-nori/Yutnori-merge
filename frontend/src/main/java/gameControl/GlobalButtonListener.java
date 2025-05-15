package gameControl;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GlobalButtonListener implements EventHandler<ActionEvent> {
    private ScreenManager screenManager;
    private GameManager gameManager;
    private GameState gameState;
    private final String actionName;

    public GlobalButtonListener(ScreenManager screenManager, String actionName) {
        this.screenManager = screenManager;
        this.actionName = actionName;
    }

    public GlobalButtonListener(GameManager gameManager, String actionName) {
        this.gameManager = gameManager;
        this.gameState = gameManager.getGameState();
        this.actionName = actionName;
    }

    @Override
    public void handle(ActionEvent event) {
        switch (actionName) {
            case "exit":
                Platform.exit();
                break;

            case "settingPage":
                if (screenManager != null) screenManager.setting();
                break;

            case "exitPage":
                if (screenManager != null) screenManager.end();
                break;

            case "randomThrow":
                System.out.println("랜덤 윷 던지기 버튼 클릭됨");
                if (gameState != null && gameState.getCurrentPhase().contains(Phase.BUTTON_CLICK)) {
                    gameManager.throwYut(0);
                }
                break;

            default:
                System.out.println("알 수 없는 액션: " + actionName);
        }
    }
}
