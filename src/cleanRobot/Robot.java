package cleanRobot;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Robot {
	
	public static final int BLOCK = 25;
	
	private int x = 1;
	private int y = 1;
	private Direction dir = Direction.D;
	private Map<String, Obstacle> oMap;
	private List<Pass> passList = new ArrayList<>();
	
	private int cur = 1;
	private int pre = 0;
	private boolean hadGone = false;
	private boolean meetOb = false;
	private boolean meetOb_a = false;
	private int meetOb_x;
	private boolean meetOb2 =false;
	private boolean meetOb2_a = false;
	private int meetOb2_x;
	private boolean meetDead_1 = false;
	private boolean meetDead_2 = false;
	private boolean meetDead_21 = false;
	
	public Robot(Map oMap) {
		this.oMap = oMap;
	}

	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillRect((x -1) * BLOCK + 50, (y - 1) * BLOCK + 50, BLOCK, BLOCK);
		g.setColor(Color.lightGray);
		for (Pass pass : passList) {
			pass.draw(g);
		}
		g.setColor(c);
	}
	
	public void move() {
		//全图扫地完成，停止移动
		if((dir == Direction.U && x==20 && y==1) || (dir == Direction.D && x==20 && y==20) 
				|| (dir == Direction.U && oMap.containsKey(x+"-"+(y-1)) && x==20) ) {
			x = 1;
			y = 1;
			dir = Direction.D;
			pre = 0;
			cur = 1;
			meetOb = false;
			meetOb_a = false;
			meetOb2 =false;
			meetOb2_a = false;
			meetDead_1 = false;
			meetDead_2 = false;
			meetDead_21 = false;
			passList.clear();
			return;
		}
		//pass
		passList.add(new Pass(x,y));
		//遇到死角_1
		if(dir == Direction.U && oMap.containsKey((x+1)+"-"+y) && y==1) {
			dir = Direction.D;
			meetDead_1 = true;
		}
		if(meetDead_1 && !oMap.containsKey((x+1)+"-"+y)) {
			meetDead_1 = false;
			dir = Direction.D;
			x = x+1;
			pre = 0;
			cur = 1;
			return;
		}
		//遇到死角_2
		if(dir == Direction.D && oMap.containsKey((x+1)+"-"+y) && y==20) {
			meetDead_2 = true;
			if(oMap.containsKey(x+"-"+(y-1))) {
				dir = Direction.L;
			}else {
				dir = Direction.U;
			}
			pre = cur;
			cur = 1;
		}
		if(meetDead_2 && oMap.containsKey(x+"-"+(y-1))) {
			x = x-1;
			meetDead_21 = true;
			return;
		}
		if(meetDead_21 && oMap.containsKey((x+1)+"-"+(y+1)) ) {
			meetDead_21 = false;
			x = x+1;
			dir = Direction.U;
			cur = 1;
			return;
		}
		//绕过障碍物
		if(cur > pre && oMap.containsKey(left()) && !meetOb2_a) {
			if(y == 20 ||y == 1) {
				meetOb = false;
			}else {
				meetOb = true;
			}
		}
		if(cur > pre && oMap.containsKey((x-2)+"-"+y) && !oMap.containsKey(left()) && !meetOb_a) {
			if(y == 20 ||y == 1) {
				meetOb2 = false;
			}else {
				meetOb2 = true;
			}
		}
		if (meetOb_a && x == meetOb_x + 2) {
			meetOb_a = false;
		}
		if (meetOb2_a && x == meetOb2_x) {
			meetOb2_a = false;
		}
		if(meetOb && !oMap.containsKey(left())) {
			dir = Direction.L;
			pre = cur;
			cur = 1;
			meetOb_x = x;
			meetOb = false;
			meetOb_a = true;
		}
		if(meetOb2 && !oMap.containsKey((x-2)+"-"+y)) {
			dir = Direction.L;
			pre = cur;
			cur = 1;
			meetOb2_x = x;
			meetOb2 = false;
			meetOb2_a = true;
		}
		if(dir == Direction.L && oMap.containsKey(down())) {
			if(!oMap.containsKey((x-1)+"-"+(y+1))) {
				dir = Direction.U;
			}
		}
		if(dir == Direction.L && oMap.containsKey(up())) {
			if(!oMap.containsKey((x-1)+"-"+(y-1))) {
				dir = Direction.D;
			}
		}
		//遇到障碍物或者边界 ==> 变向
		switch (dir) {
		case L:
			if(oMap.containsKey((x-1) + "-" + y) || x == 1) {
				if(!oMap.containsKey(x + "-" + (y-1))) {
					dir = Direction.U;
					pre = cur;
					cur = 0;
				}
			}
			break;
		case U:
			if(oMap.containsKey(x + "-" + (y - 1)) || y == 1) {
				if(!oMap.containsKey((x+1) + "-" + y)) {
					dir = Direction.D;
					x = x + 1;
					pre = cur;
					cur = 1;
					return;
				}
			}
			break;
		case R:
			if(oMap.containsKey((x+1) + "-" + y) || x == 20 || y == 20 || x == 1 || y == 1) {
				if(!oMap.containsKey(x + "-" + (y+1))) {
					dir = Direction.D;
					pre = cur;
					cur = 0;
				}
			}
			break;
		case D:
			if(oMap.containsKey(x + "-" + (y + 1)) || y == 20) {
				if(!oMap.containsKey((x+1) + "-" + y)) {
					dir = Direction.U;
					x = x + 1;
					pre = cur;
					cur = 1;
					return;
				}
			}
			break;
		}
		
		
		
		//移动
		switch (dir) {
		case L:
			x = x - 1;
			break;
		case U:
			y = y - 1;
			break;
		case R:
			x = x + 1;
			break;
		case D:
			y = y + 1;
			break;
		}
		cur++;
	}

	private String up(){
		return x+"-"+(y-1);
	}

	private String down(){
		return x+"-"+(y+1);
	}

	private String left(){
		return (x-1)+"-"+y;
	}

	private String right(){
		return (x+1)+"-"+y;
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

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	public static class Pass{
		private static final int BLOCK = 25;

		private int x;

		private int y;

		public void draw(Graphics g) {
			g.fillRect((x -1) * BLOCK + 50+2, (y - 1) * BLOCK + 50+2, BLOCK-4, BLOCK-4);
		}

		public Pass(int x, int y) {
			this.x = x;
			this.y = y;
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
	
}
