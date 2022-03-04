import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-04
 * Time: 20:37
 * Description:
 */
@WebServlet("/guessNumber2")
public class GuessNumberServlet2 extends HttpServlet {
    private int toGuessNumber = 0; // 被猜测的数据
    // 模板引擎对象 templateEngine
    private TemplateEngine engine = new TemplateEngine();

    // thymeleaf初始化
    @Override
    public void init() throws ServletException {
        // 解析器，传入的参数是 上下文对象。引擎的作用就是 加载模板文件
        // 上下文对象：一个webapp 应用，都有一个ServletContext对象，一个webapp应用下可以有很多的servlet
        //这些servlet可以通过 上下文对象 进行传递数据
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(getServletContext());
        // 对 解析器 设置一些参数
        resolver.setPrefix("/WEB-INF/template"); //设置模板文件的路径
        resolver.setSuffix(".html"); //设置模板文件的后缀名
        resolver.setCharacterEncoding("utf-8"); //编码格式
        engine.setTemplateResolver(resolver); //将 解析器 加载在模板引擎中去
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        this.toGuessNumber = (int)(Math.random() * 100) + 1;
        // 拿到 WebContext对象，实则就是一个键值对结果，用于设置模板文件的待替换的数据
        WebContext webContext = new WebContext(req, resp, getServletContext());
        // 通过 setVariable 方法可以设置替换的数据
        //因为这里只是获取原始的html文件，不需要设置替换参数

        //用模板引擎 进行页面渲染，也就是替换参数。这里的文件名不需要写后缀，因为在初始化时已经设置了
        engine.process("guessNumber", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        int guessNum = Integer.parseInt(req.getParameter("guessNum"));
        String res = "";
        if (guessNum > this.toGuessNumber) {
            res = "猜高了";
        } else if (guessNum < this.toGuessNumber) {
            res = "猜低了";
        } else {
            res = "猜对了";
        }

        // 获取WebContext对象
        WebContext webContext = new WebContext(req, resp, getServletContext());
        webContext.setVariable("result", res); //参数名是html文件里面的th:text="${result}"
        // 用引擎 进行页面渲染
        engine.process("guessNumber", webContext, resp.getWriter());
    }
}
