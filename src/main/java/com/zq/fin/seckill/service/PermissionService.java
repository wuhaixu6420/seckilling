package com.zq.fin.seckill.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.zq.fin.core.generic.GenericService;
import com.zq.fin.seckill.dao.PermissionMapper;
import com.zq.fin.seckill.entity.Permission;


/**
 * 权限 业务接口
 * 
 * @author StarZou
 * @since 2014年6月10日 下午12:02:39
 **/
@Service
@Component
public class PermissionService implements GenericService<Permission, Long> {
	
	@Resource
    private PermissionMapper permissionMapper;

    /**
     * 通过角色id 查询角色 拥有的权限
     * 
     * @param roleId
     * @return
     */
    public List<Permission> selectPermissionsByRoleId(Long roleId) {
        return permissionMapper.selectPermissionsByRoleId(roleId);
    }

	@Override
	public int insert(Permission model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Permission model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Permission selectById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Permission selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permission> selectList() {
		// TODO Auto-generated method stub
		return null;
	}

}
