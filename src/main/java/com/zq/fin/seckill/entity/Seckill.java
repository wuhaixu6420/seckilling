package com.zq.fin.seckill.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * create by y-y 2016年8月12日17:45:44
 * @author admin
 * 
 * 秒杀库存表
 * 
 */
public class Seckill {

	private long seckillId;
	
	private String name;
	
	private int number;
	
	private Date startTime;

	private Date endTime;
	
	private Date createTime;

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
