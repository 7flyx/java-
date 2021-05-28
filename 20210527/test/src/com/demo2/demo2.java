package com.demo2;

import java.io.*;

public class demo2 {
    public static void main(String[] args) throws IOException {
        //复制一个视频文件过来
        /*
            四种方式
            1. 基本的一个字节拷贝    共耗时205066毫秒
            2. 基本的一个数组开始拷贝  共耗时254毫秒
            3. 缓冲区上，一个字节拷贝  共耗时390毫秒
            4. 缓冲区上，一个数组考试拷贝  共耗时60毫秒
         */

        int startTime = (int) System.currentTimeMillis(); //开始时间

        //方法
        //method1();
        //method2();
        //method3();
        method4();

        int endTime = (int) System.currentTimeMillis(); //结束时间
        System.out.println("共耗时" + (endTime - startTime) + "毫秒");
    }

    public static void method1() throws IOException {
        //基本的一个字节的开始拷贝
        FileInputStream fis = new FileInputStream("C:\\Users\\听风\\Documents\\" +
                "Tencent Files\\2647282717\\FileRecv\\20210315_环境安装后半部分.mp4");

        FileOutputStream fos = new FileOutputStream("test\\环境安装后半部分.mp4");

        int by = 0;
        while ((by = fis.read()) != -1) {
            fos.write(by);
        }

        fis.close();
        fos.close();
    }

    public static void method2() throws IOException {
        //基本的一个数组为单位开始拷贝
        FileInputStream fis = new FileInputStream("C:\\Users\\听风\\Documents\\" +
                "Tencent Files\\2647282717\\FileRecv\\20210315_环境安装后半部分.mp4");

        FileOutputStream fos = new FileOutputStream("test\\环境安装后半部分.mp4");

        int len = 0;
        byte[] bys = new byte[1024];
        while ((len = fis.read(bys)) != -1) {
            fos.write(bys);
        }

        fis.close();
        fos.close();
    }

    public static void method3() throws IOException {
        //使用缓冲区，一个字节的拷贝
        FileInputStream fis = new FileInputStream("C:\\Users\\听风\\Documents\\" +
                "Tencent Files\\2647282717\\FileRecv\\20210315_环境安装后半部分.mp4");

        FileOutputStream fos = new FileOutputStream("test\\环境安装后半部分.mp4");

        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedOutputStream bos = new BufferedOutputStream(fos);


        int by = 0;
        while ((by = bis.read()) != -1) {
            bos.write(by);
        }

        bis.close();
        bos.close();

    }

    public static void method4() throws IOException{
        //使用缓冲区，一个数组的拷贝
        FileInputStream fis = new FileInputStream("C:\\Users\\听风\\Documents\\" +
                "Tencent Files\\2647282717\\FileRecv\\20210315_环境安装后半部分.mp4");

        FileOutputStream fos = new FileOutputStream("test\\环境安装后半部分.mp4");

        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedOutputStream bos = new BufferedOutputStream(fos);


        int len = 0;
        byte[] bys = new byte[1024];
        while ((len = bis.read(bys)) != -1) {
            bos.write(bys);
        }

        bis.close();
        bos.close();
    }
}
