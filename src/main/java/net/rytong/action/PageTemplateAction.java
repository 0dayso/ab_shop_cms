package net.rytong.action;

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
import net.rytong.utils.PagingEnumerator;
import net.rytong.vo.ArticleVo;
import net.rytong.vo.PagePathVo;
import net.rytong.vo.PageTemplateVo;
import net.rytong.vo.PageVo;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class PageTemplateAction extends CRUDActionSupport<PageTemplate> {
	private static final long serialVersionUID = 5207197603748720772L;
	@Autowired
	private PageTemplateServiceImpl pageTemplateService;
	@Autowired
	private PageServiceImpl pageService;
	@Autowired
	private ArticleTemplateServiceImpl articleTemplateService;
	@Autowired
	private ArticleServiceImpl articleService;
	@Autowired
	private IParameterDAO iParameterDAO;
	
	@Override
	public String execute() throws Exception{
		return SUCCESS;
	}

	@Override
	public CRUDService<PageTemplate> getAutowiredService() {
		return pageTemplateService;
	}
	
	public String getAllTemplate() throws Exception {
		String result = getTipResult();
		PagingEnumerator<PageTemplate> list = pageTemplateService.pageList(this.getFilterMap(), this.getPageIndex(), 2); 
		this.setPageCount(list.getPageCount(2));
		this.setTotal(list.getPageCount(1));
		
		if (list != null) {
			JSONObject obj = new JSONObject();
			obj.put("code", "ok");
			obj.put("pages", PageTemplateVo.convert(list));
			int pageIndex = this.getPageIndex();
			int pageCount = this.getPageCount();
			obj.put("pageIndex", pageIndex);
			obj.put("pageCount", pageCount);
			
			int step = 3, stepLength = 6;
			if (pageIndex > step) {
				obj.put("showPageHead", pageIndex - step + 1);
				int t = pageIndex - step + stepLength;
				obj.put("showPageEnd", pageCount > t ? t : pageCount);
			} else {
				obj.put("showPageHead", 1);
				obj.put("showPageEnd", pageCount > stepLength ? stepLength : pageCount);
			}
			result = obj.toString();
		} 
		this.setAjaxInputStream(result);
		return AJAX;
	}
	
	public String changePageTemplate() throws Exception {
		String result = getTipResult();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Employee employee = (Employee)session.getAttribute("employee");
			PageTemplate pageTemplate = this.getEntity();
			String pageId = request.getParameter("pageId");
			Page atPage = pageService.view(pageId);
			List<Article> articles = articleService.findByPageAndCustomer(Long.valueOf(pageId), employee.getCustomerName());
			List<ArticleTemplate> articleTemplates = articleTemplateService.findByPageId(pageTemplate.getId());
			templateToArticles(atPage, articles, articleTemplates);
			templateToPage(atPage, pageTemplate);
			updatePage(atPage);
			JSONObject obj = new JSONObject(new PageVo(atPage));
			List<ArticleVo> articleVos = ArticleVo.convert(articles);
			List<PagePathVo> path = PagePathVo.convert(atPage);
			obj.put("code", "ok");
			obj.put("article", articleVos);
			obj.put("path", path);
			result = obj.toString();
		} catch(Exception e) {
			e.printStackTrace();
			result = getTipResult("error", "error");
		}
		this.setAjaxInputStream(result);
		return AJAX;
	}
	
	public String getTemplate() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Long pageId = Long.parseLong(request.getParameter("id"));
		List<ArticleTemplate> list = null;
		JSONArray json = new JSONArray();
		String flag="ok";
		try{
			list = articleTemplateService.findByPageId(pageId);
		}catch(Exception e){
			e.printStackTrace();
			flag="no";
		}
		for (int i = 0; i < list.size(); i++) {
			JSONObject obj = new JSONObject();
			ArticleTemplate res = list.get(i);
			obj.put("id", res.getId().toString());
			obj.put("bgColor",res.getBgColor());
			obj.put("style",res.getStyle());
			obj.put("position", res.getPosition());
			obj.put("code", flag);
			obj.put("title", res.getTitle());
			json.put(obj);
		} 
		System.out.println(json.toString());
		this.setAjaxInputStream(json.toString());
		return AJAX;
	}
	
	private void updatePage(Page page) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String fillPath = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORHTML","0").get(0).getValue();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		List<Article> articles = articleService.findByPageAndCustomer(page.getId(), employee.getCustomerName());
		List<Page> pages = pageService.findByLevelAndCustomer(employee.getCustomerName(), 1);
		FileUtil.updatePage(pages.get(0).getUrl(), fillPath + "index.html", fillPath + "article.html", fillPath + page.getName(), page, articles);
	}
	
	public String getInfos() throws Exception {
		List<PageTemplate> list = null;
		JSONArray json = new JSONArray();
		try{
			list = pageTemplateService.findAll();
		}catch(Exception e){
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			JSONObject obj = new JSONObject();
			PageTemplate res = list.get(i);
			obj.put("id", res.getId().toString());
			obj.put("picUrl",res.getTemplateImage());
			json.put(obj);
		} 
		System.out.println(json.toString());
		this.setAjaxInputStream(json.toString());
		return AJAX;
	}
	
	 
	//修改页面的标题颜色、页面背景
	private void templateToPage(Page page, PageTemplate pageTemplate) {
		//page.setTitleBgColor(pageTemplate.getTitleBgColor());
		//page.setBgImage(pageTemplate.getBgImage());
		page.setMoban(String.valueOf(pageTemplate.getId()));
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
			return updateArticleAtPage(page, articleTemplate, article);
		}
	}

	private Article updateArticleAtPage(Page page, ArticleTemplate articleTemplate, Article article) {
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
}
