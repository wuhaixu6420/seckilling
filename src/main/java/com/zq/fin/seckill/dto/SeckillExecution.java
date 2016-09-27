package com.zq.fin.seckill.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.zq.fin.seckill.entity.SuccessKilled;
import com.zq.fin.seckill.enums.SeckillStatenum;

/**
 * 封装秒杀执行后的结果
 * @author admin
 *
 * create Y-Y 2016年8月18日16:32:42
 */
public class SeckillExecution {

	//id
	private long seckillId;
	
	//秒杀执行结果状态
	private int state;
	
	//状态表示
	private String stateInfo;
	
	//秒杀成功对象
	private SuccessKilled successKilled;
	
	public SeckillExecution(long seckillId, SeckillStatenum statEnum) {
		this.seckillId = seckillId;
		this.state = statEnum.getState();
		this.stateInfo = statEnum.getStateInfo();
	}

	public SeckillExecution(long seckillId, SeckillStatenum statEnum,
			SuccessKilled successKilled) {
		this.seckillId = seckillId;
		this.state = statEnum.getState();
		this.stateInfo = statEnum.getStateInfo();
		this.successKilled = successKilled;
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
