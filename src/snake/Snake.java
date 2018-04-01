package snake;

import java.awt.Color;
import java.awt.Graphics;

import cleanRobot.Direction;

public class Snake {
	private static final int BLOCK_WIDTH = SnakeFrame.BLOCK_WIDTH;
	private static final int BLOCK_HEIGHT = SnakeFrame.BLOCK_HEIGHT;
	
	private int[] A = {1,2};
	private int[] B = {SnakeFrame.COL,1};
	private int[] C = {SnakeFrame.COL,SnakeFrame.COL};
	private int[] D = {1,SnakeFrame.COL};
	
	private Node robot = new Node(1,1,Direction.R);
	
	public void draw(Graphics g){
		if(robot==null){
			return ;
		}
		System.out.println(robot.x + "  " + robot.y + " " + robot.dir);
		
		robot.draw(g);
		if(robot.x == B[0] && robot.y == B[1] && robot.dir == Direction.R) {
			robot.dir = Direction.D;
			--B[0];
			++B[1];
		}else if(robot.x == C[0] && robot.y == C[1] && robot.dir == Direction.D) {
			robot.dir = Direction.L;
			--C[0];
			--C[1];
		}else if(robot.x == D[0] && robot.y == D[1] && robot.dir == Direction.L) {
			robot.dir = Direction.U;
			++D[0];
			--D[1];
		}else if(robot.x == A[0] && robot.y == A[1] && robot.dir == Direction.U) {
			robot.dir = Direction.R;
			++A[0];
			++A[1];
		}else if(robot.x == SnakeFrame.COL/2 && robot.y == SnakeFrame.COL/2) {
			robot.dir = Direction.LU;
			//return;
		}
		move();
	}

	private void move() {
		Node node = null;
		switch(robot.dir){
		case L:
			node = new Node(robot.x-1,robot.y,robot.dir);
			break;
		case U:
			node = new Node(robot.x,robot.y-1,robot.dir);
			break;
		case R:
			node = new Node(robot.x+1,robot.y,robot.dir);
			break;
		case D:
			node = new Node(robot.x,robot.y+1,robot.dir);
			break;
		case LU:
			node = new Node(robot.x-1, robot.y-1, robot.dir);
			break;
		}
		robot = node;

	}
	
	public class Node {
		private int x;
		private int y;
		//方向
		private Direction dir ;
		
		public Node(int x, int y, Direction dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		public void draw(Graphics g){
			Color c = g.getColor();
			g.setColor(Color.RED);
			g.fillRect((x-1)*BLOCK_WIDTH, y*BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
			g.setColor(c);		
		}
	}


}
