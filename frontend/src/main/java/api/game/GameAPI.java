package api.game;

import api.option.OptionAPI;
import controller.PlayManager;

import java.util.ArrayList;
import java.util.List;

public class GameAPI {

    private static PlayManager playManager = OptionAPI.getPlayManager();
    private List<Integer> throwResult;

    // 랜덤 윷 던지기 (메서드 오버로딩)
    public void throwYut() {
        playManager.playerThrowYut(false);
        System.out.println("랜덤 윷 api 호출됨");
    }

    // 지정 윷 던지기 (메서드 오버로딩) 해결
    public void throwYut(int designatedYutResult) {
        System.out.println("지정 윷 api 호출됨");
    }

    //해결
    public void moveUnit(int selectedYut, int selectedUnit) {
        // 유닛 이동 로직
        playManager.setUnitMove(selectedUnit, selectedYut);
        System.out.println("유닛 이동 api 호출됨: " + selectedYut + ", " + selectedUnit);
    }

    //해결
    public List<Integer> getYutResult() {
        return playManager.getYutResult();
    }

    //해결
    public int getCurrentPlayer() {
        return 0;
    }

    public int[][] getUnitPositions() {
        return new int[1][1];
    }

    public int[][] getUnitNumberPerPosition() {
        return new int[1][1];
    }

    //해결
    public String getEvent() {
        return playManager.returnEvents();
    }

    public boolean gameEnd() {
        return false;
    }

}
