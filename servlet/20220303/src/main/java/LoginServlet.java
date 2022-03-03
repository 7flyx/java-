import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebFault;
import java.io.IOException;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-03
 * Time: 13:06
 * Description:
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");

        //从请求中拿到相应的数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //简单的判断密码是否正确等操作
        if (username == null || password == null) {
            resp.getWriter().write("用户名或者密码错误");
            return;
        }

        //获取HttpSession对象，
        //如果没有该sessionID值，就新建一个，并且还会建一个httpSession对象，以键值对的形式进行存储
        HttpSession session = req.getSession(true);
        session.setAttribute("visitCount", 0); //在session对象里面可添加键值对，键值对的内容是自己建立的
        //重定向到index页面
        resp.sendRedirect("index");
    }

}
