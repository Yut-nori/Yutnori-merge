package controller.interfaces;

import  java.util.List;

import model.GroupUnit;
import model.Player;

public interface ITurnManager {
   //String move(Player current, List<GroupUnit> playerGroups, int selectedResult, int selectedGroup, List<Integer> throwResult, boolean isTest);
   void checkTurnResult(Player current, List<Integer> throwResult);
   List<Integer> throwResult(Player player);
   List<Integer> throwResult(int setYut);

    void setNextPlayer();
    int getCurrentPlayer();
}
