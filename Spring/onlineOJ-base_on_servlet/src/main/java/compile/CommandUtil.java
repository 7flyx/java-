import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-05-14
 * Time: 11:41
 * Description: 使用多进程来，运行java代码类
 */
public class CommandUtil {
    public static int run(String cmd, String stdoutPath, String stderrPath) {
        FileOutputStream stdoutTo = null;
        FileOutputStream stderrTo = null;
        InputStream stdoutFrom = null;
        InputStream stderrFrom = null;

        try {
            // 1、获取子进程
            Process process = Runtime.getRuntime().exec(cmd);

            if (stdoutPath != null) {
                // 2、标准输出流
                stdoutFrom = process.getInputStream();
                stdoutTo = new FileOutputStream(stdoutPath);
                while (true) {
                    int ch = stdoutFrom.read();
                    if (ch == -1) {
                        break;
                    }
                    stdoutTo.write(ch);
                }
            }

            if (stderrPath != null) {
                // 3、标准错误流
                stderrFrom = process.getErrorStream();
                stderrTo = new FileOutputStream(stderrPath);
                while (true) {
                    int ch = stderrFrom.read();
                    if (ch == -1) {
                        break;
                    }
                    stderrTo.write(ch);
                }
            }

            // 4、进程等待-等待子进行执行完成之后，才能执行后续的代码
            int exitCode = process.waitFor();
            return exitCode; // 将状态码返回去
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (stderrFrom != null) {
                try {
                    stderrFrom.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (stderrTo != null) {
                try {
                    stderrTo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (stdoutFrom != null) {
                try {
                    stdoutFrom.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (stdoutTo != null) {
                try {
                    stdoutTo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        CommandUtil.run("notepad", "stdout.txt", "stderr.txt");
    }
}
