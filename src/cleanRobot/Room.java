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

	private void build(int x, int y) {
		String key = x+"-"+y;
		oMap.put(key, new Obstacle(x, y));
	}

	private void buildObstacle() {
		//(1)
//		build(1,15);
//		build(1,16);
//		build(1,17);
//		build(1,18);
//		build(1,19);
//		build(1,20);
//		build(2,15);
//		build(2,16);
//		build(2,17);
//		build(2,18);
//		build(2,19);
//		build(2,20);
//		build(6,1);
//		build(6,2);
//		build(6,3);
//		build(7,1);
//		build(7,2);
//		build(7,3);
//		build(8,1);
//		build(8,2);
//		build(8,3);
//		build(9,1);
//		build(9,2);
//		build(9,3);
//		build(7,10);
//		build(7,11);
//		build(8,10);
//		build(8,11);
//		build(13,13);
//		build(13,14);
//		build(14,13);
//		build(14,14);
//		build(15,13);
//		build(15,14);
//		build(16,13);
//		build(16,14);
//		build(17,19);
//		build(17,20);
//		build(18,19);
//		build(18,20);
//		build(19,19);
//		build(19,20);
//		build(20,19);
//		build(20,20);


		//(2)
		build(1,18);
		build(1,19);
		build(1,20);
		build(2,18);
		build(2,19);
		build(2,20);
		build(9,1);
		build(9,2);
		build(9,3);
		build(10,1);
		build(10,2);
		build(10,3);
		build(11,1);
		build(11,2);
		build(11,3);

		build(14,18);
		build(15,18);
		build(16,18);
		build(16,19);
		build(16,20);

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
		for(;;) {
			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}
}