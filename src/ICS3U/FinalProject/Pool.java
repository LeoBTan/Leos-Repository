package ICS3U.FinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

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
        this.setPreferredSize(new Dimension(800, 800));
        this.setLayout(null);

        // Load background image
        try {
            backgroundImage = new javax.imageio.ImageIO().read(
                new java.io.File("src/ICS3U/FinalProject/pooltable.png")
            );
        } catch (Exception e) {
            System.out.println("Image not found");
        }

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
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

class GamePanel extends JPanel implements ActionListener {
    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 800;
    private final int UNIT_SIZE = 25;
    private final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;

    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    boolean isGameRunning = true;

    GamePanel(Pool pool) {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.green);
        this.setFocusable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Game Started!", 50, 50);
    }
}