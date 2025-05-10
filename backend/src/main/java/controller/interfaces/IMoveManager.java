package controller.interfaces;
import java.util.List;
import model.GroupUnit;
import model.Player;

public interface IMoveManager {
    void handleUserMove(List<GroupUnit> groups, int result, int selectedGroup);
    String handlePostMoveActions(Player p, List<GroupUnit> groups, List<Integer> result, int selectedGroup, boolean isTest);
}