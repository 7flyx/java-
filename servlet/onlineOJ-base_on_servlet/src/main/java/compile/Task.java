package compile;

import common.FileUtil;

import java.io.File;
import java.util.UUID;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-05-14
 * Time: 14:58
 * Description: 一个用户提交的代码，这个事务
 */
public class Task {
    // 通过一个常量，来存放编译运行时所涉及到一些目录
    // 这里需要用文件来保存信息，还有一个原因就是为了实现“进程间通信”
    // 实现进程间通信，在Linux系统中，有很多种方式，比如：管道、信号量、信号、文件、socket等
    private String WORK_DIR; // 工作目录
    private String CLASS; // 类名
    private String CODE; // .java文件名
    private String STDIN; // 标准输入
    private String STDOUT; // 标准输出
    private String STDERR; // 标准错误
    private String COMPILE_ERROR; // 编译出错信息文件

    public Task() {
        WORK_DIR = "./tmp/" + UUID.randomUUID().toString() + "/";
        CLASS = "Test";
        CODE = WORK_DIR + CLASS + ".java";
        STDIN = WORK_DIR + "stdin.txt";
        STDOUT = WORK_DIR + "stdout.txt";
        STDERR = WORK_DIR + "stderr.txt";
        COMPILE_ERROR = WORK_DIR + "compile_error.txt";
    }

    // 完成遍历运行，并返回结果
    public Answer compileAndRun(Question question) {
        if (question == null) {
            return new Answer();
        }
        Answer answer = new Answer();
        // 0、准备好存放临时文件的目录
        File workFile = new File(WORK_DIR);
        if (!workFile.exists()) { // 不存在当前路径，就创建出来
            workFile.mkdirs(); // mkdirs，可创建多级目录
        }
        // 1、question里面code是String，需要先搞成一个.java文件，而类名统一规定为Solution
        FileUtil.writeFile(CODE, question.getCode());
        // 2、创建子进程，编译代码，可能会编译出错，然后可以用一个专门的文件
        //      来保存编译错误的信息 compileError.txt
        String compileCmd = String.format("javac -encoding utf8 %s -d %s", CODE, WORK_DIR);
        CommandUtil.run(compileCmd, null, COMPILE_ERROR);
        String compileError = FileUtil.readFile(COMPILE_ERROR);
        if (!compileError.equals("")) { // 不等于空字符串，说明stderr文件有数据,也就是说编译出错了
            // System.out.println("String：" + compileError);
            System.out.println("编译错误");
            answer.setError(1); // 1表示编译出错
            answer.setReason(compileError); // 编译出错的原因
            return answer;
        }
        // 3、创建子进程，运行代码，可能会运行超时或结果不对，运行结果数据在 stdout.txt, stderr.txt
        String runCmd = String.format("java -classpath %s %s", WORK_DIR, CLASS);
        CommandUtil.run(runCmd, STDOUT, STDERR);
        String stderrInfo = FileUtil.readFile(STDERR);
        if (!stderrInfo.equals("")) {
            System.out.println("运行错误");
            answer.setError(2); // 2表示运行出错
            answer.setReason(stderrInfo);
            return answer;
        }
        // 4、整个上述全部信息，返回Answer
        String s = FileUtil.readFile(STDOUT);
        if (s.contains("测试通过")) {
            answer.setError(0);
        } else {
            answer.setError(2);
        }
        answer.setReason(s);
        return answer;
    }

    public static void main(String[] args) {
        Task task = new Task();
        Question question = new Question();
        question.setCode("public class Solution {\n" +
                "    public static void main(String[] args) {\n" +
                "        int num1 = 3;\n" +
                "        int num2 = 4;\n" +
                "        int num3 = 5;\n" +
                "        System.out.println( \"\" + (num1 < num2) + num3);\n" +
                "    }\n" +
                "}");
        Answer answer = task.compileAndRun(question);
        System.out.println(answer);
    }
}
