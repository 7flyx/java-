package demo2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutStreamDemo {
    public static void main(String[] args) throws IOException {
        //字节输出流（写数据）
        FileOutputStream fos = new FileOutputStream("untitled\\test.txt");
        fos.write(97);  //以字节流进行写入（二进制）
       
        fos.close(); //关闭相关的系统调用
    }
}
