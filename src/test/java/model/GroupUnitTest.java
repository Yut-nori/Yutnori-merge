package model;

import controller.BoardManager;
import model.board.Board;
import model.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GroupUnitTest {

    private GroupUnit group;
    private Unit unit1, unit2;
    private List<Unit> unitList;
    private Position position0;

    @BeforeEach
    public void setUp() {
        // 보드 초기화
        BoardManager.createBoard(6);  // 육각형으로 보드 생성
        Board board = BoardManager.getBoard();
        position0 = board.getPosition(0);

        // 유닛 생성
        unit1 = new Unit();
        unit2 = new Unit();

        // 유닛 리스트 구성
        unitList = Arrays.asList(unit1, unit2);

        // 그룹 유닛 생성 (Player 생성자 변경 반영)
        Player testPlayer = new Player("testPlayer", 2, false);
        testPlayer.addUnit(unit1);
        testPlayer.addUnit(unit2);
        group = new GroupUnit(testPlayer, unitList);
    }

    /* Test 1. GroupUnit이 생성될 때, 초기위치가 0번인가?
    위치가 0으로 초기화되었고, 그룹 또한 이 위치로 초기화되어야하는지 확인하는 기본 상태 검증
    잘 수행됨.
     */
    @Test
    public void testInitialPosition() {
        assertEquals(position0, group.getCurrentPosition());
    }

    /* Test 2. 그룹 유닛의 이동 경로가 스택에 잘 기록되어 있는가?
    index 의 위치를 push 한 이후에 스택에 잘 기록되어 있는지 확인
    index 위치를 pop 하고 peek 하는 방식도 잘 기록되는가 => 이 부분은 빽도 로직을 수행할 때 필요함.
     */
    @Test
    public void testPushAndPopHistory() {
        group.pushHistory(BoardManager.getBoard().getPosition(1));
        group.pushHistory(BoardManager.getBoard().getPosition(2));
        assertEquals(2, group.peekHistory());
        assertEquals(2, group.popHistory());
        assertEquals(1, group.peekHistory());
    }

    /* Test 3. 0번 위치를 지난 상태를 표시하는 passedZero boolean 값이 setter/getter 동작 확인
    완주 판단시에 중요한 조건
    markPassedZero, markNotPassedZero 메서드의 정확한 동작을 확인한다.
    완주 조건 : 0을 통과한 기록이 있는 경우 (출발할 때 시작점은 0부터지만 그 경우에는 기록이 안됨.)
     */
    @Test
    public void testPassedZero() {
        assertFalse(group.hasPassedZero());
        group.markPassedZero();
        assertTrue(group.hasPassedZero());
        group.markNotPassedZero();
        assertFalse(group.hasPassedZero());
    }

    /* Test 4. 지름길 경로 사용을 잘 하는가?
    현재 경로 ID 설정 및 해체 기능을 검증한다.
    Path() 경로를 잘 사용하는지 확인, releasePath() 메서드를 호출 시에는 다시 미사용 상태로 넘어가야함.
     */
    @Test
    public void testPathManagement() {
        assertFalse(group.hasPath());
        group.setPath(3);
        assertTrue(group.hasPath());
        assertEquals(3, group.getCurrentPathID());
        group.releasePath();
        assertFalse(group.hasPath());
    }

    /* Test 5. Group Unit 의 위치 설정이 내부 유닛에게도 잘 반영이 되었는가?
    GroupUnit 의 위치를 설정 시에 내부에 그룹에 속해 있는 유닛들도 동일하게 갱신되는지 확인
    그룹으로 이동을 하지만, 그룹 -> 개발 유닛으로 위치 동기화 기능을 올바르게 수행하는지는 확인해야함.
     */
    @Test
    public void testPositionSyncWithUnits() {
        Position newPos = BoardManager.getBoard().getPosition(5); // 그룹의 위치 5번으로 설정
        group.setPosition(newPos);
        assertEquals(newPos, group.getCurrentPosition());
        for (Unit u : group.getUnitGroup()) {
            assertEquals(newPos, u.getCurrentPosition()); // 모든 유닛이 다 5번에 있는지 확인함.
        }
    }
}