import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-05-14
 * Time: 10:35
 * Description: 多进程的创建
 */
public class TestExec {
    public static void main(String[] args) {
        // 切记这里的Runtime是lang包下的
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        InputStream stdOutStream = null;
        FileOutputStream stdOutTo = null;
        InputStream stdErrStream = null;
        FileOutputStream stdErrTo = null;
        try {
            process = runtime.exec("notepad"); // 获取一个进程
            // 上面的这一行代码，其实是已经执行了，但是执行的结果是没有单独的打印出来，而是输出到了
            // 标准输入 输出流和 标准错误流中去了，此时我们就需要单独的打印出这些数据
            
            // 1、标准输出流，通过input系列的方法，读取这个流里面的数据
            stdOutStream = process.getInputStream();
            stdOutTo = new FileOutputStream("stdout.txt"); // 存储标准输出流里面的数据
            while (true) {
                int ch = stdOutStream.read();
                if (ch == -1) { // 文件读取结束了
                    break;
                }
                stdOutTo.write(ch); // 写入到一个文件
            }
            
            // 2、标准错误流，通过input系列的方法，读取这个流里面的数据
            stdErrStream = process.getErrorStream();
            stdErrTo = new FileOutputStream("stderr.txt");
            while (true) {
                int ch = stdErrStream.read();
                if (ch == -1) {
                    break;
                }
                stdErrTo.write(ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stdOutStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                stdOutTo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                stdErrStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                stdErrTo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            // 进程等待，在此处就会一直等着，等上面代码所创建的子进程执行完成之后，才会走后续的代码
            // 返回的是状态码：返回的是0，表示子进程运行成功；不是0，表示是有点问题的
            int exitCode = process.waitFor();
            System.out.println(exitCode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
