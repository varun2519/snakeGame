package play;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel implements ActionListener {
    static final int panel_Height = 800; // Increased height
    static final int panel_Width = 800; // Increased width
    static final int unit_size = 30; // Larger unit size
    static final int num_of_Units = (panel_Height * panel_Width) / (unit_size * unit_size);
    int foodX;
    int foodY;
    final int[] X = new int[num_of_Units];
    final int[] Y = new int[num_of_Units];
    int snakeLength = 3;
    int foodSwallowed = 0;
    char direction = 'R'; // Initial direction
    boolean running = false;
    Random random;
    Timer timer;

    public SnakePanel() {
        random = new Random();
        this.setSize(panel_Width, panel_Height);
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKey(this));
        startGame();
    }

    public void startGame() {
        running = true;
        addFood();
        timer = new Timer(130, this);
        timer.start();
    }

    public void togglePause() {
        if (running) {
            timer.stop();
        } else {
            timer.start();
        }
        running = !running;
    }

    public void addFood() {
        boolean foodOnSnake;
        do {
            foodX = random.nextInt(panel_Width / unit_size) * unit_size;
            foodY = random.nextInt(panel_Height / unit_size) * unit_size;

            // Check if the food overlaps with any part of the snake's body
            foodOnSnake = false;
            for (int i = 0; i < snakeLength; i++) {
                if (X[i] == foodX && Y[i] == foodY) {
                    foodOnSnake = true;
                    break;
                }
            }
        } while (foodOnSnake); // Keep generating until the food is not on the snake
    }


    public void move() {
        for (int i = snakeLength; i > 0; i--) {
            X[i] = X[i - 1];
            Y[i] = Y[i - 1];
        }

        switch (direction) {
            case 'L':
                X[0] -= unit_size;
                break;
            case 'R':
                X[0] += unit_size;
                break;
            case 'U':
                Y[0] -= unit_size;
                break;
            case 'D':
                Y[0] += unit_size;
                break;
        }
    }

    public void checkFood() {
        if (X[0] == foodX && Y[0] == foodY) {
            snakeLength++;
            foodSwallowed++;
            addFood();
        }
    }

    public void checkCollisions() {
        // Check if the snake collides with itself
        for (int i = snakeLength; i > 0; i--) {
            if (X[0] == X[i] && Y[0] == Y[i]) {
                running = false;
            }
        }
        // Check if the snake hits the walls
        if (X[0] < 0 || X[0] >= panel_Width || Y[0] < 0 || Y[0] >= panel_Height) {
            running = false;
        }
        if (!running) {
            timer.stop();
        }
    }

    public void draw(Graphics graphics) {
        if (running) {
            // Draw food
            graphics.setColor(new Color(244, 0, 0));
            graphics.fillOval(foodX, foodY, unit_size, unit_size);

            // Draw snake
            for (int i = 0; i < snakeLength; i++) {
                if (i == 0) {
                    graphics.setColor(Color.WHITE); // Head
                } else {
                    graphics.setColor(new Color(224, 0, 0)); // Body
                }
                graphics.fillRect(X[i], Y[i], unit_size, unit_size);
            }

            // Display score
            graphics.setColor(Color.red);
            graphics.setFont(new Font("Sans serif", Font.BOLD, 25));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: " + foodSwallowed,
                    (panel_Width - metrics.stringWidth("Score: " + foodSwallowed)) / 2,
                    graphics.getFont().getSize());
        } else {
            gameOver(graphics);
        }
    }

    public void gameOver(Graphics graphics) {
        // Game Over text
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Sans serif", Font.BOLD, 25));
        FontMetrics metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Game Over", (panel_Width - metrics.stringWidth("Game Over")) / 2, panel_Height / 2);

        // Display score
        graphics.drawString("Score: " + foodSwallowed,
                (panel_Width - metrics.stringWidth("Score: " + foodSwallowed)) / 2,
                panel_Height / 2 + graphics.getFont().getSize());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkFood();
            checkCollisions();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }
}
