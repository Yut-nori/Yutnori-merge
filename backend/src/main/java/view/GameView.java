package view;

import java.util.List;
import java.util.Scanner;

import model.GroupUnit;
import model.Player;
import model.board.Position;
import view.interfaces.IView;

public class GameView implements IView {
    private Scanner scanner;

    public GameView() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void displayWelcome(int numPlayer, int playerUnitNum, int boardEdgeNum) {
        System.out.println("=== 윷놀이 게임에 오신 것을 환영합니다! ===");
        System.out.print("[*] 플레이어 수 : " + numPlayer + " ");
        System.out.print("[*] 플레이어 당 유닛 수 : " + playerUnitNum + " ");
        System.out.println("[*] 맵 사이즈 : "+boardEdgeNum+"x"+boardEdgeNum+"\n");
    }

    @Override
    public void displayBoardStatus(Player player, List<GroupUnit> groups) {
        System.out.println("\n[현재 플레이어: " + player.getPlayerName() + "]");
        for (int i = 0; i < groups.size(); i++) {
            Position pos = groups.get(i).getCurrentPosition();
            System.out.println("[" + i + "] 그룹 위치: " + (pos != null ? pos.getIndex() : "시작지점"));
        }
    }

    @Override
    public int getGroupSelection() {
        System.out.print("이동할 그룹의 번호를 선택하세요: ");
        return scanner.nextInt();
    }

    @Override
    public void ShowDiceResult(List<Integer> throwResult) {
        System.out.println("윷 결과: " + throwResult);
    }

    public int getMoveSelection(){
        System.out.print("사용할 결과 인덱스를 선택하세요: (0~N)");
        return scanner.nextInt();
    }


    @Override
    public void displayMoveResult(String result) {
        switch (result) {
            case "ENEMY_CAPTURED" -> System.out.println("[*] 상대방 유닛을 잡았습니다! 윷을 한 번 더 던집니다!");
            case "FRIEND_STACKED" -> System.out.println("[*] 아군 유닛과 합쳐졌습니다.");
            case "GOAL_REACHED"   -> System.out.println("[*] 결승선을 통과했습니다! 유닛이 제거됩니다.");
            case "FIRST_MOVE_BACK" -> System.out.println("[*] 출발한 유닛이 없어 [빽도]를 진행할 수 없습니다. 넘어갑니다.");
            default -> System.out.println("[*] 일반 이동입니다.");
        }
    }

    @Override
    public void displayVictory(Player player) {
        System.out.println("축하합니다!! " + player.getPlayerName() + "님이 게임에서 승리하셨습니다!");
    }

    @Override
    public String displayOneMoreGame(){
        System.out.println("[*] 게임을 다시 시작하려면 'r', 종료하려면 'q'를 입력하세요.");
        return scanner.nextLine();
   }
}
