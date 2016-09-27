package com.zq.fin.seckill.dao;

import org.apache.ibatis.annotations.Param;

import com.zq.fin.seckill.entity.SuccessKilled;



/**
 * create by y-y 2016年8月12日17:45:44
 * @author admin
 * 
 * 秒杀成功明细表  DAO
 * 
 */
public interface SuccessKilledMapper {

	/**
	 * 插入购买明细，可过滤重复
	 * @param seckillId
	 * @param userPhone
	 * @return 插入的行数
	 */
	int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
	
	/**
	 * 根据id查询SuccessKilled并携带秒杀产品对象实体
	 * @param seckillId
	 * @return
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
