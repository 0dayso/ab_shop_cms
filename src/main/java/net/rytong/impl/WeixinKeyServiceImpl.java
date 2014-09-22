package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.IWeixinKeyDAO;
import net.rytong.entity.WeixinKey;
import net.rytong.service.IWeixinKeyService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeixinKeyServiceImpl implements IWeixinKeyService, CRUDService<WeixinKey>{
	@Autowired
	private IWeixinKeyDAO iWeixinKeyDAO;
	
	public List<WeixinKey> findByName(Object name) {
		return iWeixinKeyDAO.findByName(name);
	}
	
	public List<WeixinKey> findByNameAndCustomer(Object name, String customer) {
		return iWeixinKeyDAO.findByNameAndCustomer(name, customer);
	}
	
	public List<WeixinKey> findkeys() {
		return iWeixinKeyDAO.findkeys();
	}
	
	public List<WeixinKey> findkeysAndCustomer(String customer) {
		return iWeixinKeyDAO.findkeysAndCustomer(customer);
	}
	
	public List<WeixinKey> findByType(Object type) {
		return iWeixinKeyDAO.findByType(type);
	}
	
	public List<WeixinKey> findByTypeAndCustomer(Object type, String customer) {
		return iWeixinKeyDAO.findByTypeAndCustomer(type, customer);
	}
		
	@Override
	public void add(WeixinKey t) {
		iWeixinKeyDAO.save(t);
	}

	@Override
	public void delete(WeixinKey t) {
		iWeixinKeyDAO.delete(t);
	}

	@Override
	public List<WeixinKey> list() {
		return iWeixinKeyDAO.findAll();
	}

	@Override
	public List<WeixinKey> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<WeixinKey> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iWeixinKeyDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public WeixinKey update(WeixinKey t) {
		return iWeixinKeyDAO.update(t);
	}

	@Override
	public WeixinKey view(String id) {
		return iWeixinKeyDAO.findById(Integer.valueOf(id));
	}

}
