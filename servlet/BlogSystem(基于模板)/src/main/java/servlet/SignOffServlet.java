package servlet;

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
 * Date: 2022-03-11
 * Time: 18:17
 * Description: 退出登录
 */
@WebServlet("/sign_off")
public class SignOffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) { //当前就是处于为登录状态 或会话值过期等
            resp.sendRedirect("blog_login.html");
            return;
        }
        session.removeAttribute("user"); //删除相应的会话值即可
        resp.sendRedirect("blog_login.html");
    }
}
