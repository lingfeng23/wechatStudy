package com.malf.pojo;

import lombok.Data;

/**
 * @author malf
 * @description 药品
 * @project springboot
 * @since 2020/11/12
 */
@Data
public class Drugs {
	// 主键ID
	private long id;
	// 药品名称
	private String name;
	// 药品类型
	private DrugsType type;
	//

	//
	//

}
