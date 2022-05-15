package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.FileUtil;
import compile.Answer;
import compile.Question;
import compile.Task;
import dao.Problem;
import dao.ProblemDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-05-15
 * Time: 19:48
 * Description: 解析前端传过来的代码，并编译运行，判断正确性
 */
@WebServlet("/compile")
public class CompileServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    static class CompileRequest { // 前端传过来的数据
        public int id; // 题号
        public String code; // 代码
    }

    static class CompileResponse { // 需要往前端传的数据
        public int error;
        public String reason;
        private String stdout;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        resp.setContentType("application/json?charset=utf8");
        // 1、解析请求中的body正文数据
        String body = readBody(req);
        CompileRequest compileRequest = objectMapper.readValue(body, CompileRequest.class);
        // 2、先从数据库读取当前题目的信息
        ProblemDAO problemDAO = new ProblemDAO();
        Problem problem = problemDAO.selectOne(compileRequest.id);
        if (problem == null || compileRequest.code.length() == 0) { // 抹去前后空格后，是空字符串
            resp.setStatus(404);
            return;
        }
        //FileUtil.writeFile("./tmp/Test.java", problem.getTestCase()); // 测试主方法
        // 3、将用户提交的字符串类型的code，转换成.java文件
        //FileUtil.writeFile("./tmp/Solution.java", compileRequest.code); // 用户提交的代码
        String finalCode = problem.getTestCase() + compileRequest.code;
        // System.out.println(finalCode);
        // 4、跑测试方法。得到运行结果
        Question question = new Question();
        question.setCode(finalCode);
        Task task = new Task();
        Answer answer = task.compileAndRun(question);
        // 5、填写响应数据
        CompileResponse compileResponse = new CompileResponse();
        compileResponse.error = answer.getError();
        compileResponse.reason = answer.getReason();
        compileResponse.stdout = answer.getStdout();
        System.out.println(compileResponse.reason + " " + compileResponse.stdout);
        String s = objectMapper.writeValueAsString(compileResponse);
        resp.getWriter().write(s);
    }

    private String readBody(HttpServletRequest req) throws UnsupportedEncodingException {
        // 使用req中的流对象，获取字节流数据
        int contentLength = req.getContentLength(); // 单位是字节
        byte[] buffer = new byte[contentLength];
        try (InputStream inputStream = req.getInputStream()) {
            inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(buffer, "utf8"); // 明确指定编码格式
    }
}
