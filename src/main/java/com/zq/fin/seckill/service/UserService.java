package com.zq.fin.seckill.service;

import com.zq.fin.seckill.entity.User;


/**
 * 用户 业务 接口
 * 
 * @author StarZou
 * @since 2014年7月5日 上午11:53:33
 **/
public interface UserService {

	/**
	 * 用户验证
	 * 
	 * @param user
	 * @return
	 */
	User authentication(User user);
	
	/**
	 * 根据用户名查询用户
	 * 
	 * @param username
	 * @return
	 */
	User selectByUsername(String username);
	
	/**
	 * 保存或者是插入用户登录信息
	 * @param user
	 * @return
	 */
	int saveUser(User user);
}
