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
 * Date: 2022-03-16
 * Time: 19:18
 * Description: 写博客，存储提交过来的数据
 */
@WebServlet("/blogSubmit")
public class BlogSubmitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");
        User user = Util.checkLogin(req); //拿到用户
        if (user == null) {
            // 并不是ajax调用的这个方法，可以直接重定向
            resp.sendRedirect("blog_login.html");
            return;
        }

        String title = req.getParameter("title"); // 标题
        String content = req.getParameter("content");  // 内容
        if (title == null || content == null || "".equals(title) || "".equals(content)) {
            resp.getWriter().write("<h3>标题或内容为空</h3>");
            return;
        }
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
        blog.setContent(content);
        blog.setUserId(user.getUserId());
        BlogDao blogDao = new BlogDao();
        blogDao.insert(blog);
        // 重定向到列表页
        resp.sendRedirect("blog_list.html");
    }
}
