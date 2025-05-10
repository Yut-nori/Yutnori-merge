package model;


import model.board.Position;

import java.util.List;
import java.util.Stack;

public class GroupUnit {
    private Player player;
    private List<Unit> unitGroup;
    private Position currentPosition;
    private Stack<Integer> groupPathHistory; // 현재 group의 경로 히스토리 스택
    private boolean isCenterToStart;
    private boolean isInPath;
    private int pathID;


    public GroupUnit(Player player, List<Unit> unitGroup) {
        this.player = player;
        this.unitGroup = unitGroup;
        this.currentPosition = unitGroup.get(0).getCurrentPosition();
        this.groupPathHistory = new Stack<>();

        isCenterToStart = false;
        isInPath = false;

        pathID = -1;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Unit> getUnitGroup() {
        return unitGroup;
    }

    public void setUnitGroup(List<Unit> unitGroup) {
        this.unitGroup = unitGroup;
    }

    public Status getGroupStatus(){
        return unitGroup.get(0).getStatus();
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCenterToStart(boolean isCenterToStart) {
        this.isCenterToStart = isCenterToStart;
    }

    public boolean isCenterToStart() {
        return isCenterToStart;
    }

    public void setPath(int pathID) {
        isInPath = true;
        this.pathID = pathID;
    }

    public void releasePath() {
        isInPath = false;
        pathID = -1;
    }

    public boolean hasPath() {
        return isInPath;
    }

    public int getCurrentPathID() {
        return pathID;
    }

    public void setPosition(Position position) {
        this.currentPosition = position;
        for(Unit unit : this.getUnitGroup()) {
            unit.setPosition(currentPosition);
        }
    }

    public void pushHistory(Position position) {
        groupPathHistory.push(position.getIndex());
    }

    public int popHistory() {
        return groupPathHistory.pop();
    }

    public int peekHistory() {
        return groupPathHistory.peek();
    }

    public boolean isHistoryEmpty() {
        return groupPathHistory.isEmpty();
    }

    public void printHistoryStack() {
        for(int i=0;i<groupPathHistory.size();i++) {
            System.out.print(groupPathHistory.get(i) + " => ");
        }
        System.out.println();
    }

    private boolean passedZero = false;  // 0번 지점을 지난 적 있는지 여부

    public boolean hasPassedZero() {
        return passedZero;
    }

    public void markPassedZero() {
        this.passedZero = true;
    }

    public void markNotPassedZero() {
        this.passedZero = false;
    }

}

