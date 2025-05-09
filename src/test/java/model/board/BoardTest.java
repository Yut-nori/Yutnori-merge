package model.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = new Board(4);  // 기본 사각형 보드
    }

    /* Test 1. 전체 포지션 개수가 정확히 일치하는가?
    board.getNumberOfPositions9) -> 실제로 계산된 포지션 수
    board.getPositionArr().length() -> 배열의 길이
    두 값이 정확히 같아야 보드가 오류가 없이 생성된 것이다.
     */
    @Test
    void testPositionArraySize() {
        int expectedSize = board.getNumberOfPositions();
        assertEquals(expectedSize, board.getPositionArr().length, "Position 배열 길이 불일치");
    }

    /* Test 2. 외부 바깥의 경로가 정상적으로 순환되는가?
    바깥 경로의 마지막 position의 다음 경로는 시작 포지션이여야한다.
    시작 포지션의 back은 마지막 포지션이여야 한다. (나중에 도착하고 빽도를 구현하기 위함.)
    오류인 상황 : start와 연결되어 있지 않는다면 순환 경로가 끊어진 상태
     */
    @Test
    void testStartAndEndPosition() {
        Position start = board.getPosition(0);
        Position last = board.getPosition(board.getLastOuterPosNum());

        assertEquals(start, last.getNext(), "마지막 position의 next는 start");
        assertEquals(last, start.getBack(), "start position의 back은 마지막");
    }

    /* Test 3. 중심 CenterPosition이 올바르게 생성되었는가?
    센터 index에 들어오면 isCenter()는 true 로 마킹되어야 한다.
    center의 index는 전체 Position의 수의 -1 이여야한다. (배열의 마지막 위치)
     */
    @Test
    void testCenterPosition() {
        Position center = board.getCenterPos();
        assertTrue(center.isCenter(), "center 위치가 center로 체킹되어있지 않다.");
        assertEquals(board.getNumberOfPositions() - 1, center.getIndex(), "center index가 아님");
    }

    /* Test 4. inner Path (지름길)이 잘 생성되었는가?
    전체 innerPath 맵이 null 아니고, 각 index에 대해 대응하는 값이 null이 아니여야함.
    만약 해당 path에 center가 있으면, cener 객체가 명시적으로 포함되어 있어야 함.
    각 꼭짓점에서 중심까지의 지름길이 설정되어있는지를 확인하는 것
     */
    @Test
    void testInnerPathsExist() {
        assertNotNull(board.getInnerPaths(), "innerPaths가 null임");
        assertFalse(board.getInnerPaths().isEmpty(), "innerPaths가 없다.");

        for (int key : board.getInnerPaths().keySet()) {
            List<Position> path = board.getInnerPath(key);
            assertNotNull(path, "해당 vertexIndex의 innerPath가 없다.");
            if (!path.isEmpty()) {
                // center가 있는 path만 확인
                boolean hasCenter = path.stream().anyMatch(p -> p.isCenter());
                if (hasCenter) {
                    assertTrue(path.contains(board.getCenterPos()), "center 객체 참조가 포함되어있다.");
                }
            }
        }
    }
}