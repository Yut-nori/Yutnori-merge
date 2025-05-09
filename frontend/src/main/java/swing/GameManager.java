package swing;

import swing.gameBoard.RightPanel.RightPanel;
import swing.gameBoard.leftPanel.LeftPanel;
import swing.screen.Start;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameManager {

    // ** 멤버 변수 **
    private JPanel container;
    private final GameState gameState;

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

    // ** API 호출 메서드 **
    public void apiSetOption(int playerNum, int unitNum, int shape, boolean isTest) {
        optionAPI.setOption(playerNum, unitNum, shape, isTest);
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


    // 윷 던지기 메서드(랜덤 윷 인자: 0 / 지정 윷 인자: -1, 1, 2, 4, 5)
    public void throwYut(int designatedYutResult) {
        // [1] api 호출
        apiThrowYut(designatedYutResult);

        // gameState 업데이트
        updateGameStateWhenThrowingYut();

        // LeftPanel을 새롭게 그림
        leftRepaint();
    }

    public void clickUnit(int playerNum, int unitNum) {
        if(gameState.getCurrentPhase().contains(Phase.UNIT_CLICK)) {
            // 작동 -> back에서 yutResult 등을 업데이트해야함.
            // apiMoveUnit(selectedYut, selectedUnit);

            gameState.getCurrentPhase().remove(Phase.UNIT_CLICK);
            checkAndActivateYutRecordClick();
            moveUnitRepaint();

            if(turnChanged()) {
                int backPlayerNum = 1;
                gameState.setCurrentPlayer(backPlayerNum);
            }
        }
    }

    //Turn이 바뀌었는지 확인하고 이를 통해서 turn까지 바꿔줌
    public boolean turnChanged() {
        int backPlayerNum = 1;
        return gameState.getCurrentPlayer() != backPlayerNum;
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

    private void updateGameStateWhenThrowingYut() {
        gameState.setButtonClickRemaining(gameState.getButtonClickRemaining() - 1);
        List<Integer> yutResults = gameState.getYutResults();

        if(!yutResults.isEmpty()) {
            gameState.setLastResult(yutResults.get(yutResults.size() - 1 - gameState.getButtonClickRemaining()));
        }

        checkAndActivateButtonClick();
        checkAndActivateYutRecordClick();
        if(turnChanged())
            gameState.setLastResult(-1);
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

    public void moveUnitRepaint() {
        leftRepaint();
        rightRepaint();
    }

    public void switchPanel(JPanel panel) {
        Component[] components = container.getComponents();
        for (Component comp : components) {
            if (comp.getClass().equals(panel.getClass())) {
                container.remove(comp);
                break;
            }
        }
        container.add(panel);
        container.revalidate();
        container.repaint();
    }



    // ** Getters and Setters **
    public void setContainer(JPanel container) {
        this.container = container;
    }

    public GameState getGameState() {
        return gameState;
    }
}
