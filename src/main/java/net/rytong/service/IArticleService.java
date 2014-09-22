package net.rytong.service;

import java.util.List;

import net.rytong.entity.Article;


public interface IArticleService {
	public List<Article> findByPageAndCustomer(Long pageId, String customer);
	public List<Article> findByLinkPageAndCustomer(Long pageId, String customer);
}
