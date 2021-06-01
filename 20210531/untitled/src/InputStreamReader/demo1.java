package InputStreamReader;

import java.io.*;

public class demo1 {
    public static void main(String[] args) throws IOException{
        //字符输入输出流
        //两个类的读写与FileOutputSream类的差不多，几乎都是沿用了这几个类的方法
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("untitled\\test.txt"));

        //写入数据
        osw.write("hello world");
        osw.flush();
        osw.close();

        InputStreamReader isr = new InputStreamReader(new FileInputStream("untitled\\test.txt"));

        //读取数据--数组的党方式去读取，还有就是一个字符一个字符的去读取
        int len;
        char[] chs = new char[1024];
        while ((len = isr.read(chs)) != -1) {
            System.out.print(new String(chs,0,len));
        }

        isr.close();

    }
}
