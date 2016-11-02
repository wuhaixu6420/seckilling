package com.zq.fin.seckill.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.zq.fin.seckill.common.BaseConstant;

public class QNFileUtils {
	
	private static Logger logger = LoggerFactory.getLogger(QNFileUtils.class);
	//要上传的空间
	private final static String bucketname = "seckill";
	
	//设置断点记录文件保存在指定文件夹或的File对象
	public static boolean upload(byte[] data, String fileName){
		//实例化上传对象，并且传入一个recorder对象
		UploadManager uploadManager = new UploadManager();
		try {
			//调用put方法上传
			Response res = uploadManager.put(data, fileName, BaseConstant.auth.uploadToken(bucketname));
			//打印返回的信息
			logger.info(res.bodyString());
			if(res.statusCode == 200){
				return true;
			} else {
				return false;
			}
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			logger.info(r.toString());
			try {
				//响应的文本信息
				logger.info(r.bodyString());
			} catch (QiniuException e1) {
				//ignore
			}
		}
		return false;
	}
	
	public static boolean delete(String fileName){
		try {
			BucketManager bucketManager = new BucketManager(BaseConstant.auth);
			//调用delete方法移动文件
			bucketManager.delete(bucketname, fileName);
			return true;
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			logger.info(r.toString());
			try {
				//响应的文本信息
				logger.info(r.bodyString());
			} catch (QiniuException e1) {
				//ignore
			}
		}
		return false;
	}
}
