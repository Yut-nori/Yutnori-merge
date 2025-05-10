package model;

import controller.BoardManager;
import model.board.Board;
import model.board.Position;

// Unit 에 대한 Model
// 현재의 인덱스, 그리고 enum 을 통한 결승선 통과 여부 확인, 그룹화 되어있는지 확인
public class Unit {
    //private int currentPositionIdx;
    private Board currentBoard;

    private Position currentPosition;
    private Status status;
    private boolean isGrouped;

    public Unit() {
        //this.currentPositionIdx = 0;
        this.currentBoard = BoardManager.getBoard();
        this.currentPosition = currentBoard.getPositionArr()[0];
        this.status = Status.READY;
        this.isGrouped = false;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setPosition(Position position) {
        this.currentPosition = position;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isGrouped() {
        return isGrouped;
    }

    public void setGrouped(boolean isGrouped) {
        this.isGrouped = isGrouped;
    }
}
