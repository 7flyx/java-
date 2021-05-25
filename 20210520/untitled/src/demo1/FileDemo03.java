package demo1;

import java.io.File;
import java.io.IOException;

public class FileDemo03 {
    public static void main(String[] args) throws IOException {
        //File 类的一下应用---例如得到当前文件的绝对路径啊，判断是不是文件或者是文件夹啊等等
        /*
             String getAbsolutePath()
             String getName()
             String getPath()
             boolean isDirectory()
             boolean isFile()
             ......
         */

        File f1 = new File("E:\\javaSe_test\\tmp");
        System.out.println(f1.getAbsoluteFile()); //得到绝对路径
        System.out.println(f1.getName());
        System.out.println(f1.getPath());
        System.out.println(f1.isDirectory());
        System.out.println(f1.isFile());

        System.out.println("------------");

        String[] s = f1.list(); //public String[] list(),   将当前路径下的所有文件以及文件夹，包装成String数组
        File[] file = f1.listFiles();  //将当前路径下的所有文件以及文件夹，以文件数组的形式进行输出
        for (String i : s) {
            System.out.println(i);
        }

        for (File i : file) {
            //这个拿到的其实就是每个文件的绝对路径
           // System.out.println(i);  //也可以   i.getName(); 只拿到文件名
            //与上面的区别在于  这里拿到的是File类型，可以进行判断是文件还是文件夹的功能
            if(i.isDirectory()) {
                System.out.println(i.getName() + "文件夹");
            } else {
                System.out.println(i.getName() + "文件");
            }

        }

    }
}
