package com.malf.pojo;

import lombok.Data;

/**
 * @author malf
 * @description 关联用户
 * @project springboot
 * @since 2020/11/12
 */
@Data
public class User {
	// 主键ID
	private long id;
	// 名称
	private String name;
	// 密码
	private String password;
	// 用户角色
	private Role role;
	// 性别
	private Sex sex;
	// 年龄
	private Integer age;
	// 身份证号
	@IdentityID
	private String identityNumber;
	
	
}
