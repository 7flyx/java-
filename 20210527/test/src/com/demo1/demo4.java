package com.demo1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class demo4 {
    public static void main(String[] args) throws IOException {
        //从fis里面读取文件，复制到fos.txt
        FileInputStream fis = new FileInputStream("test\\fis.txt");
        FileOutputStream fos = new FileOutputStream("test\\fos.txt");

        //创建临时变量
        int by = 0;
        while((by = fis.read()) != -1) {
            fos.write(by);
        }

        //关闭资源
        fis.close();
        fos.close();
    }
}
