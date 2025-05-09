package model;

import controller.BoardManager;
import model.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;
    private Unit unit1;
    private Unit unit2;

    @BeforeEach
    public void setUp() {
        BoardManager.createBoard(5); // 오각형 보드 생성

        player = new Player("TestPlayer", 2, false);
        unit1 = new Unit();
        unit2 = new Unit();
        player.addUnit(unit1);
        player.addUnit(unit2);
    }

    /* Test 1 . Player 객체 생성 시 필드가 올바르게 초기화되는가?
    플레이어 이름, 유닛 수, 초기 승리 여부 boolean 값, 등록된 유닛 리스트 크기 확인
     */
    @Test
    public void testPlayerInitialization() {
        assertEquals("TestPlayer", player.getPlayerName());
        assertEquals(2, player.getUnitNum());
        assertFalse(player.isWinner());
        assertEquals(2, player.getUnits().size());
    }

    /* Test 2. 게임의 승리반환이 잘 되는가?
    setWinner() 메서드가 isWinner 의 플래그를 제대로 변경하는가?
    처음 게임을 시작할때는 isWinner()는 false,
    게임의 승리 조건에 도달했을 때는 setWinner(true) 를 호출하고 isWinner() 는 true
     */
    @Test
    public void testSetWinner() {
        assertFalse(player.isWinner());
        player.setWinner(true);
        assertTrue(player.isWinner());
    }

    /* Test 3. 새로운 유닛을 리스트에 잘 추가하는가?
    addUnit() 메서드가 올바르긴 결과값을 내는지 확인
    유닛을 증가했을 때, 결과가 원하는대로 증가했는지 추가한 유닛이 리스트에 포함되어 있는지 확인
     */
    @Test
    public void testAddUnit() {
        Unit unit3 = new Unit();
        player.addUnit(unit3);
        assertEquals(3, player.getUnits().size());
        assertTrue(player.getUnits().contains(unit3));
    }

    /* Test 4. throwYut() 함수가 윷 결과를 잘 생성하는가?
    반환된 결과가 null 이 나오면 안되고, 비어있어도 안된다.
    모든 결과의 윷이 빽도(-1) 부터 모 (5) 사이에 있는지 확인한다.
     */
    @Test
    public void testThrowYut() {
        List<Integer> result = player.throwYut();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        for (int val : result) {
            assertTrue(val >= -1 && val <= 5);
        }
    }
}