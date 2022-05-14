package compile;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-05-14
 * Time: 15:02
 * Description: 用户提交的代码
 */
public class Question {
    private String code;// 前端传过来的代码
    private String stdin;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStdin() {
        return stdin;
    }

    public void setStdin(String stdin) {
        this.stdin = stdin;
    }
}
