
create database if not exists oj_system;

use oj_system;

-- 题目信息
create table `oj_table`(
    id int primary key auto_increment, -- 题号
    title varchar(50), -- 题目
    level varchar(50), -- 难度
    description varchar(8192), -- 题干
    templateCode varchar(4096), -- 初始化代码
    testCase varchar(4096), -- 测试用例
    classify int -- 题目类别，比如 dp，排序、贪心等等
);

-- 题目分类
create table `classify_table` (
    classify  int primary key auto_increment,        -- 类型编号
    classify_name varchar(50) unique -- 类型名
);

-- 存储曾经提交通过的代码
create table `code_backups` (
    problemID int, -- 题号
    code varchar(4096), -- 代码
    userID int -- 所属的用户
);

-- 用户信息表
create table userinfo(
    userid int primary key auto_increment,
    username varchar(512),
    password varchar(50),
    passsum int, -- 通过的题目数
    manager int -- 1表示true，0表示false
);