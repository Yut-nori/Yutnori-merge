package api.game;

import api.option.OptionAPI;
import controller.PlayManager;

import java.util.ArrayList;
import java.util.List;

public class GameAPI {

    private PlayManager playManager;
    private List<Integer> throwResult;

    public void setPlayManager(PlayManager playManager) {
        this.playManager = playManager;
    }
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

    //해결 -> 연결하면 끝
    public int getCurrentPlayer() {
        return playManager.getCurrentPlayer();
    }

    //이게 움직인 다음 위치
    public int[][] getUnitPositions() {
        return playManager.getAllUnitsPosition();
    }

    //한 그룹 당, 유닛의 개수
    public int[][] getUnitNumberPerPosition() {
        return playManager.getUnitsNumPerGroups();
    }

    //해결
    public String getEvent() {
        return playManager.returnEvents();
    }

    public boolean gameEnd() {
        return playManager.checkEnd();
    }

}
