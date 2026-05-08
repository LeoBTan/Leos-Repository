package ICS3U.FinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class Pool extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

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
    private JButton startButton;

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
        startButton.setBounds(300, 350, 200, 100);
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.addActionListener(e -> pool.switchToGame());
        this.add(startButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Keep pixel art sharp - no smoothing
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
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

    GamePanel(Pool pool) {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.green);
        this.setFocusable(true);

        try {
            poolTableImage = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Components/PoolTable.png"));
            cueImage = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Components/Cue.png"));
        } catch (IOException e) {
            System.out.println("Error loading images: " + e.getMessage());
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Game Started!", 50, 50);
        draw(g);
    }

    public void draw(Graphics g) {
        if (isGameRunning) {
            g.drawImage(poolTableImage, 300, 150, 800, 400, this);
            g.drawImage(cueImage, 348, 198, 210, UNIT_SIZE / 4, this);
        }
    }

    public double dotProduct(double v1x, double v1y, double v2x, double v2y) {
        return v1x * v2x + v1y * v2y;
    }

    public boolean checkCollision(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double distanceSquared = dx * dx + dy * dy;
        return distanceSquared <= ((UNIT_SIZE / 2) * (UNIT_SIZE / 2));
    }
}

class Ball {
    double x, y; // Position
    double radius = 10; // Radius
    double velocityX, velocityY; // Velocity
    double friction = 0.99; // Friction coefficient

    BufferedImage sprite;

    public Ball(double x, double y, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    /**
     * Draw the ball
     * @param g
     */
    public void draw(Graphics g) {
        g.drawImage(sprite, (int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2), null);
    }

    public void update() {
        // Move the ball based on its velocity
        x += velocityX;
        y += velocityY;

        velocityX *= friction; // Friction
        velocityY *= friction; // Friction

        // Stop the ball if it's moving very slowly
        if (Math.abs(velocityX) < 0.1) velocityX = 0;
        if (Math.abs(velocityY) < 0.1) velocityY = 0;

        if ((x - radius) <= 348) {
            x = 348 + radius; // Set position to edge to limit it within the table
            velocityX = -velocityX; // Reverse the energy on collision
            velocityX *= 0.85; // Lose some energy on collision
        }
    }
}