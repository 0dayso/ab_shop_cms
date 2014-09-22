package net.rytong.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.dao.IParameterDAO;
import net.rytong.entity.Article;
import net.rytong.entity.ArticleTemplate;
import net.rytong.entity.Employee;
import net.rytong.entity.Page;
import net.rytong.entity.PageTemplate;
import net.rytong.impl.ArticleServiceImpl;
import net.rytong.impl.ArticleTemplateServiceImpl;
import net.rytong.impl.PageServiceImpl;
import net.rytong.impl.PageTemplateServiceImpl;
import net.rytong.utils.FileUtil;
import net.rytong.utils.RandomUtils;
import net.rytong.vo.ArticleVo;
import net.rytong.vo.PagePathVo;
import net.rytong.vo.PageTemplateVo;
import net.rytong.vo.PageVo;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class ArticleAction extends CRUDActionSupport<Article> {
	private static final long serialVersionUID = 5207197603748720772L;
	@Autowired
	private ArticleServiceImpl articleService;
	@Autowired
	private PageServiceImpl pageService;
	@Autowired
	private IParameterDAO iParameterDAO;
	@Autowired
	private PageTemplateServiceImpl pageTemplateService;
	@Autowired
	private ArticleTemplateServiceImpl articleTemplateService;
	
	@Override
	public String execute() throws Exception{
		return SUCCESS;
	}

	@Override
	public CRUDService<Article> getAutowiredService() {
		return articleService;
	}
	
	public String modifyColor() throws Exception{
		Article article = this.getEntity();
		articleService.update(article);
		updatePage(article.getPage());
		return NONE;
	}
	
	private void updatePage(Page page) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String fillPath = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORHTML","0").get(0).getValue();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		List<Article> articles = articleService.findByPageAndCustomer(page.getId(), employee.getCustomerName());
		List<Page> pages = pageService.findByLevelAndCustomer(employee.getCustomerName(), 1);
		FileUtil.updatePage(pages.get(0).getUrl(), fillPath + "index.html", fillPath + "article.html", fillPath + page.getName(), page, articles);
	}
	
	public String modifyStyle() throws Exception{
		Article article = this.getEntity();
		articleService.update(article);
		updatePage(article.getPage());
		JSONObject obj = new JSONObject(new ArticleVo(article));
		obj.put("code", "ok");
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}
	
	public String updateArticle() throws Exception{
		String result = getTipResult();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Employee employee = (Employee)session.getAttribute("employee");
			String fillPath = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORHTML","0").get(0).getValue();
			String showPath = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORSHOW","0").get(0).getValue();
			Article article = this.getEntity();
			String content = request.getParameter("content");
			String isLinkPage = request.getParameter("isLinkPage");
			
			Page linkPage = article.getLinkPage();
			Page atPage = article.getPage();
			if (linkPage != null) {
				if ("0".equals(isLinkPage)) { 
					linkPage.setPageType(2); //内容页
					linkPage.setTitle(article.getTitle());
					linkPage.setContent(content);
					articleService.update(article);
				} else if ("1".equals(isLinkPage)) {
					linkPage.setPageType(1); // 链接页
					String templateId = request.getParameter("templateId");
					PageTemplate pageTemplate = pageTemplateService.view(templateId);
					List<Article> articles = articleService.findByPageAndCustomer(Long.valueOf(linkPage.getId()), employee.getCustomerName());
					List<ArticleTemplate> articleTemplates = articleTemplateService.findByPageId(pageTemplate.getId());
					templateToArticles(linkPage, articles, articleTemplates);
					if (linkPage.getPageType() == 2) {
						templateToPage(atPage, linkPage, pageTemplate);
					} else if (linkPage.getPageType() == 1) {
						linkPage.setMoban(String.valueOf(pageTemplate.getId()));
					}
					updatePage(linkPage);
					JSONObject obj = new JSONObject(new PageVo(linkPage));
					List<ArticleVo> articleVos = ArticleVo.convert(articles);
					obj.put("code", "ok");
					obj.put("article", articleVos);
					List<Page> pages = getPagesfromLastPage(linkPage.getLastPage());
					List<PagePathVo> path = PagePathVo.convert(pages);
					obj.put("path", path);
					result = obj.toString();
				}
			} else {
				String pageName = "CH" + new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()) + RandomUtils.random(3) + ".html";
				linkPage = new Page();
				linkPage.setTitle(article.getTitle());
				linkPage.setName(pageName);
				linkPage.setLevel(atPage.getLevel() + 1);
				linkPage.setUrl(showPath + pageName);
				linkPage.setContent(content);
				if ("0".equals(isLinkPage)) {
					linkPage.setPageType(2); // 内容页
				} else if ("1".equals(isLinkPage)) {
					linkPage.setPageType(1); // 链接页
				}
				linkPage.setVisitCount(0);
				linkPage.setCustomer(employee.getCustomerName());
				pageService.save(linkPage);
				
				linkPage.setLastPage(atPage.getLastPage() + "|" + String.valueOf(linkPage.getId()));
				pageService.update(linkPage);
				
				article.setLinkPage(linkPage);
				articleService.update(article);
				
				if ("0".equals(isLinkPage)) { // 内容页
					String mobanHtml = FileUtil.readFile(fillPath + "content.html");
					mobanHtml = mobanHtml.replaceAll("\\{PAGE_MAIN\\}", atPage.getUrl());
					mobanHtml = mobanHtml.replaceAll("\\{PAGE_TITLE\\}", article.getTitle());
					mobanHtml = mobanHtml.replaceAll("\\{PAGE_CONTENT_OTHER\\}", "");
					mobanHtml = mobanHtml.replaceAll("\\{PAGE_CONTENT\\}", content);
					FileUtil.saveFile(fillPath + pageName, mobanHtml);
					
					updatePage(atPage);
					JSONObject obj = new JSONObject(new ArticleVo(article));
					obj.put("code", "ok");
					result = obj.toString();
				} else if ("1".equals(isLinkPage)) { // 链接页
					String templateId = request.getParameter("templateId");
					String mobanHtml = FileUtil.readFile(fillPath + "index.html");
					PageTemplate pageTemplate = pageTemplateService.view(templateId);
					FileUtil.saveFile(fillPath + pageName, mobanHtml);
					List<Article> articles = new ArrayList<Article>();
					List<ArticleTemplate> articleTemplates = articleTemplateService.findByPageId(pageTemplate.getId());
					templateToPage(atPage, linkPage, pageTemplate);
					templateToArticles(linkPage, articles, articleTemplates);
					updatePage(atPage);
					updatePage(linkPage);
					
					JSONObject obj = new JSONObject(new PageVo(linkPage));
					List<ArticleVo> articleVos = ArticleVo.convert(articles);
					obj.put("code", "ok");
					obj.put("article", articleVos);
					List<Page> pages = getPagesfromLastPage(linkPage.getLastPage());
					List<PagePathVo> path = PagePathVo.convert(pages);
					obj.put("path", path);
					result = obj.toString();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			result = getTipResult("error", "error");
		}
		this.setAjaxInputStream(result);
		return AJAX;
	}
	
	private List<Page> getPagesfromLastPage(String lastPage) {
		List<Page> pages = new ArrayList<Page>();
		String[] pageStr = lastPage.split("\\|");
		for (int i = 0; i < pageStr.length; i++) {
			Page page = pageService.view(pageStr[i]);
			pages.add(page);
		}
		return pages;
	}
	
	//修改页面的标题颜色、页面背景
	private void templateToPage(Page page, Page linkPage, PageTemplate pageTemplate) {
		linkPage.setTitleBgColor(page.getTitleBgColor());
		linkPage.setBannerImage(page.getBannerImage());
		linkPage.setBgImage(page.getBgImage());
		linkPage.setMoban(String.valueOf(pageTemplate.getId()));
		pageService.update(page);
	}
	
	//修改页面各版本样式
	private void templateToArticles(Page page, List<Article> articles, List<ArticleTemplate> articleTemplates) {
		int size = articles.size();
		deleteRemainArticles(articles, articleTemplates);
		for (int i = 0; i < articleTemplates.size(); i++) { 
			Article article = copyTemplateToArticle(page, articleTemplates.get(i), size > i ? articles.get(i) : null);
			if (article != null) {
				articles.add(article);
			}
		}
	}
	
	private void deleteRemainArticles(List<Article> articles, List<ArticleTemplate> articleTemplates) {
		if (articles.size() > articleTemplates.size()) {
			for (int pos = articleTemplates.size(); pos < articles.size(); pos++) {
				articleService.delete(articles.get(pos));
				articles.remove(articles.get(pos));
			}
		}
	}
	
	private Article copyTemplateToArticle(Page page, ArticleTemplate articleTemplate, Article article) {
		if (article == null) {
			return createNewArticleAtPage(page, articleTemplate);
		} else {
			return updateArticleAtPage(articleTemplate, article);
		}
	}

	private Article updateArticleAtPage(ArticleTemplate articleTemplate, Article article) {
		article.setBgColor(articleTemplate.getBgColor());
		article.setStyle(articleTemplate.getStyle());
		article.setPosition(articleTemplate.getPosition());
		articleService.update(article);
		return null;
	}

	private Article createNewArticleAtPage(Page page, ArticleTemplate articleTemplate) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("employee");
		Article article = new Article();
		article.setBgColor(articleTemplate.getBgColor());
		article.setStyle(articleTemplate.getStyle());
		article.setPosition(articleTemplate.getPosition());
		article.setTitle("");
		article.setPage(page);
		article.setCustomer(employee.getCustomerName());
		
		articleService.add(article);
		return article;
	}
	
	// 浏览
	public String detailJSON() throws Exception {
		Article article = this.getEntity();
		Page linkPage = article.getLinkPage();
		PageTemplate pageTemplate = null;
		if (linkPage != null) {
			String mobanId = linkPage.getMoban();
			if (linkPage.getPageType() == 1) {
				pageTemplate = pageTemplateService.view(mobanId);
			}
		}
		JSONObject obj = new JSONObject(new ArticleVo(article));
		obj.put("code", "ok");
		obj.put("page", new JSONObject(new PageVo(linkPage)));
		obj.put("moban", new JSONObject(new PageTemplateVo(pageTemplate)));
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}
	
	public String modifyTitle() throws Exception{
		String result = getTipResult();
		Article article = this.getEntity();
		articleService.update(article);
		updatePage(article.getPage());
		this.setAjaxInputStream(result);
		return AJAX;
	}
}
