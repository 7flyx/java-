package com.baidu.beans.service;

import org.springframework.stereotype.Service;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-28
 * Time: 18:21
 * Description: 博客文章 服务层
 */
@Service
public class ArtService {

    // 假设是将数据添加到数据库中
    public void addArticle(String title, String body) {
        System.out.println("添加文章成功");
    }
}
