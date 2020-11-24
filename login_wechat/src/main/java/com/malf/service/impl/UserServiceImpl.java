package com.malf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.malf.entity.User;
import com.malf.mapper.UserMapper;
import com.malf.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author malf
 * @Description TODO
 * @project login_wechat
 * @since 2020/11/24
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
