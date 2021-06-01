package BufferedInputStream;

import java.io.*;

public class demo1 {
    public static void main(String[] args) throws IOException {
        //字符缓冲输入输出流
        BufferedReader bos = new BufferedReader(new FileReader("untitled\\copy.java"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("untitled\\copy2.java"));

        //复制数据
        int len;
        char[] chs = new char[1024];

        while ((len = bos.read(chs)) != -1) {
            bw.write(chs);
        }

        //关闭资源
        bos.close();
        bw.close();
    }
}
