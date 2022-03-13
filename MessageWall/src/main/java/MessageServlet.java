import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
class Message {
    public String from;
    public String to;
    public String message;
}
@WebServlet("/message")
public class MessageServlet extends HttpServlet {
    private List<Message> messages=new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        resp.setContentType("application/json;charset=utf8");
        // 获取到消息列表.
        // 此处要做的事情, 就是把 当前 messages 这个数组, 转成 JSON 格式, 返回给浏览器即可~
        ObjectMapper objectMapper = new ObjectMapper();
        // 就是把 Java 代码中的对象, 转成 JSON 字符串~ 当前 message 是一个 List, 得到的结果也就是一个 JSON 数组
        String jsonString = objectMapper.writeValueAsString(messages);
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost");
        resp.setContentType("text/html;charset=utf8");
        // 这里要进行的操作, 就是读取请求中的 JSON 字符串, 转成 Message 对象. 然后往 messages 进行添加即可
        ObjectMapper objectMapper = new ObjectMapper();
        Message message = objectMapper.readValue(req.getInputStream(), Message.class);
        messages.add(message);
        resp.getWriter().write("插入成功");
    }
}
