package api;

import dao.ListDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-05-19
 * Time: 15:41
 * Description: 添加题目合集
 */
@WebServlet("/addList")
public class AddListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");
        resp.setCharacterEncoding("utf8");
        String listName = req.getParameter("addListName");
        boolean result = ListDAO.addList(listName);
        if (!result) { // 没有插入成功的情况，说明数据库已经有相同的合集名称了
            resp.setStatus(403);
            return;
        }
        resp.setStatus(200); // 插入成功的情况
    }
}
