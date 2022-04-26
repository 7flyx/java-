package com.baidu.beans;

import org.springframework.stereotype.Repository;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-26
 * Time: 16:27
 * Description: 数据持久层
 */
@Repository
public class UserRepository {
    public void sayHi() {
        System.out.println("hello world! repository");
    }
}
