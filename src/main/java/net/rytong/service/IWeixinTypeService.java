package net.rytong.service;

import java.util.List;

import net.rytong.entity.WeixinType;

public interface IWeixinTypeService {
	public List<WeixinType> findByCode(Object code);
}
