package com.demo1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class demo2 {
    public static void main(String[] args) throws IOException {
        //File类，字节输出流的写入操作，write
        //FileOutputStream
        FileOutputStream f1 = new FileOutputStream("test\\fos.txt");

        //字节输出流，以二进制的形式进行写入，最终以文本的形式打开，会被转换为相应的字符型
        f1.write(65);
        byte[] bys = {66,67,68};
        f1.write(bys); //字节数组的形式写入

        byte[] bys2 = "abcdef".getBytes(); //字符串转换为byte数组
        f1.write(bys2);
        f1.close();
    }
}
