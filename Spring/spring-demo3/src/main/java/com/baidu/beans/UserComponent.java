package com.baidu.beans;

import org.springframework.stereotype.Component;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-26
 * Time: 16:35
 * Description: 组件：实体类
 */
@Component
public class UserComponent {
    public void sayHi() {
        System.out.println("hello world! component");
    }
}
