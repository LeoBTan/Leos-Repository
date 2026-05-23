package ICS3U.FinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Pool extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    /**
     * Constuctor for the Pool class, sets up the main JFrame
     * Initializes the CardLayout and adds the LandingPage and GamePanel to it
     */
    public Pool() {
        // JFrame setup
        this.setTitle("Pool");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // CardLayout setup
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create and add panels
        LandingPage landingPage = new LandingPage(this);
        GamePanel gamePanel = new GamePanel(this);

        // Adding panels to the mainPanel
        mainPanel.add(landingPage, "LANDING");
        mainPanel.add(gamePanel, "GAME");

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);

        cardLayout.show(mainPanel, "LANDING");
    }

    /**
     * Method to switch from the landing page to the game panel when the start button is pressed
     */
    public void switchToGame() {
        cardLayout.show(mainPanel, "GAME");
    }

    /**
     * Run Pool
     * @param args
     */
    public static void main(String[] args) {
        new Pool();
    }
}

/**
 * LandingPage class to create the starting page
 */
class LandingPage extends JPanel {
    private BufferedImage backgroundImage;
    private final JButton startButton;

    /**
     * Constructor for the LandingPage class, sets up the layout and start button
     * @param pool
     */
    LandingPage(Pool pool) {
        this.setPreferredSize(new Dimension(800, 400));
        this.setLayout(null);

        // Load background image
        // try {
        // backgroundImage = new javax.imageio.ImageIO().read(
        // new java.io.File("src/ICS3U/FinalProject/pooltable.png")
        // );
        // } catch (Exception e) {
        // System.out.println("Image not found");
        // }

        // Create start button
        startButton = new JButton("START GAME");
        startButton.setBounds(625, 374, 200, 100);
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.addActionListener(e -> pool.switchToGame());
        this.add(startButton);

        // Import staring screen sprite
        try {
            backgroundImage = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Components/StartingScreen.png"));
        } catch (IOException e) {
            System.out.println("Error loading images: " + e.getMessage());
        }
    }

    /**
     * Drawing the background image for the landing page
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Keep pixel art sharp - no smoothing
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage, 0, 0, 1400, 700, this);
        }
    }
}

/**
 * GamePanel class where the game happens
 */
class GamePanel extends JPanel implements ActionListener {
    private final int SCREEN_WIDTH = 1400;
    private final int SCREEN_HEIGHT = 700;
    private final int UNIT_SIZE = 19;
    private final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    // final int pixelFinderX = 600;
    // final int pixelFinderY = 494;

    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];

    BufferedImage poolTableImage, cueImage, scoreboardImage, player1WinsOverlay, player2WinsOverlay;
    // BufferedImage pixelFinder;
    BufferedImage[] ballSprites = new BufferedImage[16];

    private List<Ball> balls;
    private List<Pocket> pockets;
    private Ball cueBall;

    private Point mousePoint;
    private boolean isAiming = false;
    private final Timer timer;

    private Scoring scoring = new Scoring();
    private boolean shotFired = false;

    private int stuckFramesCount = 0;
    private final int STUCK_THRESHOLD = 700;

    /**
     * Constructor for the GamePanel class, initializes the game state and loads all necessary images
     * @param pool
     */
    GamePanel(Pool pool) {
        //Setting up the panel
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.green);
        this.setFocusable(true);

        // Import non-ball sprites
        try {
            poolTableImage = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Components/PoolTable2.png"));
            cueImage = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Components/Cue.png"));
            scoreboardImage = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Components/Scoreboard.png"));
            // pixelFinder = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Components/Indicator.png"));
            player1WinsOverlay = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Components/Player1Win.png"));
            player2WinsOverlay = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Components/Player2Win.png"));
        } catch (IOException e) {
            System.out.println("Error loading images: " + e.getMessage());
        }

        // Import ball sprites
        try {
            for (int i = 0; i < 16; i++) {
                ballSprites[i] = ImageIO
                        .read(new File("src/ICS3U/FinalProject/images.png/PoolBalls/Ball" + i + ".png"));
                System.out.println("src/ICS3U/FinalProject/images.png/PoolBalls/Ball" + i + ".png");
            }
        } catch (IOException e) {
            System.out.println("Error loading ball images: " + e.getMessage());
        }

        initBalls();
        initPockets();

        // Mouse listener for aiming and shooting
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (allBallsStopped()) {
                    double distanceFromCueBall = Math.hypot(e.getX() - cueBall.x, e.getY() - cueBall.y);
                    if (distanceFromCueBall <= 50)
                        isAiming = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isAiming) {
                    double dx = cueBall.x - e.getX();
                    double dy = cueBall.y - e.getY();
                    cueBall.velocityX = dx * 0.15; // Adjust power as needed
                    cueBall.velocityY = dy * 0.15; // Adjust power as needed
                    isAiming = false;
                    shotFired = true;
                }
            }
        });

        // Track mouse movement for aiming line
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mousePoint = e.getPoint();
            }
        });

        //Set the timer
        timer = new Timer(10, this);
        timer.start();
    }

    /**
     * Initializes the balls on the table into a preset rack formation
     * Also initializes the cue ball in its starting position
     */
    public void initBalls() {
        balls = new ArrayList<>();
        cueBall = new Ball(450, 345, ballSprites[0], 0);
        balls.add(cueBall);

        double rackStartX = 850;
        double rackStartY = 345;
        int[] ballNumber = { 7, 2, 11, 3, 8, 15, 6, 10, 5, 1, 13, 4, 14, 9, 12 };
        int i = 0;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col <= row; col++) {
                double rackX = rackStartX + (row * 18.5);
                double rackY = rackStartY + (col * 20) - (row * 10);
                balls.add(new Ball(rackX, rackY, ballSprites[ballNumber[i]], ballNumber[i]));
                i++;
            }
        }
    }

    /**
     * Initializes the pockets on the table as well as the scoing system
     */
    public void initPockets() {
        pockets = new ArrayList<>();
        pockets.add(new Pocket(352, 202));
        pockets.add(new Pocket(696, 198));
        pockets.add(new Pocket(1044, 202));
        pockets.add(new Pocket(352, 494));
        pockets.add(new Pocket(696, 498));
        pockets.add(new Pocket(1044, 494));
        scoring = new Scoring();
    }

    /**
     * Checks if all the balls on the table have stopped moving
     * @return true if all balls are stopped, false otherwise
     */
    public boolean allBallsStopped() {
        for (Ball ball : balls) {
            if (!ball.isOnTable)
                continue;
            if (Math.abs(ball.velocityX) > 0.05 || Math.abs(ball.velocityY) > 0.05) {
                return false;
            }
        }
        return true;
    }

    /**
     * Main game loop, checks for ball updates, collisions, pocketing, and turn management
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //update balls
        for (Ball ball : balls)
            ball.update();

        // Check and perform collisions between balls
        for (int i = 0; i < balls.size(); i++) {
            if (!balls.get(i).isOnTable)
                continue;
            for (int j = i + 1; j < balls.size(); j++) {
                if (!balls.get(j).isOnTable)
                    continue;
                balls.get(i).Colision(balls.get(i), balls.get(j));
            }
        }

        // Check if a ball is pocketed
        for (Ball ball : balls) {
            if (!ball.isOnTable)
                continue;
            for (Pocket pocket : pockets) {
                if (pocket.isBallInPocket(ball)) {
                    ball.isOnTable = false;
                    ball.velocityX = 0;
                    ball.velocityY = 0;
                    scoring.onPocketedBall(ball.number);
                    break;
                }
            }
        }

        //Reset cue ball to break position when pocketed, replacement for ball in hand
        if (!cueBall.isOnTable) {
            cueBall.x = 450;
            cueBall.y = 345;
            cueBall.velocityX = 0;
            cueBall.velocityY = 0;
            cueBall.isOnTable = true;
        }

        //Make all balls stop after a certain time period as a backup in case of softlocks
        if (shotFired) {
            if (allBallsStopped()) {
                scoring.endTurn();
                shotFired = false;
                stuckFramesCount = 0;
            } else {
                stuckFramesCount++;
                if (stuckFramesCount >= STUCK_THRESHOLD) {
                    for (Ball ball : balls) {
                        ball.velocityX = 0;
                        ball.velocityY = 0;
                    }
                    scoring.endTurn();
                    shotFired = false;
                    stuckFramesCount = 0;
                }
            }
        }

        // Repaint the scene every frame with the new updates
        repaint();
    }

    /**
     * Draws the game scene, including the pool table, balls, aiming line, cue stick, and scoreboard
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * Called by paintComponent, responsible for drawing the entire game scene
     */
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //Images
        g.drawImage(poolTableImage, 300, 150, 800, 400, null);
        g.drawImage(scoreboardImage, 450, 600, 500, 100, null);
        // g.drawImage(pixelFinder, pixelFinderX, pixelFinderY, 4, 4, null);

        //Balls
        for (Ball b : balls) {
            b.draw(g);
        }

        //Aiming Line
        if (isAiming && mousePoint != null) {
            double dx = cueBall.x - mousePoint.x;
            double dy = cueBall.y - mousePoint.y;
            double distanceToMouse = Math.hypot(dx, dy);

            if (distanceToMouse > 0) {
                double unitX = dx / distanceToMouse;
                double unitY = dy / distanceToMouse;

                // Default length
                double lineLength = 600;

                for (Ball other : balls) {
                    if (other == cueBall)
                        continue;
                    if (!other.isOnTable)
                        continue;

                    double distToBallX = other.x - cueBall.x;
                    double distToBallY = other.y - cueBall.y;

                    double projection = (distToBallX * unitX) + (distToBallY * unitY);

                    if (projection > 0) {
                        double nearestX = cueBall.x + unitX * projection;
                        double nearestY = cueBall.y + unitY * projection;
                        double distToLine = Math.hypot(other.x - nearestX, other.y - nearestY);

                        if (distToLine < (cueBall.radius + other.radius)) {
                            double offset = Math
                                    .sqrt(Math.pow(cueBall.radius + other.radius, 2) - Math.pow(distToLine, 2));
                            double intersectionDist = projection - offset;
                            if (intersectionDist < lineLength) {
                                lineLength = intersectionDist;
                            }
                        }
                    }
                }

                // Draw the line with our new dynamic lineLength
                g2d.setColor(new Color(255, 255, 255, 120));
                g2d.setStroke(
                        new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 10 }, 0));
                g2d.drawLine((int) cueBall.x, (int) cueBall.y, (int) (cueBall.x + unitX * lineLength),
                        (int) (cueBall.y + unitY * lineLength));
                g2d.setStroke(new BasicStroke(1));
            }
        }

        if (isAiming && mousePoint != null && cueImage != null) {
            double angle = Math.atan2(cueBall.y - mousePoint.y, cueBall.x - mousePoint.x);
            double dist = Math.hypot(cueBall.x - mousePoint.x, cueBall.y - mousePoint.y);
            int offset = (int) Math.min(dist, 150);
            AffineTransform old = g2d.getTransform();
            g2d.translate(cueBall.x, cueBall.y);
            g2d.rotate(angle);
            g2d.drawImage(cueImage, (-250 - offset), -2, 230, 5, null);
            g2d.setTransform(old);
        }

        //Scoreboard
        int boardX = 450, boardY = 600, boardW = 500, boardH = 100;
        int player = scoring.getCurrentPlayer();
        Scoring.ScoringBallType p1Type = scoring.getPlayer1Type();
        Scoring.ScoringBallType p2Type = scoring.getPlayer2Type();
        List<Integer> sunk = scoring.getSunkBalls();

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        g2d.setColor(Color.YELLOW);
        if (player == 1)
            g2d.fillRect(boardX + 2, boardY + 2, boardW / 2 - 4, boardH - 4);
        else
            g2d.fillRect(boardX + boardW / 2 + 2, boardY + 2, boardW / 2 - 4, boardH - 4);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

        g2d.setFont(new Font("Arial", Font.PLAIN, 18));
        if (p1Type != Scoring.ScoringBallType.UNDETERMINED) {
            g2d.setColor(Color.BLACK);

            g2d.drawString(p1Type.toString(), boardX + 10, boardY - 8);
            g2d.drawString(p2Type.toString(), boardX + boardW / 2 + 10, boardY - 8);
        }

        int ballSize = 25, spaceBetweenBalls = 5;
        int ballRowY = boardY + 54;
        int p1X = boardX + 12;
        int p2X = boardX + boardW / 2 + 8;

        for (int ballNum : sunk) {
            if (ballNum == 8)
                continue;
            boolean isSolid = ballNum >= 1 && ballNum <= 7;
            Scoring.ScoringBallType bt = isSolid
                    ? Scoring.ScoringBallType.SOLIDS
                    : Scoring.ScoringBallType.STRIPES;

            if (bt == p1Type && ballSprites[ballNum] != null) {
                g2d.drawImage(ballSprites[ballNum], p1X, ballRowY, ballSize, ballSize, null);
                p1X += ballSize + spaceBetweenBalls;
            } else if (bt == p2Type && ballSprites[ballNum] != null) {
                g2d.drawImage(ballSprites[ballNum], p2X, ballRowY, ballSize, ballSize, null);
                p2X += ballSize + spaceBetweenBalls;
            }
        }

        if (scoring.isGameOver()) {
            g2d.drawImage((scoring.getGameState() == Scoring.GameState.PLAYER1_WINS ? player1WinsOverlay
                    : player2WinsOverlay), 400, 200, 600, 300, null);
        }
    }
}

/**
 * Ball class created for all balls on the table
 * Contains methods that have to do with any action that consists of a ball
 */
class Ball {
    double x, y; // Position
    double radius = 10; // Radius
    double velocityX, velocityY; // Velocity
    double friction = 0.98; // Friction coefficient
    int number; // Ball number (1-15)
    boolean isOnTable = true; // Whether the ball is still on the table

    BufferedImage sprite;

    /**
     * Constructor for the Ball class, initializes the position, sprite, and number of the ball
     * @param x
     * @param y
     * @param sprite
     * @param number
     */
    public Ball(double x, double y, BufferedImage sprite, int number) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.number = number;
        this.velocityX = 0;
        this.velocityY = 0;
    }

    /**
     * Update method to move the ball based on velocity
     * and applies friction as well as checking for collisions with the cushions
     */
    public void update() {
        if (!isOnTable)
            return;
        // Move the ball based on its velocity
        x += velocityX;
        y += velocityY;

        velocityX *= friction; // Friction
        velocityY *= friction; // Friction

        // Stop the ball if it's moving very slowly
        if (Math.abs(velocityX) < 0.3)
            velocityX = 0;
        if (Math.abs(velocityY) < 0.3)
            velocityY = 0;

        // Check for collisions with the cushions and bounce
        if ((x - radius) <= 353) {
            x = 353 + radius;
            velocityX = -velocityX;
            velocityX *= 0.85;
            if (velocityX < 0.1 && velocityX > -0.1) {
                velocityX = 0;
                x = 353 + radius + 1;
            }
        } else if ((x + radius) >= 1043) {
            x = 1043 - radius;
            velocityX = -velocityX;
            velocityX *= 0.85;
            if (velocityX < 0.1 && velocityX > -0.1) {
                velocityX = 0;
                x = 1043 - radius - 1;
            }
        }

        if ((y - radius) <= 203) {
            y = 203 + radius;
            velocityY = -velocityY;
            velocityY *= 0.85;
            if (velocityY < 0.1 && velocityY > -0.1) {
                velocityY = 0;
                y = 203 + radius + 1;
            }
        } else if ((y + radius) >= 493) {
            y = 493 - radius;
            velocityY = -velocityY;
            velocityY *= 0.85;
            if (velocityY < 0.1 && velocityY > -0.1) {
                velocityY = 0;
                y = 493 - radius - 1;
            }
        }
    }

    /**
     * Draw the ball
     * @param g
     */
    public void draw(Graphics g) {
        if (!isOnTable)
            return;
        g.drawImage(sprite, (int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2), null);
    }

    /**
     * Collision method to check and resolve collisions between two balls
     * @param ball1
     * @param ball2
     */
    public void Colision(Ball ball1, Ball ball2) {
        double dx = ball2.x - ball1.x;
        double dy = ball2.y - ball1.y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Check if the distance between balls is less than the sum of their radii
        // Runs collision logic if so
        if (distance <= (ball1.radius + ball2.radius) && distance > 0) {
            double nx = dx / distance;
            double ny = dy / distance;
            double overlap = (ball1.radius + ball2.radius - distance) / 2;

            ball1.x -= nx * overlap;
            ball1.y -= ny * overlap;
            ball2.x += nx * overlap;
            ball2.y += ny * overlap;

            double rvx = ball1.velocityX - ball2.velocityX;
            double rvy = ball1.velocityY - ball2.velocityY;

            double velAlongNormal = rvx * nx + rvy * ny;

            if (velAlongNormal < 0)
                return;

            double elasticity = (1.9 * velAlongNormal) / 2;
            ball1.velocityX -= elasticity * nx;
            ball1.velocityY -= elasticity * ny;
            ball2.velocityX += elasticity * nx;
            ball2.velocityY += elasticity * ny;
        }
    }
}

/**
 * Pocket class to run the logic for the pockets
 */
class Pocket {
    double x, y; // Position

    /**
     * Constructor for the Pocket class, initializes the position of the pocket
     */
    public Pocket(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Checks if a ball is in the pocket
     * @param ball
     * @return true if the ball is in the pocket, false otherwise
     */
    public boolean isBallInPocket(Ball ball) {
        double dx = ball.x - x;
        double dy = ball.y - y;
        double distance = Math.hypot(dx, dy);
        return distance <= 20;
    }
}

/**
 * Scoring class to manage the scoring system
 */
class Scoring {
    // Enums to represent the type of balls each player is assigned and the state of the game
    public enum ScoringBallType {
        UNDETERMINED, SOLIDS, STRIPES
    };

    public enum GameState {
        ACTIVE, PLAYER1_WINS, PLAYER2_WINS
    };

    private ScoringBallType player1Type = ScoringBallType.UNDETERMINED;
    private ScoringBallType player2Type = ScoringBallType.UNDETERMINED;
    private GameState gameState = GameState.ACTIVE;

    private int currentPlayer = 1;
    private boolean ballSunkThisTurn = false;
    private boolean foulThisTurn = false;

    private final List<Integer> sunkBalls = new ArrayList<>();

    /**
     * Determines the type of ball based on the number to assign to a player
     * @param ballNumber
     * @return the ScoringBallType of the ball number, or UNDETERMINED otherwise
     */
    private ScoringBallType isWhichType(int ballNumber) {
        if (ballNumber >= 1 && ballNumber <= 7)
            return ScoringBallType.SOLIDS;
        if (ballNumber >= 9 && ballNumber <= 15)
            return ScoringBallType.STRIPES;
        return ScoringBallType.UNDETERMINED;
    }

    /**
     * Method to be called once a ball is pocketed
     * and updates the game state accordingly, 
     * using a slighlty simplified version of the rules of pool
     */
    public void onPocketedBall(int ballNumber) {
        // Finishes the game if the gameState is not active
        if (gameState != GameState.ACTIVE)
            return;

        // If the cue ball is pocketed, it's a foul and the turn ends
        if (ballNumber == 0) {
            foulThisTurn = true;
            return;
        }

        // Otherwise, add the ball to the list of sunk balls
        sunkBalls.add(ballNumber);

        // Check if the ball is the 8 ball
        // If so, check if the current player has cleared all assigned balls
        // Updates the gameState accordingly
        if (ballNumber == 8) {
            ScoringBallType myType = ((currentPlayer == 1) ? player1Type : player2Type);
            boolean clearedAll = isAllSunk(myType);
            if (clearedAll && !foulThisTurn) {
                gameState = (currentPlayer == 1) ? GameState.PLAYER1_WINS : GameState.PLAYER2_WINS;
            } else {
                gameState = (currentPlayer == 1) ? GameState.PLAYER2_WINS : GameState.PLAYER1_WINS;
            }
            return;
        }

        //Sets a temp variable to the type of ball that was sunk
        ScoringBallType ballType = isWhichType(ballNumber);

        // Checks if the scoring type is unassigned
        // If so, assigns the player that sunk the ball that type and the other player the opposite type
        if (player1Type == ScoringBallType.UNDETERMINED) {
            player1Type = (currentPlayer == 1) ? ballType
                    : (ballType == ScoringBallType.SOLIDS ? ScoringBallType.STRIPES : ScoringBallType.SOLIDS);
            player2Type = player1Type == ScoringBallType.SOLIDS ? ScoringBallType.STRIPES : ScoringBallType.SOLIDS;
        }

        // If the scoriing type is assigned, checks if current player sunk their own ball
        ScoringBallType myType = (currentPlayer == 1) ? player1Type : player2Type;
        if (ballType == myType)
            ballSunkThisTurn = true;
    }

    /**
     * Method to end a turn, checking all reasons as to why  a turn should end
     */
    public void endTurn() {
        // Finishes the game if the gameState is not active
        if (gameState != GameState.ACTIVE)
            return;

        // If the player committed a foul or didn't sink a ball, switch turns
        if (foulThisTurn || !ballSunkThisTurn) {
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }

        ballSunkThisTurn = false;
        foulThisTurn = false;
    }

    /**
     * Checks if all balls of a certain type have been sunk, 
     * used to determine if a player has won after pocketing the 8 ball
     * @param type the ScoringBallType to check
     * @return true if all balls of that type have been sunk, false otherwise
     */
    public boolean isAllSunk(ScoringBallType type) {
        // Checks if the scoring type is undetermined, if so returns false
        if (type == ScoringBallType.UNDETERMINED)
            return false;
        // Determine the range of ball numbers to check based on the type (solids or stripes)
        int start = (type == ScoringBallType.SOLIDS) ? 1 : 9;
        int end = (type == ScoringBallType.SOLIDS) ? 7 : 15;
        // Checks if there are still balls on the table, if so returns false
        for (int i = start; i <= end; i++) {
            if (!sunkBalls.contains(i))
                return false;
        }
        return true;
    }

    //Getter methods
    /**
     * Gets the current player
     * @return the current player (1 or 2)
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets the current game state
     * @return the current game state
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Gets the type of ball assigned to player 1
     * @return the ScoringBallType assigned to player 1
     */
    public ScoringBallType getPlayer1Type() {
        return player1Type;
    }

    /**
     * Gets the type of ball assigned to player 2
     * @return the ScoringBallType assigned to player 2
     */
    public ScoringBallType getPlayer2Type() {
        return player2Type;
    }

    /**
     * Checks if a foul was committed this turn
     * @return true if a foul was committed, false otherwise
     */
    public boolean isFoul() {
        return foulThisTurn;
    }

    /**
     * Check if the game is over
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return gameState != GameState.ACTIVE;
    }

    /**
     * Gets the list of sunk balls
     * @return the list of sunk balls
     */
    public List<Integer> getSunkBalls() {
        return sunkBalls;
    }
}