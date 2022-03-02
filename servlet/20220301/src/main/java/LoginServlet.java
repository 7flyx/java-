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
 * Date: 2022-03-01
 * Time: 10:20
 * Description:
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        //客户端想要获取登录之后的页面
        String username = req.getParameter("username");
        String password = req.getParameter("password"); //拿取到请求中body部分的用户名和密码

        //判断用户名和密码的情况
        if (username == null || username.equals("") || password == null || password.equals("")) {
            resp.getWriter().write("<h3>用户名或密码不正确</h3>");
            return;
        }
        //判断用户名和密码的正确性，本因与数据库里面的数据进行判断，这里就简单一点
        if(!username.equals("admin") || !password.equals("123456")) {
            resp.getWriter().write("<h3>用户名或密码不正确</h3>");
            return;
        }

        //密码正确的情况，创建会话
        //getSession方法会做这些事，
        // 1、判断sessionId是否存在，不存在就新建sessionID和HttpSession对象
        // 2、如果sessionID存在，就拿到对应的value值
        // 3、在http响应中，设置set-cookie的这个header，value是sessionID
        HttpSession session = req.getSession(true); //根据请求中的sessionId来判断，如果没有该值就新建一个session
        session.setAttribute("visitCount", 0); //设置httpSession对象里面的键值对
        //登录成功后，应重新跳转到新的网页，这里使用重定向进行实现
        resp.sendRedirect("index");
    }
}
