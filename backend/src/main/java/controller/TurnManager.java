package controller;

import java.util.List;
import java.util.ArrayList;
import model.Status;

import controller.interfaces.IMoveManager;
import controller.interfaces.ITurnManager;
import model.GroupUnit;
import model.Player;
import model.Unit;
import view.interfaces.IView;

public class
TurnManager implements ITurnManager {
    private int currentPlayer = 0 ;
    private int numPlayer = 2;
    private GroupManager groupManager;
    private IMoveManager moveManager;
    private IView view;


    public TurnManager(int numPlayer, GroupManager groupManager, IMoveManager moveManager, IView view) {
        this.numPlayer = numPlayer;
        this.groupManager = groupManager;
        this.moveManager = moveManager;
        this.view = view;
    }

    //플레이어 턴 관리
    //yut 결과를 throwReuslt에 저장, throwResult가 Empty 되기 전까지, 플레이어가 유닛 이동 명령을 내릴 수 있음
    //유닛 이동은 groupManager에서 실행
    @Override
    public String move(Player current, List<GroupUnit> playerGroups, int selectedResult, int selectedGroup, List<Integer> throwResult,boolean isTest ) {
        moveManager.handleUserMove(playerGroups, selectedResult, selectedGroup);
        String resultEvent = moveManager.handlePostMoveActions(current, playerGroups, throwResult, selectedGroup, isTest);
        //playerGroups = groupManager.getGroupsByPlayer(current);
        //이동을 완료한 후, 모든 플레이어의 유닛의 상태를 조회하여, 게임 종료 여부 확인
        view.displayBoardStatus(current, groupManager.getGroupsByPlayer(current));
        if (isAllUnitsEnded(current)) {
            current.setWinner(true);
            view.displayVictory(current);
        }
        if(throwResult.isEmpty()) setNextPlayer();
        return resultEvent;
    }

    @Override
    public List<Integer> throwResult(Player player){
        List<Integer> throwResult = new ArrayList<>();
        throwResult.addAll(player.throwYut());
        System.out.println("return throwResult, "+" value : "+throwResult);
        return throwResult;
    }

    @Override
    public List<Integer> throwResult(int setYut){
        List<Integer> throwResult = new ArrayList<>();
        throwResult.add(setYut);
        System.out.println("return Test throwResult, "+" value : "+throwResult);
        return throwResult;
    }


    @Override
    //현재 플레이어 정보 getter
    public void setNextPlayer() {
        currentPlayer = (currentPlayer + 1) % numPlayer;
    }

    @Override
    public int getCurrentPlayer(){
        return currentPlayer;
    }


    //doPlayerTurn에서 호출하여, 모든 유닛이 빠져나왔는지 검사
    private boolean isAllUnitsEnded(Player player) {
        for (Unit unit : player.getUnits()) {
            if(unit.getStatus() != Status.END){ return false;}
        }
        return true;
    }
}
