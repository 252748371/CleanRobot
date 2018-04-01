package snake;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SnakeFrame extends Frame{
	
	private static final long serialVersionUID = 5346256374679648106L;
	//方格的宽度和长度
	public static final int BLOCK_WIDTH = 25 ;
	public static final int BLOCK_HEIGHT = 25 ;
	//界面的方格的行数和列数
	public static final int ROW = 21;
	public static final int COL = 20;
	
	//画图的线程对象
	private MyPaintThread paintThread = new MyPaintThread();

	private Image offScreenImage = null;
	
	private Snake snake = new Snake();
	
	private static SnakeFrame sf =null;
	
	public static void main(String[] args) {
		sf = new SnakeFrame();
		sf.launch();
	}
	
	public void launch(){
		
		this.setTitle("Room");
		this.setSize(ROW*BLOCK_HEIGHT+200, COL*BLOCK_WIDTH+200);
		this.setLocation(0, 0);
		this.setBackground(Color.WHITE);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		this.setResizable(true);
		this.setVisible(true);
		new Thread(paintThread).start();
	}

	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		/*
		 * 将界面画成由ROW*COL的方格构成,两个for循环即可解决
		 * */
		for(int i=0;i<=ROW;i++){
			g.drawLine(0, i*BLOCK_HEIGHT, COL*BLOCK_WIDTH,i*BLOCK_HEIGHT );
		}
		for(int i=0;i<=COL;i++){
			g.drawLine(i*BLOCK_WIDTH, 0 , i*BLOCK_WIDTH ,ROW*BLOCK_HEIGHT);
		}
		//矩形的左上角、矩形的左下角
		//g.fillRect(BLOCK_WIDTH, BLOCK_HEIGHT, BLOCK_WIDTH * 2, BLOCK_HEIGHT * 2);
		paintObstacle();
		g.drawLine(35, 25, 45, 45);
		g.setColor(c);
		snake.draw(g);
	}
	
	private void paintObstacle() {
		
	}
	
	/*
	 * 重画线程类
	 * */
	private class MyPaintThread implements Runnable{
		//running不能改变，改变后此线程就结束了
//		private static final boolean  running = true;
		@Override
		public void run() {
			while(true){
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}		
		}
	}
}
