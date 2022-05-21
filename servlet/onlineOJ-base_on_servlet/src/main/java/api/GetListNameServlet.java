package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Util;
import dao.ListDAO;
import dao.ListDAO.ListName;
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
 * Date: 2022-05-19
 * Time: 16:44
 * Description: 获取题目合集列表
 */
@WebServlet("/getListName")
public class GetListNameServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf8");
        resp.setContentType("application/json;charset=utf8");
        User user = Util.checkLogin(req);
        if (user == null) {
            resp.setStatus(304);; // 重定向到登录页面
            return;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<ListName> listAll = ListDAO.getListAll();
        String ans = objectMapper.writeValueAsString(listAll);
        resp.getWriter().write(ans);
    }
}
