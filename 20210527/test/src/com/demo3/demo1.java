package com.demo3;

import java.io.*;

public class demo1 {
    public static void main(String[] args) throws IOException {
        //字符输出与输入流
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("test\\osw.txt"));
        InputStreamReader isr = new InputStreamReader(new FileInputStream("test\\osw.txt"));
        //写入数据
        osw.write("中国"); //字符串的形式写入

        osw.close();


        int len = 0;
        char[] bys = new char[1024];
        while ((len = isr.read(bys)) != -1) {
            System.out.print(bys);
        }


        isr.close();
    }
}
