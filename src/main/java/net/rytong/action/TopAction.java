package net.rytong.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.entity.Conference;
import net.rytong.entity.Employee;
import net.rytong.entity.Resource;
import net.rytong.entity.Subclaues;
import net.rytong.entity.Top;
import net.rytong.impl.ConferenceServiceImpl;
import net.rytong.impl.SubClauseServiceImpl;
import net.rytong.impl.TopServiceImpl;
import net.rytong.service.IParameterService;
import net.rytong.service.IResourceService;
import net.rytong.utils.TimeHelper;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Scope("prototype")
@Component
public class TopAction extends CRUDActionSupport<Top> {
	private static final long serialVersionUID = 1L;
	@Autowired
	private TopServiceImpl topServiceImpl;
	@Autowired
	private ConferenceServiceImpl conferenceServiceImpl;
	@Autowired
	private SubClauseServiceImpl subClauseServiceImpl;
	@Autowired
	private IResourceService iResourceService;
	@Autowired
	private IParameterService iParameterService;
	private File fileInput;
	private String fileInputContentType;
	private String fileInputFileName;
	private String name;
	@Override
	public CRUDService<Top> getAutowiredService() {
		return topServiceImpl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAllTopByConference() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> filterMap = new HashMap<String, Object>();
		String conference = request.getParameter("conferenceId");
		String state = request.getParameter("state");
		
		if (StringUtils.isNotBlank(conference)) {
			filterMap.put("conference", conference);
		}
		if (StringUtils.isNotBlank(state)) {
			filterMap.put("state", state);
		}
		
		List<Top>  list = topServiceImpl.list(filterMap);
		JSONArray json = new JSONArray();
		for (Top v : list) {
			JSONObject obj = new JSONObject();
			obj.put("id", v.getId());
			obj.put("state", v.getState());
			obj.put("name", v.getName());
			obj.put("count", "0");
			json.put(obj);
		} 
		this.setAjaxInputStream(json.toString());
		return AJAX;
	}
	
	public String getAllTopSelectByConference() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		Map<String, Object> filterMap = new HashMap<String, Object>();
		StringBuffer stringBuffer = new StringBuffer();
		String conference = request.getParameter("conferenceId");
		String state = request.getParameter("state");
		
		if (StringUtils.isNotBlank(conference)) {
			filterMap.put("conference", conference);
		}
		if (StringUtils.isNotBlank(state)) {
			filterMap.put("state", state);
		}
		
		List<Top>  list = topServiceImpl.list(filterMap);
		
		String id = request.getParameter("id");
		stringBuffer.append("<select name=\"topId\" id=\"topId\">");
		for(Top top : list){
			if(StringUtils.isNotBlank(id) && top.getId().toString().equals(id)){
				stringBuffer.append("<option value='"+top.getId()+"' selected=\"selected\">"+top.getName()+"</option>");
				continue;
			} 
			stringBuffer.append("<option value='"+top.getId()+"'>"+top.getName()+"</option>");
		}
		stringBuffer.append("</select>");
		response.getWriter().print(stringBuffer.toString());
		return NONE;
	}
	
	public String addTop() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Top top = this.getEntity();
		String conference = request.getParameter("conferenceId");
		String state = request.getParameter("topState");
		top.setState(Integer.valueOf(state));
		top.setSort(0);
		top.setCreateTime(TimeHelper.getCurrentTime());
		Conference con = conferenceServiceImpl.view(conference);
		top.setConference(con);
		this.add();
		return getAllTopByConference();
	}
	
	public String addSubClause() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		Top top = this.getEntity();
		String title = request.getParameter("title");
		String logo = request.getParameter("logo");
		String content = request.getParameter("content");
		Subclaues sub = new Subclaues();
		sub.setContent(content);
		if (StringUtils.isBlank(logo)) {
			sub.setLogoUrl(ip + "common.jpg");
			sub.setBanner(ip + "common.jpg");
		} else {
			sub.setLogoUrl(logo);
			sub.setBanner(logo);
		}
		sub.setTitle(title);
		sub.setTop(top);
		sub.setLinkUrl("");
		sub.setZan(0);
		sub.setCode(TimeHelper.getCurrentDetailTime() + "");
		sub.setCreateTime(TimeHelper.getCurrentTime());
		subClauseServiceImpl.add(sub);
		return getAllSubByTop();
	}
	
	public String delSub() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String subId = request.getParameter("subId");
		Subclaues sub = subClauseServiceImpl.view(subId);
		Top top = sub.getTop();
		subClauseServiceImpl.delete(sub);
		return getAllSubByTop(top);
	}
	
	private String getAllSubByTop(Top top) throws Exception {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("topId", top.getId());
		List<Subclaues>  list = subClauseServiceImpl.list(filterMap);
		JSONArray json = new JSONArray();
		for (Subclaues v : list) {
			JSONObject obj = new JSONObject();
			obj.put("id", v.getId());
			obj.put("title", v.getTitle());
			obj.put("logo", v.getLogoUrl());
			obj.put("content", v.getContent());
			obj.put("describe", v.getDescribe() != null ? v.getDescribe() : "");
			obj.put("banner", v.getBanner() != null ? v.getBanner() : "");
			json.put(obj);
		} 
		this.setAjaxInputStream(json.toString());
		return AJAX;
	}
	
	public String editTop() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Top top = this.getEntity();
		String state = request.getParameter("topState");
		top.setState(Integer.valueOf(state));
		this.edit();
		return getAllTopByConference();
	}
	
	public String editSubClause() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Top top = this.getEntity();
		String subId = request.getParameter("subId");
		String title = request.getParameter("title");
		String logo = request.getParameter("logo");
		String content = request.getParameter("content");
		
		Subclaues sub = subClauseServiceImpl.view(subId);
		sub.setTitle(title);
		sub.setLogoUrl(logo);
		sub.setContent(content);
		sub.setTop(top);
		
		subClauseServiceImpl.update(sub);
		return getAllSubByTop();
	}
	
	public String editSubDetail() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String subId = request.getParameter("subId");
		String describe = request.getParameter("describe");
		String banner = request.getParameter("banner");
		
		Subclaues sub = subClauseServiceImpl.view(subId);
		sub.setBanner(banner);
		sub.setDescribe(describe);
		
		subClauseServiceImpl.update(sub);
		return getAllSubByTop();
	}
	
	public String delTop() throws Exception {
		this.delete();
		return getAllTopByConference();
	}
	
	public String getAllSubByTop() throws Exception {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		Top top = this.getEntity();
		filterMap.put("topId", top.getId());
		List<Subclaues>  list = subClauseServiceImpl.list(filterMap);
		JSONArray json = new JSONArray();
		for (Subclaues v : list) {
			JSONObject obj = new JSONObject();
			obj.put("id", v.getId());
			obj.put("title", v.getTitle());
			obj.put("logo", v.getLogoUrl());
			obj.put("content", v.getContent());
			obj.put("describe", v.getDescribe() != null ? v.getDescribe() : "");
			obj.put("banner", v.getBanner() != null ? v.getBanner() : "");
			json.put(obj);
		} 
		this.setAjaxInputStream(json.toString());
		return AJAX;
	}
	
	// 上传图片资源
	public String upload() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		
		String path = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IMAGE_PATH", "0").get(0).getValue();
		String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		String newFileName = TimeHelper.getCurrentDetailTime() + "_" + fileInputFileName;
		File file = new File(path + employee.getCustomerName());
		if(!file.exists()){
			file.mkdir();
		}
		fileInput.renameTo(new File(path + employee.getCustomerName() + File.separator + newFileName));
		//保存到资源表
		Resource resource = new Resource();
		resource.setType("image");//图片资源
		resource.setContent(newFileName);
		resource.setPicName(newFileName);
		resource.setCustomer(employee.getCustomerName());
		iResourceService.add(resource);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
        json.put("status", "success");
        json.put("link", ip + employee.getCustomerName() + "/" +newFileName);
        json.put("id", resource.getId());
		response.getWriter().print(json);
		return NONE;
	}
	
	
	public File getFileInput() {
		return fileInput;
	}
	public void setFileInput(File fileInput) {
		this.fileInput = fileInput;
	}
	public String getFileInputContentType() {
		return fileInputContentType;
	}
	public void setFileInputContentType(String fileInputContentType) {
		this.fileInputContentType = fileInputContentType;
	}
	public String getFileInputFileName() {
		return fileInputFileName;
	}
	public void setFileInputFileName(String fileInputFileName) {
		this.fileInputFileName = fileInputFileName;
	}
}
