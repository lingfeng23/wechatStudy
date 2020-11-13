package com.malf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author malf
 * @description 医院职工
 * @project springboot
 * @since 2020/11/12
 */
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Employee {
	// 主键ID
	private long id;
	// 关联用户
	private User user;
	// 姓名
	private String name;
	// 部门
	private Department department;
	// 详细地址
	private String address;
	// 备注
	private String remark;

}
