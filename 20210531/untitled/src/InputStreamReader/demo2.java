package InputStreamReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class demo2 {
    public static void main(String[] args) throws IOException {
        //从文件中复制一个Java文件，用FileReader
        FileReader fr = new FileReader("untitled\\demo1.java");
        FileWriter fw = new FileWriter("untitled\\copy.java");

        //复制文件-- 同样的复制数据也有两种方法，一个就是字符，另一个就是数据，还有一个就是加缓冲区
        int len;
        char[] chs = new char[1024];
        while((len = fr.read(chs)) != -1) {
            fw.write(chs);
        }

        fw.close();
        fr.close();
    }
}
