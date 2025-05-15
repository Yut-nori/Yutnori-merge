package api;

import controller.PlayManager;

public class OptionAPI {

    private PlayManager playManager;

    public void setOption(int playerNum, int unitNum, int shape, boolean isTest) {
        // API 호출 로직
        String[] playerNameList = new String[playerNum];
        for(int i=0;i<playerNum;i++) {
            playerNameList[i] = "Player" + (i + 1);
        }
        playManager = new PlayManager(playerNum, shape, playerNameList, unitNum, isTest);
        System.out.println("Option set!: " + playerNum + ", " + unitNum + ", " + shape);
    }

    public PlayManager getPlayManager() {
        return playManager;
    }
}