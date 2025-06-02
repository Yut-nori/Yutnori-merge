package model.board;

public class Position {
    private Position next;
    private boolean isVertex;
    private Position altNext;
    private int index;
    private boolean isCenter;   // 나중에 Center 인지 확인해줄 것임.

    public Position(int index) {
        this.index = index;
        next = altNext = null;
        isVertex = false;
        isCenter = false;
    }

    public boolean isVertex() {
        return isVertex;
    }

    public void setVertex(boolean isVertex) {
        this.isVertex = isVertex;
    }

    /* next Getter/Setter */
    public Position getNext() {
        return next;
    }

    public void setNext(Position nextPos) {
        this.next = nextPos;
    }

    /* Alternative Next, Back Getter/Setter */
    public Position getAltNext() {
        return altNext;
    }

    public void setAltNext(Position altNextPos) {
        this.altNext = altNextPos;
    }

    /* 자기자신, next의 Index Getter */
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getNextIndex() {
        if(isVertex) return altNext.getIndex();
        else return index + 1;
    }

    public void setCenter(boolean isCenter) {
        this.isCenter = isCenter;
        this.setVertex(true);
    }

    public boolean isCenter() {
        return isCenter;
    }
}
