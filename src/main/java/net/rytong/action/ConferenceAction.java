package net.rytong.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.dao.ICustomerDAO;
import net.rytong.dao.IParameterDAO;
import net.rytong.entity.Conference;
import net.rytong.entity.Customer;
import net.rytong.entity.Employee;
import net.rytong.entity.Resource;
import net.rytong.impl.ConferenceServiceImpl;
import net.rytong.service.IParameterService;
import net.rytong.service.IResourceService;
import net.rytong.utils.TimeHelper;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class ConferenceAction extends CRUDActionSupport<Conference> {
	private static final long serialVersionUID = 5207197603748720772L;
	@Autowired
	private ConferenceServiceImpl conferenceServiceImpl;
	
	@Autowired
	private IParameterDAO iParameterDAO;
	@Autowired
	private IResourceService iResourceService;
	@Autowired
	private ICustomerDAO iCustomerDAO;
	@Autowired
	private IParameterService iParameterService;
	private String title;
	private String content;
	private String resType;
	private File imagefile;
	private String imagefileFileName;
	private String imagefileContentType;
	@Override
	public String execute() throws Exception{
		return SUCCESS;
	}
	public String search() throws Exception {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		Conference p = this.getEntity();
		if (StringUtils.isNotBlank(p.getTitle())) {
			filterMap.put("title", p.getTitle());
		}
		Customer customer = getCustomerBySession();
		filterMap.put("customerId", customer.getId().toString());
		this.setFilterMap(filterMap);
		return this.pageList();
	}
	
	public String getInfos() throws Exception {
		List<Conference> list = null;
		JSONArray json = new JSONArray();
		try{
			Customer customer = getCustomerBySession();
			if(customer != null){
				Map<String,Object> filterMap = new HashMap<String,Object>();
				filterMap.put("customerId", customer.getId().toString());
				list = conferenceServiceImpl.getAllData(filterMap);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(list != null){
			for (int i = 0; i < list.size(); i++) {
				JSONObject obj = new JSONObject();
				Conference res = list.get(i);
				obj.put("id",res.getId().toString());
				obj.put("content",res.getContent());
				obj.put("title", res.getTitle());
				json.put(obj);
			} 
		}
		this.setAjaxInputStream(json.toString());
		return AJAX;
	}

	//根据ID访问会议信息
	public String getInfoById() throws JSONException{
		Conference p = this.getEntity();
		JSONObject json = new JSONObject();
		if(p != null){
			json.put("id", p.getId().toString());
			json.put("title", p.getTitle());
			json.put("logoUrl", p.getLogoUrl());
			json.put("content", p.getContent());
			json.put("state", p.getState());
		}
		this.setAjaxInputStream(json.toString());
		return AJAX;
	}
	
	public String editAjax() throws JSONException{
		Conference p = this.getEntity();
		Conference p1 = conferenceServiceImpl.update(p);
		JSONObject json = new JSONObject();
		if(p1 != null){
			json.put("status","success");
			json.put("id", p1.getId().toString());
			json.put("title", p1.getTitle());
			json.put("logoUrl", p1.getLogoUrl());
			json.put("content", p1.getContent());
			json.put("state", p1.getState());
		}
		this.setAjaxInputStream(json.toString());
		return AJAX;
	}
	
	public String deleteById() throws JSONException{
		Conference p = this.getEntity();
		conferenceServiceImpl.delete(p);
		List<Conference> list = null;
		JSONArray json = new JSONArray();
		try{
			Customer customer = getCustomerBySession();
			if(customer != null){
				Map<String,Object> filterMap = new HashMap<String,Object>();
				filterMap.put("customerId", customer.getId().toString());
				list = conferenceServiceImpl.getAllData(filterMap);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(list != null){
			for (int i = 0; i < list.size(); i++) {
				JSONObject obj = new JSONObject();
				Conference res = list.get(i);
				obj.put("id",res.getId().toString());
				obj.put("content",res.getContent());
				obj.put("title", res.getTitle());
				json.put(obj);
			} 
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
		  String newFileName = TimeHelper.getCurrentDetailTime() + "_" + imagefileFileName;
		  File file = new File(path + employee.getCustomerName());
		  if(!file.exists()){
		   file.mkdir();
		  }
		  imagefile.renameTo(new File(path + employee.getCustomerName() + File.separator + newFileName));
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
	
	
	/**
	 * 上传临时文件
	 * @return
	 */
	public String uploadFile(){
        String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
        try {
    		String newFileName = saveFile();
    		ip = "http://localhost:8080/images/";
    		String url = ip + employee.getCustomerName() + "/" + newFileName;
            JSONObject json = new JSONObject();
            json.put("status", "success");
            json.put("url", url);
            json.put("name",newFileName);
            
            HttpServletResponse response = ServletActionContext.getResponse();
    		response.setCharacterEncoding("utf-8");
    		response.getWriter().print(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NONE;
    }
	
	//添加
	public String add() throws Exception {
		Conference p = this.getEntity();
		Customer customer = getCustomerBySession();
		p.setCustomer(customer);
		conferenceServiceImpl.add(p);
		return REDIRECT;
	}
	
	private Customer getCustomerBySession() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		Customer customer = null;
		customer = employee.getCustomer();
		if(customer == null){
			String customerName = employee.getCustomerName();
			List<Customer> customers = iCustomerDAO.findByShotName(customerName, null);
			if(customers != null && customers.size() > 0){
				customer = customers.get(0);
			}
		}
		return customer;
	}
	@Override
	public CRUDService<Conference> getAutowiredService() {
		return conferenceServiceImpl;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getResType() {
		return resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
	public File getImagefile() {
		return imagefile;
	}
	public void setImagefile(File imagefile) {
		this.imagefile = imagefile;
	}
	public String getImagefileFileName() {
		return imagefileFileName;
	}
	public void setImagefileFileName(String imagefileFileName) {
		this.imagefileFileName = imagefileFileName;
	}
	public String getImagefileContentType() {
		return imagefileContentType;
	}
	public void setImagefileContentType(String imagefileContentType) {
		this.imagefileContentType = imagefileContentType;
	}
	
	
	public String getAllInfos() throws Exception {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		Conference conference = this.getEntity();
		int state = conference.getState();
		
		filterMap.put("state", state);
		
		Customer customer = getCustomerBySession();
		filterMap.put("customerId", customer.getId().toString());
		List<Conference>  list = conferenceServiceImpl.getAllData(filterMap);
		JSONArray json = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			JSONObject obj = new JSONObject();
			Conference res = list.get(i);
			obj.put("id",res.getId().toString());
			obj.put("content",res.getContent());
			obj.put("title", res.getTitle());
			obj.put("state", res.getState());
			json.put(obj);
		} 
		this.setAjaxInputStream(json.toString());
		return AJAX;
	}
	
	
	public String getAllConference() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		StringBuffer stringBuffer = new StringBuffer();
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		stringBuffer.append("<select name=\"conference\" id=\"conference\">");
		List<Conference> conferences = null;
		try{
			Customer customer = getCustomerBySession();
			if(customer != null){
				Map<String,Object> filterMap = new HashMap<String,Object>();
				filterMap.put("customerId", customer.getId().toString());
				conferences = conferenceServiceImpl.getAllData(filterMap);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(conferences != null){
			for(Conference conference : conferences){
				String stateValue = "关闭";
				int state = conference.getState();
				if("1".equals(state)){
					stateValue = "开启";
				}
				if(StringUtils.isNotBlank(id) && conference.getId().toString().equals(id)){
					stringBuffer.append("<option value='"+conference.getId()+"' selected=\"selected\">"+conference.getTitle() + "(" +stateValue + ")" +"</option>");
					continue;
				} else if (StringUtils.isNotBlank(name) && conference.getTitle().toString().equals(name)) {
					stringBuffer.append("<option value='"+conference.getId()+"' selected=\"selected\">"+conference.getTitle()+  "(" +stateValue + ")" + "</option>");
					continue;
				}
				stringBuffer.append("<option value='"+conference.getId()+"'>"+conference.getTitle()+"</option>");
			}
		}
		stringBuffer.append("</select>");
		response.getWriter().print(stringBuffer.toString());
		return NONE;
	}
	
	private String saveFile() {
		String newFileName = "";
        String imgUrl = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IMAGE_PATH", "0").get(0).getValue();
        imgUrl = "D:\\apache-tomcat-6.0.35\\webapps\\images\\";
        HttpServletRequest request = ServletActionContext.getRequest();
        Employee employee = (Employee)request.getSession().getAttribute("employee");
        File pathFile = new File(imgUrl + employee.getCustomerName());
        if(!pathFile.exists()){
        	pathFile.mkdir();
        }
        try {
        	File file = this.getImagefile();
    		String fileType = getExtention(this.getImagefileFileName());
    		newFileName = "s"+TimeHelper.getCurrentDetailTime() + "_" + new Random().nextInt(1000) +fileType;
    		file.renameTo(new File(imgUrl + employee.getCustomerName() + File.separator + newFileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFileName;
	}
	private static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}
