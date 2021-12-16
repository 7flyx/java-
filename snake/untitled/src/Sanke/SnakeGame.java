package Sanke;

import javax.swing.*;

/**
 * @author Swyee
 **/
public class SnakeGame extends JFrame {
    SnakeGrid snakeGrid= new SnakeGrid();
    Button button = new Button(snakeGrid);
    public SnakeGame(){
        this.setBounds(100, 50, 700, 500);//设置窗口大小
        this.setLayout(null);//更改layout 以便添加组件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭窗口的状态
        this.setResizable(false);//窗口不可以改变大小
        this.add(snakeGrid);
        this.add(button);
        //设置焦点
        snakeGrid.setFocusable(true);
        snakeGrid.requestFocus();
        snakeGrid.Music();//调用打开音乐的方法
        this.setVisible(true);//设置焦点状态为true
    }
    public static void main(String[] args) {
        new SnakeGame();
    }

}