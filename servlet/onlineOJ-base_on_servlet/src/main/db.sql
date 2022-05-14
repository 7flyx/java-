
create database if not exists oj_system;
use oj_system;

create table `oj_table`(
    id int primary key auto_increment, -- 题号
    title varchar(50), -- 题目
    level varchar(50), -- 难度
    description varchar(4096), -- 题干
    templateCode varchar(4096), -- 初始化代码
    testCase varchar(4096) -- 测试用例
);