package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.IVisitsDAO;
import net.rytong.entity.Visits;
import net.rytong.service.IVisitsService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VisitsServiceImpl implements IVisitsService, CRUDService<Visits> {
	
	@Autowired
	private IVisitsDAO iVisitsDAO;
	
	/**
	 * 模糊查询大客户
	 */
	public PagingEnumerator<Visits> customerMatch(String shortName, String companyName,
			String address, String contactor, String username, int pageIndex,
			int pageCount) {
		return iVisitsDAO.findByProperties(shortName, companyName, address, contactor, username, pageIndex, pageCount);
	}
	
	/**
	 * 统计数据
	 */
	public List<Object[]> getNumsByUser(Map<String, Object> filterMap) {
		return iVisitsDAO.getNumsByUser(filterMap);
	}
	
	/**
	 * 详细信息
	 */
	public List<Visits> getDetilsByUser(Map<String, Object> filterMap) {
		return iVisitsDAO.getDetilsByUser(filterMap);
	}
	
	
	/**
	 * 删除大客户
	 */
	public void delete(Visits customer) {
		iVisitsDAO.delete(customer);
	}

	/**
	 * 通过ID获得大客户
	 */
	public Visits findById(Long id) {
		return iVisitsDAO.findById(id);
	}
	
	public List<Visits> findByName(String name){
		return iVisitsDAO.findByCompanyName(name);
	}

	/**
	 * 添加大客户
	 */
	public void save(Visits customer) {
		iVisitsDAO.save(customer);
	}

	/**
	 * 更新大客户
	 */
	public Visits update(Visits customer) {
		return iVisitsDAO.update(customer);
	}
	/**
	 * 查询所有的大客户列表
	 */
	public List<Visits> findAll() {
		return iVisitsDAO.findAll(null);
	}

	public List<Visits> findByShotName(String shotName) {
		return iVisitsDAO.findByShotName(shotName, null);
	}

	@Override
	public void add(Visits t) {
		iVisitsDAO.save(t);
	}

	@Override
	public List<Visits> list() {
		return iVisitsDAO.findAll();
	}

	@Override
	public List<Visits> list(Map<String, Object> filterMap) {
		return iVisitsDAO.pageList(filterMap, 1, 20);
	}

	@Override
	public PagingEnumerator<Visits> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iVisitsDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public Visits view(String id) {
		return iVisitsDAO.findById(Long.valueOf(id));
	}
}
