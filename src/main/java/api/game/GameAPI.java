package api.game;

public class GameAPI {

    // 랜덤 윷 던지기 (메서드 오버로딩)
    public void throwYut() {
        System.out.println("랜덤 윷 api 호출됨");
    }

    // 지정 윷 던지기 (메서드 오버로딩)
    public void throwYut(int designatedYutResult) {
        System.out.println("지정 윷 api 호출됨");
    }

    public void moveUnit(int selectedYut, int selectedUnit) {
        // 유닛 이동 로직
        System.out.println("유닛 이동 api 호출됨: " + selectedYut + ", " + selectedUnit);
    }
}
