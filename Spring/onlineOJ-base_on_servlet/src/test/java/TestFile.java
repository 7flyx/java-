import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-05-14
 * Time: 9:38
 * Description: 将一个文件数据 写入到另一个文件中
 */
public class Test1 {
    public static void main(String[] args) {
        String srcPath =  "D:/test1.txt";
        String descPath =  "D:/test2.txt";
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(srcPath);
            fileOutputStream = new FileOutputStream(descPath);
            while (true) {
                /*
                    此处read方法是用int接收数据的，原因有2：
                    1、read自身的返回值是byte，范围在[-128, 127]，但是我们只是需要读取数据而已，并不需要多读取的数据进行四则运算
                        所以这里我们只需要无符号数，范围在0~255即可
                    2、读取的文件末尾的时候，会读取到EOF（end of file），这个返回值是-1
                 */
                int ch = fileInputStream.read(); // 读取数据
                if (ch == -1) { // 文件读取完了
                    break;
                }
                fileOutputStream.write(ch); // 写入数据
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
