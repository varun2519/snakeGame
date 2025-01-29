package play;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKey extends KeyAdapter {
    private SnakePanel snakePanel;

    public MyKey(SnakePanel snakePanel) {
        this.snakePanel = snakePanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char currentDirection = snakePanel.getDirection();
        char newDirection = currentDirection;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:  newDirection = 'L'; break;
            case KeyEvent.VK_RIGHT: newDirection = 'R'; break;
            case KeyEvent.VK_UP:    newDirection = 'U'; break;
            case KeyEvent.VK_DOWN:  newDirection = 'D'; break;
            case KeyEvent.VK_SPACE: 
                snakePanel.togglePause(); 
                return; // Pause functionality handled, no need to process further
            // Optional: Add WASD support
            case KeyEvent.VK_A: newDirection = 'L'; break;
            case KeyEvent.VK_D: newDirection = 'R'; break;
            case KeyEvent.VK_W: newDirection = 'U'; break;
            case KeyEvent.VK_S: newDirection = 'D'; break;
            default:
                return; // Ignore other keys
        }

        if (isValidDirectionChange(currentDirection, newDirection)) {
            snakePanel.setDirection(newDirection);
        }
    }

    private boolean isValidDirectionChange(char currentDirection, char newDirection) {
        return (currentDirection == 'L' && newDirection != 'R') ||
               (currentDirection == 'R' && newDirection != 'L') ||
               (currentDirection == 'U' && newDirection != 'D') ||
               (currentDirection == 'D' && newDirection != 'U');
    }
}
