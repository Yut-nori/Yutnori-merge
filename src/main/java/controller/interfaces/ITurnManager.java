package controller.interfaces;

import model.Player;

public interface ITurnManager {
    void doPlayerTurn(Player player, boolean isTest, int[][] testResult);
    int getNextPlayer();
}
