package com.zq.fin.seckill.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zq.fin.seckill.common.BaseConstant;
import com.zq.fin.seckill.dto.reg.RegModelResult;
import com.zq.fin.seckill.dto.reg.RegModelVerify;
import com.zq.fin.seckill.entity.User;
import com.zq.fin.seckill.exception.ExceedException;
import com.zq.fin.seckill.exception.UpdateErrorException;
import com.zq.fin.seckill.service.RegisterService;
import com.zq.fin.seckill.service.UserService;
import com.zq.fin.seckill.util.MD5Util;
import com.zq.fin.seckill.util.ObjectUtil;


/**
 * 用户 业务 接口
 * 
 * @author StarZou
 * @since 2014年7月5日 上午11:53:33
 **/
@Service
public class RegisterServiceImpl extends BaseConstant implements RegisterService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	
	@Override
	public RegModelResult authenticationByusername(String username) {
		User user = userService.selectByUsername(username);
		if(ObjectUtil.isEmpty(user)){
			return new RegModelResult(true, "该用户名可以使用");
		} else {
			return new RegModelResult(false, "该用户名已重复");
		}
	}

	@Override
	public RegModelResult selectByUsername(RegModelVerify regModelVerify) throws UpdateErrorException, ExceedException {
		try {
			User user = new User();
			user.setUsername(regModelVerify.getUsername());
			user.setPassword(MD5Util.MD5(regModelVerify.getPassword()));
			user.setState(normal_state);
			user.setCreateTime(new Date());
			int result = userService.saveUser(user);
			if(result == 0){
				throw new UpdateErrorException("userInfo update or insert error");
			} else if(result > 1) {
				throw new ExceedException("userInfo update exceed");
			} else {
				return new RegModelResult(true, "用户创建成功");
			}
		} catch (UpdateErrorException e) {
			throw e;
		} catch (ExceedException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
}
