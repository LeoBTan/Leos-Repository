package Fun;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Snake {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int GRID_SIZE = 20;
    private static final int CELL_SIZE = WIDTH / GRID_SIZE;

    private static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }

    private static class SnakePanel extends JPanel implements KeyListener {
        private ArrayList<Point> snake = new ArrayList<>();
        private Point food;
        private int dx = 1, dy = 0;
        private int score = 0;
        private boolean gameOver = false;
        private boolean paused = false;
        private Random rand = new Random();
        private int delay = 120; // ms per tick
        private Timer timer;

        SnakePanel() {
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
            setBackground(Color.BLACK);
            setFocusable(true);
            addKeyListener(this);

            snake.add(new Point(GRID_SIZE / 2, GRID_SIZE / 2));
            spawnFood();
            repaint();

            timer = new Timer(delay, e -> {
                if (!paused && !gameOver) {
                    move();
                    repaint();
                }
            });
            timer.start();
        }

        private void spawnFood() {
            int x, y;
            do {
                x = rand.nextInt(GRID_SIZE);
                y = rand.nextInt(GRID_SIZE);
            } while (onSnake(x, y));
            food = new Point(x, y);
        }

        private boolean onSnake(int x, int y) {
            for (Point p : snake) {
                if (p.x == x && p.y == y) return true;
            }
            return false;
        }

        private void move() {
            Point head = snake.get(0);
            int nx = head.x + dx;
            int ny = head.y + dy;

            // Wall collision
            if (nx < 0 || ny < 0 || nx >= GRID_SIZE || ny >= GRID_SIZE) {
                gameOver = true;
                return;
            }
            // Self collision
            for (int i = 0; i < snake.size(); i++) {
                Point p = snake.get(i);
                if (p.x == nx && p.y == ny) {
                    gameOver = true;
                    return;
                }
            }

            snake.add(0, new Point(nx, ny));

            if (nx == food.x && ny == food.y) {
                score += 10;
                spawnFood();
                // speed up slightly each time
                if (delay > 40) delay -= 2;
                timer.setDelay(delay);
            } else {
                snake.remove(snake.size() - 1);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Grid lines
            g.setColor(new Color(30, 30, 30));
            for (int i = 1; i < GRID_SIZE; i++) {
                g.fillRect(i * CELL_SIZE, 0, 1, HEIGHT);
                g.fillRect(0, i * CELL_SIZE, WIDTH, 1);
            }

            // Food
            g.setColor(Color.RED);
            g.fillOval(food.x * CELL_SIZE + 4, food.y * CELL_SIZE + 4,
                       CELL_SIZE - 8, CELL_SIZE - 8);

            // Snake
            for (int i = 0; i < snake.size(); i++) {
                Point p = snake.get(i);
                if (i == 0) {
                    g.setColor(Color.GREEN.brighter());
                } else {
                    g.setColor(Color.GREEN);
                }
                g.fillRect(p.x * CELL_SIZE + 1, p.y * CELL_SIZE + 1,
                           CELL_SIZE - 2, CELL_SIZE - 2);
            }

            // Score
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Score: " + score, 10, 25);

            if (gameOver) {
                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 48));
                String msg = "GAME OVER";
                int sw = g.getFontMetrics().stringWidth(msg);
                g.drawString(msg, (WIDTH - sw) / 2, HEIGHT / 2);

                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 20));
                String restart = "Press SPACE to restart";
                int rw = g.getFontMetrics().stringWidth(restart);
                g.drawString(restart, (WIDTH - rw) / 2, HEIGHT / 2 + 40);
            }

            if (paused && !gameOver) {
                g.setColor(Color.YELLOW);
                g.setFont(new Font("Arial", Font.BOLD, 36));
                String msg = "PAUSED";
                int sw = g.getFontMetrics().stringWidth(msg);
                g.drawString(msg, (WIDTH - sw) / 2, HEIGHT / 2);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            if (code == KeyEvent.VK_UP && dy != 1) { dx = 0; dy = -1; }
            else if (code == KeyEvent.VK_DOWN && dy != -1) { dx = 0; dy = 1; }
            else if (code == KeyEvent.VK_LEFT && dx != 1) { dx = -1; dy = 0; }
            else if (code == KeyEvent.VK_RIGHT && dx != -1) { dx = 1; dy = 0; }
            else if (code == KeyEvent.VK_SPACE) {
                if (gameOver) restart();
                else paused = !paused;
            }
        }

        private void restart() {
            snake.clear();
            snake.add(new Point(GRID_SIZE / 2, GRID_SIZE / 2));
            dx = 1; dy = 0;
            score = 0;
            delay = 120;
            timer.setDelay(delay);
            gameOver = false;
            paused = false;
            spawnFood();
        }

        @Override
        public void keyReleased(KeyEvent e) {}
        @Override
        public void keyTyped(KeyEvent e) {}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Snake");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            SnakePanel panel = new SnakePanel();
            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            panel.requestFocusInWindow();
        });
    }
}
