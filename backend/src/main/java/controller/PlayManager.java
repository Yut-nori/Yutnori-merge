package controller;

import java.util.ArrayList;
import java.util.List;
/*
import controller.interfaces.IMoveManager;
import controller.interfaces.ITurnManager;
import controller.interfaces.IGroupManager;*/

import model.GroupUnit;
import model.Player;
import model.Status;
import model.Unit;
import model.board.Board;
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
    private String resultEvent;
    /* TurnManger와 MoveManager 인터페이스화*/
    protected GroupManager groupManager;
    protected TurnManager turnManager;
    protected MoveManager moveManager;
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
        this.playerList = new ArrayList<>();
        this.throwResult = new ArrayList<>();

        groupManager = new GroupManager();
        iView = new GameView();
        this.moveManager = new MoveManager( this.groupManager, this.iView);
        turnManager = new TurnManager(numPlayer ,iView);

        BoardManager.createBoard(boardEdgeNum);
        this.gameBoard = BoardManager.getBoard();

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

    //그룹 매니저 생성
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


    public void playerThrowYut() {
        Player current = this.playerList.get(currentPlayer);
        this.throwResult.addAll(turnManager.throwResult(current));
    }

    public void playerThrowYut(int setYut) {
        this.throwResult.addAll(turnManager.throwResult(setYut));
    }

    public List<Integer> getYutResult() {
        return this.throwResult;
    }

    public void setUnitMove(int selectGroup, int selectedYut) {
        Player current = this.playerList.get(currentPlayer);
        List<GroupUnit> playerGroups = groupManager.getGroupsByPlayer(current);
        boolean isTest = this.isTest;

        throwResult.remove(throwResult.indexOf(selectedYut));
        moveManager.handleUserMove(playerGroups, selectedYut, selectGroup);
        this.resultEvent = moveManager.handlePostMoveActions(current, playerGroups, throwResult,selectGroup,isTest);
        turnManager.checkTurnResult(current, throwResult);
        //turnManager.move(current, playerGroups, selectedYut, selectGroup, throwResult, isTest);

        setCurrentPlayer();
    }

    public void setCurrentPlayer(){
        this.currentPlayer = turnManager.getCurrentPlayer();
    }

    public int getCurrentPlayer(){
        return currentPlayer;
    }

    public String returnEvents(){
        return this.resultEvent;
    }


    //-1 출발 안 함
    //-2 끝난거
    //
    public int[][] getAllUnitsPosition(){
        List<GroupUnit> playerAllGroups;
        int [][] allUnitsPosition = new int[this.playerList.size()][playerUnitNum];
        for(int i = 0; i < this.playerList.size(); i++){
            playerAllGroups = groupManager.getGroupsByPlayer(this.playerList.get(i));
            for(int j = 0; j < playerAllGroups.size(); j++){
                allUnitsPosition[i][j] = playerAllGroups.get(j).getCurrentPosition().getIndex();
                if(allUnitsPosition[i][j] == 0 && playerAllGroups.get(j).getGroupStatus() == Status.READY){
                    allUnitsPosition[i][j] = -1;
                }
            }
        }
        return allUnitsPosition;
    }

    public int[][] getUnitsNumPerGroups(){
        List<GroupUnit> playerAllGroups;
        int [][] unitsPerGroups = new int[this.playerList.size()][playerUnitNum];
        for(int i = 0; i < this.playerList.size(); i++){
            playerAllGroups = groupManager.getGroupsByPlayer(this.playerList.get(i));
            for(int j = 0; j < playerAllGroups.size(); j++){
                unitsPerGroups[i][j] = playerAllGroups.get(j).getUnitGroup().size();
            }
        }
        return unitsPerGroups;
    }
}
