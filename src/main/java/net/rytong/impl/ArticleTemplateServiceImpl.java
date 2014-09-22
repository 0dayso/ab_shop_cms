package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.IArticleTemplateDAO;
import net.rytong.entity.ArticleTemplate;
import net.rytong.service.IArticleTemplateService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleTemplateServiceImpl implements IArticleTemplateService, CRUDService<ArticleTemplate> {
	@Autowired
	private IArticleTemplateDAO iArticleTemplateDAO;

	public void delete(ArticleTemplate page) {
		iArticleTemplateDAO.delete(page);
	}

	public ArticleTemplate findById(Long id) {
		return iArticleTemplateDAO.findById(id);
	}
	
	public void save(ArticleTemplate page) {
		iArticleTemplateDAO.save(page);
	}

	public ArticleTemplate update(ArticleTemplate page) {
		return iArticleTemplateDAO.update(page);
	}

	public List<ArticleTemplate> findAll() {
		return iArticleTemplateDAO.findAll(null);
	}

	@Override
	public void add(ArticleTemplate t) {
		iArticleTemplateDAO.save(t);
	}

	@Override
	public List<ArticleTemplate> list() {
		return iArticleTemplateDAO.findAll();
	}

	@Override
	public List<ArticleTemplate> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<ArticleTemplate> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iArticleTemplateDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public ArticleTemplate view(String id) {
		return iArticleTemplateDAO.findById(Long.valueOf(id));
	}
	@Override
	public List<ArticleTemplate> findByPageId(Long pageId) {
		return iArticleTemplateDAO.findByPageId(pageId);
	}
}
