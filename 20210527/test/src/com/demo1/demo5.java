package com.demo1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

public class demo5 {
    public static void main(String[] args) throws IOException {
        //从文件中复制一个照片到该目录下
        FileInputStream fis = new FileInputStream("C:\\Users\\听风\\Desktop\\获取更多资源.jpg");
        FileOutputStream fos = new FileOutputStream("test\\1.jpg");

        byte[] by = new byte[1024];
        int len = 0;
        while ((len = fis.read(by)) != -1) {
            fos.write(by);
        }

        fis.close();
        fos.close();
    }
}
