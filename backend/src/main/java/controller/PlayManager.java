package controller;

import java.util.ArrayList;
import java.util.List;

import model.GroupUnit;
import model.Player;
import model.Unit;
import model.board.Board;
import controller.interfaces.*;
import view.GameView;
import view.interfaces.IView;


public class PlayManager {
    private List<Player> playerList;
    private int numPlayer;
    private int currentPlayer;
    private int playerUnitNum;
    private Board gameBoard;
    private List<Integer> throwResult;
    private boolean isTest;

    /* TurnManger와 MoveManager 인터페이스화*/
    protected  BoardManager boardManager;
    protected  GroupManager groupManager;
    protected  TurnManager turnManager;
    protected  MoveManager moveManager;
    protected  IView iView;

    public PlayManager(
            int numPlayer,
            int boardEdgeNum,
            String[] playerNameList,
            int playerUnitNum,
            boolean isTest
    ) {
        this.numPlayer = numPlayer;
        this.playerUnitNum = playerUnitNum;
        this.isTest = isTest;

        boardManager = new BoardManager();
        groupManager = new GroupManager();
        iView = new GameView();
        moveManager = new MoveManager(groupManager, iView);
        turnManager = new TurnManager(numPlayer, groupManager, moveManager, iView);

        this.gameBoard = new Board(boardEdgeNum);

        createPlayer(playerNameList, playerUnitNum);
        createGroupManager(this.playerList);
    }

    //유저 생성
    public final void createPlayer(String[] playerNameList, int playerUnitNum) {
        for (String name : playerNameList) {
            Player player = new Player(name, playerUnitNum, false);
            for (int i = 0; i < playerUnitNum; i++) {
                player.addUnit(new Unit());
            }
            this.playerList.add(player);
        }
    }

    //그룹 매니저 생성 -> (구)UnitManager
    public final void createGroupManager(List<Player> playerList) {
        for (Player player : playerList) {
            List<Unit> units = player.getUnits();
            for (Unit unit : units) {
                this.groupManager.createGroup(player, unit);
            }
        }
    }

    //게임 종료 확인, GamePlay가 끝날 때마다 모든 player을 조회하여 끝난 플레이어가 있으면 return 하여 종료
    public boolean checkEnd() {
        // 게임의 끝 확인
        for (Player player : playerList) {
            if (player.isWinner()) return true;
        }
        return false;
    }

    public List<Integer> getYutResult(boolean isTest, int[][] testResult) {
        Player current = this.playerList.get(currentPlayer);
        this.throwResult.addAll(turnManager.throwResult(current, isTest));
        return this.throwResult;
    }

    public void setUnitMove(int selectUnit, int selectedYut) {
        Player current = this.playerList.get(currentPlayer);
        List<GroupUnit> playerGroups = groupManager.getGroupsByPlayer(current);
        int selected = 0;
        /*for(int i=0;i<throwResult.size();i++) {
            if(throwResult.get(i) == selectedYut) {
                selected = throwResult.get(i);
                throwResult.remove(i);
                break;
            }
        }*/
        turnManager.move(current, playerGroups, throwResult);
    }

    public int getCurrentPlayer(){
        return this.currentPlayer;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayer;
    }

    public GroupManager getGroupManager() {
        return groupManager;
    }

    public IMoveManager getMoveManager() {
        return moveManager;
    }
}
