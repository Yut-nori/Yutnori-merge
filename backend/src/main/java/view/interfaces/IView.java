package view.interfaces;

import model.GroupUnit;
import model.Player;

import java.util.List;

public interface IView {

    // 게임 시작 시, 환영 메시지 출력
    public void displayWelcome(int numPlayer, int playerUnitNum, int boardEdgeNum);

        // 현재 플레이어와 그룹의 상태 출력
    void displayBoardStatus(Player player, List<GroupUnit> groups);

    // 이동할 그룹 선택 받기
    int getGroupSelection();

    // 사용할 주사위 결과 인덱스 선택 받기
    void ShowDiceResult(List<Integer> throwResult);

    int getMoveSelection();

    // 이동 결과에 대한 메시지 출력
    void displayMoveResult(int result);

    // 게임 승리 메시지 출력
    void displayVictory(Player player);

    // 게임 종료_재시작 결정
    String displayOneMoreGame();
}