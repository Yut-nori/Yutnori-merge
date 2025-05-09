package controller;

import model.Yut.YutResult;

import java.util.ArrayList;
import java.util.List;

public class YutManager {
    private List<Integer> yutResList;

    public YutManager() {
        yutResList = new ArrayList<>();
    }

    public void setExtraTurn() {
        // 윷이랑, 모 나올 때 한번 더 하도록 ..do while 문 사용
        yutResList.clear();

        int result;

        do {
            YutResult.throwYuts();
            result = YutResult.getYutResult();
            yutResList.add(result);
        } while (result == 4 || result == 5);
    }

    public List<Integer> getYutResList() {
        return yutResList;
    }
}