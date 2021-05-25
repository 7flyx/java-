package demo1;

import java.io.File;

public class FileDemo05 {
    public static void main(String[] args) {
        //File的递归遍历调用，拿到路径下的所有文件的绝对路径
        File f1 = new File("E:\\javaSe_test\\tmp");

        //递归遍历--原理就跟之前写的勒索病毒加密文件，是一样的
        FindAllFilePath(f1);
    }

    public static void FindAllFilePath(File f) {
        //首先拿到当前路径的所有文件夹以及文件
        File[] FileArray = f.listFiles();

        //递归调用，这里判断一下该路径下是否为空
        if (FileArray != null) {
            for(File file : FileArray) {
                if (file.isDirectory()) {
                    //是文件夹，就再次进入
                    FindAllFilePath(file);
                } else {
                    //是文件，就输出绝对路径
                    System.out.println(file.getAbsoluteFile());
                }
            }
        }
    }
}
