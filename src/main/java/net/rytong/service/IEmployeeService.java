package net.rytong.service;

import java.util.List;

import net.rytong.entity.Employee;
import net.rytong.utils.PagingEnumerator;

public interface IEmployeeService {

	/**
	 * 添加员工
	 * @param employee
	 */
	public void save(Employee employee);

	/**
	 * 删除员工
	 * @param employee
	 */
	public void delete(Employee employee);

	/**
	 * 修改员工
	 * @param employee
	 * @return
	 */
	public Employee update(Employee employee);

	/**
	 * 通过ID获得员工信息
	 * @param id
	 * @return
	 */
	public Employee findById(Long id);

	/**
	 * 模糊查询员工
	 * @param login
	 * @param name
	 * @param idType
	 * @param idNo
	 * @param gender
	 * @param mobile
	 * @param area
	 * @param address
	 * @param signStartTime
	 * @param signEndTime
	 * @param email
	 * @param postcode
	 * @param pageIndex
	 * @param pageCount
	 * @return
	 */
	public PagingEnumerator<Employee> findByProperties(String login,
			String name, String idType, String idNo, String gender,
			String mobile, String area, String address, Long signStartTime,
			Long signEndTime, String email, String postcode, int pageIndex,
			int pageCount);
	
	public List<Employee> findByLogin(String login);
}
