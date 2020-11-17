package com.malf.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author malf
 * @description MyBatis 配置
 * @project springboot
 * @since 2020/11/17
 */
@EnableTransactionManagement
@Configuration
public class MyBatisConfig {

	@Resource(name = "myRoutingDataSource")
	private DataSource myRoutingDataSource;

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(myRoutingDataSource);
		sqlSessionFactoryBean.setMapperLocations(
				new PathMatchingResourcePatternResolver()
						.getResources("classpath:mapper/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager platformTransactionManager() {
		return new DataSourceTransactionManager(myRoutingDataSource);
	}
}