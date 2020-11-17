package com.malf.bean;

import com.malf.enums.DBTypeEnum;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author malf
 * @description 通过 ThreadLocal 将数据源设置到每个线程上下文中
 * @project springboot
 * @since 2020/11/17
 */
public class DBContextHolder {
	private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

	private static final AtomicInteger counter = new AtomicInteger(-1);

	public static void set(DBTypeEnum dbType) {
		contextHolder.set(dbType);
	}

	public static DBTypeEnum get() {
		return contextHolder.get();
	}

	public static void master() {
		set(DBTypeEnum.MASTER);
		System.out.println("切换到master");
	}

	public static void slave() {
		//  轮询
		int index = counter.getAndIncrement() % 2;
		if (counter.get() > 9999) {
			counter.set(-1);
		}
		if (index == 0) {
			set(DBTypeEnum.SLAVE1);
			System.out.println("切换到slave1");
		} else {
			set(DBTypeEnum.SLAVE2);
			System.out.println("切换到slave2");
		}
	}

}
