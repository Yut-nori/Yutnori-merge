package gameControl;

import javafx.scene.layout.Pane;

public class GameManager {

//    // ** 멤버 변수 **
    private Pane panelContainer; // JavaFX에서는 Pane 사용
    private final GameState gameState;
    private ScreenManager screenManager;
//
//    // ** API 객체 변수 **
//    private final api.option.OptionAPI optionAPI;
//    private final api.game.GameAPI gameAPI;
//    private final api.restart.RestartAPI restartAPI;
//
    public GameManager(GameState gameState) {
        this.gameState = gameState;
//        this.optionAPI = new api.option.OptionAPI();
//        this.gameAPI = new api.game.GameAPI();
//        this.restartAPI = new api.restart.RestartAPI();
    }
//
    public void setScreenManager(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }
//
//    // API 호출
//    public void apiSetOption(int playerNum, int unitNum, int shape, boolean isTest) {
//        optionAPI.setOption(playerNum, unitNum, shape, isTest);
//        gameAPI.setPlayManager(optionAPI.getPlayManager());
//    }
//
//    public void apiThrowYut(int designatedYutResult) {
//        if (designatedYutResult == 0) gameAPI.throwYut();
//        else gameAPI.throwYut(designatedYutResult);
//    }
//
//    public void apiRestartGame() {
//        restartAPI.restartGame();
//    }
//
//    public void apiMoveUnit(int selectedYut, int selectedUnit) {
//        gameAPI.moveUnit(selectedYut, selectedUnit);
//    }
//
//    public void throwYut(int designatedYutResult) {
//        if (gameState.isTest() || gameState.getYutResults().isEmpty())
//            apiThrowYut(designatedYutResult);
//
//        updateGameStateWhenThrowingYut();
//        debug();
//
//        leftRepaint();
//        topRepaint();
//    }
//
//    public void clickUnit(int playerNum, int unitNum) {
//        if (gameState.getCurrentPlayer() == playerNum &&
//                gameState.getCurrentPhase().contains(Phase.UNIT_CLICK)) {
//
//            int yutCount = gameState.getYutResults().size();
//            gameAPI.moveUnit(gameState.getClickedYutResult(), unitNum);
//
//            gameState.setCountClickedButton(gameState.getCountClickedButton() - 1);
//            gameState.getCurrentPhase().remove(Phase.UNIT_CLICK);
//
//            if (turnChanged()) {
//                gameState.setCurrentPhase(EnumSet.of(Phase.BUTTON_CLICK));
//                gameState.setCountClickedButton(0);
//            }
//
//            setGameStateByBackWhenMoveUnit();
//
//            if (gameState.getClickedYutResult() == -1 && yutCount == gameState.getYutResults().size()) {
//                gameState.setCountClickedButton(gameState.getCountClickedButton() + 1);
//            }
//
//            gameState.setClickedYutResult(0);
//            checkAndActivateYutRecordClick();
//            checkAndActivateButtonClick();
//            debug();
//
//            if (gameState.isGameEnd())
//                screenManager.end();
//
//            moveUnitRepaint();
//        }
//    }
//
//    public boolean turnChanged() {
//        return gameState.getCurrentPlayer() != gameAPI.getCurrentPlayer();
//    }
//
//    private void checkAndActivateButtonClick() {
//        if (gameState.getCountClickedButton() != 0 &&
//                gameState.getCountClickedButton() == gameState.getYutResults().size()) {
//            gameState.getCurrentPhase().remove(Phase.BUTTON_CLICK);
//        } else {
//            gameState.getCurrentPhase().add(Phase.BUTTON_CLICK);
//        }
//    }
//
//    private void checkAndActivateYutRecordClick() {
//        if (!gameState.getYutResults().isEmpty()) {
//            gameState.getCurrentPhase().add(Phase.YUT_RECORD_CLICK);
//        } else {
//            gameState.getCurrentPhase().remove(Phase.YUT_RECORD_CLICK);
//        }
//    }
//
//    private void activateUnitClick() {
//        gameState.getCurrentPhase().add(Phase.UNIT_CLICK);
//    }
//
//    private void setGameStateByBackWhenThrowYut() {
//        gameState.setYutResults(gameAPI.getYutResult());
//        gameState.setEvent(gameAPI.getEvent());
//        gameState.setCurrentPlayer(gameAPI.getCurrentPlayer());
//    }
//
//    private void setGameStateByBackWhenMoveUnit() {
//        gameState.setYutResults(gameAPI.getYutResult());
//        gameState.setEvent(gameAPI.getEvent());
//        gameState.setCurrentPlayer(gameAPI.getCurrentPlayer());
//        gameState.setUnitPosition(gameAPI.getUnitPositions());
//        gameState.setUnitNumberPerPosition(gameAPI.getUnitNumberPerPosition());
//        gameState.setGameEnd(gameAPI.gameEnd());
//    }
//
//    private void updateGameStateWhenThrowingYut() {
//        setGameStateByBackWhenThrowYut();
//
//        gameState.setCountClickedButton(gameState.getCountClickedButton() + 1);
//        List<Integer> yutResults = gameState.getYutResults();
//
//        if (!gameState.isTest()) {
//            if (!yutResults.isEmpty()) {
//                gameState.setLastResult(yutResults.get(gameState.getCountClickedButton() - 1));
//            }
//        } else {
//            gameState.setLastResult(yutResults.get(yutResults.size() - 1));
//        }
//
//        checkAndActivateButtonClick();
//        checkAndActivateYutRecordClick();
//    }
//
//    public void clickYut(int yutResult) {
//        if (gameState.getCurrentPhase().contains(Phase.YUT_RECORD_CLICK)) {
//            gameState.setClickedYutResult(yutResult);
//            activateUnitClick();
//        }
//    }
//
//    public void leftRepaint() {
//        switchPanel(new LeftPanel(this));
//    }
//
//    public void rightRepaint() {
//        switchPanel(new RightPanel(this));
//    }
//
//    private void topRepaint() {
//        switchPanel(new TopPanel(this));
//    }
//
//    private void centerRepaint() {
//        switchPanel(new MainBoard(this));
//    }
//
//    public void moveUnitRepaint() {
//        leftRepaint();
//        rightRepaint();
//        topRepaint();
//        centerRepaint();
//    }
//
//    public void switchPanel(Pane panel) {
//        panelContainer.getChildren().clear();
//        panelContainer.getChildren().add(panel);
//    }
//
    // ** Getters and Setters **
    public void setPanelContainer(Pane panelContainer) {
        this.panelContainer = panelContainer;
    }

    public GameState getGameState() {
        return gameState;
    }
//
//    public void debug() {
//        System.out.println("Player: " + gameState.getCurrentPlayer());
//        System.out.println("Yut: " + gameState.getYutResults());
//        System.out.println("Event: " + gameState.getEvent());
//        System.out.println("Turn: " + gameState.getCurrentPhase());
//        System.out.println("Unit: " + Arrays.deepToString(gameState.getUnitPosition()));
//        System.out.println("UnitNumberPerPosition: " + Arrays.deepToString(gameState.getUnitNumberPerPosition()));
//    }
}
