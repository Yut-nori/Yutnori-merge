package model.Yut;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class YutResultTest {


    /* Test 1. 윷의 결과 숫자 범위가 잘 나오는가?
    윷을 던지는 throwYuts() 메서드를 수행할 때, 빽도부터 모까지 -1부터 5까지의 범위의 숫자가 나와야함.
    반복테스트를 통해 6가지의 범위 외에 Invalid 한 값의 유무를 확인
     */
    @RepeatedTest(10)
    public void testYutResultRange() {
        YutResult.throwYuts();
        int result = YutResult.getYutResult();

        // 결과는 -1 (빽도) 또는 1~5 (도~모) 중 하나여야 함
        assertTrue(result >= -1 && result <= 5,
                "윷 결과는 반드시 -1 이상 5 이하이여야함.");
    }

    /* Test 2. 숫자와 문자열 매핑이 잘 되는가?
    윷 결과가 나온 숫자에 대해, 정확한 한글 이름(빽도,도,개,걸,윷,모)으로 잘 매핑되어 나오는지 확인
    모든 케이스가 다 나오는지 확인하기 위해 10번의 테스트 과정을 수행
     */
    @RepeatedTest(10)
    public void testResultNameMatchesResult() {
        YutResult.throwYuts();
        int result = YutResult.getYutResult();
        String name = YutResult.getResultName();

        switch (result) {
            case -1 -> assertEquals("빽도", name);
            case 1 -> assertEquals("도", name);
            case 2 -> assertEquals("개", name);
            case 3 -> assertEquals("걸", name);
            case 4 -> assertEquals("윷", name);
            case 5 -> assertEquals("모", name);
            default -> fail("Invalid result: " + result);
        }
    }
}