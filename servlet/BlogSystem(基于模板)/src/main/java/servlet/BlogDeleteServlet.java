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

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-14
 * Time: 17:18
 * Description: 删除博客功能
 */
@WebServlet("/blog_delete")
public class BlogDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        // 首先还是判断当前是否处于登录状态
        User user = Util.checkLogin(req);
        if (user == null) {
            resp.sendRedirect("blog_login.html");
            return;
        }
        // 判断请求中的blogID是否正确
        req.setCharacterEncoding("utf8");
        String blogId = req.getParameter("blogId");
        if (blogId == null || blogId.equals("")) {
            resp.getWriter().write("<h3>当前博客ID非法</h3>");
            return;
        }
        BlogDao blogDao = new BlogDao();
        Blog blog = blogDao.selectOne(Integer.parseInt(blogId));
        if (blog == null) {
            resp.getWriter().write("<h3>当前博客并不存在</h3>");
            return;
        }
        // 判断blogID是不是属于当前登录用户的
        if (blog.getUserId() != user.getUserId()) {
            resp.getWriter().write("<h3>抱歉，您暂无权限！</h3>");
            return;
        }
        // 删除博客，并跳转到博客列表页
        blogDao.deleteOne(Integer.parseInt(blogId));
        resp.sendRedirect("blog_list.html"); //博客列表页
    }
}
