package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Util;
import compile.Answer;
import compile.Question;
import compile.Task;
import dao.Problem;
import dao.ProblemDAO;
import dao.SaveCodeDAO;
import dao.User;

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
        public int userID; // 用户ID
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
        resp.setCharacterEncoding("utf8");
        resp.setContentType("application/json?charset=utf8");
        User user = Util.checkLogin(req);
        if (user == null) {
            resp.setStatus(304); // 重定向到登录页面
            return;
        }
        CompileResponse compileResponse = new CompileResponse();
        // 1、解析请求中的body正文数据
        String body = readBody(req);
        CompileRequest compileRequest = objectMapper.readValue(body, CompileRequest.class);
        // 2、先从数据库读取当前题目的信息
        ProblemDAO problemDAO = new ProblemDAO();
        Problem problem = problemDAO.selectOne(compileRequest.id);
        if (problem == null) {
            compileResponse.error = 3; // 没有找到题目
            compileResponse.reason = "没有找到该题目：id=" + compileRequest.id;
            String response = objectMapper.writeValueAsString(compileResponse);
            resp.getWriter().write(response);
            return;
        }
        int packageTail = compileRequest.code.indexOf("class Solution"); // 定位类名，类名以前的可能是导包的数据，分割出来
        if (packageTail == -1) {
            compileResponse.error = 3; // 没有找到题目
            compileResponse.reason = "代码没有找到Solution类";
            String response = objectMapper.writeValueAsString(compileResponse);
            resp.getWriter().write(response);
            return;
        }
        String packageData = compileRequest.code.substring(0, packageTail); // 左闭右开区间
        String userCode = compileRequest.code.substring(packageTail);
        packageTail = problem.getTestCase().indexOf("public");// 定位测试方法中导入的包
        String packageData2 = "";
        String testCode = problem.getTestCase();
        if (packageTail != -1) {
            packageData2 = problem.getTestCase().substring(0, packageTail);
            testCode = problem.getTestCase().substring(packageTail);
        }
//        3、 拼接测试方法和用户提交的代码
        String finalCode = packageData + packageData2 + userCode + testCode;
        System.out.println(finalCode);
        // 4、跑测试方法。得到运行结果
        Question question = new Question();
        question.setCode(finalCode);
        Task task = new Task();
        Answer answer = task.compileAndRun(question);
        // 5、填写响应数据
        compileResponse.error = answer.getError();
        compileResponse.reason = answer.getReason();
        compileResponse.stdout = answer.getStdout();
        System.out.println(compileResponse.reason + " " + compileResponse.stdout);
        String s = objectMapper.writeValueAsString(compileResponse);
        resp.getWriter().write(s);

        //6. 假设代码运行成功，需要将用户代码保存到数据库
        SaveCodeDAO.saveCodeToDB(compileRequest.id, compileRequest.code, user.getUserID());
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
