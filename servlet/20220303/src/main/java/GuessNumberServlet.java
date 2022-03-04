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
 * Time: 19:51
 * Description:
 */

@WebServlet("/guessNumber")
public class GuessNumberServlet extends HttpServlet {
    private int toGuessNumber = 0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //返回初始的界面
        resp.setContentType("text/html;charset=utf8");
        toGuessNumber = (int)(Math.random() * 100) + 1; //生成1~100之间的随机数
        //以字符串的形式 返回html文件
        resp.getWriter().write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>猜数字</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div>\n" +
                "        <form action=\"guessNumber\" method=\"POST\">\n" +
                "            <input type=\"text\" name=\"guessNum\">\n" +
                "            <input type=\"submit\" value=\"猜\">\n" +
                "        </form>\n" +
                "        <div>\n" +
                "            结果：\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        // 拿取请求中 用户输入的number
        int guessNum = Integer.parseInt(req.getParameter("guessNum"));
        String res = "";
        if (guessNum > this.toGuessNumber) {
            res = "猜高了";
        } else if (guessNum < this.toGuessNumber) {
            res = "猜低了";
        } else {
            res = "猜对了";
        }
        //将结果 以字符串的形式 拼接在html代码中
        resp.getWriter().write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>猜数字</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div>\n" +
                "        <form action=\"guessNumber\" method=\"POST\">\n" +
                "            <input type=\"text\" name=\"guessNum\">\n" +
                "            <input type=\"submit\" value=\"猜\">\n" +
                "        </form>\n" +
                "        <div>\n" +
                "            结果：\n" + res +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>");
    }
}
