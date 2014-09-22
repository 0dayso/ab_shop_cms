package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.IPageTemplateDAO;
import net.rytong.entity.PageTemplate;
import net.rytong.service.IPageTemplateService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageTemplateServiceImpl implements IPageTemplateService, CRUDService<PageTemplate> {
	@Autowired
	private IPageTemplateDAO iPageTemplateDAO;

	public void delete(PageTemplate PageTemplate) {
		iPageTemplateDAO.delete(PageTemplate);
	}

	public PageTemplate findById(Long id) {
		return iPageTemplateDAO.findById(id);
	}
	
	public PageTemplate findById(Long id, Long time){
		return iPageTemplateDAO.findById(id, time);
	}
	
	public void save(PageTemplate PageTemplate) {
		iPageTemplateDAO.save(PageTemplate);
	}

	public PageTemplate update(PageTemplate PageTemplate) {
		return iPageTemplateDAO.update(PageTemplate);
	}

	public List<PageTemplate> findAll() {
		return iPageTemplateDAO.findAll(null);
	}
	
	public List<PageTemplate> findByCustomer(String customer) {
		return iPageTemplateDAO.findByCustomer(customer);
	}
	
	public List<PageTemplate> findByLevelAndCustomer(String customer, int level) {
		return iPageTemplateDAO.findByLevelAndCustomer(customer, level);
	}

	@Override
	public void add(PageTemplate t) {
		iPageTemplateDAO.save(t);
	}

	@Override
	public List<PageTemplate> list() {
		return iPageTemplateDAO.findAll();
	}

	@Override
	public List<PageTemplate> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<PageTemplate> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iPageTemplateDAO.PageList(filterMap, pageNo, pageSize);
	}

	@Override
	public PageTemplate view(String id) {
		return iPageTemplateDAO.findById(Long.valueOf(id));
	}
}
