package servlet;

import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-12
 * Time: 17:35
 * Description: 用户返回user用户
 */
public class Util {
    public static User checkLogin(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null) { //当前未登录状态或者会话值过期等
            return null;
        }
        User user = (User) session.getAttribute("user"); //从HttpSession对象中拿取user对象
        return user;
    }
}
