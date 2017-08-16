package com.zq.fin.seckill.service.image;

import java.io.InputStream;

import com.zq.fin.seckill.dto.DataResult;

/**
 * 上传图片
 * @author wuhaixu
 *
 */
public interface ImageService {

	/**
	 * 图片上传
	 * @return
	 */
	DataResult<?> upload(Long userId, String ownedSpace, InputStream inputStream, String fileName);
	
	/**
	 * 显示所有图片
	 * v0.1 :  只显示所有图片，不进行判断所属空间
	 * v0.2 :  根据所属空间，显示所属空间内的所有图片
	 * @param userId
	 * @param ownedSpace
	 * @return
	 */
	DataResult<?> showImage(Long userId, String ownedSpace);
	
	/**
	 * 通过id，查询对应的图片
	 * @param id
	 * @return
	 */
	DataResult<?> showDetailImage(Long id);
	
	/**
	 * 通过id，删除图片信息
	 * @param id
	 * @return
	 */
	DataResult<?> deleteImage(Long id);
	
}
