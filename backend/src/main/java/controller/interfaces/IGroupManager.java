package controller.interfaces;
import java.util.List;
import model.GroupUnit;
import model.Player;
import model.Unit;
import model.board.Position;

public interface IGroupManager {
    GroupUnit createGroup(Player player, Unit unit);
    List<GroupUnit> getGroupsByPlayer(Player player);
    void mergeGroups(GroupUnit targetGroup, Position position);
    void resetGroupToStart(GroupUnit group);
    List<GroupUnit> getGroup();
    void unitPassed(GroupUnit completedGroup);
}