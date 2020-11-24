package com.malf.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author malf
 * @Description TODO
 * @project login_wechat
 * @since 2020/11/25
 */
@RestController
public class LoginController {
    /**
     * 登录
     *
     * @param phone
     * @param password
     * @return
     */
    @PostMapping("/doLogin")
    public Map doLogin(String phone, String password) {
        Map map = new HashMap();
        if ((phone.equals("10086") && password.equals("123456"))) {
            map.put("code", 200);
            map.put("result", "登录成功");
            System.out.println("登录成功");
        } else {
            map.put("result", "no");
        }
        return map;
    }
}
