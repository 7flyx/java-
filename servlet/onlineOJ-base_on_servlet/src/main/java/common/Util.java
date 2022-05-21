package common;

import dao.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-05-21
 * Time: 17:36
 * Description: 检查cookie和session
 */
public class Util {
    public static User checkLogin(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null) {
           return null;
        }
        return (User) session.getAttribute("user");
    }
}
