package com.baidu.beans;

import org.springframework.stereotype.Service;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-26
 * Time: 16:23
 * Description: 服务层
 */
@Service // 将当前类注入容器类
public class UserService {
    public void sayHi() {
        System.out.println("hello world! service");
    }
}
