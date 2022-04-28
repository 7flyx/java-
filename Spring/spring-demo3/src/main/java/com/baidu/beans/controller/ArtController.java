package com.baidu.beans.controller;

import com.baidu.beans.service.ArtService;
import com.baidu.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-28
 * Time: 18:19
 * Description: 博客文章控制层，主要做前端传输的数据，进行校验
 */
@Controller
public class ArtController {
    // 将下层调用的对象，先注入进来（拿到）
    // 1、属性注入，使用简单，缺点是只能在IoC容器中使用，移植性很差
//    @Autowired
//    private ArtService artService;

    // 2、构造方法注入
//    private ArtService artService;
//
//    @Autowired
//    public ArtController(ArtService artService) {
//        this.artService = artService;
//    }

    // 3、Setter方法注入
//    private ArtService artService;
//
//    @Autowired // 这里其实就是get、set方法，那个意思
//    public void setArtService(ArtService artService) {
//        this.artService = artService;
//    }



    //Resource注解，进行注入。只能使用在属性注入和Setter注入中
//    @Resource
//    private ArtService artService;

    private ArtService artService;

    @Resource
    public void setArtService(ArtService artService) {
        this.artService = artService;
    }

    @Resource(name = "firstName") // 当容器中有多个Users对象时，需要指定注入的哪个对象
    private Users users;

    @Autowired
    @Qualifier("firstName") // 等价于上面的Resource（name = ‘firstName’）
    private Users users2;

    public void addArticle(String title, String body) {
        if (title == null || body == null || body.equals("") || title.equals("")) {
            System.out.println("参数有误");
            return;
        }
        // 调用下层Service的方法
        artService.addArticle(title, body);
    }
}
