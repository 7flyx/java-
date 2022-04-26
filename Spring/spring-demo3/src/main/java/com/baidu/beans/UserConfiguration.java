package com.baidu.beans;

import org.springframework.context.annotation.Configuration;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-26
 * Time: 16:38
 * Description: 配置注解
 */
@Configuration
public class UserConfiguration {
    public void sayHi() {
        System.out.println("hello world! configuration");
    }
}
