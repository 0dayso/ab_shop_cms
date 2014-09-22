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
import net.rytong.entity.Resource;
import net.rytong.entity.Visits;
import net.rytong.impl.VisitsServiceImpl;
import net.rytong.service.IParameterService;
import net.rytong.service.IResourceService;
import net.rytong.utils.TimeHelper;
import net.rytong.vo.ResourceVo;
import net.rytong.vo.VisitsVo;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class VisitsAction extends CRUDActionSupport<Visits>{
	private static final long serialVersionUID = 1L;
	@Autowired
	private VisitsServiceImpl iVisitsService;
	@Autowired
	private IParameterService iParameterService;
	@Autowired
	IResourceService iResourceService;
	private String beginDate;
	private String endDate;
	private String infoType;

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String search() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		Map<String, Object> filterMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(beginDate)) {
			filterMap.put("beginDate", TimeHelper.getDate(beginDate));
		}
		if (StringUtils.isNotBlank(endDate)) {
			filterMap.put("endDate", TimeHelper.getDate(endDate));
		}
		List<Visits>  list = iVisitsService.list(filterMap);
		
		JSONArray json = new JSONArray();
		for (Visits v : list) {
			JSONObject obj = new JSONObject();
			obj.put("count", "100");
			obj.put("username", v.getUserName());
			json.put(obj);
		} 
		out.print(json.toString());
		return NONE;
	}
	
	public String getNumsByUser() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		Map<String, Object> filterMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(beginDate)) {
			filterMap.put("beginDate", TimeHelper.getDate(beginDate));
		}
		if (StringUtils.isNotBlank(endDate)) {
			filterMap.put("endDate", TimeHelper.getDate(endDate));
		}
		Visits visits = this.getEntity();
		String infoType = visits.getInfoType();
		if (StringUtils.isNotBlank(infoType)) {
			filterMap.put("infoType", infoType);
		}
		filterMap.put("customer", employee.getCustomerName());
		List<Object[]>  list = iVisitsService.getNumsByUser(filterMap);
		JSONArray json = new JSONArray();
		for (Object[] v : list) {
			JSONObject obj = new JSONObject();
			obj.put("username", v[0]);
			obj.put("count", v[1]);
			json.put(obj);
		} 
		this.setAjaxInputStream(json.toString());
		return AJAX;
	}
	
	public String getDetilsByUser() throws Exception {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(beginDate)) {
			filterMap.put("beginDate", TimeHelper.getDate(beginDate));
		}
		if (StringUtils.isNotBlank(endDate)) {
			filterMap.put("endDate", TimeHelper.getDate(endDate));
		}
		Visits visits = this.getEntity();
		String infoType = visits.getInfoType();
		String userName = visits.getUserName();
		if (StringUtils.isNotBlank(infoType)) {
			filterMap.put("infoType", infoType);
		}
		if (StringUtils.isNotBlank(userName)) {
			filterMap.put("userName", userName);
		}
		List<Visits>  listEn = iVisitsService.getDetilsByUser(filterMap);
		List<VisitsVo> list = VisitsVo.convert(listEn);
		JSONObject obj = new JSONObject();
		obj.put("list", list);
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}
	
	public String getVisitResource() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		String showPath = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORSHOW", "0").get(0).getValue();
		Visits visits = this.getEntity();
		String info = visits.getInputInfo();
		String[] infos = info.split(",");
		
		List<Resource> resouces = new ArrayList<Resource>();
		for (int i = 0; i < infos.length; i++) {
			Resource r = iResourceService.view(infos[i]);
			r.setPicName(ip + employee.getCustomerName() + "/" + r.getPicName());
			String url = r.getUrl();
			if (StringUtils.isNotBlank(url)) {
				r.setUrl(url.startsWith("http:") ? url : (showPath + employee.getCustomerName() + "/" + url));
			} else {
				r.setUrl("");
			}
			resouces.add(r);
		}
		List<ResourceVo> list = ResourceVo.convert(resouces);
		JSONObject obj = new JSONObject();
		obj.put("list", list);
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}
	
	@Override
	public void validate() {
	}
	
	
	public  String formatDateOrTime(Object value) {
		if (value != null) {
			String temp = value.toString();
			if (temp.length() == 14) {// 20100105161858
				return temp.substring(0, 4) + "-" + temp.subSequence(4, 6) + "-" + temp.substring(6, 8) + " "
						+ temp.substring(8, 10) + ":" + temp.substring(10, 12) + ":" + temp.substring(12, 14);
			} else if (temp.length() == 8) {// 20091223
				return temp.substring(0, 4) + "-" + temp.subSequence(4, 6) + "-" + temp.substring(6, 8);
			} else if (temp.length() == 6) {// 153218
				return temp.substring(0, 2) + ":" + temp.substring(2, 4) + ":" + temp.substring(4, 6);
			} else if (temp.length() == 4) {// 153218
				return temp.substring(0, 2) + ":" + temp.substring(2, 4);
			}
		}
		return null;
	}
	

	@Override
	public CRUDService<Visits> getAutowiredService() {
		return iVisitsService;
	}
}