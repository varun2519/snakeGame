package play;

import javax.swing.JFrame;

public class Player {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game"); // Title of the game window
        SnakePanel panel = new SnakePanel(); // Your game panel

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // Prevent resizing to maintain gameplay consistency
        frame.pack();
        frame.setSize(SnakePanel.panel_Width, SnakePanel.panel_Height); // Match the game panel size
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setVisible(true);
    }
}
