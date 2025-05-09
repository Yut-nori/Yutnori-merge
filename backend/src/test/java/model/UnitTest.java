package model;

import controller.BoardManager;
import model.board.Board;
import model.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    private Unit unit;
    private Board board;

    @BeforeEach
    void setUp() {
        BoardManager.createBoard(4); // 사각형 보드로 테스트 진행
        board = BoardManager.getBoard();
        unit = new Unit();
    }

    /* Test 1. Unit의 객체 생성 직후의 초기상태는 어떠한가?
    currentPosition은 보드의 시작점인 index가 0에 위치해있어야한다.
    status는 READY 상태
     */
    @Test
    void testInitialState() {
        assertEquals(board.getPosition(0), unit.getCurrentPosition(), "첫 위치 start는 보드의 0번 인덱스여야 함");
        assertEquals(Unit.Status.READY, unit.getStatus(), "초기 상태는 READY여야 함");
        assertFalse(unit.isGrouped(), "초기 그룹화 상태는 false여야 함"); // 이동을 해야 true로 변경
    }

    /* Test 2. Unit의 상태가 변경 기능이 잘 동작하는가?
    setStatus(ON) 이후 getStatus()는 ON
    setStatus(END) 이후 getStatus()는 END 변경
    게임 이동 이동중에 상태변화로 중요한 기능에 해당
     */
    @Test
    void testSetStatus() {
        unit.setStatus(Unit.Status.ON);
        assertEquals(Unit.Status.ON, unit.getStatus());

        unit.setStatus(Unit.Status.END);
        assertEquals(Unit.Status.END, unit.getStatus());
    }

    /* Test 3. 유닛의 그룹 여부를 정확히 저장하고 반환하는가?
    setGrouped(true) 후 isGrouped()는 true
	setGrouped(false) 후 isGrouped()는 false
	다른 유닛과의 grouping이 잘 진행되는가?
     */
    @Test
    void testSetGrouped() {
        unit.setGrouped(true);
        assertTrue(unit.isGrouped());

        unit.setGrouped(false);
        assertFalse(unit.isGrouped());
    }

    /* Test 4. 유닛의 현재 위치를 설정하는가?
    setPosition(pos)로 설정한 후 getCurrentPosition()이 해당 위치인지
    게임에서 이동유닛을 구현할 때 필수 사용 메서드, 위치 설정의 정확성 확인
     */
    @Test
    void testSetPosition() {
        Position newPos = board.getPosition(2);
        unit.setPosition(newPos);
        assertEquals(newPos, unit.getCurrentPosition());
    }
}