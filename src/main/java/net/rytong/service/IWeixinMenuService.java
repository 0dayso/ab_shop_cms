package net.rytong.service;

import java.util.List;

import net.rytong.entity.WeixinMenu;

public interface IWeixinMenuService {
	public List<WeixinMenu> findByLevel(Object level);
	public List<WeixinMenu> findByLevelAndParent(Object level, Object subId);
	public List<WeixinMenu> findByLevelAndCustomer(Object level, String customer);
	public List<WeixinMenu> findByTypeAndCustomer(Object type, String customer);
}
