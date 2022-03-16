package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Blog;
import model.BlogDao;
import model.User;
import model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-16
 * Time: 19:46
 * Description: 返回用户的个人信息
 */
@WebServlet("/getUser")
public class GetUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf8");
        req.setCharacterEncoding("utf8");
        User user = Util.checkLogin(req);
        String blogId = req.getParameter("blogId"); //拿到博客id
        if (user == null) {
            // 还没登录的状态，返回403，交给js进行重定向
            resp.setStatus(403);
            return;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        if(blogId == null) {
            // 返回登录账号的个人信息
            String userString = objectMapper.writeValueAsString(user);
            resp.getWriter().write(userString);
            return;
        }
        // 获取博客博主的个人信息
        BlogDao blogDao = new BlogDao();
        Blog blog = blogDao.selectOne(Integer.parseInt(blogId));
        // 以json格式的数据写入
        UserDao userDao = new UserDao();
        User curUser = userDao.getUserById(blog.getUserId()); // 博客文章中拿取用户ID
        String userString = objectMapper.writeValueAsString(curUser);
        resp.getWriter().write(userString);
    }
}
