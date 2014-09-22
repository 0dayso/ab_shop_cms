package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.ICustomerDAO;
import net.rytong.entity.Customer;
import net.rytong.service.ICustomerService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceImpl implements ICustomerService, CRUDService<Customer> {
	
	@Autowired
	private ICustomerDAO iCustomerDAO;
	
	/**
	 * 模糊查询大客户
	 */
	public PagingEnumerator<Customer> customerMatch(String shortName, String companyName,
			String address, String contactor, String username, int pageIndex,
			int pageCount) {
		return iCustomerDAO.findByProperties(shortName, companyName, address, contactor, username, pageIndex, pageCount);
	}

	/**
	 * 删除大客户
	 */
	public void delete(Customer customer) {
		iCustomerDAO.delete(customer);
	}

	/**
	 * 通过ID获得大客户
	 */
	public Customer findById(Long id) {
		return iCustomerDAO.findById(id);
	}
	
	public List<Customer> findByName(String name){
		return iCustomerDAO.findByCompanyName(name);
	}

	/**
	 * 添加大客户
	 */
	public void save(Customer customer) {
		iCustomerDAO.save(customer);
	}

	/**
	 * 更新大客户
	 */
	public Customer update(Customer customer) {
		return iCustomerDAO.update(customer);
	}
	/**
	 * 查询所有的大客户列表
	 */
	public List<Customer> findAll() {
		return iCustomerDAO.findAll(null);
	}

	public List<Customer> findByShotName(String shotName) {
		return iCustomerDAO.findByShotName(shotName, null);
	}

	@Override
	public void add(Customer t) {
		iCustomerDAO.save(t);
	}

	@Override
	public List<Customer> list() {
		return iCustomerDAO.findAll();
	}

	@Override
	public List<Customer> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<Customer> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iCustomerDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public Customer view(String id) {
		return iCustomerDAO.findById(Long.valueOf(id));
	}

}
