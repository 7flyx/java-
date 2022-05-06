package com.example.springbootdemo4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-05-06
 * Time: 21:54
 * Description: 所有的需要加入IoC容器的Bean，必须放在当前项目启动类的同级目录，或者下级目录。启动类：springbootdemo4Application，
 *              对于当前这个工程来说。
 */
@Controller // 标记着需要放入IoC容器中
@ResponseBody // 这个方法也是可以加在类前面的
public class Hello {

    @ResponseBody // 标记着当前方法是放回的数据，而不是静态文件（html）
    @RequestMapping("/helloworld") // URL映射，也就是前面学的WebServlet注解
    public String helloWorld() {
        return "<h1>这样一个方法就相当于Servlet中的一个servlet类</h1>";
    }

    @ResponseBody
    @RequestMapping("/helloworld2")
    public HashMap<String, Object> helloWorld2() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("java", "hello world");
        map.put("这是HashMap", "会自动转换为json格式的数据");
        return map;
    }

}
