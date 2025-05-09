package swing;

import java.util.*;

public class GameState {
    private int totalPlayerNumber = 4;
    private int unitNumberPerPlayer = 4;
    private int shape = 4;
    private boolean isTest;

    private List<Integer> yutResults;
    private int lastResult;
    //만약 yutResults보다 커지게 되면 button 동작 안하게
    private int buttonClickRemaining = 0;

    /**
     * 어떤 YutResult를 클릭했는지 setting /
     * UnitIcon을 클릭할 때 이 값을 기준으로 back에 정보 전달
     */
    private int clickedYutResult = 0;

    private int currentPlayer = 1;
    private int[][] unitPosition;
    private int[][] unitNumberPerPosition;

    private EnumSet<Phase> currentPhase;



    private String event = "Event";

    public GameState() {
        yutResults = new ArrayList<>();
        currentPhase = EnumSet.noneOf(Phase.class);
    }

    /**
     * 재시작 혹은 시작할 때
     * initiate_state를 사용함으로써 game_state를 초기화
     */
    public void initiateState(int playerNum, int UnitNum, int shape, boolean isTest) {
        this.totalPlayerNumber = playerNum;
        this.unitNumberPerPlayer = UnitNum;
        this.shape = shape;
        this.isTest = isTest;
        this.yutResults.clear();
        this.lastResult = 1;
        this.currentPlayer = 1;
        this.unitPosition = new int[playerNum][UnitNum];
        for(int i = 0; i < playerNum; i++)
            Arrays.fill(this.unitPosition[i], -1);
        this.unitNumberPerPosition = new int[playerNum][UnitNum];
        this.currentPhase = EnumSet.of(Phase.BUTTON_CLICK);
        this.buttonClickRemaining = 1;
        this.clickedYutResult = 0;
    }

    public void consumeYutResult(int clicked) {
        if (!yutResults.isEmpty()) yutResults.remove(clicked);
    }

    public int getTotalPlayerNumber() {
        return totalPlayerNumber;
    }

    public int getUnitNumberPerPlayer() {
        return unitNumberPerPlayer;
    }

    public int getShape() {
        return shape;
    }

    public boolean isTest() {
        return isTest;
    }

    public List<Integer> getYutResults() {
        return yutResults;
    }

    public void setYutResults(List<Integer> yutResults) {
        this.yutResults = yutResults;
    }

    public int getLastResult() {
        return lastResult;
    }

    public void setLastResult(int lastResult) {
        this.lastResult = lastResult;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int[][] getUnitPosition() {
        return unitPosition;
    }

    public void setUnitPosition(int[][] unitPosition) {
        this.unitPosition = unitPosition;
    }

    public int[][] getUnitNumberPerPosition() {
        return unitNumberPerPosition;
    }

    public void setUnitNumberPerPosition(int[][] unitNumberPerPosition) {
        this.unitNumberPerPosition = unitNumberPerPosition;
    }

    public EnumSet<Phase> getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(EnumSet<Phase> currentPhase) {
        this.currentPhase = currentPhase;
    }

    public int getButtonClickRemaining() {
        return buttonClickRemaining;
    }

    public void setButtonClickRemaining(int buttonClickRemaining) {
        this.buttonClickRemaining = buttonClickRemaining;
    }

    public int getClickedYutResult() {
        return clickedYutResult;
    }

    public void setClickedYutResult(int clickedYutResult) {
        this.clickedYutResult = clickedYutResult;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
