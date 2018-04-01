package cleanRobot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;

public class Room extends JFrame implements Runnable{
	
	private static final long serialVersionUID = 6743056722296157062L;
	private static final int BLOCK= 25 ;
	private static final int ROW = 20;
	private Image image;
	private Map<String, Obstacle> oMap = new HashMap<>();
	
	private Robot robot;
	
	public Room() {
		super();
		buildObstacle();
		robot = new Robot(oMap);
	}
	
	public static void main(String[] args) {
		Room room = new Room();
		
		room.setSize(BLOCK * ROW + 100, BLOCK * ROW + 100);
		room.setTitle("Room");
		room.setLocation(0, 0);
		room.setResizable(false);
		room.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		room.setVisible(true);
		new Thread(room).start();
	}

	private void buildObstacle() {
//		oMap.put("3-18", new Obstacle(3, 18));
		oMap.put("4-18", new Obstacle(4, 18));
		oMap.put("5-18", new Obstacle(5, 18));
		oMap.put("6-20", new Obstacle(6, 20));
		oMap.put("6-18", new Obstacle(6, 18));
		oMap.put("6-19", new Obstacle(6, 19));
//		oMap.put("9-11", new Obstacle(9, 11));
//		oMap.put("10-11", new Obstacle(10, 11));
//		oMap.put("11-11", new Obstacle(11, 11));
//		oMap.put("12-11", new Obstacle(12, 11));
//		oMap.put("8-9", new Obstacle(8, 9));
//		oMap.put("9-9", new Obstacle(9, 9));
//		oMap.put("10-9", new Obstacle(10, 9));
//		oMap.put("11-9", new Obstacle(11, 9));
//		oMap.put("11-10", new Obstacle(11, 10));
//		oMap.put("11-11", new Obstacle(11, 11));
//		oMap.put("11-12", new Obstacle(11, 12));
//		oMap.put("10-12", new Obstacle(10, 12));
//		oMap.put("10-11", new Obstacle(10, 11));
//		oMap.put("10-10", new Obstacle(10, 10));
//		oMap.put("11-13", new Obstacle(11, 13));
//		oMap.put("12-13", new Obstacle(12, 13));
//		oMap.put("13-13", new Obstacle(13, 13));
//		oMap.put("14-13", new Obstacle(14, 13));
	}

	@Override
	public void paint(Graphics g) {
		drawBufferedImage();
		g.drawImage(image, 0, 0, this);
	}
	
	//双缓冲
	private void drawBufferedImage() {
		image = createImage(BLOCK * ROW + 100, BLOCK * ROW + 100);
		Graphics g = image.getGraphics();
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		for(int i = 0;i <= ROW;i++) {
			g.drawLine(50, i * BLOCK + 50, ROW * BLOCK + 50, i * BLOCK + 50);
		}
		for(int i =0;i <= ROW;i++) {
			g.drawLine(i * BLOCK + 50, 50, i * BLOCK + 50, ROW * BLOCK + 50);
		}
		g.drawLine(10, 10, 20, 20);
		for(Entry<String, Obstacle> entry : oMap.entrySet()) {
			entry.getValue().draw(g);
		}
		g.setColor(c);
		robot.draw(g);
		robot.move();
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}
}