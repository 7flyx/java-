package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.Problem;
import dao.ProblemDAO;

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
        ProblemDAO problemDAO = new ProblemDAO();
        String id = req.getParameter("id");
        String listID = req.getParameter("listID"); // 具体的一个集合内里面的题目
        if (id != null && !id.equals("")){
            Problem problem = problemDAO.selectOne(Integer.parseInt(id));
            String respString = objectMapper.writeValueAsString(problem);
            resp.getWriter().write(respString);
        } else if (listID != null && !listID.equals("")) {
            List<Problem> problems = problemDAO.selectOfGather(Integer.parseInt(listID));
            String s = objectMapper.writeValueAsString(problems);
            resp.getWriter().write(s);
        }
        System.out.println(listID);
    }
}
