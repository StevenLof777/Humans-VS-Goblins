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
    private final int MAX_GOBLINS = 12;
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

    private void moveHuman() {

        int pos; //
        short ch; //Screen data pos
//        Determine humans position
        if (human_x % BLOCK_SIZE == 0 && human_y % BLOCK_SIZE == 0) {
            pos = human_x / BLOCK_SIZE + NUMBER_OF_BLOCKS * (int) (human_y / BLOCK_SIZE);
            ch = screenData[pos];

            if ((ch & 16) != 0) {
                screenData[pos] = (short) (ch & 15);
            }
//            Checks for collision
            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    humand_x = req_dx;
                    humand_y = req_dy;
                }
            }
            // Check for standstill
            if ((humand_x == -1 && humand_y == 0 && (ch & 1) != 0)
                    || (humand_x == 1 && humand_y == 0 && (ch & 4) != 0)
                    || (humand_x == 0 && humand_y == -1 && (ch & 2) != 0)
                    || (humand_x == 0 && humand_y == 1 && (ch & 8) != 0)) {
                humand_x = 0;
                humand_y = 0;
            }
        }
        human_x = human_x + HUMAN_SPEED * humand_x;
        human_y = human_y + HUMAN_SPEED * humand_y;
    }

    private void drawHuman(Graphics2D g2d) {
        if (req_dx == -1) {
            g2d.drawImage(human, human_x + 1, human_y + 1, this);
        } else if (req_dx == 1) {
            g2d.drawImage(human, human_x + 1, human_y + 1, this);
        } else if (req_dy == -1) {
            g2d.drawImage(human, human_x + 1, human_y + 1, this);
        } else {
            g2d.drawImage(human, human_x + 1, human_y + 1, this);
        }
    }

    private void moveGoblins(Graphics2D g2d) {

        int pos;
        int count;

        for (int i = 0; i < NUMBER_OF_GOBLINS; i++) {
            if (goblin_x[i] % BLOCK_SIZE == 0 && goblin_y[i] % BLOCK_SIZE == 0) {
                pos = goblin_x[i] / BLOCK_SIZE + NUMBER_OF_BLOCKS * (int) (goblin_y[i] / BLOCK_SIZE);

                count = 0;

//                If a goblin encounters a border then change directions.
//                Borders are 16 + 2 or 4 or 6 or 8
                if ((screenData[pos] & 1) == 0 && goblin_dx[i] != 1) {
                    dx[count] = -1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 2) == 0 && goblin_dy[i] != 1) {
                    dx[count] = 0;
                    dy[count] = -1;
                    count++;
                }

                if ((screenData[pos] & 4) == 0 && goblin_dx[i] != -1) {
                    dx[count] = 1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 8) == 0 && goblin_dy[i] != -1) {
                    dx[count] = 0;
                    dy[count] = 1;
                    count++;
                }
                if (count == 0) {

                    if ((screenData[pos] & 15) == 15) {
                        goblin_dx[i] = 0;
                        goblin_dy[i] = 0;
                    } else {
//                        Determines where goblins are
                        goblin_dx[i] = -goblin_dx[i];
                        goblin_dy[i] = -goblin_dy[i];
                    }

                } else {
                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    goblin_dx[i] = dx[count];
                    goblin_dy[i] = dy[count];
                }

            }

            goblin_x[i] = goblin_x[i] + (goblin_dx[i] * goblinSpeed[i]);
            goblin_y[i] = goblin_y[i] + (goblin_dy[i] * goblinSpeed[i]);
            drawGoblin(g2d, goblin_x[i] + 1, goblin_y[i] + 1);

            if (
                    human_x > (goblin_x[i] - 12) && human_x < (goblin_x[i] + 12)
                    && human_y > (goblin_y[i] - 12) && human_y < (goblin_y[i] + 12)
                    && inGame
            ) {

                dying = true;
            }
        }
    }

    public void drawGoblin(Graphics2D g2d, int x, int y){
        g2d.drawImage(goblin, x,y, this);
    }

    private void checkMaze() {
        int i = 0;
        boolean finished = true;

//        Implement treasure chest later
        while (i < NUMBER_OF_BLOCKS * NUMBER_OF_BLOCKS && finished) {

            if ((screenData[i]) != 0) {
                finished = false;
            }

            i++;
        }
        if (finished) {
            initLevel();
        }
    }

    private void showIntroScreen(Graphics2D g2d) {
        String start = "Press SPACE to start";
        g2d.setColor(Color.red);
        g2d.drawString(start, (SCREEN_SIZE)/4, 150);
    }

    private void drawStats(Graphics2D g) {
        g.setFont(smallFont);
        g.setColor(Color.yellow);
        String s = "Gold: " + gold;
        g.drawString(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);

//        implement hp later
    }

    private void playGame(Graphics2D g2d) {
        if (dying) {
            death();
        } else {
            moveHuman();
            drawHuman(g2d);
            moveGoblins(g2d);
            checkMaze();
        }
    }

    private void death() {

        hp--;

        if (hp == 0) {
            inGame = false;
        }

        continueLevel();
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
                }

                i++;
            }
        }
    }

    private void initGame() {
        gold=0;
        hp=3;
        NUMBER_OF_GOBLINS=2;
        initLevel();
    }

    private void initLevel(){
//        Cops level data to screen data
        int i;
        for (i = 0; i < NUMBER_OF_BLOCKS * NUMBER_OF_BLOCKS; i++) {
            screenData[i] = levelData[i];
        }

        continueLevel();
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
        drawStats(g2d);

        if (inGame) {
            playGame(g2d);
        } else {
            showIntroScreen(g2d);
        }

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