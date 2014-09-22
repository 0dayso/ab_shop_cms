package net.rytong.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.dao.IParameterDAO;
import net.rytong.entity.Article;
import net.rytong.entity.Employee;
import net.rytong.entity.Page;
import net.rytong.entity.Resource;
import net.rytong.entity.WeixinKey;
import net.rytong.entity.WeixinMenu;
import net.rytong.impl.ArticleServiceImpl;
import net.rytong.impl.PageServiceImpl;
import net.rytong.impl.WeixinKeyServiceImpl;
import net.rytong.impl.WeixinMenuServiceImpl;
import net.rytong.service.IResourceService;
import net.rytong.utils.FileUtil;
import net.rytong.utils.RandomUtils;
import net.rytong.vo.ArticleVo;
import net.rytong.vo.PagePathVo;
import net.rytong.vo.PageVo;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class PageAction extends CRUDActionSupport<Page> {
	private static final long serialVersionUID = 5207197603748720772L;
	@Autowired
	private PageServiceImpl pageService;
	@Autowired
	private WeixinMenuServiceImpl iWeixinMenuService;
	@Autowired
	private WeixinKeyServiceImpl iWeixinKeyService;
	@Autowired
	IResourceService iResourceService;
	@Autowired
	private IParameterDAO iParameterDAO;
	@Autowired
	private ArticleServiceImpl iArticleService;
	private String pageUrl;
	
	@Override
	public String execute() throws Exception{
		return SUCCESS;
	}

	@Override
	public CRUDService<Page> getAutowiredService() {
		return pageService;
	}

	@Override
	public void addOtherFilter() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		if (!"admin".equals(employee.getName())) {
			this.getFilterMap().put("customer", employee.getCustomer());
		}
		this.setPageUrl(iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORSHOW", "0").get(0).getValue());
		super.addOtherFilter();
	}
	
	public String editJSON() throws Exception {
		String result = getTipResult();
		try {
			Page page = this.getEntity();
			pageService.update(page);
			updatePage(page);
			JSONObject obj = new JSONObject(new PageVo(page));
			obj.put("code", "ok");
			result = obj.toString();
		}  catch(Exception e) {
			e.printStackTrace();
			result = getTipResult("error", "error");
		}
		this.setAjaxInputStream(result);
		return AJAX;
	}
	
	@Override
	public String deleteJSON() throws Exception {
		Page page = this.getEntity();
		String result = getTipResult();
		try {
			getAutowiredService().delete(page);
		}  catch(Exception e) {
			e.printStackTrace();
			result = getTipResult("error", "error");
		}
		updatePage(page);
		this.setAjaxInputStream(result);
		return AJAX;
	}
	
	public String modifyHeaderColor() throws Exception {
		String result = getTipResult();
		try {
			Page page = this.getEntity();
			pageService.update(page);
			updatePage(page);
		}  catch(Exception e) {
			e.printStackTrace();
			result = getTipResult("error", "error");
		}
		this.setAjaxInputStream(result);
		return AJAX;
	}
	
	public String setDefaultBgImage() throws Exception {
		String showPath = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORSHOW","0").get(0).getValue();
		Page page = this.getEntity();
		page.setBgImage(showPath + "images/" + page.getBgImage());
		pageService.update(page);
		updatePage(page);
		JSONObject obj = new JSONObject(new PageVo(page));
		obj.put("code", "ok");
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}
	
	public String getPage() throws Exception {
		String result = getTipResult();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Employee employee = (Employee)session.getAttribute("employee");
			List<Page> pages = pageService.findByLevelAndCustomer(employee.getCustomerName(), 1);
			String fillPath = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORHTML","0").get(0).getValue();
			String showPath = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORSHOW","0").get(0).getValue();
			String title = request.getParameter("title");
			String titleBgColor = request.getParameter("titleBgColor");
			String bannerImage = request.getParameter("bannerImage");
			String bgImage = request.getParameter("bgImage");
			
			if (pages == null || pages.size() == 0) {
				// 页面操作
				String mobanHtml = FileUtil.readFile(fillPath + "index.html");
				mobanHtml = mobanHtml.replaceAll("\\{PAGE_TITLEBGCOLOR\\}", titleBgColor);
				mobanHtml = mobanHtml.replaceAll("\\{PAGE_TITLE\\}", title);
				mobanHtml = mobanHtml.replaceAll("\\{PAGE_BANNERIMAGE\\}", showPath + "images/" + bannerImage);
				mobanHtml = mobanHtml.replaceAll("\\{PAGE_BGIMAGE\\}", showPath + "images/" + bgImage);
				mobanHtml = mobanHtml.replaceAll("\\{PAGE_ARTICLE\\}", "");
				
				String pageName = "WX" + new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()) + RandomUtils.random(3) + ".html";
				FileUtil.saveFile(fillPath + pageName, mobanHtml);
	
				Page page = new Page();
				page.setTitle(title);
				page.setName(pageName);
				page.setLevel(1);
				page.setPageType(1);//链接页
				page.setUrl(showPath + pageName);
				page.setVisitCount(0);
				page.setCustomer(employee.getCustomerName());
				pageService.save(page);
				
				page.setLastPage(page.getId() + "");
				pageService.update(page);
				
				JSONObject obj = new JSONObject(new PageVo(page));
				List<PagePathVo> path = PagePathVo.convert(page);
				obj.put("code", "ok");
				obj.put("path", path);
				result = obj.toString();
			} else {
				Page page = pages.get(0);
				JSONObject obj = new JSONObject(new PageVo(page));
				List<Article> articles = iArticleService.findByPageAndCustomer(page.getId(), employee.getCustomerName());
				List<ArticleVo> articleVos = ArticleVo.convert(articles);
				obj.put("code", "ok");
				obj.put("article", articleVos);
				List<PagePathVo> path = PagePathVo.convert(page);
				obj.put("path", path);
				result = obj.toString();
			}
		} catch(Exception e) {
			e.printStackTrace();
			result = getTipResult("error", "error");
		}
		this.setAjaxInputStream(result);
		return AJAX;
	}
	
	public String changePage() throws Exception {
		String result = getTipResult();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Employee employee = (Employee)session.getAttribute("employee");
			Page page = this.getEntity();
			JSONObject obj = new JSONObject(new PageVo(page));
			List<Article> articles = iArticleService.findByPageAndCustomer(page.getId(), employee.getCustomerName());
			List<ArticleVo> articleVos = ArticleVo.convert(articles);
			obj.put("code", "ok");
			obj.put("article", articleVos);
			List<Page> pages = getPagesfromLastPage(page.getLastPage());
			List<PagePathVo> path = PagePathVo.convert(pages);
			obj.put("path", path);
			result = obj.toString();
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
	
	private void updatePage(Page page) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String fillPath = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORHTML","0").get(0).getValue();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		List<Article> articles = iArticleService.findByPageAndCustomer(page.getId(), employee.getCustomerName());
		List<Page> pages = pageService.findByLevelAndCustomer(employee.getCustomerName(), 1);
		FileUtil.updatePage(pages.get(0).getUrl(), fillPath + "index.html", fillPath + "article.html", fillPath + page.getName(), page, articles);
	}
	
	public String addArticle() throws Exception {
		String result = getTipResult();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Employee employee = (Employee)session.getAttribute("employee");
	           		
	        String pageId = request.getParameter("pageId");
			String style = request.getParameter("style");
			String position = request.getParameter("position");
			String bgcolor = request.getParameter("bgcolor");
			String title = request.getParameter("title");
			
			if (StringUtils.isBlank(pageId)) {
				result = getTipResult("error", "error");
			} else {
				Page page = pageService.view(pageId);
				
				if (page != null) {
					Article article = new Article();
					article.setBgColor(bgcolor);
					article.setPosition(Integer.valueOf(position) + 1);
					article.setStyle(style);
					article.setTitle(title);
					article.setPage(page);
					article.setCustomer(employee.getCustomerName());
					
					iArticleService.add(article);
					JSONObject obj = new JSONObject(new ArticleVo(article));
					obj.put("code", "ok");
					result = obj.toString();
					updatePage(page);
				} else {
					result = getTipResult("error", "error");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			result = getTipResult("error", "error");
		}
		this.setAjaxInputStream(result);
		return AJAX;
	}
	
	public String addPage() throws Exception {
		String result = getTipResult();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Employee employee = (Employee)session.getAttribute("employee");
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			String pageName = request.getParameter("pageName");
			String menuId = request.getParameter("menuId");
			String keyId = request.getParameter("keyId");
			
			if (StringUtils.isNotBlank(menuId)) {
				WeixinMenu menu = iWeixinMenuService.view(menuId);
				List<Resource> resources = menu.getResources();
				if (resources != null && resources.size() > 0) {
					Resource r = resources.get(0);
					r.setUrl(pageName);
					iResourceService.update(r);
				}
			} else if (StringUtils.isNotBlank(keyId)) {
				WeixinKey key = iWeixinKeyService.view(keyId);
				List<Resource> resources = key.getResources();
				if (resources != null && resources.size() > 0) {
					Resource r = resources.get(0);
					r.setUrl(pageName);
					iResourceService.update(r);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			result = getTipResult("error", "error");
		}
		this.setAjaxInputStream(result);
		return AJAX;
	}
	
	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
}
