package controller;

import java.util.ArrayList;
import java.util.List;

import controller.interfaces.IGroupManager;
import model.GroupUnit;
import model.Player;
import model.Status;
import model.Unit;
import model.board.Position;

public class GroupManager implements IGroupManager {
    private List<GroupUnit> groupList;

    public GroupManager() {
        this.groupList = new ArrayList<>();
    }

    public GroupUnit createGroup(Player player, Unit unit) {
        List<Unit> units = new ArrayList<>();
        units.add(unit);
        GroupUnit group = new GroupUnit(player, units);
        groupList.add(group);
        return group;
    }

    public List<GroupUnit> getGroupsByPlayer(Player player) {
        List<GroupUnit> result = new ArrayList<>();
        for (GroupUnit group : groupList) {
            if (group.getPlayer().getPlayerName().equals(player.getPlayerName())) {
                result.add(group);
            }
        }
        return result;
    }

    public void mergeGroups(GroupUnit targetGroup, Position position) {
        for (GroupUnit group : groupList) {
            if (group.getCurrentPosition().equals(position) && group != targetGroup) {
                targetGroup.getUnitGroup().addAll(group.getUnitGroup());
                groupList.remove(group);
                break;
            }
        }
    }

    public void unitPassed(GroupUnit group) {
        for (Unit unit : group.getUnitGroup()) {
            unit.setStatus(Status.END);
        }
        groupList.remove(group);
    }

    public void resetGroupToStart(GroupUnit group) {
        for (Unit unit : group.getUnitGroup()) {
            unit.setStatus(Status.READY);
            unit.setPosition(BoardManager.getBoard().getPositionArr()[0]);
        }
        group.setPosition(BoardManager.getBoard().getPositionArr()[0]);
    }

    public List<GroupUnit> getGroup() {
        return groupList;
    }
}
