package net.rytong.service;

import java.util.List;

import net.rytong.entity.WeixinKey;


public interface IWeixinKeyService {
	public List<WeixinKey> findByName(Object name);
	public List<WeixinKey> findkeys();
	public List<WeixinKey> findkeysAndCustomer(String customer);
	public List<WeixinKey> findByNameAndCustomer(Object name, String customer);
	public List<WeixinKey> findByTypeAndCustomer(Object type, String customer);
}
