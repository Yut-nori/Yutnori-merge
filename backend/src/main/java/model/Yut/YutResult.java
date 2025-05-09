package model.Yut;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Yut.Yut;


// NUM_OF_YUTS 으로 윷 개수 지정. 윷이 4개 이상이라면 이 클래스에서 유지.보수 가능
// 윷을 던져 나오는 도, 개, 걸, 윷, 모, 빽도에 대한 값을 yutResult로 반환
public class YutResult {
    private static final int NUM_OF_YUTS = 4; // 윷 개수 (변경 가능)
    private static List<Yut> yutList = new ArrayList<>();
    private static int yutResult;

    public static void throwYuts() {
        yutList.clear();
        Random rand = new Random();

        int backDoIndex = rand.nextInt(NUM_OF_YUTS); // 빽도 윷 하나 지정
        int flatCount = 0;

        // 윷 개수 만큼 생성하고 하나의 값은 빽도 윷으로 지정
        for (int i = 0; i < NUM_OF_YUTS; i++) {
            boolean isBackDo = (i == backDoIndex);
            Yut yut = new Yut(isBackDo);
            yut.toss();
            yutList.add(yut);
        }

        // flat 개수 세기
        for (Yut y : yutList) {
            if (y.isFlat()) flatCount++;
        }

        // 결과값 반환
        if (flatCount == 1 && yutList.get(backDoIndex).isFlat()) {
            yutResult = -1; // 빽도
        } else {
            switch (flatCount) {
                case 1 -> yutResult = 1; // 도
                case 2 -> yutResult = 2; // 개
                case 3 -> yutResult = 3; // 걸
                case 4 -> yutResult = 4; // 윷
                case 0 -> yutResult = 5; // 모 (5개일 때)
                default -> yutResult = 0; // 예외
            }
        }
    }

    public static int getYutResult() {
        return yutResult;
    }

    // 테스트 용 : 윷이 어떻게 나왔는지를 결과로 출력
    // Yut 1: Round
    // Yut 2: Flat (BackDo Yut)
    // Yut 3: Round
    // Yut 4: Round
    public static void printYuts() {
        for (int i = 0; i < yutList.size(); i++) {
            Yut yut = yutList.get(i);
            String shape = yut.isFlat() ? "Flat" : "Round";
            String type = yut.isBackDoYut() ? "(BackDo Yut)" : "";
            System.out.println("Yut " + (i + 1) + ": " + shape + " " + type);
        }
    }

    public static String getResultName() {
        return switch (yutResult) {
            case -1 -> "빽도";
            case 1 -> "도";
            case 2 -> "개";
            case 3 -> "걸";
            case 4 -> "윷";
            case 5 -> "모";
            default -> "Invalid error";
        };
    }
}
