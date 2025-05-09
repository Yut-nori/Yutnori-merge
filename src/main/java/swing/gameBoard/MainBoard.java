package swing.gameBoard;

import swing.GameManager;
import swing.gameBoard.RightPanel.UnitIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static swing.gameBoard.UnitPosition.*;
import static swing.util.File.getFileName;
import static swing.util.File.imageLoading;

class MainBoard extends JPanel {
    private final String screenName = "GameBoard";
    private Map<String, BufferedImage> images = new HashMap<>();
    private final int shape;
    private final GameManager gm;
    public MainBoard(GameManager gm) {
        setLayout(null);
        setOpaque(false);
        this.gm = gm;
        this.shape = gm.getGameState().getShape();
        switch (shape) {
            case 4 -> setBounds(310, 60, 660, 640);
            case 5 -> setBounds(315, 70, 690, 690);
            case 6 -> setBounds(320, 60, 660, 660);
        }

        List<String> imageNames = getFileName(screenName);
        images = imageLoading(imageNames, screenName);

        int[][] unitPosition = gm.getGameState().getUnitPosition();
        int[][] unitGrouped = gm.getGameState().getUnitNumberPerPosition();
        for(int i = 0; i < unitPosition.length; i++) {
            Color color = switch(i + 1) {
                case 1 -> Color.RED;
                case 2 -> Color.BLUE;
                case 3 -> Color.GREEN;
                case 4 -> Color.YELLOW;
                default -> throw new IllegalStateException("Unexpected value: " + i);
            };
            for(int j = 0; j < unitPosition[i].length; j++) {
                if(unitPosition[i][j] == -1) continue;
                switch (shape) {
                    case 4 -> addUnit(i, j, rectangleUnitPositions[unitPosition[i][j]][0], rectangleUnitPositions[unitPosition[i][j]][1], unitGrouped[i][j], color);
                    case 5 -> addUnit(i, j, pentagonUnitPositions[unitPosition[i][j]][0], pentagonUnitPositions[unitPosition[i][j]][1], unitGrouped[i][j], color);
                    case 6 -> addUnit(i, j, hexagonUnitPositions[unitPosition[i][j]][0], hexagonUnitPositions[unitPosition[i][j]][1], unitGrouped[i][j], color);
                }
            }
        }
    }

    private void addUnit(int playerNum, int unitNum, int x, int y, int groupedNum, Color color) {
        UnitIcon unit = new UnitIcon(gm, color, playerNum, unitNum, groupedNum);
        unit.setBounds(x, y, 30, 30);
        this.add(unit);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        switch(shape) {
            case 4:
                g.drawImage(images.get("rectangle.png"), 0, 0, getWidth(), getHeight(), this);
                break;
            case 5:
                g.drawImage(images.get("pentagon.png"), 0, 0, getWidth(), getHeight(), this);
                break;
            case 6:
                g.drawImage(images.get("hexagon.png"), 0, 0, getWidth(), getHeight(), this);
                break;
            default:
                break;
        }

    }
}