package controller;

import model.GroupUnit;
import model.Player;
import model.Unit;
import model.board.Position;
import model.Status;

import java.util.List;

public class GroupPositionChecker {
    private GroupManager groupManager; //의존성 주입(결합도 낮추기)

    public GroupPositionChecker(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    public boolean isFriendlyInPosition(Player current, Position position ) {
        List<GroupUnit> groups = groupManager.getGroupsByPlayer(current);
        GroupUnit currentGroup = null;
        int findEqual = 0;

        for (GroupUnit group : groups) {
            if (group.getCurrentPosition().equals(position)) {

                // 병합 대상 그룹이 READY 에 있으면 합치지 않음.
                boolean allReady = true;
                for (Unit unit : group.getUnitGroup()) {
                    if (unit.getStatus() != Status.READY) {
                        allReady = false;
                        break;
                    }
                }
                if (allReady) continue;

                // 조회 중 자기 자신을 만난 경우도 포함되므로 ++
                findEqual++;
            }

            if (findEqual >= 2) {
                currentGroup = group;
                groupManager.mergeGroups(currentGroup, position);
                return true;
            }
        }
        return false;
    }

    public boolean isEnemytInPosition(Player current, Position position) {
        List<GroupUnit> groupList = groupManager.getGroup();
        for (GroupUnit group : groupList) {
            if (group.getCurrentPosition().equals(position)
                    && !group.getPlayer().getPlayerName().equals(current.getPlayerName())) {

                // READY 상태에 있는 상대 Unit 은 잡지 않음.
                boolean allReady = true;
                for (Unit unit : group.getUnitGroup()) {
                    if (unit.getStatus() != Status.READY) {
                        allReady = false;
                        break;
                    }
                }
                if (allReady) continue;
                groupManager.resetGroupToStart(group);
                for (Unit unit : group.getUnitGroup()) {
                    groupManager.createGroup(group.getPlayer(), unit);
                }
                groupList.remove(group);
                return true;
            }
        }
        return false;
    }
}


