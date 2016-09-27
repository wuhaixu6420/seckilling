package com.zq.fin.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zq.fin.seckill.entity.Seckill;


/**
 * create by y-y 2016年8月12日17:45:44
 * @author admin
 * 
 * 秒杀库存表 DAO
 * 
 */
public interface SeckillMapper {

	/**
	 * 减库存
	 * @param seckillId
	 * @param killTime
	 * @return  如果影响行数>1,表示更新的记录行数
	 */
	int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);
	
	/**
	 * 根据id查询秒杀对象
	 * @param seckillId
	 * @return
	 */
	Seckill queryById(long seckillId);
	
	/**
	 * 根据偏移量查询秒杀商品列表
	 * @param offet
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(@Param("offet") int offet, @Param("limit") int limit);
	
}
