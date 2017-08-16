package com.zq.fin.seckill.service.xw;

import java.util.List;

import com.zq.fin.seckill.dto.DataResult;
import com.zq.fin.seckill.entity.xw.Journalism;

/**
 * 
 * 描述：新闻信息	接口
 * @author wuhaixu
 * @created 2017年3月23日 下午4:21:31
 * @since
 */
public interface JournalismService {

	
	/**
	 * 
	 * 描述：分页查询
	 * @author wuhaixu
	 * @created 2017年3月27日 下午3:55:44
	 * @since 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	DataResult<List<Journalism>> queryJournalismPageList(String pageNo, String pageSize);
	
	/**
	 * 
	 * 描述：查询新闻（单个）
	 * @author wuhaixu
	 * @created 2017年3月28日 下午4:05:33
	 * @since 
	 * @param id
	 * @return
	 */
	DataResult<?> queryJournalismOne(String id);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * 描述：测试，查询所有新闻信息
	 * @author wuhaixu
	 * @created 2017年3月24日 下午4:40:04
	 * @since 
	 * @return
	 */
	List<Journalism> queryJournalismList();
	
	
}
