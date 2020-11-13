package com.malf.pojo;

/**
 * @author malf
 * @description TODO
 * @project springboot
 * @since 2020/11/12
 */
public enum Sex {
	M("男性"), F("女性"), UNKNOWN("未知");
	private final String sex;

	private Sex(String sex) {
		this.sex = sex;
	}

}
