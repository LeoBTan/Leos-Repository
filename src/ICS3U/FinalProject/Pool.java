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
    private final int SCREEN_WIDTH = 900;
    private final int SCREEN_HEIGHT = 700;
    private final int UNIT_SIZE = 25;
    private final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;

    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    boolean isGameRunning = true;

    private double vxA = 0;
    private double vyA = 0;
    private double vxB = 0;
    private double vyB = 0;

    private double friction = 0.98;

    BufferedImage ball1Image, ball2Image, ball3Image, ball4Image, ball5Image, ball6Image, ball7Image, ball8Image,
            ball9Image, ball10Image, ball11Image, ball12Image, ball13Image, ball14Image, ball15Image, cueBallImage;

    GamePanel(Pool pool) {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.green);
        this.setFocusable(true);

        try {
            ball1Image = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Balls/Ball1.png"));
        } catch (IOException e) {
            System.out.println("Could not find Ball1.png!");
            e.printStackTrace();
        }

        try {
            ball2Image = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Balls/Ball2.png"));
        } catch (IOException e) {
            System.out.println("Could not find Ball2.png!");
            e.printStackTrace();
        }

        try {
            ball3Image = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Balls/Ball3.png"));
        } catch (IOException e) {
            System.out.println("Could not find Ball3.png!");
            e.printStackTrace();
        }

        try {
            ball4Image = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Balls/Ball4.png"));
        } catch (IOException e) {
            System.out.println("Could not find Ball4.png!");
            e.printStackTrace();
        }

        try {
            ball5Image = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Balls/Ball5.png"));
        } catch (IOException e) {
            System.out.println("Could not find Ball5.png!");
            e.printStackTrace();
        }

        try {
            ball6Image = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Balls/Ball6.png"));
        } catch (IOException e) {
            System.out.println("Could not find Ball6.png!");
            e.printStackTrace();
        }

        try {
            ball7Image = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Balls/Ball7.png"));
        } catch (IOException e) {
            System.out.println("Could not find Ball7.png!");
            e.printStackTrace();
        }

        try {
            ball8Image = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Balls/Ball8.png"));
        } catch (IOException e) {
            System.out.println("Could not find Ball8.png!");
            e.printStackTrace();
        }

        try {
            ball9Image = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Balls/Ball9.png"));
        } catch (IOException e) {
            System.out.println("Could not find Ball9.png!");
            e.printStackTrace();
        }

        try {
            ball10Image = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Balls/Ball10.png"));
        } catch (IOException e) {
            System.out.println("Could not find Ball10.png!");
            e.printStackTrace();
        }

        try {
            ball11Image = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Balls/Ball11.png"));
        } catch (IOException e) {
            System.out.println("Could not find Ball11.png!");
            e.printStackTrace();
        }

        try {
            ball12Image = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Balls/Ball12.png"));
        } catch (IOException e) {
            System.out.println("Could not find Ball12.png!");
            e.printStackTrace();
        }

        try {
            ball13Image = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Balls/Ball13.png"));
        } catch (IOException e) {
            System.out.println("Could not find Ball13.png!");
            e.printStackTrace();
        }

        try {
            ball14Image = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Balls/Ball14.png"));
        } catch (IOException e) {
            System.out.println("Could not find Ball14.png!");
            e.printStackTrace();
        }

        try {
            ball15Image = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Balls/Ball15.png"));
        } catch (IOException e) {
            System.out.println("Could not find Ball15.png!");
            e.printStackTrace();
        }

        try {
            cueBallImage = ImageIO.read(new File("src/ICS3U/FinalProject/images.png/Balls/Ball16.png"));
        } catch (IOException e) {
            System.out.println("Could not find Ball16.png!");
            e.printStackTrace();
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
            g.drawImage(ball1Image, 100, 200, UNIT_SIZE, UNIT_SIZE, this);
            g.drawImage(ball2Image, 100, 300, UNIT_SIZE, UNIT_SIZE, this);
            g.drawImage(ball3Image, 100, 400, UNIT_SIZE, UNIT_SIZE, this);
            g.drawImage(ball4Image, 100, 500, UNIT_SIZE, UNIT_SIZE, this);
            g.drawImage(ball5Image, 200, 200, UNIT_SIZE, UNIT_SIZE, this);
            g.drawImage(ball6Image, 200, 300, UNIT_SIZE, UNIT_SIZE, this);
            g.drawImage(ball7Image, 200, 400, UNIT_SIZE, UNIT_SIZE, this);
            g.drawImage(ball8Image, 200, 500, UNIT_SIZE, UNIT_SIZE, this);
            g.drawImage(ball9Image, 300, 200, UNIT_SIZE, UNIT_SIZE, this);
            g.drawImage(ball10Image, 300, 300, UNIT_SIZE, UNIT_SIZE, this);
            g.drawImage(ball11Image, 300, 400, UNIT_SIZE, UNIT_SIZE, this);
            g.drawImage(ball12Image, 300, 500, UNIT_SIZE, UNIT_SIZE, this);
            g.drawImage(ball13Image, 400, 200, UNIT_SIZE, UNIT_SIZE, this);
            g.drawImage(ball14Image, 400, 300, UNIT_SIZE, UNIT_SIZE, this);
            g.drawImage(ball15Image, 400, 400, UNIT_SIZE, UNIT_SIZE, this);
            g.drawImage(cueBallImage, 400, 500, UNIT_SIZE, UNIT_SIZE, this);
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

    public void moveCueBall() {
        // Update cue ball position
        x[0] += vxA;
        y[0] += vyA;
    
        // Apply friction
        vxA *= friction;
        vyA *= friction;
    
        // Check for collisions with walls
        if (x[0] < 0 || x[0] > SCREEN_WIDTH - UNIT_SIZE) {
            vxA = -vxA;
        }
        if (y[0] < 0 || y[0] > SCREEN_HEIGHT - UNIT_SIZE) {
            vyA = -vyA;
        }

        if (checkCollision(x[0], y[0], x[1], y[1])) {
            // Simple elastic collision response
            double tempVxA = vxA;
            double tempVyA = vyA;
            vxA = vxB;
            vyA = vyB;
            vxB = tempVxA;
            vyB = tempVyA;
        }
    }
}