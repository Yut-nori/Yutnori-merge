package swing;

import swing.gameBoard.topPanel.TopPanel;
import swing.gameBoard.leftPanel.LeftPanel;
import swing.gameBoard.rightPanel.RightPanel;

import javax.swing.*;
import java.awt.*;
import java.util.EnumSet;
import java.util.List;

public class GameManager {

    // ** 멤버 변수 **
    private JPanel panelContainer;
    private final GameState gameState;
    private ScreenManager screenManager;

    // ** API 객체 변수 **
    private final api.option.OptionAPI optionAPI;
    private final api.game.GameAPI gameAPI;
    private final api.restart.RestartAPI restartAPI;

    // ** Constructor **
    public GameManager(GameState gameState) {
        this.gameState = gameState;

        this.optionAPI = new api.option.OptionAPI();
        this.gameAPI = new api.game.GameAPI();
        this.restartAPI = new api.restart.RestartAPI();
    }

    public void setScreenManager(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }


    // ** API 호출 메서드 **
    public void apiSetOption(int playerNum, int unitNum, int shape, boolean isTest) {
        optionAPI.setOption(playerNum, unitNum, shape, isTest);
        gameAPI.setPlayManager(optionAPI.getPlayManager());
    }
    public void apiThrowYut(int designatedYutResult) {
        if (designatedYutResult == 0) gameAPI.throwYut(); // 랜덤 윷 던지기 호출
        else gameAPI.throwYut(designatedYutResult); // 지정 윷 던지기 메서드 호출


    }
    public void apiRestartGame() {
        restartAPI.restartGame();
    }
    public void apiMoveUnit(int selectedYut, int selectedUnit) {
        gameAPI.moveUnit(selectedYut, selectedUnit);
    }


    // ** 메서드 모음 **//
    // 윷 던지기 메서드(랜덤 윷 인자: 0 / 지정 윷 인자: -1, 1, 2, 4, 5)
    public void throwYut(int designatedYutResult) {
        // [1] api 호출
        if (gameState.getYutResults().isEmpty())
            apiThrowYut(designatedYutResult);

        // [2] GameState 업데이트
        updateGameStateWhenThrowingYut();

        // [3] LeftPanel, topPanel을 새롭게 그림
        leftRepaint();
        topRepaint();
    }

    // 말을 클릭했을 경우 처리 메서드
    public void clickUnit(int playerNum, int unitNum) {
        
        // 유닛이 클릭 가능한 상태에서만 동작 수행
        // 현재 player가 움직일 수 있는 말인지 체크
        if(gameState.getCurrentPlayer() == playerNum && gameState.getCurrentPhase().contains(Phase.UNIT_CLICK)) {
            int originYutCount = gameState.getYutResults().size() - 1;

            gameAPI.moveUnit(gameState.getClickedYutResult(), unitNum);

            // [1] 유닛 클릭이 불가능하게 변경
            gameState.getCurrentPhase().remove(Phase.UNIT_CLICK);
            checkAndActivateYutRecordClick();

            if(turnChanged()) {
                gameState.setCurrentPhase(EnumSet.of(Phase.BUTTON_CLICK));
                gameState.setButtonClickRemaining(1);
            }

            setGameStateByBackWhenMoveUnit();

            if(gameState.isGameEnd())
                screenManager.end();

            int changedYutCount = gameState.getYutResults().size();
            if(changedYutCount != originYutCount) {
                gameState.setButtonClickRemaining(gameState.getButtonClickRemaining() + originYutCount - changedYutCount);
            }

            moveUnitRepaint();
        }

    }

    //Turn이 바뀌었는지 확인
    public boolean turnChanged() {
        return gameState.getCurrentPlayer() != gameAPI.getCurrentPlayer();
    }

    private void checkAndActivateButtonClick() {
        if(gameState.getButtonClickRemaining() == 0)
            gameState.getCurrentPhase().remove(Phase.BUTTON_CLICK);
        else
            gameState.getCurrentPhase().add(Phase.BUTTON_CLICK);
    }

    private void checkAndActivateYutRecordClick() {
        if(!gameState.getYutResults().isEmpty())
            gameState.getCurrentPhase().add(Phase.YUT_RECORD_CLICK);
        else
            gameState.getCurrentPhase().remove(Phase.YUT_RECORD_CLICK);
    }

    private void activateUnitClick() {
        gameState.getCurrentPhase().add(Phase.UNIT_CLICK);
    }

    private void setGameStateByBackWhenThrowYut() {
        gameState.setYutResults(gameAPI.getYutResult());
        gameState.setEvent(gameAPI.getEvent());
        gameState.setCurrentPlayer(gameAPI.getCurrentPlayer());
    }

    private void setGameStateByBackWhenMoveUnit() {
        gameState.setYutResults(gameAPI.getYutResult());
        gameState.setEvent(gameAPI.getEvent());
        gameState.setCurrentPlayer(gameAPI.getCurrentPlayer());
        gameState.setUnitPosition(gameAPI.getUnitPositions());
        gameState.setUnitNumberPerPosition(gameAPI.getUnitNumberPerPosition());
        gameState.setGameEnd(gameAPI.gameEnd());
    }

    private void updateGameStateWhenThrowingYut() {
        /* if(turnChanged())
            gameState.setLastResult(-1);*/
        setGameStateByBackWhenThrowYut();

        gameState.setButtonClickRemaining(gameState.getButtonClickRemaining() - 1);
        List<Integer> yutResults = gameState.getYutResults();

        if(!yutResults.isEmpty()) {
            gameState.setLastResult(yutResults.get(yutResults.size() - 1 - gameState.getButtonClickRemaining()));
        }

        checkAndActivateButtonClick();
        checkAndActivateYutRecordClick();
    }

    public void clickYut(int yutResult) {
        if(gameState.getCurrentPhase().contains(Phase.YUT_RECORD_CLICK)) {
            gameState.setClickedYutResult(yutResult);
            activateUnitClick();
        }
    }

    public void leftRepaint() {
        switchPanel(new LeftPanel(this));
    }

    public void rightRepaint() {
        switchPanel(new RightPanel(this));
    }

    private void topRepaint() { switchPanel(new TopPanel(this)); }

    public void moveUnitRepaint() {
        leftRepaint();
        rightRepaint();
        topRepaint();
    }

    public void switchPanel(JPanel panel) {
        Component[] components = panelContainer.getComponents();
        for (Component comp : components) {
            if (comp.getClass().equals(panel.getClass())) {
                panelContainer.remove(comp);
                break;
            }
        }
        panelContainer.add(panel);
        panelContainer.revalidate();
        panelContainer.repaint();
    }



    // ** Getters and Setters **
    public void setPanelContainer(JPanel panelContainer) {
        this.panelContainer = panelContainer;
    }

    public GameState getGameState() {
        return gameState;
    }
}
