import controller.Starter;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 윷놀이 게임을 시작합니다 ===");

        // 플레이어 수, 이름, 유닛 수, 보드 변 수 설정
        int playerCount = 2;
        String[] playerNames = {"player1", "player2"};
        int unitCount = 2;
        int edgeCount = 5;

        // Starter 인스턴스 생성
        Starter starter = new Starter(playerCount, playerNames, unitCount, edgeCount);

        int[][] eachTestResult = new int[playerCount][];

        eachTestResult[0] = new int[]{4, 4, 4, 3, 4, 4};
        eachTestResult[1] = new int[]{10,1,3,3,-1,5,1,2,3,4,5};

        // 실제 확률 기반 플레이 실행
        starter.start(true, eachTestResult); // false = 확률 기반, testResult = null
    }
}
