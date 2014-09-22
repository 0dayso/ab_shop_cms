package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.IResourceDAO;
import net.rytong.entity.Resource;
import net.rytong.service.IResourceService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourceServiceImpl implements IResourceService, CRUDService<Resource>{
	@Autowired
	private IResourceDAO iResourceDAO;
	
	@Override
	public List<Resource> findByType(Object type) {
		return iResourceDAO.findByType(type);
	}
	@Override
	public List<Resource> findByTypeAndCustomer(Object type, String customer) {
		return iResourceDAO.findByTypeAndCustomer(type, customer);
	}
	
	@Override
	public void add(Resource t) {
		iResourceDAO.save(t);
	}

	@Override
	public void delete(Resource t) {
		iResourceDAO.delete(t);
	}

	@Override
	public List<Resource> list() {
		return iResourceDAO.findAll();
	}

	@Override
	public List<Resource> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<Resource> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iResourceDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public Resource update(Resource t) {
		return iResourceDAO.update(t);
	}

	@Override
	public Resource view(String id) {
		return iResourceDAO.findById(Integer.valueOf(id));
	}
	@Override
	public List<Object[]> countsByCustomer(String customer) {
		return iResourceDAO.countsByCustomer(customer);
	}
	
	
}
