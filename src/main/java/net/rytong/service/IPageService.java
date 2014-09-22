package net.rytong.service;

import java.util.List;

import net.rytong.entity.Page;


public interface IPageService {
	public List<Page> findByLevelAndCustomer(String customer, int level);
	public Page findById(Long id, Long time);
}
