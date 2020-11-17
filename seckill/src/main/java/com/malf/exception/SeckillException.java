package com.malf.exception;

/**
 * @author malf
 * @description 秒杀基础异常
 * @project seckill
 * @since 2020/11/17
 */
public class SeckillException extends RuntimeException {
	public SeckillException(String message) {
		super(message);
	}

	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}
}
