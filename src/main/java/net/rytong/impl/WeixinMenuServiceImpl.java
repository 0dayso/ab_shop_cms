package net.rytong.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.rytong.core.CRUDService;
import net.rytong.dao.IWeixinMenuDAO;
import net.rytong.entity.WeixinMenu;
import net.rytong.service.IWeixinMenuService;
import net.rytong.utils.PagingEnumerator;

@Component
public class WeixinMenuServiceImpl implements IWeixinMenuService, CRUDService<WeixinMenu>{
	@Autowired
	private IWeixinMenuDAO iWeixinMenuDAO;
	
	public List<WeixinMenu> findByType(Object type) {
		return iWeixinMenuDAO.findByType(type);
	}
	
	@Override
	public List<WeixinMenu> findByTypeAndCustomer(Object type, String customer) {
		return iWeixinMenuDAO.findByTypeAndCustomer(type, customer);
	}
	
	public List<WeixinMenu> findByLevel(Object level) {
		return iWeixinMenuDAO.findByLevel(level);
	}
	
	public List<WeixinMenu> findByLevelAndCustomer(Object level, String customer) {
		return iWeixinMenuDAO.findByLevelAndCustomer(level, customer);
	}
	
	public List<WeixinMenu> findByLevelAndParent(Object level, Object subId) {
		return iWeixinMenuDAO.findByLevelAndParent(level, subId);
	}
	
	@Override
	public void add(WeixinMenu t) {
		iWeixinMenuDAO.save(t);
	}

	@Override
	public void delete(WeixinMenu t) {
		iWeixinMenuDAO.delete(t);
	}

	@Override
	public List<WeixinMenu> list() {
		return iWeixinMenuDAO.findAll();
	}

	@Override
	public List<WeixinMenu> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<WeixinMenu> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iWeixinMenuDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public WeixinMenu update(WeixinMenu t) {
		return iWeixinMenuDAO.update(t);
	}

	@Override
	public WeixinMenu view(String id) {
		return iWeixinMenuDAO.findById(Integer.valueOf(id));
	}
}
