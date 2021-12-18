package com.cqsx.snake;

import javax.swing.*;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-12-18
 * Time: 15:36
 * Description: 测试游戏开始，主界面
 */
public class StartGames {
    public static void main(String[] args) {
        //1. 先设置一个Windows的窗口：JFrame
        //JFrame是指一个计算机语言-java的GUI程序的基本思路是以JFrame为基础,它是屏幕上window的对象,能够最大化、最小化、关闭。”
        JFrame frame = new JFrame("贪吃蛇小游戏");//界面框的标题：贪吃蛇小游戏
        frame.setBounds(20, 20, 900, 725); //前两个参数是界面左上角位置，后两个参数是长宽
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置退出按钮
        frame.setResizable(false);//将界面框的大小固定

        //2. 在Windows窗口下 绘制一个画板
        frame.add(new GamePanel());

        frame.setVisible(true); //将界面显示在屏幕上
    }
}
