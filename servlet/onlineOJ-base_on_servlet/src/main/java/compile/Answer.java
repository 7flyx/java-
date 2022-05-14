package compile;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-05-14
 * Time: 14:59
 * Description: 编译运行后的结果
 */
public class Answer {
    private int error; // 状态码：0-》成功，1-》编译错误，2-》运行出错（可能是超时，可能是结果不对）
    private String stderr;
    private String stdout;
    private String reason;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getStderr() {
        return stderr;
    }

    public void setStderr(String stderr) {
        this.stderr = stderr;
    }

    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "compile.Answer{" +
                "error=" + error +
                ", stderr='" + stderr + '\'' +
                ", stdout='" + stdout + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
