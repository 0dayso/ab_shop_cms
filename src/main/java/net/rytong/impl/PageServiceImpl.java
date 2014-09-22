package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.IPageDAO;
import net.rytong.entity.Page;
import net.rytong.service.IPageService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageServiceImpl implements IPageService, CRUDService<Page> {
	@Autowired
	private IPageDAO iPageDAO;

	public void delete(Page page) {
		iPageDAO.delete(page);
	}

	public Page findById(Long id) {
		return iPageDAO.findById(id);
	}
	
	public Page findById(Long id, Long time){
		return iPageDAO.findById(id, time);
	}
	
	public void save(Page page) {
		iPageDAO.save(page);
	}

	public Page update(Page page) {
		return iPageDAO.update(page);
	}

	public List<Page> findAll() {
		return iPageDAO.findAll(null);
	}
	
	public List<Page> findByCustomer(String customer) {
		return iPageDAO.findByCustomer(customer);
	}
	
	public List<Page> findByLevelAndCustomer(String customer, int level) {
		return iPageDAO.findByLevelAndCustomer(customer, level);
	}

	@Override
	public void add(Page t) {
		iPageDAO.save(t);
	}

	@Override
	public List<Page> list() {
		return iPageDAO.findAll();
	}

	@Override
	public List<Page> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<Page> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iPageDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public Page view(String id) {
		return iPageDAO.findById(Long.valueOf(id));
	}
}
