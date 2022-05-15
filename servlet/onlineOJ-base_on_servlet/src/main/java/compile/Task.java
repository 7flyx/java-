package compile;

import common.FileUtil;

import java.io.File;

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
    private static final String WORK_DIR = "./tmp/"; // 工作目录
    private static final String CLASS = "Test"; // 类名
    private static final String CODE = WORK_DIR + CLASS + ".java"; // .java文件名
    private static final String STDIN = WORK_DIR + "stdin.txt"; // 标准输入
    private static final String STDOUT = WORK_DIR + "stdout.txt"; // 标准输出
    private static final String STDERR = WORK_DIR + "stderr.txt"; // 标准错误
    private static final String COMPILE_ERROR = WORK_DIR + "compile_error.txt"; // 编译出错信息文件

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
        if (s.contains("success")) {
            answer.setError(0);
        } else {
            answer.setError(2);
        }
        answer.setStdout(s);
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
