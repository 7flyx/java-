package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.User;
import dao.UserDAO;

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
 * Date: 2022-05-18
 * Time: 21:48
 * Description: 处理登录请求
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    static class MyUser {
        public String username;
        public String password;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");
        ObjectMapper objectMapper = new ObjectMapper();
        MyUser myUser = objectMapper.readValue(req.getInputStream(), MyUser.class);
        String username = myUser.username;
        String password = myUser.password;
        if (username == null || password == null || username.equals("") || password.equals("")) {
            resp.setStatus(403);
            return;
        }
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUser(username);
        if (user == null || !password.equals(user.getPassword())) {
            resp.setStatus(403);
//            System.out.println("账号或密码错误");
            return;
        }
        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);
        resp.setStatus(200); // 让前端进行重定向操作
    }
}
