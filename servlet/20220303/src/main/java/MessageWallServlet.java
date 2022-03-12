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
 * Date: 2022-03-04
 * Time: 22:34
 * Description: 基于模板引擎的表白墙
 */

class Message {
    public String from;
    public String to;
    public String message;
}

@WebServlet("/message")
public class MessageWallServlet extends HttpServlet {
    private List<Message> messageList = new ArrayList<>();
    // 模板引擎
    private TemplateEngine engine = new TemplateEngine();

    @Override
    public void init() throws ServletException {
        // 模板解析器
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(getServletContext());
        resolver.setPrefix("/WEB-INF/template/"); // 模板文件的路径
        resolver.setSuffix(".html"); // 模板文件的后缀名
        resolver.setCharacterEncoding("utf-8");
        engine.setTemplateResolver(resolver); // 将模板解析器放入模板引擎中
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        // 键值对的一个结果 WebContext
        WebContext webContext = new WebContext(req, resp, getServletContext());
        webContext.setVariable("messages", messageList); // 将结果存储在WebContext中
        engine.process("messageWall", webContext, resp.getWriter()); //将此写入响应
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8"); // 设置请求的编码方式
        Message msg = new Message();
        msg.from = req.getParameter("from");
        msg.to = req.getParameter("to");
        msg.message = req.getParameter("message");
        System.out.println(msg.from + " " + msg.to + " " + msg.message);
        messageList.add(msg); //存储在一张表中

        // 将结果存储在WebContext中 然后写入响应
        resp.setContentType("text/html;charset=utf8");
        WebContext webContext = new WebContext(req, resp, getServletContext());
        // 此处的messages，是跟html中的th:each="message : ${messages}" 对应的
        webContext.setVariable("messages", messageList);
        engine.process("messageWall", webContext, resp.getWriter());
    }
}
