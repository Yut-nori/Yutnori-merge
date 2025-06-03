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
    private IView view;

    public TurnManager(int numPlayer, IView view) {
        this.numPlayer = numPlayer;
        this.view = view;
    }

    @Override
    public void checkTurnResult(Player current, List<Integer> throwResult){
        if (isAllUnitsEnded(current)) {
            current.setWinner(true);
            //view.displayVictory(current);
        }
        if(throwResult.isEmpty()) setNextPlayer();
        System.out.println("checkTurnResult, "+" value : "+throwResult);
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
