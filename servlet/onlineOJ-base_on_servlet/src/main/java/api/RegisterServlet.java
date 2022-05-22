package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.User;
import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-05-18
 * Time: 22:40
 * Description: 注册账号
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");
        ObjectMapper objectMapper = new ObjectMapper();
        LoginServlet.MyUser myUser = objectMapper.readValue(req.getInputStream(), LoginServlet.MyUser.class);
        if (myUser.username == null || myUser.password == null || myUser.password.equals("") || myUser.username.equals("")) {
            resp.setStatus(403);
            return;
        }

        // 判断用户名是否已经存在了
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUser(myUser.username);
        if (user != null) { // 用户已经存在的情况下，直接返回
            resp.setStatus(403);
            resp.getWriter().write("用户名已存在");
            return;
        }
        userDAO.addUser(myUser.username, myUser.password, false); // 在数据库注册一个新用户
        resp.setStatus(200);
    }
}
