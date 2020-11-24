package com.malf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.malf.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author malf
 * @Description TODO
 * @project login_wechat
 * @since 2020/11/24
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
