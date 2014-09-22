package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.IWeixinRespDAO;
import net.rytong.entity.WeixinResp;
import net.rytong.service.IWeixinRespService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeixinRespServiceImpl implements IWeixinRespService, CRUDService<WeixinResp>{
	@Autowired
	private IWeixinRespDAO iWeixinRespDAO;
	
	@Override
	public void add(WeixinResp t) {
		iWeixinRespDAO.save(t);
	}

	@Override
	public void delete(WeixinResp t) {
		iWeixinRespDAO.delete(t);
	}

	@Override
	public List<WeixinResp> list() {
		return iWeixinRespDAO.findAll();
	}

	@Override
	public List<WeixinResp> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<WeixinResp> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iWeixinRespDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public WeixinResp update(WeixinResp t) {
		return iWeixinRespDAO.update(t);
	}

	@Override
	public WeixinResp view(String id) {
		return iWeixinRespDAO.findById(Integer.valueOf(id));
	}
}
