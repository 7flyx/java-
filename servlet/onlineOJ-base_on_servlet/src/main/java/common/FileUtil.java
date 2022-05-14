package common;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-05-14
 * Time: 16:07
 * Description: 读文件和写文件操作
 */
public class FileUtil {
    public static String readFile(String filePath) {
        if (filePath == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        // 通过字符流读取文件
        try (FileReader fileReader = new FileReader(filePath)) {
            while (true) {
                int ch = fileReader.read();
                if (ch == -1) {
                    break;
                }
                sb.append((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void writeFile(String descFile, String content) {
        if (descFile ==  null || content == null) {
            return;
        }
        try(FileWriter fileWriter = new FileWriter(descFile)) {
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileUtil.writeFile("d:/test.txt", "hello world");
        System.out.println(FileUtil.readFile("d:/test.txt"));

    }
}
