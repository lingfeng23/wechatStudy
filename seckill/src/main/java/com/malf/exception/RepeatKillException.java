package com.malf.exception;

/**
 * @author malf
 * @description 重复秒杀异常, 不需要我们手动去try catch
 * @project seckill
 * @since 2020/11/17
 */
public class RepeatKillException extends SeckillException {
	public RepeatKillException(String message) {
		super(message);
	}

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}
}
