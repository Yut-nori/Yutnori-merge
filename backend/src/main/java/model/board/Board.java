package model.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private int edgeNum;            // 변 개수 (사각형 -> 4, 오각형 -> 5, n각형 -> n)
    private int positionNum;        // 필요한 총 Position 수

    private int outerPositionNum;   // 바깥쪽 각 변마다 꼭짓점 사이의 Position 개수 (기본 : 4개)
    private int innerPositionNum;   // 꼭짓점과 중심점 사이의 Position 개수 (기본 : 2개)
    private Position start, end;
    private Position centerPos;

    private Position[] positionArr;
    private Map<Integer, List<Position>> innerPaths;

    /* n각형 보드 기본 -> 나중에 vertex와 vertex 사이끼리의 길이를 조정할 수 있고,
    vertex와 center 사이의 거리를 조정할 수도 있음.  */
    public Board(int edgeNum) {
        this(edgeNum, 4, 2);
    }

    /* n각형 보드에서 바깥쪽 / 안쪽 칸 개수 customize 가능 */
    public Board(int edgeNum, int outerPositionNum, int innerPositionNum) {
        this.edgeNum = edgeNum;
        this.positionNum = edgeNum * (outerPositionNum + innerPositionNum + 1) + 1;

        positionArr = new Position[positionNum];

        this.outerPositionNum = outerPositionNum;
        this.innerPositionNum = innerPositionNum;

        start = end = null;

        //paths = new Path[edgeNum - 2];
        innerPaths = new HashMap<>();
        createPositions();
    }

    private void createPositions() {
        int lastOuterPosNum = edgeNum * (outerPositionNum + 1) - 1;      // 바깥쪽 Position들 중 가장 마지막 Position

        /* 바깥쪽 테두리 Position 생성 및 연결*/
        for (int i = 0; i <= lastOuterPosNum; i++) {
            Position pos = new Position(i);
            positionArr[i] = pos;

            /* 첫 Position 생성 */
            if (i == 0) {
                start = end = pos;
                /* 나머지 점들은 일반적인 방법으로 이중연결리스트 연결 */
            } else {
                end.setNext(pos);
                end = pos;
            }

            /* 바깥쪽 n-2 개의 altNextPos를 가질 수 있는 Vertex 지정 */
            if ((i % (outerPositionNum + 1) == 0) && (i < (outerPositionNum + 1) * (edgeNum - 1)) && i != 0)
                pos.setVertex(true);
        }

        // 마지막 바깥쪽 position을 시작점과 연결해 원형 연결
        end.setNext(start);

        // Center 생성 및 등록
        centerPos = new Position(positionNum - 1);
        positionArr[positionNum - 1] = centerPos;
        centerPos.setCenter(true);

        // === 내부 경로 생성 및 연결 ===
        int innerBaseIndex = lastOuterPosNum + 1;

        List<Integer> vertexOrder = new ArrayList<>();
        for (int i = 1; i < edgeNum; i++) {
            vertexOrder.add(i * (outerPositionNum + 1));
        }
        vertexOrder.add(0);

        for (int i = innerBaseIndex; i < positionNum; i++) {
            if(i != positionNum - 1) positionArr[i] = new Position(i);
        }

        // center 연결
        for (int i = 0; i < vertexOrder.size(); i++) {
            int vIdx = vertexOrder.get(i);
            int innerStartPosIdx = innerBaseIndex + innerPositionNum * i;
            int innerLastPosIdx = innerBaseIndex + innerPositionNum * (i + 1) - 1;

            Position innerStartPos = positionArr[innerStartPosIdx];

            List<Position> eachInnerPath = new ArrayList<>();

            if (i < vertexOrder.size() - 2) {
                Position startVertex = positionArr[vertexOrder.get(i)];
                Position destVertex;
                if ((i + edgeNum / 2) < vertexOrder.size()) {
                    destVertex = positionArr[vertexOrder.get(i + edgeNum / 2)];
                } else {
                    destVertex = positionArr[0];
                }

                eachInnerPath.add(startVertex);
                eachInnerPath.add(innerStartPos);

                // 시작 꼭짓점 -> 중심점까지 innerPath 등록
                for (int j = innerStartPosIdx; j < innerLastPosIdx; j++) {
                    Position nextPos = positionArr[j + 1];
                    eachInnerPath.add(nextPos);
                }
                eachInnerPath.add(centerPos);

                int nextOfCenterPosIdx;
                if (vIdx > Math.ceil(innerBaseIndex / 2.0d)) {
                    nextOfCenterPosIdx = centerPos.getIndex() - 1;
                } else {
                    if (edgeNum % 2 == 0) {
                        nextOfCenterPosIdx = innerLastPosIdx + edgeNum;
                    } else {
                        nextOfCenterPosIdx = innerLastPosIdx + edgeNum - 1;
                    }
                }

                Position nextOfCenterPos = positionArr[nextOfCenterPosIdx];
                eachInnerPath.add(nextOfCenterPos);
                for (int j = nextOfCenterPosIdx; j > nextOfCenterPosIdx - innerPositionNum + 1; j--) {
                    Position nextPos = positionArr[j - 1];
                    eachInnerPath.add(nextPos);
                }
                eachInnerPath.add(destVertex);
            }

            innerPaths.put(vIdx, eachInnerPath);
        }

        // 지름길(시작점으로 들어오는 방향) 은 Path와 관계없이 시작점 방향으로 altNext로 연결
        int shortcutPosIdx = centerPos.getIndex() - 1;
        Position shortcutPos = positionArr[shortcutPosIdx];
        centerPos.setAltNext(shortcutPos);

        Position tmp = shortcutPos;
        for (int i = shortcutPosIdx; i > shortcutPosIdx - innerPositionNum + 1; i--) {
            Position altNextPos = positionArr[i - 1];
            tmp.setAltNext(altNextPos);
            tmp = altNextPos;
        }

        tmp.setAltNext(positionArr[0]);
    }


    public Position[] getPositionArr() {
        return positionArr;
    }

    public int getNumberOfPositions() {
        return positionNum;
    }

    public Position getCenterPos() {
        return positionArr[positionNum - 1];
    }

    public int getLastOuterPosNum() {
        return (outerPositionNum + 1) * edgeNum - 1;
    }

    public List<Position> getInnerPath(int vertexIndex) {
        return innerPaths.get(vertexIndex);
    }

    public Map<Integer, List<Position>> getInnerPaths() {
        return innerPaths;
    }

    public Position getPosition(int index) {
        return positionArr[index];
    }

    public int getOuterLength() {
        return edgeNum * (outerPositionNum + 1);
    }
}
