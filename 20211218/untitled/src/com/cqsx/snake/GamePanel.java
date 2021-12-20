package com.cqsx.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-12-18
 * Time: 16:00
 * Description: 整个游戏的画板
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener {

    private int length; //蛇的长度
    private int score; //成绩

    //0下标位置是头部
    private final int[] snakeX; //存储蛇的横坐标
    private final int[] snakeY; //存储蛇的纵坐标

    private char direct; //方向，L左，R右，U上，D下

    private boolean isStart; //判断游戏是否已经开始，默认是false
    private final Timer timer; //计时器。根据一定的时间，刷新帧。也就是平时说的帧数。以这个来控制蛇的速度

    private int foodX; //食物的坐标
    private int foodY;

    private boolean isFail; //判断游戏是不是已经结束，默认是false

    //构造方法
    public GamePanel() {
        snakeX = new int[600];
        snakeY = new int[600];

        //this，super
        timer = new Timer(100, this);
        init(); //初始化界面
    }

    //初始化
    public void init() {
        length = 3;
        score = 0;
        snakeX[0] = 100;
        snakeY[0] = 100;

        snakeX[1] = 75;
        snakeY[1] = 100;

        snakeX[2] = 50;
        snakeY[2] = 100;

        direct = 'R';


        getFood(); //获取新的食物

        //获取键盘的监听事件
        this.setFocusable(true); //有焦点
        this.addKeyListener(this);
        //让计时器动起来
        timer.start();
    }

    //调用父类方法，画一个Component组件
    //Graphics： 画笔
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //清屏
        this.setBackground(Color.WHITE); //设置背景颜色


        GameData.background.paintIcon(this, g, 25, 75); //画背景图片

        GameData.header.paintIcon(this, g, 25, 11); //将顶部的广告栏画出来


       // g.fillRect(25, 75, 850, 600); //整个蛇的活动区域
        GameData.food.paintIcon(this, g, foodX, foodY); //将食物画出来


        //将蛇头画出来
        if (direct == 'R') { //向右走的头部
            GameData.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (direct == 'L') {//向左走的头部
            GameData.left.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (direct == 'U') {//向上走的头部
            GameData.up.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (direct == 'D') {//向下走的头部
            GameData.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }



        //将蛇的身体画出来
        for (int i = 1; i < length; i++) {
            GameData.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        //将成绩写到右上角
        g.setColor(Color.WHITE); //设置画笔颜色
        g.setFont(new Font("微软雅黑", Font.BOLD, 18)); //设置字体格式
        g.drawString("长度: " + length, 750, 35);
        g.drawString("成绩: " + score, 750, 57);

        //判断游戏是否已经开始
        if (isStart == false) {
            g.setColor(Color.WHITE); //设置画笔颜色
            g.setFont(new Font("微软雅黑", Font.BOLD, 40)); //设置字体格式
            g.drawString("按下空格开始游戏", 300, 350);
        }

        //判断游戏是不是已经结束了
        if (isFail) {
            g.setColor(Color.red); //设置画笔颜色
            g.setFont(new Font("微软雅黑", Font.BOLD, 40)); //设置字体格式
            g.drawString("游戏结束，按下空格重新开始", 230, 350);
        }
    }

    //键盘被按下，还没有弹起
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) { //按下空格的时候
            if (isFail) { //游戏已经失败了，再次按下空格，就是重新开始游戏
                isFail = false;
                init(); //重新初始化整个画板
            } else {
                isStart = !isStart;
            }
        } else if (keyCode == KeyEvent.VK_UP && this.direct != 'D') { //上
            this.direct = 'U';
        } else if (keyCode == KeyEvent.VK_DOWN && this.direct != 'U') { //下
            this.direct = 'D';
        } else if (keyCode == KeyEvent.VK_LEFT && this.direct != 'R') { //左
            this.direct = 'L';
        } else if (keyCode == KeyEvent.VK_RIGHT && this.direct != 'L') { //右
            this.direct = 'R';
        }
        repaint(); //刷新界面。此处不调研也行，因为Timer会自动调用相应方法
    }

    //定时器，监听时间，也就是 帧：执行相应的操作
    @Override
    public void actionPerformed(ActionEvent e) {
        //在游戏没失败，并且是游戏开始的状态下，才会移动身体
        if (isStart && !isFail) {
            //身体的移动。把前一节身体，往后一节的位置放
            for (int i = length - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }

            //头部的移动
            if (this.direct == 'R') { //向右移动
                snakeX[0] += 25;
                if (snakeX[0] > 850) {
                    snakeX[0] = 25;
                }
            } else if (this.direct == 'L') { //向左移动
                snakeX[0] -= 25;
                if (snakeX[0] < 25) {
                    snakeX[0] = 850;
                }
            } else if (this.direct == 'U') { //向上移动
                snakeY[0] -= 25;
                if (snakeY[0] < 75) {
                    snakeY[0] = 650;
                }
            } else if (this.direct == 'D') { //向下移动
                snakeY[0] += 25;
                if (snakeY[0] > 650) {
                    snakeY[0] = 75;
                }
            }

            //判断头部是不是和身体相撞了
            for (int i = 1; i < length; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    this.isFail = true;
                    break;
                }
            }

            //在头部移动之后，判断食物和头部是不是重合了
            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                length++; //蛇的长度加1
                score += 10; //分数+10
                getFood(); //重新刷新食物的位置
            }
            repaint(); //重新刷新界面
        }

        timer.start(); //让计时器动起来
    }

    //随机生成食物的坐标
    private void getFood() {
        this.foodX = (int) (Math.random() * 34) * 25 + 25; //生成25~825之间的数
        this.foodY = (int) (Math.random() * 23) * 25 + 75; //生成75~650之间的数
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //键盘弹起
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //键盘按下并弹起
    }
}
