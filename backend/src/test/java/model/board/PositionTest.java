package model.board;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.board.*;

public class PositionTest {

    /* Test 1. 기본 위치의 연결 확인
    Position 간의 next, back 연결이 제대로 되는지 확인함.
    예를 들어 pos1 -> pos2 로 이동하고, pos -> pos1 으로 돌아올 수 있는지 확인
     */
    @Test
    public void testIndexAndNextBack() {
        Position pos1 = new Position(1);
        Position pos2 = new Position(2);
        Position pos3 = new Position(3);

        pos1.setNext(pos2);
        pos2.setBack(pos1);

        assertEquals(1, pos1.getIndex());
        assertEquals(pos2, pos1.getNext());
        assertEquals(pos1, pos2.getBack());
    }

    /* Test 2. 보조 경로(AltNext)의 연결 확인
    altNext, altBack 이 정상 연결되는지 확인
    정점(vertex)이거나 센터에서 교차로로 가는 지름길 같은 경로가 있을 때 사용
     */
    @Test
    public void testAltNextAndAltBack() {
        Position pos1 = new Position(1);
        Position pos2 = new Position(99);

        pos1.setAltNext(pos2);
        pos2.setAltBack(pos1);

        assertEquals(pos2, pos1.getAltNext());
        assertEquals(pos1, pos2.getAltBack());
    }

    /* Test 3. 정점(vertex) 여부에 따라 next index 계산 여부
    getNextIndex()가 정점 여부에 따라 동작이 바뀌는지를 테스트함.
    isVertex()가 false이면 index + 1, isVertex()가 true이면 altNext.getIndex() 반환
    이를 통해 정점일 때, 안쪽의 지름길로 이동할 수 있도록 함.
     */
    @Test
    public void testVertexFlagAndAltNextIndex() {
        Position pos = new Position(5);
        Position alt = new Position(99);
        pos.setAltNext(alt);

        // 일반 상태면 +1
        assertEquals(6, pos.getNextIndex());

        // 정점일 경우 altNext의 인덱스를 따름
        pos.setVertex(true);
        assertEquals(99, pos.getNextIndex());
    }

    /* Test 4. 센터 설정시 자동 정점 설정 여부
    setCenter(true)를 하면 자동으로 isVertex도 true로 바뀌는지 확인함
    isCenter만 true가 되는게 아니라, 정점이기도 해야 하므로 isVertex도 true가 되는지 확인함.
    이를 통해 센터 내에서 어떤 경로를 통해 이동할 수 있는지도 확인할 수 있음.
     */
    @Test
    public void testIsCenterFlagSetsVertex() {
        Position center = new Position(0);
        center.setCenter(true);

        assertTrue(center.isCenter());
        assertTrue(center.isVertex());
    }
}