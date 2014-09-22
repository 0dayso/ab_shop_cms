package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.ITopDAO;
import net.rytong.entity.Top;
import net.rytong.service.ITopService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopServiceImpl implements ITopService, CRUDService<Top>{
	@Autowired
	private ITopDAO iTopDAO;

	@Override
	public List<Top> list() {
		return iTopDAO.findAll(null);
	}

	@Override
	public List<Top> list(Map<String, Object> filterMap) {
		return iTopDAO.list(filterMap);
	}

	@Override
	public PagingEnumerator<Top> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iTopDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public Top view(String id) {
		return iTopDAO.findById(Long.valueOf(id));
	}

	@Override
	public void add(Top t) {
		iTopDAO.save(t);
	}

	@Override
	public Top update(Top t) {
		return iTopDAO.update(t);
	}

	@Override
	public void delete(Top t) {
		iTopDAO.delete(t);
	}
}
