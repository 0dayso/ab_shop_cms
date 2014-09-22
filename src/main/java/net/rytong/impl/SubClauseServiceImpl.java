package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.ISubclauesDAO;
import net.rytong.entity.Subclaues;
import net.rytong.service.ISubClauseServiceImpl;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubClauseServiceImpl implements ISubClauseServiceImpl, CRUDService<Subclaues> {
	@Autowired
	private ISubclauesDAO iSubClauesDAO;
	
	@Override
	public List<Subclaues> list() {
		return iSubClauesDAO.findAll(null);
	}

	@Override
	public List<Subclaues> list(Map<String, Object> filterMap) {
		return iSubClauesDAO.list(filterMap);
	}

	@Override
	public PagingEnumerator<Subclaues> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return null;
	}

	@Override
	public Subclaues view(String id) {
		return iSubClauesDAO.findById(Long.valueOf(id));
	}

	@Override
	public void add(Subclaues t) {
		iSubClauesDAO.save(t);
	}

	@Override
	public Subclaues update(Subclaues t) {
		return iSubClauesDAO.update(t);
	}

	@Override
	public void delete(Subclaues t) {
		iSubClauesDAO.delete(t);
	}

	

}
