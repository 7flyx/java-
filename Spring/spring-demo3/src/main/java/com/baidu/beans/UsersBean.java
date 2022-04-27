package com.baidu.beans;

import com.baidu.model.Users;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-27
 * Time: 14:24
 * Description: 方法注解@Bean
 */

@Component // 此时这个注解，表示当前类
public class UsersBean {

    //@Bean // 方法注解，表示将这个方法的返回值加载到容器中
    @Bean(name = {"firstName", "secondName"}) // 起别名，可以有多个别名
    public Users getUser() {
        Users users = new Users();
        users.setAge(20);
        users.setId(100);
        users.setName("飞人");
        return users;
    }
}
