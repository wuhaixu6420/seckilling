package com.zq.fin.seckill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zq.fin.core.generic.GenericDao;
import com.zq.fin.seckill.entity.Image;
import com.zq.fin.seckill.entity.ImageExample;
import com.zq.fin.seckill.entity.User;

/**
 * 用户Dao接口
 * 
 * @author StarZou
 * @since 2014年7月5日 上午11:49:57
 **/
public interface ImageMapper extends GenericDao<Image, Long> {
	int countByExample(ImageExample example);

	int deleteByExample(ImageExample example);

	int deleteByPrimaryKey(Long id);

	int insert(Image record);

	int insertSelective(Image record);

	List<Image> selectByExample(ImageExample example);

	Image selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") User record, @Param("example") ImageExample example);

	int updateByExample(@Param("record") Image record, @Param("example") ImageExample example);

	int updateByPrimaryKeySelective(Image record);
	
	int updateByPrimaryKey(Image record);
	
}