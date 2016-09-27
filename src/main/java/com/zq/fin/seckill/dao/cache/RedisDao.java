package com.zq.fin.seckill.dao.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.zq.fin.seckill.entity.Seckill;
import com.zq.fin.seckill.util.ObjectUtil;

/**
 * created Y-Y 2016年8月25日15:05:49
 * @author admin
 *
 */
public class RedisDao {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final JedisPool jedisPool;
	
	public RedisDao(String ip, int port){
		jedisPool = new JedisPool(ip, port);
	}
	
	//通过class来序列化
	private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);
	
	/**
	 * 将redis中的二进制seckill   反序列化  获取到redis
	 * 
	 * 键值对  key->value       
	 * 
	 * reids中存放数据的形式就是以键值对的形式存储的
	 * @param seckillId
	 * @return
	 */
	public Seckill getSeckill(long seckillId){
		//redis 操作逻辑
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				String key = "seckill:" + seckillId;
				//并没有实现内部序列化操作
				//get -> byte[] -> 反序列化 -> Object(Seckill)
				//采用自定义序列化
				//protostuff : pojo
				byte[] bytes = jedis.get(key.getBytes());
				//缓存获取到
				if(ObjectUtil.isNotEmpty(bytes)){
					//空对象
					Seckill seckill = schema.newMessage();
					ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
					//seckill 被反序列化
					return seckill;
				}
			} finally {
				jedis.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 将seckill  序列化，添加到redis中
	 * @param seckill
	 * @return
	 */
	public String putSeckill(Seckill seckill){
		//set Object(Seckill) ->序列化-> byte[]
		try {
			//通过jedis池子获取对应的jedis
			Jedis jedis = jedisPool.getResource();
			try {
				String key = "seckill:" + seckill.getSeckillId();
				
				byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema, 
						LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
				//超时缓存
				int timeout = 60 * 60;//缓存超时1个小时
				String result = jedis.setex(key.getBytes(), timeout, bytes);
				
				return result;
			} finally {
				jedis.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return null;
	}
	
}
