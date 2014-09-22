package net.rytong.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.entity.Employee;
import net.rytong.entity.Parameter;
import net.rytong.impl.ParameterServiceImpl;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Scope("prototype")
@Component
public class ParameterAction extends CRUDActionSupport<Parameter> {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ParameterServiceImpl iParameterService;
	
	// 个性化查询
	public String search() throws Exception {		
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> filterMap = new HashMap<String, Object>();
		Parameter p = this.getEntity();
		if (StringUtils.isNotBlank(p.getCode())) {
			filterMap.put("code", p.getCode());
		}
		if (StringUtils.isNotBlank(p.getName())) {
			filterMap.put("name", p.getName());
		}
		if (StringUtils.isNotBlank(p.getValue())) {
			filterMap.put("value", p.getValue());
		}
		if (p.getEffectiveDate() != null) {
			filterMap.put("effectiveDate", p.getEffectiveDate());
		}
		if (p.getExpirationDate() != null) {
			filterMap.put("expirationDate", p.getExpirationDate());
		}
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		if (!"admin".equals(employee.getName())) {
			filterMap.put("customer", employee.getCustomerName());
		} else {
			filterMap.put("customer", "0");
		}
		this.setFilterMap(filterMap);
		return this.pageList();
	}
	
	// 异步获取报文
	public String codeValide() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		String str = null;
		Parameter p = this.getEntity();
		int i = iParameterService.getParameterByCodeAndNameAndCustomer(p.getCode(), p.getName(), employee.getCustomerName()).size();
		if(i == 0){
			str = "参数名称可以添加";
			HttpServletResponse response = ServletActionContext.getResponse();  
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");  
			PrintWriter pw = response.getWriter();  
			pw.print(str); 
			return NONE;
		}else{
			str = "同一类别下参数名称不能相同";
			HttpServletResponse response = ServletActionContext.getResponse();  
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");  
			PrintWriter pw = response.getWriter();  
			pw.print(str); 
			return NONE;
		}	
	}

	public String savePort(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		String[] paraCode = {"URL","TOKEN","APPID","SECRET"};
		String[] paraValue = {url, token, appId, secret};
		for(int i = 0; i < paraValue.length ; i++){
			if(StringUtils.isNotBlank(paraValue[i])){
				Parameter  parameter = null;
				List<Parameter> parameters = iParameterService.getParameterByCodeAndNameAndCustomer(paraCode[i], "WEIXIN", employee.getCustomerName());
				if(parameters != null && parameters.size() > 0){
					parameter = parameters.get(0);
					parameter.setValue(paraValue[i]);
					iParameterService.update(parameter);
				}else{
					try{
						parameter = new Parameter();
						parameter.setName("WEIXIN");
						parameter.setCode(paraCode[i]);
						parameter.setValue(paraValue[i]);
						parameter.setEffectiveDate(0);
						parameter.setExpirationDate(0);
						parameter.setCustomer(employee.getCustomerName());
						iParameterService.add(parameter);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
		String result = "{'result':'success'}";
		this.setAjaxInputStream(result);
		return AJAX;
	}
	
	public String getPort() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		String plat = iParameterService.getParameterByCodeAndNameAndCustomer("PORT", "PLATFORM", "0").get(0).getValue();
		String[] paraCode = {"TOKEN","APPID","SECRET"};
		String[] paraValue = {token, appId, secret};
		List<Parameter> list = new ArrayList<Parameter>();
		
		List<Parameter> parameters = iParameterService.getParameterByCodeAndNameAndCustomer("URL", "WEIXIN", employee.getCustomerName());
		if(parameters != null && parameters.size() > 0){
			Parameter parameter = parameters.get(0);
			parameter.setValue(plat + employee.getCustomerName());
			list.add(parameter);
		} else {
			Parameter parameter = new Parameter();
			parameter.setCode("URL");
			parameter.setValue(plat + employee.getCustomerName());
			list.add(parameter);
		}
		
		for(int i = 0; i < paraValue.length ; i++){
			parameters = iParameterService.getParameterByCodeAndNameAndCustomer(paraCode[i], "WEIXIN", employee.getCustomerName());
			if(parameters != null && parameters.size() > 0){
				list.add(parameters.get(0));
			}
		}
		JSONArray json = new JSONArray(list);
		this.setAjaxInputStream(json.toString());
		return AJAX;
	}
	
	@Override
	public void validate() {
	}

	@Override
	public CRUDService<Parameter> getAutowiredService() {
		return iParameterService;
	}
	
	private String url;
	private String token;
	private String appId;
	private String secret;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
}
