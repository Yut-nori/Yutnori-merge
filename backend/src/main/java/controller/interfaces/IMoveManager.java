package controller.interfaces;
import java.util.List;
import model.GroupUnit;
import model.Player;

public interface IMoveManager {
    int handleUserMove(List<GroupUnit> groups, List<Integer> result);
    String handlePostMoveActions(Player p, List<GroupUnit> groups, List<Integer> result, int selectedGroup);
}