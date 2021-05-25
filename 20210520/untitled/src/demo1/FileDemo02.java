package demo1;

import java.io.File;
import java.io.IOException;

public class FileDemo02 {
    public static void main(String[] args) throws IOException {
        //创建文件以及创建文件夹
        /*
            boolean createNewFile()  文件
            boolean mkdir()   一级文件夹
            boolean mkdirs()  多级文件夹

            以上的方法，如果在当前目录下有名字一样的文件（夹），则会返回false
         */

        File f1 = new File("E:\\javaSe_test\\tmp");
        System.out.println(f1.mkdir());  //创建名叫 tmp  的文件夹 返回true或false

        File f2 = new File(f1,"test.txt");  //在f1的文件路径里面创建一个文本文件
        System.out.println(f2.createNewFile());

        File f3 = new File(f1,"dir1\\dir2\\dir3"); //创建多级目录，如果使用mkdir会报错的
        System.out.println(f3.mkdirs()); //多级目录，必须使用mkdirs（）


    }
}
