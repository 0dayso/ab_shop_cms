package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.IWeixinTypeDAO;
import net.rytong.entity.WeixinType;
import net.rytong.service.IWeixinTypeService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeixinTypeServiceImpl implements IWeixinTypeService, CRUDService<WeixinType>{
	@Autowired
	private IWeixinTypeDAO iWeixinTypeDAO;
	
	@Override
	public void add(WeixinType t) {
		iWeixinTypeDAO.save(t);
	}

	@Override
	public void delete(WeixinType t) {
		iWeixinTypeDAO.delete(t);
	}

	@Override
	public List<WeixinType> list() {
		return iWeixinTypeDAO.findAll();
	}

	@Override
	public List<WeixinType> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<WeixinType> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iWeixinTypeDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public WeixinType update(WeixinType t) {
		return iWeixinTypeDAO.update(t);
	}

	@Override
	public WeixinType view(String id) {
		return iWeixinTypeDAO.findById(Long.valueOf(id));
	}

	@Override
	public List<WeixinType> findByCode(Object code) {
		return iWeixinTypeDAO.findByCode(code, null);
	}
}
