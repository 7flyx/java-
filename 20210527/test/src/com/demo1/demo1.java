package com.demo1;

import java.io.File;

public class demo1 {
    public static void main(String[] args) {
        //File类的使用，遍历得到一个路径下的所有文件信息
        File f1 = new File("E:\\javaSe_test\\tmp");
        getAllFile(f1);
    }

    public static void getAllFile(File f1) {
        //递归调用该函数
        File[] file = f1.listFiles();
        if(file != null) {
            for (File f : file) {
                if (f.isDirectory()) {
                    getAllFile(f);
                } else {
                    System.out.println(f.getAbsolutePath());
                }
            }
        }
    }
}
