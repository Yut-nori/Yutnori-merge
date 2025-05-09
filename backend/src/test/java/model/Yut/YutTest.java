package model.Yut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

public class YutTest {

    private Yut regularYut;
    private Yut backDoYut;

    @BeforeEach
    public void setUp() {
        regularYut = new Yut(false);       // 일반 윷
        backDoYut = new Yut(true);         // 백도 윷
    }

    /* Test 1. toss 결과 값이 유효한가?
    Yut 객체에 toss() 메서드를 수행했을 때, 윷의 상태는 앞면 또는 뒷면 중 하나가 나와야함.
    랜덤 결과라도 하나 (isFlat() 이나 isRound())만 나와야함.
    항상 유효한 결과가 나오는지 확인하기 위해 10번의 테스트를 수행
     */
    @RepeatedTest(10)
    public void testTossReturnsBoolean() {
        regularYut.toss();
        assertTrue(regularYut.isFlat() || regularYut.isRound());

        backDoYut.toss();
        assertTrue(backDoYut.isFlat() || backDoYut.isRound());
    }


    /* Test 2. 빽도 윷 여부가 정확하게 구분되는가.
    생성자에서 isBackdoYut 값을 설정하면 메서드가 올바르게 수행되는지 확인
    빽도 윷은 isBackDoYut()에서 true를 반환해야하고, regularYut은 false를 반환해야함.
     */
    @RepeatedTest(5)
    public void testBackDoYutFlag() {
        assertTrue(backDoYut.isBackDoYut());
        assertFalse(regularYut.isBackDoYut());
    }
}