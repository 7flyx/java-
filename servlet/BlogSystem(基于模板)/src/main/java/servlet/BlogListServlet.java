package servlet;

import model.Blog;
import model.BlogDao;
import model.User;
import model.UserDao;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-10
 * Time: 18:57
 * Description: 基于thymeleaf模板引擎实现
 */
@WebServlet("/blog_list.html")
public class BlogListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        req.setCharacterEncoding("utf8");
        User user = Util.checkLogin(req); //从HttpSession中获取user对象
        if (user == null) {
            resp.sendRedirect("blog_login.html"); //重定向到登录页面
            return;
        }
        BlogDao blogDao = new BlogDao();
        List<Blog> blogList = blogDao.selectAllOfUser(user.getUserId()); // 后续可好一点的就是 当前页面只返回部分博客，涉及到分页等功能
        TemplateEngine engine = (TemplateEngine) getServletContext().getAttribute("engine");
        // 用WebContext 这个键值对对象 将数据进行关联
        WebContext webContext = new WebContext(req, resp, getServletContext());
        webContext.setVariable("blogs", blogList); // 将所有的结果在WebContext进行关联
        webContext.setVariable("user", user); //将个人信息也添加进去
        engine.process("blog_list", webContext, resp.getWriter()); //将数据填充进模板中
    }
}
