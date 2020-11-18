package com.malf.dto;

import java.util.Date;

/**
 * @author malf
 * @description 秒杀时数据库处理的结果对象
 * @project seckill
 * @since 2020/11/17
 */
public class Exposer {
	/*是否开启秒杀 */
	private boolean exposed;
	/*  对秒杀地址进行加密措施  */
	private String md5;
	/* id为seckillId的商品秒杀地址   */
	private long seckillId;
	/* 系统当前的时间   */
	private Date now;
	/* 秒杀开启的时间   */
	private Date start;
	/*  秒杀结束的时间  */
	private Date end;

	public Exposer(boolean exposed, String md5, long seckillId) {
		this.exposed = exposed;
		this.md5 = md5;
		this.seckillId = seckillId;
	}

	public Exposer(boolean exposed, long seckillId, Date now, Date start, Date end) {
		this.exposed = exposed;
		this.seckillId = seckillId;
		this.now = now;
		this.start = start;
		this.end = end;
	}

	public Exposer(boolean exposed, long seckillId) {
		this.exposed = exposed;
		this.seckillId = seckillId;
	}

	public boolean isExposed() {
		return exposed;
	}

	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "Exposer{" +
				"exposed=" + exposed +
				", md5='" + md5 + '\'' +
				", seckillId=" + seckillId +
				", now=" + now +
				", start=" + start +
				", end=" + end +
				'}';
	}
}
