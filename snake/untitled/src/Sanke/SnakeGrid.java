package Sanke;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Swyee
 **/
public class SnakeGrid extends JPanel {
    Food food = new Food();
    Snake snake = new Snake(food);//创建蛇
    ImageIcon image = new ImageIcon("E:\\Github_Coding\\java-\\snake\\untitled\\lib\\sky.jpg");//图片文件地址
    File f = new File("E:\\Github_Coding\\java-\\snake\\untitled\\lib\\music.wav");//音乐文件地址
    SnakeThread snakeThread = new SnakeThread();

    public SnakeGrid() {
        this.setBounds(0, 0, 700, 400);
        this.setBackground(Color.black);
        snakeThread.start();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                snake.keyboard(e);
            }
        });
    }

    /**
     * 设置画笔
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        image.paintIcon(this, g, 0, 0); //设置背景图片
        snake.move();//蛇移动
        snake.draw(g);
        food.draw(g);
    }

    //读取音乐文件
    void Music() {
        try {
            URI uri = f.toURI();
            URL url = uri.toURL();
            AudioClip aau = Applet.newAudioClip(url);
            aau.loop();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    class SnakeThread extends Thread {
        boolean flag = true;

        @Override
        public void run() {
            while (Snake.islive && flag) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (Snake.islive && Button.isMove) {
                    repaint();
                }
            }
            if (flag) {
                JOptionPane.showMessageDialog(SnakeGrid.this, "游戏结束");
            }
        }

        public void stopThread() {
            flag = false;
        }
    }
}



