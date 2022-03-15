-- 数据库在进行部署或迁移的时候，需要使用到建库建表等操作

create database if not exists BlogSystem;
use BlogSystem; -- 选中数据库

-- 建表
create table Blog(
    blogId int primary key auto_increment, -- 文章编号
    title varchar(1024), -- 标题
    content mediumtext,  -- 正文
    postTime datetime, -- 发布时间
    userId int -- 用户id
);

create table User (
    userId int primary key auto_increment,
    username varchar(128),
    password varchar(128)
    -- 也还可以设置一些其他的信息，比如头像 GitHub地址等等，日常所见的 都可以设置进来
);