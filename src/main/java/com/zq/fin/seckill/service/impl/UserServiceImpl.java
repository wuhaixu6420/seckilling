package com.zq.fin.seckill.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zq.fin.seckill.dao.UserMapper;
import com.zq.fin.seckill.entity.User;
import com.zq.fin.seckill.entity.UserExample;
import com.zq.fin.seckill.service.UserService;

/**
 * 用户 业务 接口
 * 
 * @author StarZou
 * @since 2014年7月5日 上午11:53:33
 **/
@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper userMapper;

	public User authentication(User user) {
		return userMapper.authentication(user);
	}

	public User selectByUsername(String username) {
		UserExample example = new UserExample();
		example.createCriteria().andUsernameEqualTo(username);
		final List<User> list = userMapper.selectByExample(example);
		return list.get(0);
	}

	@Override
	public int saveUser(User user) {
		UserExample userExample = new UserExample();
		userExample.createCriteria()
		.andUsernameEqualTo(user.getUsername());
		int result = 0;
		List<User> users = userMapper.selectByExample(userExample);
		if(users.isEmpty()){
			result = userMapper.insert(user);
		} else {
			User inserUser = users.get(0);
			inserUser.setPassword(user.getPassword());
			inserUser.setState(user.getState());
			result = userMapper.updateByPrimaryKey(inserUser);
		}
		return result;
	}

}
