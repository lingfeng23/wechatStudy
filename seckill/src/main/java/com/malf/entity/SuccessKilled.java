package com.malf.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author malf
 * @description 秒杀状态表
 * @project seckill
 * @since 2020/11/17
 */
public class SuccessKilled implements Serializable {
	private static final long serialVersionUID = 1834437127882846202L;

	private long seckillId;
	/* 用户的手机号码 */
	private long userPhone;
	/* 秒杀状态 */
	private short state;
	/* 创建时间 */
	private Date createTime;
	/* 秒杀商品信息 */
	private Seckill seckill;

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public long getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Seckill getSeckill() {
		return seckill;
	}

	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}

	@Override
	public String toString() {
		return "SuccessKilled{" +
				"seckillId=" + seckillId +
				", userPhone=" + userPhone +
				", state=" + state +
				", createTime=" + createTime +
				", seckill=" + seckill +
				'}';
	}
}
