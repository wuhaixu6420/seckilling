package com.zq.fin.seckill.dao.xw;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zq.fin.core.feature.orm.mybatis.Page;
import com.zq.fin.seckill.entity.xw.Journalism;
import com.zq.fin.seckill.entity.xw.JournalismExample;

/**
 * 
 * 描述：新闻mapper		用于数据库操作
 * @author wuhaixu
 * @created 2017年3月23日 下午4:24:44
 * @since
 */
public interface JournalismMapper {
	
	int countByExample(JournalismExample example);

    int deleteByExample(JournalismExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Journalism record);

    int insertSelective(Journalism record);

    List<Journalism> selectByExample(JournalismExample example);

    Journalism selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Journalism record, @Param("example") JournalismExample example);

    int updateByExample(@Param("record") Journalism record, @Param("example") JournalismExample example);

    int updateByPrimaryKeySelective(Journalism record);

    int updateByPrimaryKey(Journalism record);
    
    /**
     * 
     * 描述：根据时间，查询最新的新闻信息
     * @author wuhaixu
     * @created 2017年3月27日 下午1:55:02
     * @since 
     * @param page	根据page分页查询
     * @param example
     * @return
     */
    List<Journalism> selectNewestByExample(Page<Journalism> page, JournalismExample example);
}
