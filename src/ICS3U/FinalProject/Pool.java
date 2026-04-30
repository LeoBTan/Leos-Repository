package ICS3U.FinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pool extends JFrame {
    public Pool() {
        this.add(new GamePanel());
        this.setTitle("Pool");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Pool();
    }
}

class GamePanel extends JPanel implements ActionListener {
    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 800;
    private final int UNIT_SIZE = 25;
    private final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;

    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];

    boolean isGameRunning = false;

    GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.addMouseListener(new MyMouseAdapter());
        this.addMouseMotionListener(new MyMouseMotionAdapter());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    
    public class MyKeyAdapter extends KeyAdapter {
        
    }

    public class MyMouseAdapter extends MouseAdapter {
        
    }

    public class MyMouseMotionAdapter extends MouseMotionAdapter {
        
    }
}