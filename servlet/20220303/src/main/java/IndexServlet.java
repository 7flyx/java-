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
 * Date: 2022-03-03
 * Time: 13:33
 * Description:
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        //从请求中根据sessionID的中获取HttpSession对象，
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.getWriter().write("抱歉，您尚未登录");
            return;
        }
        Integer visitCount =  (Integer) session.getAttribute("visitCount"); //根据键，找到值
        visitCount += 1;
        session.setAttribute("visitCount", visitCount);

        resp.getWriter().write("visitCount: " + visitCount);
    }
}
