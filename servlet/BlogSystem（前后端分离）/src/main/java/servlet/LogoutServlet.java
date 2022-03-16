package servlet;

import model.User;

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
 * Date: 2022-03-16
 * Time: 19:40
 * Description: 注销操作
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        User user = Util.checkLogin(req);
        if (user != null) {
            HttpSession session = req.getSession(false);
            session.removeAttribute("user"); // 删除session中存储的会话
        }
        // 重定向到登录页面
        resp.sendRedirect("blog_login.html");
    }
}
