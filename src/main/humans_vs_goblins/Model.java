package main.humans_vs_goblins;

import java.awt.*;

public class Model {
    private Dimension dimension;

//    How big block is in game
    private final int BLOCK_SIZE = 24;
//    Blocks in width and height
//    15 blocks down, 15 across
    private final int NUMBER_OF_BLOCKS = 15;
//    Screen size is determined by n blocks times block size
    private final int SCREEN_SIZE = NUMBER_OF_BLOCKS * BLOCK_SIZE;
//    0 = collision
//    1 = left border
//    2 = top border
//    4 = right border
//    8 = bottom border
//    16 = Empty spaces
//    Add number of desired attribute to empty space to make block
//          Ex: right border on wall is 4 + 16 for 20
    private final short levelData[] = {
            19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            25, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28
    };

    private short[] screenData;

    private Model() {
        initVariables();
        initGame();
    }

    private void initGame() {
        initLevel();
    }

    public void initLevel() {
        int i;
        for (i = 0; i < 255; i++) {
            screenData[i] = levelData[i];
        }
    }

    private void initVariables() {
//        Set screen data to blocks squared
        screenData = new short[225];
        dimension = new Dimension(400, 400);
    }
//    private void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        Graphics2D g2d = (Graphics2D) g;
//
//        g2d.setColor(Color.gray);
//        g2d.fillRect(0, 0, dimension.width, dimension.height);
//
//        drawMaze(g2d);
//
//
//        playGame(g2d);
//
//
//        Toolkit.getDefaultToolkit().sync();
//        g2d.dispose();
//    }
}