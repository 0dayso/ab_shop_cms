package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.IEmployeeDAO;
import net.rytong.entity.Employee;
import net.rytong.service.IEmployeeService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeServiceImpl implements IEmployeeService, CRUDService<Employee> {
	@Autowired
	private IEmployeeDAO iEmployeeDAO;
	
	public void delete(Employee employee) {
		iEmployeeDAO.delete(employee);
	}

	public Employee findById(Long id) {
		return iEmployeeDAO.findById(id);
	}

	public void save(Employee employee) {
		iEmployeeDAO.save(employee);
	}

	public Employee update(Employee employee) {
		return iEmployeeDAO.update(employee);
	}

	public PagingEnumerator<Employee> findByProperties(String login,
			String name, String idType, String idNo, String gender,
			String mobile, String area, String address, Long signStartTime,
			Long signEndTime, String email, String postcode, int pageIndex,
			int pageCount) {
		return iEmployeeDAO.findByProperties(login, name, idType, idNo, gender, mobile, area, address, signStartTime, signEndTime, email, postcode ,pageIndex, pageCount);
	}

	public List<Employee> findByLogin(String login) {
		return iEmployeeDAO.findByLogin(login, null);
	}

	@Override
	public void add(Employee t) {
		iEmployeeDAO.save(t);
	}

	@Override
	public List<Employee> list() {
		return iEmployeeDAO.findAll();
	}

	@Override
	public List<Employee> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<Employee> pageList(Map<String, Object> filterMap,int pageNo, int pageSize) {
		return iEmployeeDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public Employee view(String id) {
		return iEmployeeDAO.findById(Long.valueOf(id));
	}

}
