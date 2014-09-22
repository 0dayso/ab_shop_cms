package net.rytong.service;

import java.util.List;

import net.rytong.entity.Customer;
import net.rytong.utils.PagingEnumerator;

public interface ICustomerService {

	/**
	 * 通过ID获得大客户
	 * @param id
	 * @return
	 */
	public Customer findById(Long id);
	
	/**
	 * 根据公司名称查找客户
	 * @param name
	 * @return
	 */
	public List<Customer> findByName(String name);

	/**
	 * 模糊查询大客户
	 * @param shortName
	 * @param companyName
	 * @param address
	 * @param contactor
	 * @param username
	 * @param pageIndex
	 * @param pageCount
	 * @return
	 */
	public PagingEnumerator<Customer> customerMatch(String shortName, String companyName,
			String address, String contactor, String username, int pageIndex,
			int pageCount);

	/**
	 * 添加大客户
	 * @param customer
	 */
	public void save(Customer customer);

	/**
	 * 更新大客户
	 * @param customer
	 * @return
	 */
	public Customer update(Customer customer);

	/**
	 * 删除大客户
	 * @param customer
	 */
	public void delete(Customer customer);
	
	public List<Customer> findAll();
	
	public List<Customer> findByShotName(String shotName);
}
