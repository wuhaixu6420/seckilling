package com.zq.fin.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.zq.fin.seckill.dao.SeckillMapper;
import com.zq.fin.seckill.dao.SuccessKilledMapper;
import com.zq.fin.seckill.dao.cache.RedisDao;
import com.zq.fin.seckill.dto.Exposer;
import com.zq.fin.seckill.dto.SeckillExecution;
import com.zq.fin.seckill.entity.Seckill;
import com.zq.fin.seckill.entity.SuccessKilled;
import com.zq.fin.seckill.enums.SeckillStatenum;
import com.zq.fin.seckill.exception.RepeatKillException;
import com.zq.fin.seckill.exception.SeckillCloseException;
import com.zq.fin.seckill.exception.SeckillException;
import com.zq.fin.seckill.service.SeckillService;
import com.zq.fin.seckill.util.ObjectUtil;

/**
 * @author admin
 * create Y-Y 2016年8月18日16:27:06
 */
//@Component @Service @Dao @Controller
@Service
public class SeckillServiceImpl implements SeckillService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//注入service依赖
	@Autowired//@Resource,@Inject
	private SeckillMapper seckillMapper;
	
	@Autowired
	private SuccessKilledMapper successKilledMapper;
	
//	@Autowired
//	private RedisDao redisDao;
	
	//MD5盐值字符串，用于混淆MD5
	private final String slat = "~!@123#$asdf%^eth&*(4235)_+";
	
	@Override
	public List<Seckill> getSeckillList() {
		return seckillMapper.queryAll(0, 4);
	}

	@Override
	public Seckill getById(long seckillId) {
		return seckillMapper.queryById(seckillId);
	}

	@Override
	public Exposer exportSeckillUrl(long seckillId) {
		//优化点：缓存优化：在超时的基础上维护一致性的
		//降低数据库访问量
//		//1: 访问redis
//		Seckill seckill = redisDao.getSeckill(seckillId);
//		if(ObjectUtil.isEmpty(seckill)){
//			//2:访问数据库
//			seckill = seckillMapper.queryById(seckillId);
//			
//			//如果查询不到，返回id
//			if(ObjectUtil.isEmpty(seckill)){
//				return new Exposer(false, seckillId);
//			} else {
//				//3:放入redis
//				redisDao.putSeckill(seckill);
//			}
//		}
		Seckill seckill = seckillMapper.queryById(seckillId);
		
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		//系统当前时间
		Date nowTime = new Date();
		
		if(nowTime.getTime() < startTime.getTime()//秒杀没有开始
				|| nowTime.getTime() > endTime.getTime()){//秒杀已经结束
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}
		//转化特定字符串的过程，不可逆
		String md5 = getMD5(seckillId);
		
		return new Exposer(true, md5, seckillId);
	}
	
	/**
	 * 根据盐值获取MD5
	 * @param seckillId
	 * @return
	 */
	private String getMD5(long seckillId){
		String base = seckillId + "/" + slat;
		
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		
		return md5;
	}

	@Override
	@Transactional
	/**
	 * 使用注解控制事务方法的优点：
	 * 1:开发团队达成一致约定，明确标注事务方法的变成风格
	 * 2:保证事务方法的执行时间尽可能短,不要穿插其他网络操作RPC/HTTP请求或者剥离到事务方法外部
	 * 3:不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
	 */
	public SeckillExecution executeSeckill(long seckillId, long userPhone,
			String md5) throws SeckillException, RepeatKillException,
			SeckillCloseException {
		if(ObjectUtil.isEmpty(md5) || !md5.equals(getMD5(seckillId))){
			throw new SeckillException("seckill data rewrite");
		}
		//执行秒杀逻辑：减库存 + 记录购买行为
		Date nowtime = new Date();
		try {
			//减库存成功
			//记录购买行为
			int insertCount = successKilledMapper.insertSuccessKilled(seckillId, userPhone);
			//唯一：seckillId, userPhone
			if(insertCount <= 0){
				//重复秒杀
				throw new RepeatKillException("seckill reseated");
			} else {
				//减库存，热点商品竞争
				int updateCount = seckillMapper.reduceNumber(seckillId, nowtime);
				if(updateCount <= 0){
					//没有更新到记录,秒杀结束，rollback
					throw new SeckillCloseException("seckill is closed");
				} else {
					//秒杀成功，commit
					SuccessKilled successKilled = successKilledMapper.queryByIdWithSeckill(seckillId, userPhone);
					//使用枚举字典，来代替常量
					return new SeckillExecution(seckillId, SeckillStatenum.SUCCESS, successKilled);
				}
			}
			
		} catch (SeckillCloseException e) {
			throw e;
		} catch (RepeatKillException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			
			//所有编译器异常 转化成运行期异常
			//运行期异常，spring会自动回滚
			throw new SeckillException("seckill inner error :" + e.getMessage());
		}
		
	}
	
}
