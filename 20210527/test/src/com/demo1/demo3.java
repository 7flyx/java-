package com.demo1;

import java.io.FileInputStream;
import java.io.IOException;

public class demo3 {
    public static void main(String[] args) {
        //FileInputStream 读取文件中数据
        FileInputStream fis = null;
        try {
             fis = new FileInputStream("test\\fis.txt");
             int by = 0;
             while (( by = fis.read()) != -1) {
                 System.out.println((char)by);
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.fillInStackTrace();
                }
            }
        }

    }
}
