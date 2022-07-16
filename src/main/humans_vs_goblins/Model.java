package main.humans_vs_goblins;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Model extends JPanel implements ActionListener {
    private Dimension dimension;
    private final Font smallFont = new Font("Comic Sans", Font.BOLD, 14);
    private boolean inGame = false;
    private boolean dying = false;

    //    How big block is in game
    private final int BLOCK_SIZE = 24;
    //    Blocks in width and height
//    15 blocks down, 15 across
    private final int NUMBER_OF_BLOCKS = 15;
    //    Screen size is determined by n blocks times block size
    private final int SCREEN_SIZE = NUMBER_OF_BLOCKS * BLOCK_SIZE;
    private final int MAX_GOBLINS = 10;
    private final int HUMAN_SPEED = 2;

    private int NUMBER_OF_GOBLINS = 0;
    private int hp, gold;
    private int[] dx, dy;
    private int[] goblin_x, goblin_y, goblin_dx, goblin_dy, goblinSpeed;

    private Image goblin, human;

    private int human_x, human_y, humand_x, humand_y;
//    req y and x are determined from t adaptor
    private int req_dx, req_dy;

    private int currentSpeed = 3;
    private short[] screenData;
    private Timer timer;

    private final short levelData[] = {
            19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,//    0 = collision
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,//    1 = left border
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,//    2 = top border
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,//    4 = right border
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,//    8 = bottom border
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,//    16 = Empty spaces
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,//    Add number of desired attribute
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,//    to empty space to make block
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,//       Ex: right border on wall is 4 + 16 for 20
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            25, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28
    };

    public Model() {
        loadImages();
        initVariables();
        addKeyListener(new TAdapter());
        setFocusable(true);
        initGame();
    }

    private void loadImages() {
        human = new ImageIcon("images/knight.gif").getImage().getScaledInstance(36,36,0);
        goblin = new ImageIcon("images/goblin.gif").getImage().getScaledInstance(36,36,0);
    }

    private void initVariables() {
//        Set screen data to blocks squared
        screenData = new short[NUMBER_OF_BLOCKS * NUMBER_OF_BLOCKS];
        dimension = new Dimension(400, 400);
        goblin_x = new int[0];
        goblin_y = new int[MAX_GOBLINS];
        goblin_dx = new int[MAX_GOBLINS];
        goblin_dy = new int[MAX_GOBLINS];
        goblinSpeed = new int[MAX_GOBLINS];
        dx = new int[4];
        dy = new int[4];

        timer = new Timer(60, this);
        timer.start();
    }


    private void playGame(Graphics2D g2d) {

    }

    private void drawMaze(Graphics2D g2d) {
        short i = 0;
        int x, y;
        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

                g2d.setColor(Color.darkGray);
                g2d.setStroke(new BasicStroke(5));

                if ((levelData[i] == 0)) {
                    g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                }

                if ((screenData[i] & 1) != 0) {
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 2) != 0) {
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & 4) != 0) {
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0) {
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 16) != 0) {
                    g2d.setColor(Color.gray);
                    g2d.fillOval(x + 10, y + 10, 6, 6);
                }

                i++;
            }
        }
    }

    private void initGame() {
        gold=0;
        NUMBER_OF_GOBLINS=2;
        initLevel();
    }

    private void initLevel(){
//        Cops level data to screen data
        int i;
        for (i = 0; i < NUMBER_OF_BLOCKS * NUMBER_OF_BLOCKS; i++) {
            screenData[i] = levelData[i];
        }
    }

    private void continueLevel() {

        int dx = 1;
        int random;

        for (int i = 0; i < NUMBER_OF_GOBLINS; i++) {

            goblin_y[i] = 4 * BLOCK_SIZE; //start position
            goblin_x[i] = 4 * BLOCK_SIZE;
            goblin_dy[i] = 0;
            goblin_dx[i] = dx;
            dx = -dx;
            random = (int) (Math.random() * (currentSpeed + 1));

            if (random > currentSpeed) {
                random = currentSpeed;
            }
        }

        human_x = 7 * BLOCK_SIZE;  //start position
        human_y = 11 * BLOCK_SIZE;
        humand_x = 0;	//reset direction move
        humand_y = 0;
        req_dx = 0;		// reset direction controls
        req_dy = 0;
        dying = false;
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.gray);
        g2d.fillRect(0, 0, dimension.width, dimension.height);

        drawMaze(g2d);

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (inGame) {
                if (key ==  KeyEvent.VK_LEFT) {
                    req_dx = -1;
                    req_dy = 0;
                } else if (key ==  KeyEvent.VK_RIGHT) {
                    req_dx = 1;
                    req_dy = 0;
                } else if (key ==  KeyEvent.VK_UP) {
                    req_dx = 0;
                    req_dy = -1;
                } else if (key ==  KeyEvent.VK_DOWN) {
                    req_dx = 0;
                    req_dy = 1;
//                    Escape turns off game
                } else if (key ==  KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    inGame = false;
                }
            } else {
//                Space starts game
                if (key == KeyEvent.VK_SPACE) {
                    inGame = true;
                    initGame();
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }


}