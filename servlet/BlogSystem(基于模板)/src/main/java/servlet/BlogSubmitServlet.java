package servlet;

import model.Blog;
import model.BlogDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-12
 * Time: 17:33
 * Description:
 */
@WebServlet("/blog_submit")
public class BlogSubmitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 首先还是判断是否处于登录状态
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");
        User user = Util.checkLogin(req);
        if (user == null) { //用户为登录的情况
            resp.sendRedirect("blog_login.html");
            return;
        }
        // 读取请求中的数据，并添加到数据库里面
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        if (title == null || content == null || title.equals("") || content.equals("")) { // 空标题或内容
            // 表示当前内容为空的情况，设置请求头，告诉下一个页面
            resp.sendRedirect("blog_edit.html"); //也可引入模板引擎，给模板添加相应的提示
            return;
        }
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setUserId(user.getUserId());
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
        // 添加进数据库
        BlogDao blogDao = new BlogDao();
        blogDao.insert(blog);
        // 跳转到博客列表页
        resp.sendRedirect("blog_list.html");
    }
}
