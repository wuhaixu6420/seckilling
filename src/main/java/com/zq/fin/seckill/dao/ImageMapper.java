package com.zq.fin.seckill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zq.fin.core.generic.GenericDao;
import com.zq.fin.seckill.entity.User;
import com.zq.fin.seckill.entity.UserExample;

/**
 * 用户Dao接口
 * 
 * @author StarZou
 * @since 2014年7月5日 上午11:49:57
 **/
public interface ImageMapper extends GenericDao<User, Long> {
	int countByExample(UserExample example);

	int deleteByExample(UserExample example);

	int deleteByPrimaryKey(Long id);

	int insert(User record);

	int insertSelective(User record);

	List<User> selectByExample(UserExample example);

	User selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

	int updateByExample(@Param("record") User record, @Param("example") UserExample example);

	int updateByPrimaryKeySelective(User record);
	
	int updateByPrimaryKey(User record);
	
}