package com.baidu.beans;

import org.springframework.stereotype.Controller;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-26
 * Time: 14:54
 * Description:
 */

// 等价于 <bean id = "user" class = "com.baidu.beans"></bean>
@Controller // 使用这个注解，可以让content:component-scan 扫描到
public class UserController {
    public void sayHi() {
        System.out.println("hello world! Controller");
    }
}
