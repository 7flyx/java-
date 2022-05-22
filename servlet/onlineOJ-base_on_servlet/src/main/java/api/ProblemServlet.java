package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Util;
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
import java.util.List;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-05-15
 * Time: 12:01
 * Description: 查找题目
 */
@WebServlet("/problem")
public class ProblemServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        resp.setContentType("application/json;charset=utf8");
        User user = Util.checkLogin(req);
        if (user == null) {
            resp.setStatus(304); // 重定向到登录页面
            return;
        }
        ProblemDAO problemDAO = new ProblemDAO();
        String id = req.getParameter("id");
        String listID = req.getParameter("listID"); // 具体的一个集合内里面的题目
        String returnCode = req.getParameter("returnCode");
        if (id != null && !id.equals("")){
            Problem problem = problemDAO.selectOne(Integer.parseInt(id));
            String savedCode = SaveCodeDAO.isSavedCode(problem.getId(), user.getUserID());
            if(savedCode != null && (returnCode == null || returnCode.equals("false"))) { // 如果之前提交过成功通过的代码，那就拿取出来
                problem.setTemplateCode(savedCode);
            } else if (returnCode != null && returnCode.equals("true")) { // 只保留模板代码，节省网络带宽
                problem.setTitle("");
                problem.setLevel("");
                problem.setDescription("");
            }
            problem.setTestCase("");
            String respString = objectMapper.writeValueAsString(problem);
            resp.getWriter().write(respString);
        } else if (listID != null && !listID.equals("")) {
            List<Problem> problems = problemDAO.selectOfGather(Integer.parseInt(listID));
            String s = objectMapper.writeValueAsString(problems);
            resp.getWriter().write(s);
        }
//        System.out.println(listID);
    }
}
