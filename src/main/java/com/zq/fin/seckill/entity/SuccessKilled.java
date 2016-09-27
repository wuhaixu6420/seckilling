package com.zq.fin.seckill.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * create by y-y 2016年8月12日17:45:44
 * @author admin
 * 
 * 秒杀成功明细表
 * 
 */
public class SuccessKilled {

	private long seckillId;
	
	private long userPhone;
	
	private short state;
	
	private Date cerareTime;
	
	//多对一
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

	public Date getCerareTime() {
		return cerareTime;
	}

	public void setCerareTime(Date cerareTime) {
		this.cerareTime = cerareTime;
	}
	
	public Seckill getSeckill() {
		return seckill;
	}

	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
