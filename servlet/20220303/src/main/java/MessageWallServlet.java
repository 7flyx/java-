import org.thymeleaf.TemplateEngine;
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
 * Description:
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
        resolver.setCharacterEncoding("uft-8");
        engine.setTemplateResolver(resolver);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
