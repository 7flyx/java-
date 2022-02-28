import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

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
 * Date: 2022-02-27
 * Time: 8:43
 * Description: 校园表白墙java代码部分
 */
class Message {
    public String from;
    public String to;
    public String message;
}
@WebServlet("/hello")
public class MessageWall extends HttpServlet {
    //存储客户端发送过来的数据
    private List<Message> messages = new ArrayList<>();

    // 当客户端那边发送http请求是get方法时，则会调用这方法，一般都是需要服务器向客户端发送一些数据
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        resp.setContentType("text/html;charset=utf8");// 设置响应报文的编码方式
        //约定好前后端交互的形式，比如请求和响应的body部分，使用什么格式的数据？例如json、xml等
        ObjectMapper objectMapper = new ObjectMapper(); //Jackson库的对象，用于处理json文件
        String message = objectMapper.writeValueAsString(messages); //参数是数组，返回的结果也是json数组的形式，但是是字符串表示的
        resp.getWriter().write(message); //将转换好的json文件发送给客户端
    }

    //当客户端那边发送http请求是post方法时，将会调用这个方法，一般post就是客户端向服务器提供数据，用于存储的
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost");
        resp.setContentType("text/html;charset=utf8");
        ObjectMapper objectMapper = new ObjectMapper();
        //从请求的body中读取json数据，可使用getInputStream方法

        Message message = objectMapper.readValue(req.getInputStream(), Message.class);
        messages.add(message);
        resp.getWriter().write("插入成功");
    }
}
