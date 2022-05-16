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
        if (id == null || id.equals("")) {
            List<Problem> list = problemDAO.selectAll();
            String respString = objectMapper.writeValueAsString(list); // 将表中的数据，转换为json格式的数据
            resp.getWriter().write(respString);
        } else {
            Problem problem = problemDAO.selectOne(Integer.parseInt(id));
            String respString = objectMapper.writeValueAsString(problem);
            resp.getWriter().write(respString);
        }
    }
}
