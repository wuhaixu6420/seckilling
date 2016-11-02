package com.zq.fin.seckill.service.image.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zq.fin.seckill.common.BaseConstant;
import com.zq.fin.seckill.dao.ImageMapper;
import com.zq.fin.seckill.dto.DataRseult;
import com.zq.fin.seckill.entity.Image;
import com.zq.fin.seckill.entity.ImageExample;
import com.zq.fin.seckill.service.image.ImageService;
import com.zq.fin.seckill.util.ObjectUtil;
import com.zq.fin.seckill.util.QNFileUtils;

@Service
public class ImageServiceImpl extends BaseConstant implements ImageService {
	
	@Autowired
	private ImageMapper imageMapper;

	@Override
	public DataRseult<?> upload(Long userId, String ownedSpace, InputStream inputStream, String fileName) {
		try {
			//将文件输入流   转化为二进制
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] bs = new byte[inputStream.available()];
			int len = 0;
			while ((len = inputStream.read(bs)) != -1) {
				baos.write(bs, 0, len);  
			}
			byte bImage[] = baos.toByteArray();
			//上传云端
			boolean uploadFlag = QNFileUtils.upload(bImage, fileName);
			
			if(uploadFlag){
				//创建Image
				Image image = new Image();
				//上传时间
				image.setDate(new Date());
				//添加图片名称
				image.setName(fileName);
				//上传路径
				image.setUrl(QNurl + fileName.replace(" ", "%20"));
				//用户id
				image.setUserId(userId);
				//添加所属空间
				if(ObjectUtil.isEmpty(ownedSpace)){
					ownedSpace = "默认";
				}
				//没有就默认
				imageMapper.insert(image);
				return new DataRseult<>(true, "上传成功");
			} else {
				//上传失败
				return new DataRseult<>(true, "上传失败");
			}
		} catch (IOException e) {
			e.printStackTrace();
			return new DataRseult<>(true, "上传异常");
		}
	}
	
	@Override
	public DataRseult<?> showImage(Long userId, String ownedSpace) {
		ImageExample imageExample = new ImageExample();
		//根据所属空间查询对应的所有图片信息
		if(ObjectUtil.isEmpty(ownedSpace)){
			imageExample.createCriteria()
			.andUserIdEqualTo(userId);
		} else {
			imageExample.createCriteria()
			.andUserIdEqualTo(userId)
			.andOwnedSpaceEqualTo(ownedSpace);
		}
		List<Image> images = imageMapper.selectByExample(imageExample);
		return new DataRseult<>(true, images);
	}

	@Override
	public DataRseult<?> showDetailImage(Long id) {
		//通过条件查询  通过id 查询对应的image信息
		ImageExample imageExample = new ImageExample();
		imageExample.createCriteria()
		.andIdEqualTo(id);
		List<Image> images = imageMapper.selectByExample(imageExample);
		if(images.isEmpty()){
			return new DataRseult<>(false, "该图片不存在");
		} else {
			return new DataRseult<>(true, images.get(0));
		}
	}
	
	@Override
	public DataRseult<?> deleteImage(Long id) {
		//因为七牛的删除文件需要名称，所以先查询
		ImageExample imageExample = new ImageExample();
		imageExample.createCriteria()
		.andIdEqualTo(id);
		//根据id查询对应的image
		List<Image> images = imageMapper.selectByExample(imageExample);
		if(!images.isEmpty()){
			//删除图片,数据库
			int result = imageMapper.deleteByPrimaryKey(id);
			if(result == 1){
				//删除图片，七牛云
				QNFileUtils.delete(images.get(0).getName());
				return new DataRseult<>(true, "删除成功");
			}
		}
		return new DataRseult<>(false, "删除失败");
	}
}
