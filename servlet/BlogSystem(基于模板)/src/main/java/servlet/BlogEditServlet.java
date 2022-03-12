package servlet;

import model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-12
 * Time: 18:17
 * Description:
 */
@WebServlet("/blog_edit.html")
public class BlogEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 首先判断当前是不是登录状态
        User user = Util.checkLogin(req);
        if (user == null) {
            resp.sendRedirect("blog_login.html");
            return;
        }
        String reference = req.getHeader("Referer");
        resp.setContentType("text/html;charset=utf8");
        TemplateEngine engine = (TemplateEngine) getServletContext().getAttribute("engine");
        WebContext webContext = new WebContext(req, resp, getServletContext());
        webContext.setVariable("value", !reference.contains("blog_edit.html") ? 0 : 1); //1表示当前页面是上次输入内容空时 跳转过来的
        engine.process("blog_edit", webContext, resp.getWriter());
    }
}
