package net.rytong.service;

import java.util.List;

import net.rytong.entity.ArticleTemplate;


public interface IArticleTemplateService {
	public List<ArticleTemplate> findByPageId(Long pageId);
}
