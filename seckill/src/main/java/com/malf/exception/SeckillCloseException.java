package com.malf.exception;

/**
 * @author malf
 * @description 秒杀已经关闭异常, 当秒杀结束就会出现这个异常
 * @project seckill
 * @since 2020/11/17
 */
public class SeckillCloseException extends SeckillException {
	public SeckillCloseException(String message) {
		super(message);
	}

	public SeckillCloseException(String message, Throwable cause) {
		super(message, cause);
	}
}
