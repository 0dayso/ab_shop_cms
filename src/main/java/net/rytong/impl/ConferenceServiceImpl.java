package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.IConferenceDAO;
import net.rytong.entity.Conference;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConferenceServiceImpl implements CRUDService<Conference>{
	
	@Autowired
	private IConferenceDAO iConferenceDAO;
	

	@Override
	public List<Conference> list() {
		return iConferenceDAO.findAll(null);
	}

	@Override
	public List<Conference> list(Map<String, Object> filterMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingEnumerator<Conference> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iConferenceDAO.pageList(filterMap, pageNo, pageSize);
	}

	public List<Conference> getAllData(Map<String,Object> filterMap){
		return iConferenceDAO.getAllData(filterMap);
	}	
	@Override
	public Conference view(String id) {
		return iConferenceDAO.findById(Long.parseLong(id));
	}

	@Override
	public void add(Conference t) {
		iConferenceDAO.save(t);
	}

	@Override
	public Conference update(Conference t) {
		return iConferenceDAO.update(t);
	}

	@Override
	public void delete(Conference t) {
		iConferenceDAO.delete(t);
	}

	
	
}
