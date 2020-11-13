package com.malf.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author malf
 * @description 病人
 * @project springboot
 * @since 2020/11/12
 */
@Data
public class Patient {
	// 主键ID
	private long id;
	// 关联用户
	private User user;
	// 姓名
	private String name;
	// 家庭住址
	private String address;
	// 出生日期
	private Date birthDate;
	
}
