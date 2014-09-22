package net.rytong.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.dao.IParameterDAO;
import net.rytong.entity.Employee;
import net.rytong.entity.EmployeeTemplate;
import net.rytong.entity.Resource;
import net.rytong.entity.WeixinKey;
import net.rytong.entity.WeixinMenu;
import net.rytong.impl.EmployeeTemplateServiceImpl;
import net.rytong.impl.WeixinKeyServiceImpl;
import net.rytong.impl.WeixinMenuServiceImpl;
import net.rytong.service.IResourceService;
import net.rytong.vo.TipResultVo;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class PackPageAction extends CRUDActionSupport<EmployeeTemplate> {
	private static final long serialVersionUID = 5207197603748720772L;
	@Autowired
	private EmployeeTemplateServiceImpl employeeTemplateServiceImpl;
	@Autowired
	private WeixinMenuServiceImpl iWeixinMenuService;
	@Autowired
	private WeixinKeyServiceImpl iWeixinKeyService;
	@Autowired
	IResourceService iResourceService;
	@Autowired
	private IParameterDAO iParameterDAO;
	private String pageUrl;
	
	@Override
	public String execute() throws Exception{
		return SUCCESS;
	}

	@Override
	public CRUDService<EmployeeTemplate> getAutowiredService() {
		return employeeTemplateServiceImpl;
	}

	@Override
	public void addOtherFilter() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		if (!"admin".equals(employee.getName())) {
			this.getFilterMap().put("customer", employee.getCustomerName());
		}
		this.setPageUrl(iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORSHOW", "0").get(0).getValue());
		super.addOtherFilter();
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
			//保存到用户和模板的对应关系
			EmployeeTemplate et = new EmployeeTemplate();
			et.setCode("moban");
			et.setEmployeeId(employee.getId());
			et.setCustomer(employee.getCustomerName());
			et.setName(pageName);
			et.setPageName(pageName);
			employeeTemplateServiceImpl.add(et);
		} catch(Exception e) {
			e.printStackTrace();
			result = getTipResult("error", "error");
		}
		this.setAjaxInputStream(result);
		return AJAX;
	}
	
	public String getTipResult(String code, String msg) {
		String code1 = StringUtils.isNotBlank(code) ? code : "ok";
		String msg1 = StringUtils.isNotBlank(code) ? msg : "ok";
		TipResultVo vo = new TipResultVo(code1, msg1);
		JSONObject obj = new JSONObject(vo);
		return obj.toString();
	}
	
	public String getTipResult() {
		return getTipResult(null, null);
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
}
