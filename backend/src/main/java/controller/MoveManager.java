package controller;

import java.util.List;

import controller.interfaces.IMoveManager;
import model.Status;
import view.interfaces.IView;
import model.GroupUnit;
import model.Player;
import model.board.Position;

public class MoveManager implements IMoveManager {
    private GroupManager groupManager;
    private GroupMovement groupMovement;
    private GroupPositionChecker groupPositionChecker;
    private IView view;
    /*
        public static final int ENEMY_CAPTURED = 1;
        public static final int FRIEND_STACKED = 2;
        public static final int GOAL_REACHED = 3;
        public static final int FIRST_MOVE_BACK = 4;
    */


    public MoveManager(GroupManager groupManager, IView view) {
        this.groupManager = groupManager;
        this.groupMovement = new GroupMovement(groupManager);
        this.groupPositionChecker = new GroupPositionChecker(groupManager);
        this.view = view;
    }

    @Override
    public void handleUserMove(List<GroupUnit> playerGroups, int throwResult, int selectedGroup) {
        GroupUnit movingGroup = playerGroups.get(selectedGroup);
        groupMovement.moveGroup(movingGroup, throwResult);
    }

    @Override
    public String handlePostMoveActions(Player current, List<GroupUnit> playerGroups, List<Integer> throwResult, int selectedGroup, boolean checkMode) {
        // selectedGroup이 제거될 수 있으므로 먼저 참조를 저장
        GroupUnit completedGroup = playerGroups.get(selectedGroup);
        Position newPos = completedGroup.getCurrentPosition();
        boolean mode = checkMode;
        String result = "NORMAL_MOVE";

        //hasPassedZero()를 검사하도록 추가
        if (newPos.getIndex() > 0 && completedGroup.hasPassedZero()) {
            result = "GOAL_REACHED";
            groupManager.unitPassed(completedGroup); // 정확한 그룹 제거
        } else if (groupPositionChecker.isEnemytInPosition(current, newPos)) {
            result = "ENEMY_CAPTURED";
            if(!mode)
                throwResult.addAll(current.throwYut());
        } else if (groupPositionChecker.isFriendlyInPosition(current, newPos)) {
            result = "FRIEND_STACKED";
        } else if (completedGroup.isHistoryEmpty()) {
            for (GroupUnit groupUnit : playerGroups) {
                if (groupUnit.getGroupStatus() == Status.ON) {
                    throwResult.add(-1);
                    break;
                }
            }
            result = "FIRST_MOVE_BACK";
        }
        view.displayMoveResult(result);
        return result;
    }
}