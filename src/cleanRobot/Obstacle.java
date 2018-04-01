package cleanRobot;

import java.awt.Graphics;

public class Obstacle {
	
	private static final int BLOCK = 25;
	
	private int x;
	
	private int y;

	public Obstacle(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		g.fillRect((x -1) * BLOCK + 50, (y - 1) * BLOCK + 50, BLOCK, BLOCK);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
