package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.RespectBinding;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-22
 * Time: 12:23
 * Description:
 */

@RestController
public class UserList {

    // 路由，也就是servlet那边的 WebServlet(/)
    @RequestMapping("/adduser")
    public String adduser() {
        return "添加成功";
    }
}
