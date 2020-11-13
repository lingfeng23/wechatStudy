package com.malf.pojo;

import lombok.Data;

import java.util.Set;

/**
 * @author malf
 * @description 部门/科室
 * @project springboot
 * @since 2020/11/12
 */
@Data
public class Department {
	// 主键ID
	private long id;
	// 部门/科室名称
	private String name;
	// 部门/科室全称
	private String fullName;
	// 上级部门/科室
	private Department department;
	// 下级部门/科室
	private Set<Department> departments;
	// 部门/科室位置
	private String address;
	//
	//
	//
	//

}
