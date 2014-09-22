package net.rytong.service;

import java.util.List;

import net.rytong.entity.PageTemplate;


public interface IPageTemplateService {
	public List<PageTemplate> findByLevelAndCustomer(String customer, int level);
	public PageTemplate findById(Long id, Long time);
}
