package demo1;

import java.io.File;
import java.io.IOException;

public class FileDemo04 {
    public static void main(String[] args) throws IOException {
        //File类的创建与删除
        File f1 = new File("untitled\\test.txt"); //相对路径
        System.out.println(f1.createNewFile());

        //删除
        System.out.println(f1.delete());

        File f2 = new File("untitled\\dir1");
        System.out.println(f2.mkdir()); //建文件夹
        System.out.println(f2.delete()); //删文件夹
    }
}
