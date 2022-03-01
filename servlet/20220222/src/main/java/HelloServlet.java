import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-22
 * Time: 22:25
 * Description:
 */

class Message {
    public String from;
    public String to;
    public String message;
}

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8"); //设置http响应体的编码格式
        resp.setHeader("Refresh", "1"); //时间单位是秒
        resp.getWriter().write(System.currentTimeMillis() + "");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("你好啊 post");
        ObjectMapper objectMapper = new ObjectMapper(); //操作json文件的第三方库
        //req.getInputStream()方法是读取请求体的全部数据
        objectMapper.readValue(req.getInputStream(), Message.class);
    }
}
