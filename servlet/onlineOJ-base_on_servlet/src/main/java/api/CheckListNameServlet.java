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
 * Date: 2022-05-20
 * Time: 16:40
 * Description: 检查合集名称是否存在
 */
@WebServlet("/checkListName")
public class CheckListNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");
        String listName = req.getParameter("listName");
        System.out.println("listname " + listName);
        if (listName == null || listName.equals("")) {
            resp.setStatus(403);
            resp.getWriter().write("合集不存在");
            return;
        }
        int classifyNumber = ListDAO.checkList(listName);
        System.out.println(classifyNumber);
        if (classifyNumber != -1) {
            resp.setStatus(200);
            resp.getWriter().write(classifyNumber +"");
        } else {
            resp.setStatus(403);
            resp.getWriter().write("合集不存在");
        }
    }
}
