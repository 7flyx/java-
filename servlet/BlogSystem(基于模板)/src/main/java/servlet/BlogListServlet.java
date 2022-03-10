package servlet;

import model.Blog;
import model.BlogDao;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private TemplateEngine engine = new TemplateEngine();
    private List<Blog> blogList = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        // 对模板引擎进行初始化，实则就是添加一个模板解析器
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(getServletContext()); // 参数是长下文对象
        resolver.setPrefix("WEB-INF/template/"); // 模板文件的路径
        resolver.setSuffix(".html"); //模板文件的后缀
        resolver.setCharacterEncoding("utf-8");
        engine.setTemplateResolver(resolver); // 将解析器加载到模板引擎中去
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        BlogDao blogDao = new BlogDao();
        blogList = blogDao.selectAll();
        // 用WebContext 这个键值对对象 将数据进行关联
        WebContext webContext = new WebContext(req, resp, getServletContext());
        webContext.setVariable("blogs", blogList); // 将所有的结果在WebContext进行关联
        engine.process("blog_list", webContext, resp.getWriter()); //将数据填充进模板中
    }
}
