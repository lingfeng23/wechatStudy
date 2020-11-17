package com.malf.aop;

import com.malf.bean.DBContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author malf
 * @description 设置路由 key
 * 默认情况下，所有的查询都走从库，插入/修改/删除走主库。
 * 我们通过方法名来区分操作类型（CRUD）
 * @project springboot
 * @since 2020/11/17
 */
@Aspect
@Component
public class DataSourceAop {

	@Pointcut("!@annotation(com.malf.annotation.Master) " +
			"&& (execution(* com.malf.service..*.select*(..)) " +
			"|| execution(* com.malf.service..*.get*(..)))")
	public void readPointcut() {

	}

	@Pointcut("@annotation(com.malf.annotation.Master) " +
			"|| execution(* com.malf.service..*.insert*(..)) " +
			"|| execution(* com.malf.service..*.add*(..)) " +
			"|| execution(* com.malf.service..*.update*(..)) " +
			"|| execution(* com.malf.service..*.edit*(..)) " +
			"|| execution(* com.malf.service..*.delete*(..)) " +
			"|| execution(* com.malf.service..*.remove*(..))")
	public void writePointcut() {

	}

	@Before("readPointcut()")
	public void read() {
		DBContextHolder.slave();
	}

	@Before("writePointcut()")
	public void write() {
		DBContextHolder.master();
	}

	/**
	 * 另一种写法：if...else... 判断哪些需要读从数据库，其余的走主数据库
	 */
//	@Before("execution(* com.malf.service.impl.*.*(..))")
//	public void before(JoinPoint jp) {
//		String methodName = jp.getSignature().getName();
//		if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
//			DBContextHolder.slave();
//		} else {
//			DBContextHolder.master();
//		}
//	}

}
