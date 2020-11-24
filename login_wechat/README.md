## Springboot (整合微信小程序(未实现))实现登录与增删改查

### 开发前准备

#### 前置知识
- java基础
- SpringBoot简单基础知识

#### 环境参数
- 开发工具：IDEA
- 基础环境：Maven+JDK8
- 主要技术：SpringBoot、lombok、mybatis-plus、mysql 、微信小程序
- SpringBoot版本：2.3.5.RELEASE

### 2.开发者服务器

#### 2.1 初始配置 
- pom.xml
- application.yml

#### 2.2 小程序用户表
```
CREATE table users(
 id int not null PRIMARY key auto_increment,
 name varchar(255) not null,
 age int not null );

insert into users value(null,'陈克锋',18);
insert into users value(null,'陈克帅',11);
insert into users value(null,'陈克兵',14);
```

#### 2.3 pojo
- User

#### 2.4 mapper
- UserMapper

#### 2.5 service
- UserService
- UserServiceImpl

#### 2.6 controller
- LoginController
- UserController

