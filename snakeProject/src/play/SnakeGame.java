package play;

import javax.swing.JFrame;

public class SnakeGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        SnakePanel panel = new SnakePanel();

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // Disable resizing to maintain consistent gameplay
        frame.pack();
        frame.setSize(SnakePanel.panel_Width, SnakePanel.panel_Height); // Match panel size
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }
}
