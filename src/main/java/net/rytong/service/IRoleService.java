/**
 * 
 */
package net.rytong.service;


import java.util.List;

import net.rytong.entity.Role;
import net.rytong.utils.PagingEnumerator;

/**
 * @author ZhangGuohua
 *
 */
public interface IRoleService {
	
	/**
	 * 验证角色是否存在
	 * @author RobotJi
	 * @param value
	 * @return boolean
	 * @date 2011-7-8
	 */
	public boolean isRoleExist(String value);
	/**
	 * 查询角色
	 * @param name
	 * @param departName
	 * @param createDate
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PagingEnumerator<Role> findByProperties(String name,String departName,Integer beginTime,Integer endTime,int pageIndex,int pageSize);
	
	/**
	 * 添加角色
	 * @param role
	 */
	public void roleAdd(Role role);
	
	/**
	 * 查询所有角色
	 */
	public List<Role>  roleList();
	
	/**
	 * 根据ID查询角色
	 * @param id
	 * @return
	 */
	public Role findById(Long id);
	
	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	public Role editRole(Role role);
	
	/**
	 * 删除角色
	 * @param role
	 */
	public void delRole(Role role);
	
	/**
	 * 根据名称查询角色
	 * @param name
	 * @return
	 */
	public List<Role> findByName(String name);
}
