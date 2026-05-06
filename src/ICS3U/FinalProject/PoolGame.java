package ICS3U.FinalProject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;



public class PoolGame extends JPanel {
    public void loadAssets() {
        BufferedImage ball1.png;

        try {
            // This loads the pixel data into the 'ball1' variable
            ball1 = ImageIO.read(new File("assets/ball_1.png"));
        } catch (IOException e) {
            System.out.println("Error: Could not find the image file!");
    }
}




    // An array to hold all 16 ball images
    private BufferedImage[] balls = new BufferedImage[16];
    private BufferedImage table;

    public PoolGame() { // Constructor for PoolGame
        try {
            // 1. Load the table background
            table = ImageIO.read(new File("table.png"));

            // 2. Load all 16 balls using a loop
            for (int i = 0; i < 16; i++) {
                balls[i] = ImageIO.read(new File("Ball" + i + ".png"));
            }
        } catch (IOException e) {
            System.out.println("Error: One or more images missing! Check your filenames.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Keep pixel art sharp (no blurring)
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        // Draw the table first
        if (table != null) {
            g.drawImage(table, 0, 0, getWidth(), getHeight(), null);
        }

        // Draw all 16 balls in a simple line for now
        int startX = 50;
        int startY = 200;
        
        for (int i = 0; i < balls.length; i++) {
            if (balls[i] != null) {
                // Draws each ball 40 pixels apart
                g.drawImage(balls[i], startX + (i * 40), startY, null);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("2D Pixel Pool");
        frame.add(new PoolGame());
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    
    }
}
