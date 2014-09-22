package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.IArticleDAO;
import net.rytong.entity.Article;
import net.rytong.service.IArticleService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleServiceImpl implements IArticleService, CRUDService<Article> {
	@Autowired
	private IArticleDAO iArticleDAO;

	public void delete(Article page) {
		iArticleDAO.delete(page);
	}

	public Article findById(Long id) {
		return iArticleDAO.findById(id);
	}
	
	public void save(Article page) {
		iArticleDAO.save(page);
	}

	public Article update(Article page) {
		return iArticleDAO.update(page);
	}

	public List<Article> findAll() {
		return iArticleDAO.findAll(null);
	}

	@Override
	public void add(Article t) {
		iArticleDAO.save(t);
	}

	@Override
	public List<Article> list() {
		return iArticleDAO.findAll();
	}

	@Override
	public List<Article> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<Article> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iArticleDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public Article view(String id) {
		return iArticleDAO.findById(Long.valueOf(id));
	}
	@Override
	public List<Article> findByPageAndCustomer(Long pageId, String customer) {
		return iArticleDAO.findByPageAndCustomer(pageId, customer);
	}
	@Override
	public List<Article> findByLinkPageAndCustomer(Long pageId, String customer) {
		return iArticleDAO.findByLinkPageAndCustomer(pageId, customer);
	}
	
}
