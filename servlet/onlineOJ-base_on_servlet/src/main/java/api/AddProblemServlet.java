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

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-05-20
 * Time: 17:53
 * Description: 新增题目
 */
@WebServlet("/addProblem")
public class AddProblemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");
        ObjectMapper objectMapper = new ObjectMapper();
        Problem problem = objectMapper.readValue(req.getInputStream(), Problem.class);
//        System.out.println(problem.getClassify());
        ProblemDAO problemDAO = new ProblemDAO();
        int listID = problemDAO.insert(problem); // 返回值是一个合集的id
        if (listID == 0) { // 插入失败
            resp.setStatus(403);
        } else { // 插入成功
            resp.setStatus(200);
            resp.getWriter().write(listID); // 将ret返回去
//            System.out.println(listID);
        }
    }


}
