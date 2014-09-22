package net.rytong.service;

import java.util.List;
import java.util.Map;

import net.rytong.entity.Visits;
import net.rytong.utils.PagingEnumerator;

public interface IVisitsService {

	/**
	 * 通过ID获得大客户
	 * @param id
	 * @return
	 */
	public Visits findById(Long id);
	
	/**
	 * 根据公司名称查找客户
	 * @param name
	 * @return
	 */
	public List<Visits> findByName(String name);

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
	public PagingEnumerator<Visits> customerMatch(String shortName, String companyName,
			String address, String contactor, String username, int pageIndex,
			int pageCount);

	/**
	 * 添加大客户
	 * @param customer
	 */
	public void save(Visits customer);

	/**
	 * 更新大客户
	 * @param customer
	 * @return
	 */
	public Visits update(Visits customer);

	/**
	 * 删除大客户
	 * @param customer
	 */
	public void delete(Visits customer);
	
	public List<Visits> findAll();
	
	public List<Visits> findByShotName(String shotName);
	
	public List<Object[]> getNumsByUser(Map<String, Object> filterMap);
	
	public List<Visits> getDetilsByUser(Map<String, Object> filterMap);
}
