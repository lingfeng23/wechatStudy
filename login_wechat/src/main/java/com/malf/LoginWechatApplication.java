package com.malf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.malf.mapper") // mapper接口的扫描包
public class LoginWechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginWechatApplication.class, args);
    }

}
