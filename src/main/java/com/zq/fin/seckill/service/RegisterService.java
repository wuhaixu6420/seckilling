package com.zq.fin.seckill.service;

import com.zq.fin.seckill.dto.reg.RegModelResult;
import com.zq.fin.seckill.dto.reg.RegModelVerify;
import com.zq.fin.seckill.exception.ExceedException;
import com.zq.fin.seckill.exception.UpdateErrorException;


/**
 * 用户 业务 接口
 * 
 * @author StarZou
 * @since 2014年7月5日 上午11:53:33
 **/
public interface RegisterService {

	/**
	 * 验证用户名是否存在
	 * 
	 * @param user
	 * @return
	 */
	RegModelResult authenticationByusername(String username);
	
	/**
	 * 注册该用户信息
	 * 
	 * @param username
	 * @return
	 */
	RegModelResult selectByUsername(RegModelVerify regModelVerify) throws UpdateErrorException, ExceedException;
}
