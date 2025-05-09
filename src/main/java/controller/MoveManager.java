package controller;

import java.util.List;

import controller.interfaces.IMoveManager;
import view.interfaces.IView;
import model.GroupUnit;
import model.Player;
import model.board.Position;

public class MoveManager implements IMoveManager {
    private GroupManager groupManager;
    private GroupMovement groupMovement;
    private GroupPositionChecker groupPositionChecker;
    private IView view;

    public static final int ENEMY_CAPTURED = 1;
    public static final int FRIEND_STACKED = 2;
    public static final int GOAL_REACHED = 3;
    public static final int FIRST_MOVE_BACK = 4;

    public MoveManager(GroupManager groupManager, IView view) {
        this.groupManager = groupManager;
        this.groupMovement = new GroupMovement(groupManager);
        this.groupPositionChecker = new GroupPositionChecker(groupManager);
        this.view = view;
    }

    @Override
    public int handleUserMove(List<GroupUnit> playerGroups, List<Integer> throwResult) {
        view.displayBoardStatus(playerGroups.get(0).getPlayer(), playerGroups);
        view.ShowDiceResult(throwResult);

        int selectUnit = view.getGroupSelection();
        int selectPosition = view.getMoveSelection();

        GroupUnit selectedGroup = playerGroups.get(selectUnit);
        int moveDistance = throwResult.get(selectPosition);

        groupMovement.moveGroup(selectedGroup, moveDistance);

        throwResult.remove(selectPosition);
        return selectUnit;
    }

    @Override
    public void handlePostMoveActions(Player current, List<GroupUnit> playerGroups, List<Integer> throwResult, int selectedGroup) {
        // selectedGroup이 제거될 수 있으므로 먼저 참조를 저장
        GroupUnit completedGroup = playerGroups.get(selectedGroup);
        Position newPos = completedGroup.getCurrentPosition();
        int result = 0;

        //hasPassedZero()를 검사하도록 추가
        if (newPos.getIndex() > 0 && completedGroup.hasPassedZero()) {
            result = GOAL_REACHED;
            groupManager.unitPassed(completedGroup); // 정확한 그룹 제거
        } else if (groupPositionChecker.isEnemytInPosition(current, newPos)) {
            result = ENEMY_CAPTURED;
            throwResult.addAll(current.throwYut());
        } else if (groupPositionChecker.isFriendlyInPosition(current, newPos)) {
            result = FRIEND_STACKED;
        } else if(completedGroup.isHistoryEmpty()){
            result =FIRST_MOVE_BACK;
        }

        view.displayMoveResult(result);
    }
}