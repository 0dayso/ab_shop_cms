package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.IWeixinUserDAO;
import net.rytong.entity.WeixinUser;
import net.rytong.service.IWeixinUserService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeixinUserServiceImpl implements IWeixinUserService, CRUDService<WeixinUser> {
	
	@Autowired
	private IWeixinUserDAO iWeixinUserDAO;

	@Override
	public void save(WeixinUser weixinUser) {
        iWeixinUserDAO.save(weixinUser);
	}

	@Override
	public void delete(WeixinUser weixinUser) {
		iWeixinUserDAO.delete(weixinUser);
	}

	@Override
	public WeixinUser getWeixinUserById(Long id) {
		return iWeixinUserDAO.findById(id);
	}

	@Override
	public WeixinUser update(WeixinUser weixinUser) {
		return iWeixinUserDAO.update(weixinUser);
	}

	@Override
	public PagingEnumerator<WeixinUser> weixinUserMatch(String name,
			String idNo, String mobile,String email, int startIndex, int maxCount) {
		return iWeixinUserDAO.weixinUserMatch(name, idNo, mobile, email, startIndex, maxCount);
	}

	@Override
	public void add(WeixinUser t) {
		iWeixinUserDAO.save(t);
	}

	@Override
	public List<WeixinUser> list() {
		return iWeixinUserDAO.findAll();
	}

	@Override
	public List<WeixinUser> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<WeixinUser> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iWeixinUserDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public WeixinUser view(String id) {
		return iWeixinUserDAO.findById(Long.valueOf(id));
	}

}
