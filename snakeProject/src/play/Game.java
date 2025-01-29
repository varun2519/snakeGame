package play;

import java.awt.Graphics;

public interface Game {
	public void move();
	public void addFood();
	public void checkFood();
	public void playGame();
	public void checkHit();
	public void draw(Graphics graphics);
	public void gameOver(Graphics graphics);

}
