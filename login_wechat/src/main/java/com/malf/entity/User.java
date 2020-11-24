package com.malf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author malf
 * @Description TODO
 * @project login_wechat
 * @since 2020/11/24
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("users")
public class User {
    
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
}
