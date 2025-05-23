package model.board;

public class Position {
    private Position next;
    private Position back;
    private boolean isVertex;
    private Position altNext;
    private Position altBack;
    private int index;
    private boolean isCenter;   // 나중에 Center 인지 확인해줄 것임.

    public Position(int index) {
        this.index = index;
        next = back = altNext = altBack = null;
        isVertex = false;
        isCenter = false;
    }

    public boolean isVertex() {
        return isVertex;
    }

    public void setVertex(boolean isVertex) {
        this.isVertex = isVertex;
    }

    /* next, back Getter/Setter */
    public Position getNext() {
        return next;
    }

    public void setNext(Position nextPos) {
        this.next = nextPos;
    }

    public Position getBack() {
        return back;
    }

    public void setBack(Position backPos) {
        this.back = backPos;
    }

    /* Alternative Next, Back Getter/Setter */
    public Position getAltNext() {
        return altNext;
    }

    public void setAltNext(Position altNextPos) {
        this.altNext = altNextPos;
    }

    public Position getAltBack() {
        return altBack;
    }

    public void setAltBack(Position altBackPos) {
        this.altBack = altBackPos;
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

    public void printPositionInfo() {
        System.out.println("POSITION INDEX : " + this.getIndex() + ", IS VERTEX? : " + this.isVertex() + ", IS CENTER? : " + this.isCenter());
        System.out.println("BACK POS : " + this.getBack().getIndex() + " ... " + this.getIndex() + " ... NEXT POS : " + this.getNext().getIndex());
    }
}

