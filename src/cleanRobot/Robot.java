package cleanRobot;

import java.awt.Color;
import java.awt.Graphics;
import java.text.NumberFormat;
import java.util.*;

public class Robot {

    public static final int BLOCK = 25;

    private int x = 1;
    private int y = 1;
    private Direction dir = Direction.D;
    private Map<String, Obstacle> oMap;
    private Set<Pass> passList = new HashSet<>();
    private int againNum = 0;
    private int cur = 1;
    private int pre = 0;
    private int raoX = 0;
    private int raoY = 0;
    private int RAO1 = 0;
    private int RAO2 = 0;
    private int RAO3 = 0;
    private int RAO4 = 0;
    private int RAO5 = 0;
    private int RAO6 = 0;
    private int RAO7 = 0;
    private int RAO8 = 0;
    private int DEAD1 = 0;
    private int DEAD2 = 0;
    private int DEAD3 = 0;
    private int DEAD4 = 0;
    private int DEAD5 = 0;
    private int DEAD6 = 0;
    private int DEAD7 = 0;
    private int DEAD8 = 0;
    private int Longitudinal = 0;
    private int all;
    private String fugailv1;
    private String chongfulv1;
    private String fugailv2;
    private String chongfulv2;
    NumberFormat numberFormat = NumberFormat.getInstance();

    public Robot(Map oMap) {
        this.oMap = oMap;
        all = 400 - oMap.size();
        numberFormat.setMaximumFractionDigits(2);
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect((x - 1) * BLOCK + 50, (y - 1) * BLOCK + 50, BLOCK, BLOCK);
        g.setColor(Color.lightGray);
        for (Pass pass : passList) {
            pass.draw(g);
        }
        g.setColor(c);
    }

    public void move() {
        if (Longitudinal == 0 || Longitudinal == 2) {
            //pass
            addPass();
            //全图扫地完成，停止移动
            if ((dir == Direction.U && x == 20 && y == 1) || (dir == Direction.D && x == 20 && y == 20)
                    || (dir == Direction.U && isOb(0, -1) && x == 20)) {
                x = 1;
                y = 1;
                dir = Direction.R;
                pre = 0;
                cur = 1;
                count1();
                if (Longitudinal == 0)
                    Longitudinal = 1;
                if (Longitudinal == 2)
                    dir = Direction.D;
                return;
            }



            //RAO1
            if (cur > pre && x != 1 && !isOb(-1, 0) && isOb(-1, 1) && dir == Direction.U && RAO3 != 2 && RAO4 != 2) {
                dir = Direction.L;
                pre = cur;
                cur = 0;
                RAO1 = 1;
                raoX = x;
            }
            if (RAO1 == 1 && (!isOb(-1, 1) || isOb(-1, 0))) {
                dir = Direction.U;
                pre = cur;
                cur = 0;
                RAO1 = 2;
            }
            if (RAO1 == 2 && x == raoX + 2) {
                RAO1 = 0;
            }
            //RAO2
            if (cur > pre && x != 1 && !isOb(-2, 0) && isOb(-2, 1) && dir == Direction.U && RAO3 != 2 && RAO4 != 2 && !isOb(-1, 0)) {
                dir = Direction.L;
                pre = cur;
                cur = 1;
                raoX = x;
                x = x - 1;
                RAO2 = 1;
                return;
            }
            if (RAO2 == 1 && (!isOb(-1, 1) || isOb(-1, 0))) {
                dir = Direction.U;
                pre = cur;
                cur = 0;
                RAO2 = 2;
            }
            if (RAO2 == 2 && x == raoX + 1) {
                RAO2 = 0;
            }
            //RAO3
            if (cur > pre && x != 1 && !isOb(-1, 0) && isOb(-1, -1) && dir == Direction.D && RAO1 != 2 && RAO2 != 2) {
                dir = Direction.L;
                pre = cur;
                cur = 0;
                RAO3 = 1;
                raoX = x;
            }
            if (RAO3 == 1 && (!isOb(-1, -1) || isOb(-1, 0))) {
                dir = Direction.D;
                pre = cur;
                cur = 0;
                RAO3 = 2;
            }
            if (RAO3 == 2 && x == raoX + 2) {
                RAO3 = 0;
            }
            //RAO4
            if (cur > pre && x != 1 && !isOb(-2, 0) && isOb(-2, -1) && dir == Direction.D && RAO1 != 2 && RAO2 != 2 && !isOb(-1, 0)) {
                dir = Direction.L;
                pre = cur;
                cur = 1;
                raoX = x;
                x = x - 1;
                RAO4 = 1;
                return;
            }
            if (RAO4 == 1 && (!isOb(-1, -1) || isOb(-1, 0))) {
                dir = Direction.D;
                pre = cur;
                cur = 0;
                RAO4 = 2;
            }
            if (RAO4 == 2 && x == raoX + 2) {
                RAO4 = 0;
            }


            //DEAD3
            if (dir == Direction.U && isOb(0, -1) && isOb(1, 0)) {
                DEAD2 = 0;
                DEAD3 = 1;
                dir = Direction.L;
                pre = cur;
                cur = 0;
            }
            if (DEAD3 == 1 && !isOb(0, -1)) {
                dir = Direction.U;
                DEAD3 = 2;
                pre = cur;
                cur = 0;
            }
            if (DEAD3 == 2 && !isOb(1, 0) && isOb(1, 1)) {
                dir = Direction.U;
                DEAD3 = 0;
                pre = cur;
                cur = 1;
                x = x + 1;
                return;
            }
            //DEAD4
            if (dir == Direction.D && isOb(0, 1) && isOb(1, 0)) {
                DEAD4 = 1;
                dir = Direction.L;
                pre = cur;
                cur = 0;
            }
            if (DEAD4 == 1 && !isOb(0, 1)) {
                dir = Direction.D;
                DEAD4 = 2;
                pre = cur;
                cur = 0;
            }
            if (DEAD4 == 2 && !isOb(1, 0) && isOb(1, -1)) {
                DEAD4 = 0;
                pre = cur;
                cur = 1;
                x = x + 1;
                return;
            }

            //DAEA1
            if (dir == Direction.U && isOb(1, 0) && (y == 1 || isOb(0, -1)) && DEAD2 != 1 && DEAD3 != 1) {
                dir = Direction.D;
                pre = cur;
                cur = 0;
                DEAD1 = 1;
            }
            if (DEAD1 == 1 && !isOb(1, 0)) {
                dir = Direction.D;
                x = x + 1;
                pre = cur;
                cur = 1;
                DEAD1 = 0;
                return;
            }
            //DEAD2
            if (dir == Direction.D && isOb(1, 0) && (y == 20 || isOb(0, 1)) && DEAD4 != 1) {
                dir = Direction.U;
                pre = cur;
                cur = 0;
                DEAD2 = 1;
            }
            if (DEAD2 == 1 && !isOb(1, 0)) {
                x = x + 1;
                pre = cur;
                cur = 1;
                DEAD2 = 0;
                return;
            }


            //遇到障碍物或者边界 ==> 变向
            switch (dir) {
                case U:
                    if ((isOb(0, -1) || y == 1) && !isOb(1, 0)) {
                        dir = Direction.D;
                        x = x + 1;
                        pre = cur;
                        cur = 1;
                        return;
                    }
                    break;
                case D:
                    if ((isOb(0, 1) || y == 20) && !isOb(1, 0)) {
                        dir = Direction.U;
                        x = x + 1;
                        pre = cur;
                        cur = 1;
                        return;
                    }
                    break;
            }
        } else if (Longitudinal == 1 || Longitudinal == 3) {
            //pass
            addPass();
            //全图扫地完成，停止移动
            if ((dir == Direction.L && x == 1 && y == 20) || (dir == Direction.L && isOb(-1, 0) && y == 20)) {
                x = 1;
                y = 1;
                dir = Direction.D;
                pre = 0;
                cur = 1;
                count2();
                if (Float.valueOf(fugailv1) > Float.valueOf(fugailv2) || Longitudinal == 1) {
                    Longitudinal = 2;
                }else{
                    Longitudinal = 3;
                }
                return;
            }



            //RAO5
            if (cur > pre && y != 1 && !isOb(0, -1) && isOb(1, -1) && dir == Direction.L && RAO7 != 2 && RAO8 != 2) {
                dir = Direction.U;
                pre = cur;
                cur = 0;
                RAO5 = 1;
                raoY = y;
            }
            if (RAO5 == 1 && (!isOb(1, -1) || isOb(0, -1))) {
                dir = Direction.L;
                pre = cur;
                cur = 0;
                RAO5 = 2;
            }
            if (RAO5 == 2 && x == raoX + 2) {
                RAO5 = 0;
            }
            //RAO6
            if (cur > pre && y != 1 && !isOb(0, -2) && isOb(1, -2) && dir == Direction.L && RAO7 != 2 && RAO8 != 2 && !isOb(0, -1)) {
                dir = Direction.U;
                pre = cur;
                cur = 1;
                raoY = y;
                y = y - 1;
                RAO6 = 1;
                return;
            }
            if (RAO6 == 1 && (!isOb(1, -1) || isOb(0, -1))) {
                dir = Direction.L;
                pre = cur;
                cur = 0;
                RAO6 = 2;
            }
            if (RAO6 == 2 && y == raoY + 1) {
                RAO6 = 0;
            }
//			//RAO7
            if (cur > pre && y != 1 && !isOb(0, -1) && isOb(-1, -1) && dir == Direction.R && RAO5 != 2 && RAO6 != 2) {
                dir = Direction.U;
                pre = cur;
                cur = 0;
                RAO7 = 1;
                raoY = y;
            }
            if (RAO7 == 1 && (!isOb(-1, -1) || isOb(0, -1))) {
                dir = Direction.R;
                pre = cur;
                cur = 0;
                RAO7 = 2;
            }
            if (RAO7 == 2 && y == raoY + 2) {
                RAO7 = 0;
            }
//			//RAO7
            if (cur > pre && y != 1 && !isOb(0, -2) && isOb(-1, -2) && dir == Direction.R && RAO5 != 2 && RAO6 != 2 && !isOb(0, -1)) {
                dir = Direction.U;
                pre = cur;
                cur = 1;
                raoY = y;
                y = y - 1;
                RAO8 = 1;
                return;
            }
            if (RAO8 == 1 && (!isOb(-1, -1) || isOb(0, -1))) {
                dir = Direction.R;
                pre = cur;
                cur = 0;
                RAO8 = 2;
            }
            if (RAO8 == 2 && y == raoY + 2) {
                RAO8 = 0;
            }


            //DEAD7
            if (dir == Direction.L && isOb(-1, -0) && isOb(0, 1)) {
                DEAD6 = 0;
                DEAD7 = 1;
                dir = Direction.U;
                pre = cur;
                cur = 0;
            }
            if (DEAD7 == 1 && !isOb(-1, 0)) {
                dir = Direction.L;
                DEAD7 = 2;
                pre = cur;
                cur = 0;
            }
            if (DEAD7 == 2 && !isOb(0, 1) && isOb(1, 1)) {
                dir = Direction.L;
                DEAD7 = 0;
                pre = cur;
                cur = 1;
                y = y + 1;
                return;
            }
//			//DEAD8
            if (dir == Direction.R && isOb(1, 0) && isOb(0, 1)) {
                DEAD8 = 1;
                dir = Direction.U;
                pre = cur;
                cur = 0;
            }
            if (DEAD8 == 1 && !isOb(1, 0)) {
                dir = Direction.R;
                DEAD8 = 2;
                pre = cur;
                cur = 0;
            }
            if (DEAD8 == 2 && !isOb(0, 1) && isOb(-1, 1)) {
                DEAD8 = 0;
                pre = cur;
                cur = 1;
                y = y + 1;
                return;
            }
//
//			//DEAD5
            if (dir == Direction.L && isOb(0, 1) && (x == 1 || isOb(-1, 0)) && DEAD6 != 1 && DEAD7 != 1) {
                dir = Direction.R;
                pre = cur;
                cur = 0;
                DEAD5 = 1;
            }
            if (DEAD5 == 1 && !isOb(0, 1)) {
                dir = Direction.R;
                y = y + 1;
                pre = cur;
                cur = 1;
                DEAD5 = 0;
                return;
            }
//			//DEAD6
            if (dir == Direction.R && isOb(0, 1) && (x == 20 || isOb(1, 0)) && DEAD8 != 1) {
                dir = Direction.L;
                pre = cur;
                cur = 0;
                DEAD6 = 1;
            }
            if (DEAD6 == 1 && !isOb(0, 1)) {
                y = y + 1;
                pre = cur;
                cur = 1;
                DEAD6 = 0;
                return;
            }


            //遇到障碍物或者边界 ==> 变向
            switch (dir) {
                case R:
                    if ((isOb(1, 0) || x == 20) && !isOb(0, 1)) {
                        dir = Direction.L;
                        y = y + 1;
                        pre = cur;
                        cur = 1;
                        return;
                    }
                    break;
                case L:
                    if ((isOb(-1, 0) || x == 1) && !isOb(0, 1)) {
                        dir = Direction.R;
                        y = y + 1;
                        pre = cur;
                        cur = 1;
                        return;
                    }
                    break;
            }
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

    private void addPass() {
        Pass pass = new Pass(x, y);
        if (!passList.add(pass)) {
            againNum++;
        }
    }

    private void count1() {
        int passListSize = passList.size();
        fugailv1 = numberFormat.format(((float) passListSize / (float) all * 100));
        chongfulv1 = numberFormat.format((float) againNum / (float) passListSize * 100);
        System.out.println("可清扫区域：" + all + "个");
        System.out.println("完成扫地任务区域："+passListSize);
        System.out.println("重复走过的区域："+againNum);
        System.out.println("纵向覆盖率：" + fugailv1 + "%");
        System.out.println("纵向重复率：" + chongfulv1 + "%");
        passList.clear();
        againNum = 0;
    }

    private void count2() {
        int passListSize = passList.size();
        fugailv2 = numberFormat.format(((float) passListSize / (float) all * 100));
        chongfulv2 = numberFormat.format((float) againNum / (float) passListSize * 100);
        System.out.println("可清扫区域：" + all + "个");
        System.out.println("完成扫地任务区域："+passListSize);
        System.out.println("重复走过的区域："+againNum);
        System.out.println("横向覆盖率：" + fugailv2+ "%");
        System.out.println("横向重复率：" + chongfulv2 + "%");
        passList.clear();
        againNum = 0;
    }

    private boolean isOb(int offX, int offY) {
        return oMap.containsKey((x + offX) + "-" + (y + offY));
    }

    public static class Pass {
        private static final int BLOCK = 25;

        private int x;

        private int y;

        public void draw(Graphics g) {
            g.fillRect((x - 1) * BLOCK + 50 + 2, (y - 1) * BLOCK + 50 + 2, BLOCK - 4, BLOCK - 4);
        }

        public Pass(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pass pass = (Pass) o;

            if (x != pass.x) return false;
            return y == pass.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

}
