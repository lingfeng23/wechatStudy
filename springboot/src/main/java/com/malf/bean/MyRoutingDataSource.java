package com.malf.bean;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * @author malf
 * @description 获取路由 key
 * @project springboot
 * @since 2020/11/17
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {
	@Nullable
	@Override
	protected Object determineCurrentLookupKey() {
		return DBContextHolder.get();
	}

}
