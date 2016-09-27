package com.zq.fin.seckill.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.zq.fin.core.generic.GenericService;
import com.zq.fin.seckill.dao.RoleMapper;
import com.zq.fin.seckill.entity.Role;

/**
 * 角色 业务接口
 * 
 * @author StarZou
 * @since 2014年6月10日 下午4:15:01
 **/
@Service
@Component
public class RoleService implements GenericService<Role, Long> {
	
	@Resource
    private RoleMapper roleMapper;

    /**
     * 通过用户id 查询用户 拥有的角色
     * 
     * @param userId
     * @return
     */
    public List<Role> selectRolesByUserId(Long userId) {
        return roleMapper.selectRolesByUserId(userId);
    }

	@Override
	public int insert(Role model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Role model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Role selectById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> selectList() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
