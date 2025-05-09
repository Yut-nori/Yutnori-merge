package controller;

import model.board.Board;

public class BoardManager {
    private static Board gameBoard;

    public static void createBoard(int edgeNum) {
        gameBoard = new Board(edgeNum);
    }

    public static Board getBoard() {
        return gameBoard;
    }

}
