package com.malf.pojo;

import lombok.Data;

/**
 * @author malf
 * @description 床位信息
 * @project springboot
 * @since 2020/11/12
 */
@Data
public class BedInfo {
	// 主键ID
	private long id;
	// 楼层
	private Integer floor;
	// 门牌号
	private Integer door;
	// 床位
	private Integer bedNumber;
	// 床位状态
	private boolean status; // true 为有人，false 为没人
	//

	//
}
