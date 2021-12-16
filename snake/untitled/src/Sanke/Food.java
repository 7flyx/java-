package Sanke;

import java.awt.*;
import java.util.Map;

public class Food {
    int row;
    int col;


    public Food() {
        row = 10;//创建食物的大小
        col = 10;
    }

    public void repearShow() {
        row = (int) (Math.random() * 18);//生成随机数 乘以食物的大小可以得到坐标
        col = (int) (Math.random() * 32);
    }

    public void draw(Graphics g) {//把食物画出来
        g.setColor(Color.red);
        g.fillRect(col * 20, row * 20, 20, 20);//表示坐标
//        if ((int) (Math.random() * 2) == 1) {
//        } else {
//            g.fillOval(col * 20, row * 20, 20, 20);
//        }
    }

    public Rectangle getCoordinates() {
        return new Rectangle(col * 20, row * 20, 20, 20);//获得食物的坐标

    }


}
