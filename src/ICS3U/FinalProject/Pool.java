package ICS3U.FinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

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

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    
}