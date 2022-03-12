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
 * Date: 2022-03-11
 * Time: 18:06
 * Description: 登录操作
 */
@WebServlet("/login.html")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");
        // 从请求中获取用户名和密码，并判断数据库中是否有
        String username = req.getParameter("username"); // 此处的参数就是html标签中的name属性
        String password = req.getParameter("password");
        if (username == null || password == null || username.equals("") || password.equals("")) {
            resp.getWriter().write("<h3>用户名或密码错误！</h3>");
            return;
        }
        UserDao userDao = new UserDao();
        User user = userDao.getUserByName(username); //根据用户名在数据查找用户信息
        if (user == null) {
            resp.getWriter().write("<h3>该用户名不存在</h3>");
            return;
        }
        if (!user.getPassword().equals(password)) {
            resp.getWriter().write("<h3>用户名或密码错误</h3>");
            return;
        }

        //建立会话值
        HttpSession session = req.getSession(true);
        session.setAttribute("user", user); //将user对象放入HttpSession中
        resp.sendRedirect("blog_list.html"); // 重定向到博客列表页
    }
}
