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

    public Pool() {
        this.setTitle("Pool");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        LandingPage landingPage = new LandingPage(this);
        GamePanel gamePanel = new GamePanel(this);

        mainPanel.add(landingPage, "LANDING");
        mainPanel.add(gamePanel, "GAME");

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);

        cardLayout.show(mainPanel, "LANDING");
    }

    public void switchToGame() {
        cardLayout.show(mainPanel, "GAME");
    }

    public static void main(String[] args) {
        new Pool();
    }
}

class LandingPage extends JPanel {
    private BufferedImage backgroundImage;
    private final JButton startButton;

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

        try {
            backgroundImage = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Components/StartingScreen.png"));
        } catch (IOException e) {
            System.out.println("Error loading images: " + e.getMessage());
        }
    }

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

class GamePanel extends JPanel implements ActionListener {
    private final int SCREEN_WIDTH = 1400;
    private final int SCREEN_HEIGHT = 700;
    private final int UNIT_SIZE = 19;
    private final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;

    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    boolean isGameRunning = true;

    BufferedImage poolTableImage, cueImage;
    BufferedImage[] ballSprites = new BufferedImage[16];

    private List<Ball> balls;
    private Ball cueBall;

    private Point mousePoint;
    private boolean isAiming = false;
    private Timer timer;

    GamePanel(Pool pool) {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.green);
        this.setFocusable(true);

        try {
            poolTableImage = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Components/PoolTableNew.png"));
            cueImage = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Components/Cue.png"));
        } catch (IOException e) {
            System.out.println("Error loading images: " + e.getMessage());
        }

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

        this.addMouseListener(new MouseAdapter() {
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
                    cueBall.velocityX = dx * 0.1; // Adjust power as needed
                    cueBall.velocityY = dy * 0.1; // Adjust power as needed
                    isAiming = false;
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                mousePoint = e.getPoint();
            }
        });

        timer = new Timer(10, this);
        timer.start();
    }

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
                double x = rackStartX + (row * 18.5);
                double y = rackStartY + (col * 20) - (row * 10);
                balls.add(new Ball(x, y, ballSprites[ballNumber[i]], ballNumber[i]));
                i++;
            }
        }
    }

    public boolean allBallsStopped() {
        for (Ball ball : balls) {
            if (ball.velocityX != 0 || ball.velocityY != 0)
                return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Ball ball : balls)
            ball.update();

        for (int i = 0; i < balls.size(); i++) {
            for (int j = i + 1; j < balls.size(); j++) {
                balls.get(i).Colision(balls.get(i), balls.get(j));
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(poolTableImage, 300, 150, 800, 400, null);

        for (Ball b : balls) {
            b.draw(g);
        }

        if (isAiming && mousePoint != null && cueImage != null) {
            double angle = Math.atan2(cueBall.y - mousePoint.y, cueBall.x - mousePoint.x);
            AffineTransform old = g2d.getTransform();
            g2d.translate(cueBall.x, cueBall.y);
            g2d.rotate(angle);
            g2d.drawImage(cueImage, -250, -2, 230, 5, null);
            g2d.setTransform(old);
        }
    }
}

class Ball {
    double x, y; // Position
    double radius = 10; // Radius
    double velocityX, velocityY; // Velocity
    double friction = 0.98; // Friction coefficient
    int number; // Ball number (1-15)

    BufferedImage sprite;

    public Ball(double x, double y, BufferedImage sprite, int number) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.number = number;
        this.velocityX = 0;
        this.velocityY = 0;
    }

    public void update() {
        // Move the ball based on its velocity
        x += velocityX;
        y += velocityY;

        velocityX *= friction; // Friction
        velocityY *= friction; // Friction

        // Stop the ball if it's moving very slowly
        if (Math.abs(velocityX) < 0.1)
            velocityX = 0;
        if (Math.abs(velocityY) < 0.1)
            velocityY = 0;

        if ((x - radius) <= 348) {
            x = 348 + radius; // Set position to edge to limit it within the table
            velocityX = -velocityX; // Reverse the energy on collision
            velocityX *= 0.85; // Lose some energy on collision
        } else if ((x + radius) >= 1048) {
            x = 1048 - radius; // Set position to edge to limit it within the table
            velocityX = -velocityX; // Reverse the energy on collision
            velocityX *= 0.85; // Lose some energy on collision
        }

        if ((y - radius) <= 202) {
            y = 202 + radius; // Set position to edge to limit it within the table
            velocityY = -velocityY; // Reverse the energy on collision
            velocityY *= 0.85; // Lose some energy on collision
        } else if ((y + radius) >= 490) {
            y = 490 - radius; // Set position to edge to limit it within the table
            velocityY = -velocityY; // Reverse the energy on collision
            velocityY *= 0.85; // Lose some energy on collision
        }
    }

    /**
     * Draw the ball
     * 
     * @param g
     */
    public void draw(Graphics g) {
        g.drawImage(sprite, (int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2), null);
    }

    public void Colision(Ball ball1, Ball ball2) {
        double dx = ball2.x - ball1.x; // Distance in x direction
        double dy = ball2.y - ball1.y; // Distance in y direction
        double distance = Math.sqrt(dx * dx + dy * dy); // Actual distance between the centers of the two balls

        if (distance <= (ball1.radius + ball2.radius) && distance > 0) { // Check if the balls are colliding and not on top of each other
            // Calculate the normal vector
            double nx = dx / distance;
            double ny = dy / distance;
            double overlap = (ball1.radius + ball2.radius - distance) / 2;

            ball1.x -= nx * overlap;
            ball1.y -= ny * overlap;
            ball2.x += nx * overlap;
            ball2.y += ny * overlap;

            // Calculate the relative velocity
            double rvx = ball1.velocityX - ball2.velocityX;
            double rvy = ball1.velocityY - ball2.velocityY;

            // Calculate the velocity along the normal
            double velAlongNormal = rvx * nx + rvy * ny;

            if (velAlongNormal < 0) return; // Balls are moving away from each other

            double elasticity = (1.8 * velAlongNormal) / 2; // Coefficient of restitution (1 for perfectly elastic)
            ball1.velocityX -= elasticity * nx;
            ball1.velocityY -= elasticity * ny;
            ball2.velocityX += elasticity * nx;
            ball2.velocityY += elasticity * ny;
        }
    }
}