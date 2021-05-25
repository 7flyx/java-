package demo1;

import java.io.File;

public class FileDemo01 {
    public static void main(String[] args) {
        //文件File类的简单创建
        File f1 = new File("E:\\javaSe_test\\test.txt");
        System.out.println(f1); // E:\javaSe_test\test.txt

        File f2 = new File("E:\\javaSe_test","test.txt"); //父路径与子路径的拼接,两者之间不需要再拼接\\
        System.out.println(f2); //E:\javaSe_test\test.txt

        File f3 = new File("E:\\javaSe_test");
        File f4 = new File(f3,"test.txt");
        System.out.println(f4); //E:\javaSe_test\test.txt
    }
}
