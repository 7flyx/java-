package com.cqsx.snake;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-12-18
 * Time: 16:02
 * Description: 存放游戏所需要的一些素材
 */
public class GameData {
    //url指的是统一资源定位系统。url是用于完整地描述Internet上网页和其他资源的地址的一种标识方法。
    private final static URL headerURL = GameData.class.getResource("/lib/head.png");//拿到资源的地址
    public static ImageIcon header = new ImageIcon(headerURL); //画出header

    //拿取资源的地址
    private final static URL bodyURl = GameData.class.getResource("/lib/body.png");
    private final static URL foodURl = GameData.class.getResource("/lib/food.png");
    private final static URL upURl = GameData.class.getResource("/lib/up.png");
    private final static URL downURl = GameData.class.getResource("/lib/down.png");
    private final static URL leftURl = GameData.class.getResource("/lib/left.png");
    private final static URL rightURl = GameData.class.getResource("/lib/right.png");
    private final static URL background1 = GameData.class.getResource("/lib/back1.png");

    public static ImageIcon body = new ImageIcon(bodyURl); //画出body
    public static ImageIcon food = new ImageIcon(foodURl); //画出食物
    public static ImageIcon up = new ImageIcon(upURl); //画出向上的头部
    public static ImageIcon down = new ImageIcon(downURl); //画出向下的头部
    public static ImageIcon left = new ImageIcon(leftURl); //画出向左的头部
    public static ImageIcon right = new ImageIcon(rightURl); //画出向右的头部
    public static ImageIcon background = new ImageIcon(background1);
    //public static Image background =  //画出向右的头部

}
