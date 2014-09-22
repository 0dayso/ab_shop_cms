package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.IRoleDAO;
import net.rytong.entity.Role;
import net.rytong.service.IRoleService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImpl implements IRoleService, CRUDService<Role>{
	@Autowired
	private IRoleDAO iRoleDAO;
	
	public PagingEnumerator<Role> findByProperties(String name,
			String departName, Integer beginTime,Integer endTime, int pageIndex, int pageSize) {
		return iRoleDAO.findByProperties(name, departName, beginTime, endTime, pageIndex, pageSize);
	}
	
	public void roleAdd(Role role) {
		iRoleDAO.save(role);
	}
	
	public List<Role> roleList(){
		return iRoleDAO.findAll(null);
	}

	public Role findById(Long id) {
		return iRoleDAO.findById(id);
	}

	public Role editRole(Role role) {
		return iRoleDAO.update(role);
	}

	public void delRole(Role role) {
		iRoleDAO.delete(role);
	}

	public List<Role> findByName(String name) {
		return iRoleDAO.findByName(name, null);
	}
	/**
	 * 验证角色是否存在
	 * @author RobotJi
	 * @param value
	 * @return boolean
	 * @date 2011-7-8
	 */
	public boolean isRoleExist(String value){
		return iRoleDAO.isRoleExist(value);
	}

	@Override
	public void add(Role t) {
		iRoleDAO.save(t);
	}

	@Override
	public void delete(Role t) {
		iRoleDAO.delete(t);
	}

	@Override
	public List<Role> list() {
		return iRoleDAO.findAll();
	}

	@Override
	public List<Role> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<Role> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iRoleDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public Role update(Role t) {
		return iRoleDAO.update(t);
	}

	@Override
	public Role view(String id) {
		return iRoleDAO.findById(Long.valueOf(id));
	}
	
}
