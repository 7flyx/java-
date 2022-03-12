package servlet;

import model.Blog;
import model.BlogDao;
import model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

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
 * Time: 15:12
 * Description:
 */
@WebServlet("/blog_details.html")
public class BlogDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        User user = Util.checkLogin(req); //从HttpSession对象中拿取user对象
        // 先从请求中获取到blogID的值，然后从数据库中拿取相应的文章数据即可
        String blogId = req.getParameter("blogId");
        if (blogId == null || blogId.equals("")) {
            String html = "<h3>blogID有误!</h3>";
            resp.getWriter().write(html);
            return;
        }
        BlogDao blogDao = new BlogDao();
        Blog blog = blogDao.selectOne(Integer.parseInt(blogId));
        if (blog == null) {
            String html = "<h3>blogID不存在!</h3>";
            resp.getWriter().write(html);
            return;
        }

        // 模板引擎
        TemplateEngine engine = (TemplateEngine) getServletContext().getAttribute("engine");
        WebContext webContext = new WebContext(req, resp, getServletContext());
        webContext.setVariable("blog", blog);
        webContext.setVariable("user", user); //将个人信息也加载进去
        engine.process("blog_details", webContext, resp.getWriter());
    }
}
