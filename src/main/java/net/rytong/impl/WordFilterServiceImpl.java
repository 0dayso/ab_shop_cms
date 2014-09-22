package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.IVisitDAO;
import net.rytong.entity.WordFilter;
import net.rytong.service.IWordFilterService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WordFilterServiceImpl implements IWordFilterService, CRUDService<WordFilter>{
	@Autowired
	private IVisitDAO iVisitDAO;
	
	public List<WordFilter> getAllWord(Map<String, Object> filterMap) {
		return iVisitDAO.getAllWord(filterMap);
	}
	
	@Override
	public void add(WordFilter t) {
		iVisitDAO.save(t);
	}

	@Override
	public void delete(WordFilter t) {
		iVisitDAO.delete(t);
	}

	@Override
	public List<WordFilter> list() {
		return iVisitDAO.findAll();
	}

	@Override
	public List<WordFilter> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<WordFilter> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iVisitDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public WordFilter update(WordFilter t) {
		return iVisitDAO.update(t);
	}

	@Override
	public WordFilter view(String id) {
		return iVisitDAO.findById(Long.valueOf(id));
	}
}
