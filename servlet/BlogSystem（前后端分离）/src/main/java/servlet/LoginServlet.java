package servlet;

import model.User;
import model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-15
 * Time: 21:24
 * Description: 登录操作
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username == null || password == null || "".equals(username) || "".equals(password)) {
            // 用户名或密码缺失的情况
            String html = "<h3>用户名或密码不正确！</h3>";
            resp.getWriter().write(html);
            return;
        }
        // 与数据库里面的数据进行判断
        UserDao userDao = new UserDao();
        User user = userDao.getUserByName(username);
        if (!user.getPassword().equals(password)) {
            String html = "<h3>用户名或密码不正确！</h3>";
            resp.getWriter().write(html);
            return;
        }
        // 用户名和密码正确的情况下
        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);
        resp.sendRedirect("blog_list.html"); // 重定向到博客列表页
    }
}
