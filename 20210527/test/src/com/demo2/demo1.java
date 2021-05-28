package com.demo2;

import java.io.*;

public class demo1 {
    public static void main(String[] args) throws IOException {
        //BufferedOutputStream 类的使用 缓冲输出流
        FileOutputStream fos = new FileOutputStream("test\\fos2.txt");
        FileInputStream fis = new FileInputStream("test\\fos2.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        BufferedInputStream bis = new BufferedInputStream(fis);

        byte[] bys = {65,66,67,68,69};
        bos.write(bys);
        bos.write("hello".getBytes());
        bos.flush();
        //将数据读入到fis文件中
        int len = 0;
        byte[] by = new byte[1024];
        while ((len = bis.read(by)) != -1) {
            System.out.println(new String(by,0 ,len)); //String，可以将一个字节数组转换为String字符串类型
        }

        //关闭资源
        fis.close();
        fos.close();

    }
}
