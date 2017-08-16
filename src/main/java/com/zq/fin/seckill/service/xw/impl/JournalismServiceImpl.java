package com.zq.fin.seckill.service.xw.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zq.fin.core.feature.orm.mybatis.Page;
import com.zq.fin.seckill.dao.xw.JournalismMapper;
import com.zq.fin.seckill.dto.DataResult;
import com.zq.fin.seckill.entity.xw.Journalism;
import com.zq.fin.seckill.entity.xw.JournalismExample;
import com.zq.fin.seckill.service.BaseService;
import com.zq.fin.seckill.service.xw.JournalismService;
import com.zq.fin.seckill.util.ObjectUtil;

/**
 * 
 * 描述：新闻信息实现接口
 * @author wuhaixu
 * @created 2017年3月23日 下午4:22:21
 * @since
 */
@Service
public class JournalismServiceImpl extends BaseService implements JournalismService {
	
	/*
	 * JournalismServiceImpl私有常量
	 */
	private static final String PAGE_NO = "1";
	private static final String PAGE_SIZE = "10";

	@Resource
	private JournalismMapper journalismMapper;
	
	@Override
	public DataResult<List<Journalism>> queryJournalismPageList(String pageNo, String pageSize) {
		logger.info(new Exception().getStackTrace()[0].getMethodName() + logerEnums.get(0));
		
		//判断pageNo，并转化为Integer
		Integer page_no = Integer.valueOf(ObjectUtil.isEmpty(pageNo) ? PAGE_NO : pageNo);
		//判断pageSize，并转化为Integer
		Integer page_size = Integer.valueOf(ObjectUtil.isEmpty(pageSize) ? PAGE_SIZE : pageSize);
		
		//创建page
		Page<Journalism> page = new Page<Journalism>(page_no, page_size);
		//创建查询条件
		JournalismExample journalismExample = new JournalismExample();
		journalismExample.setOrderByClause("create_time desc");
		
		//查询
		List<Journalism> journalisms = journalismMapper.selectNewestByExample(page, journalismExample);
		
		logger.info(new Exception().getStackTrace()[0].getMethodName() + logerEnums.get(1));
		return new DataResult<List<Journalism>>(true, journalisms);
	}
	
	@Override
	public DataResult<?> queryJournalismOne(String id) {
		logger.info(new Exception().getStackTrace()[0].getMethodName() + logerEnums.get(0));
		if(ObjectUtil.isNotEmpty(id)){
			return new DataResult<Journalism>(false, "传入参数为空");
		}
		Long value = null;
		try {
			//强制转换
			value = Long.valueOf(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new DataResult<>(false, "强制转换异常");
		}
		//创建条件
		JournalismExample journalismExample = new JournalismExample();
		journalismExample.createCriteria().andIdEqualTo(value);
		//查询
		List<Journalism> journalisms = journalismMapper.selectByExample(journalismExample);
		
		logger.info(new Exception().getStackTrace()[0].getMethodName() + logerEnums.get(1));
		if(journalisms.isEmpty()){
			return new DataResult<>(false, "查询失败");
		}else {
			return new DataResult<Journalism>(true, journalisms.get(0));
		}
	}
	
	
	
	
	
	/**
	 * test
	 */
	@Override
	public List<Journalism> queryJournalismList() {
		logger.info(new Exception().getStackTrace()[0].getMethodName() + logerEnums.get(0));
		
		JournalismExample journalismExample = new JournalismExample();
		journalismExample.createCriteria()
		.andTitleIsNotNull();
		logger.info(new Exception().getStackTrace()[0].getMethodName() + logerEnums.get(1));
		return journalismMapper.selectByExample(journalismExample);
	}

}
