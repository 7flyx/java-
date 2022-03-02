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
 * Time: 11:26
 * Description:
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        // 此时是get方法，而不是post
        //首先根据sessionID拿取HttpSession对象
        HttpSession session = req.getSession(false); //如果没有该sessionID值，则会返回false
        if(session == null) {
            //理论上，这里需要跳转到登录页面
            resp.getWriter().write("<h3>抱歉，当前您还未登录！</h3>");
            return;
        }
        //拿到session里面的键值对，进行操作
        Integer visitCount = (Integer) session.getAttribute("visitCount");
        visitCount += 1;
        session.setAttribute("visitCount", visitCount); //再将修改好的值，重新放回键值对中
        //将最新的参数值，写入响应中返回
        resp.getWriter().write("<h3>visitCount: " + visitCount + "</h3>");
    }
}
