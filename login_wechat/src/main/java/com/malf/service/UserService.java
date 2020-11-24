package com.malf.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.malf.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author malf
 * @Description TODO
 * @project login_wechat
 * @since 2020/11/24
 */
@Repository
public interface UserService extends IService<User> {
}

