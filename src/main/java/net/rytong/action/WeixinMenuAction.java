package net.rytong.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.dao.ICustomerDAO;
import net.rytong.entity.Customer;
import net.rytong.entity.Employee;
import net.rytong.entity.Resource;
import net.rytong.entity.WeixinMenu;
import net.rytong.impl.WeixinMenuServiceImpl;
import net.rytong.service.IParameterService;
import net.rytong.service.IResourceService;
import net.rytong.vo.WeixinMenuVo;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeixinMenuAction extends CRUDActionSupport<WeixinMenu> {
	private static final long serialVersionUID = 1012161232986360461L;
	@Autowired
	private WeixinMenuServiceImpl iWeixinMenuServiceImpl;
	@Autowired
	private IParameterService iParameterService;
	@Autowired
	private IResourceService iResourceService;
	@Autowired
	private ICustomerDAO iCustomerDAO;

	@Override
	public String execute() throws Exception{
		return SUCCESS;
	}
	
	public String search() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		Map<String, Object> filterMap = new HashMap<String, Object>();
		WeixinMenu p = this.getEntity();
		String tCustomer = p.getCustomer();
		if (StringUtils.isNotBlank(tCustomer)) {
			tCustomer = iCustomerDAO.findById(Long.valueOf(p.getCustomer())).getShotName();
		}
		if (StringUtils.isNotBlank(p.getName())) {
			filterMap.put("name", p.getName());
		}
		if (StringUtils.isNotBlank(p.getType())) {
			filterMap.put("type", p.getType());
		}
		if (p.getLevel() != null) {
			filterMap.put("level", p.getLevel());
		}
		if (p.getState() != null) {
			filterMap.put("state", p.getState());
		}
		if (StringUtils.isNotBlank(tCustomer)) {
			filterMap.put("customer", tCustomer);
		}
		if (!"admin".equals(employee.getName())) {
			filterMap.put("customer", employee.getCustomerName());
		}
		this.setFilterMap(filterMap);
		return this.pageList();
	}
	
	// 编辑编辑Menu
	public String addOrUpdateMenu() throws Exception {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			Employee employee = (Employee)request.getSession().getAttribute("employee");
			HttpServletResponse response = ServletActionContext.getResponse();
			String menuId = request.getParameter("menuId");
			String lastMenuId = request.getParameter("lastMenuId");
			String name = request.getParameter("name");
			
			if (StringUtils.isBlank(menuId)) { // 添加Menu
				WeixinMenu menu = new WeixinMenu();
				String subId = null;
				int level = 1;
				
				if (StringUtils.isNotBlank(lastMenuId)) { // 添加一级Menu
					WeixinMenu lastMenu = iWeixinMenuServiceImpl.view(lastMenuId);
					subId = lastMenu.getId() + "";
					level = 2;
				}
				menu.setLevel(level);
				menu.setName(name);
				menu.setType("text"); // 设置默认回复方式
				menu.setState(0);
				menu.setSort(0);
				menu.setSubId(subId);
				menu.setCustomer(employee.getCustomerName());
				iWeixinMenuServiceImpl.add(menu);
				response.setCharacterEncoding("utf-8");
				String json = "{\"success\":\"0\", \"name\":\"" + menu.getName() + "\", \"id\":\"" + menu.getId() + "\"}";
				response.getWriter().print(json);
			} else { // 编辑Menu
				WeixinMenu menu = iWeixinMenuServiceImpl.view(menuId);
				menu.setName(name);
				iWeixinMenuServiceImpl.update(menu);
				response.setCharacterEncoding("utf-8");
				String json = "{\"success\":\"0\", \"name\":\"" + menu.getName() + "\", \"id\":\"" + menu.getId() + "\"}";
				response.getWriter().print(json);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String menuResponse() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		String showPath = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORSHOW","0").get(0).getValue();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String menuId = request.getParameter("menuId");
		// 一级菜单如果存在子菜单，不予响应
		WeixinMenu menu = iWeixinMenuServiceImpl.view(menuId);
		WeixinMenuVo vo = new WeixinMenuVo(menu);
		if (menu.getLevel() == 1) {
			List<WeixinMenu> list = iWeixinMenuServiceImpl.findByLevelAndParent(2, menu.getId() + "");
			if (list.size() > 0) {
				return NONE;
			}
		} 
		if ("text".equals(menu.getType())) {
			List<Resource> resources = menu.getResources();
			if (resources != null && resources.size() > 0) {
				vo.setContent(resources.get(0).getContent());
			}
		} else if ("image".equals(menu.getType())) {
			Resource r = menu.getResources().get(0);
			vo.setPicLink(ip + employee.getCustomerName() + "/" + r.getPicName());
			vo.setResourceId(r.getId() + "");
		} else if ("news".equals(menu.getType())) {
			Resource r = menu.getResources().get(0);
			vo.setPicLink(ip + employee.getCustomerName() + "/" + r.getPicName());
			vo.setResourceId(r.getId() + "");
			vo.setContent(r.getContent());
			vo.setResouceTitle(r.getTitle());
			String url = r.getUrl();
			if (StringUtils.isNotBlank(url)) {
				if (url.startsWith("http:")) {
					vo.setUrl(url);
				} else {
					vo.setUrl(showPath + employee.getCustomerName() + "/" + r.getUrl());
				}
			} else {
				vo.setUrl("");
			}
		}
		JSONObject obj = new JSONObject(vo);
		out.print(obj.toString());
		return NONE;
	}
	
	public void getMenuDetail() throws Exception {
		WeixinMenuVo vo = new WeixinMenuVo();
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
			String menuId = request.getParameter("menuId");
			WeixinMenu menu = iWeixinMenuServiceImpl.view(menuId);
			vo = new WeixinMenuVo(menu);
			
			if ("text".equals(menu.getType())) {
				List<Resource> resources = menu.getResources();
				if (resources != null && resources.size() > 0) {
					vo.setContent(resources.get(0).getContent());
				}
			} else if ("image".equals(menu.getType())) {
				Resource r = menu.getResources().get(0);
				vo.setPicLink(ip + employee.getCustomerName() + File.separator + r.getPicName());
				vo.setResourceId(r.getId() + "");
			} else if ("news".equals(menu.getType())) {
				Resource r = menu.getResources().get(0);
				vo.setPicLink(ip + employee.getCustomerName() + File.separator + r.getPicName());
				vo.setResourceId(r.getId() + "");
				vo.setContent(r.getContent());
				vo.setResouceTitle(r.getTitle());
			}
		} catch(Exception e) {
			//e.printStackTrace();
		}
		JSONObject obj = new JSONObject(vo);
		out.print(obj.toString());
	}
	
	// 编辑编辑Menu
	public String deleteMenu() throws Exception {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String menuId = request.getParameter("menuId");
			WeixinMenu menu = iWeixinMenuServiceImpl.view(menuId);
			if (menu.getLevel() == 1) {
				List<WeixinMenu> childMenus = iWeixinMenuServiceImpl.findByLevelAndParent(2, menu.getId() + "");
				for (WeixinMenu m : childMenus) {
					iWeixinMenuServiceImpl.delete(m);
				}
			}
			iWeixinMenuServiceImpl.delete(menu);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	// 编辑文本
	public String addText() throws Exception {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			Employee employee = (Employee)request.getSession().getAttribute("employee");
			String menuId = request.getParameter("menuId");
			String content = request.getParameter("content");
			
			WeixinMenu menu = iWeixinMenuServiceImpl.view(menuId);
			menu.setType("text");
			List<Resource> list = menu.getResources();
			if (list != null && list.size() > 0) {
				Resource r = menu.getResources().get(0);
				r.setContent(content);
				iResourceService.update(r);
			} else {
				Resource resource = new Resource();
				resource.setTitle("text");
				resource.setType("text"); // 文本
				resource.setCustomer(employee.getCustomerName());
				resource.setContent(content);
				List<Resource> resources = new ArrayList<Resource>();
				resources.add(resource);
				iResourceService.add(resource);
				menu.setResources(resources);
			}
			iWeixinMenuServiceImpl.update(menu);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	// 编辑链接
	public String addLink() throws Exception {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String menuId = request.getParameter("menuId");
			String url = request.getParameter("url");
			WeixinMenu menu = iWeixinMenuServiceImpl.view(menuId);
			if (StringUtils.isNotBlank(url)) {
				menu.setUrl(url);
			}
			iWeixinMenuServiceImpl.update(menu);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	// 编辑图片
	public String addImage() throws Exception {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String menuId = request.getParameter("menuId");
			String resourceId = request.getParameter("resourceId");
			WeixinMenu menu = iWeixinMenuServiceImpl.view(menuId);
			menu.setType("image");
			Resource resource = iResourceService.view(resourceId);
			List<Resource> resources = new ArrayList<Resource>();
			resources.add(resource);
			menu.setResources(resources);
			iWeixinMenuServiceImpl.update(menu);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	// 编辑图片
	public String addNews() throws Exception {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String menuId = request.getParameter("menuId");
			String resourceId = request.getParameter("resourceId");
			WeixinMenu menu = iWeixinMenuServiceImpl.view(menuId);
			menu.setType("news");
			Resource resource = iResourceService.view(resourceId);
			List<Resource> resources = new ArrayList<Resource>();
			resources.add(resource);
			menu.setResources(resources);
			iWeixinMenuServiceImpl.update(menu);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public void getAll() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// 查询一级菜单
		List<WeixinMenu> list = iWeixinMenuServiceImpl.findByLevelAndCustomer(1, employee.getCustomerName());
		List<WeixinMenu> clist = iWeixinMenuServiceImpl.findByLevelAndCustomer(2, employee.getCustomerName()); 
		JSONArray json = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			JSONObject obj = new JSONObject();
			int id = list.get(i).getId();
			List<WeixinMenu> isList = new ArrayList<WeixinMenu>();
			for (int j = 0; j < clist.size(); j++) {
				if (Integer.valueOf(clist.get(j).getSubId()) == id) {
					isList.add(clist.get(j));
				}
			}
			obj.put("level2", WeixinMenuVo.convert(isList));
			obj.put("level1", new JSONObject(new WeixinMenuVo(list.get(i))));
			json.put(obj);
		} 
		out.print(json.toString());
	}
	
	
	@Override
	public String add() throws Exception {
		WeixinMenu p = this.getEntity();
		String tCustomer = p.getCustomer();
		if (StringUtils.isNotBlank(p.getCustomer())) {
			tCustomer = iCustomerDAO.findById(Long.valueOf(p.getCustomer())).getShotName();
		}
		p.setCustomer(tCustomer);
		return super.add();
	}
	
	@Override
	public String edit() throws Exception {
		WeixinMenu p = this.getEntity();
		String tCustomer = p.getCustomer();
		if (StringUtils.isNotBlank(p.getCustomer())) {
			tCustomer = iCustomerDAO.findById(Long.valueOf(p.getCustomer())).getShotName();
		}
		p.setCustomer(tCustomer);
		return super.edit();
	}
	
	@Override
	public String detail() throws Exception {
		WeixinMenu p = this.getEntity();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("weixinMenu", p);
		return super.detail();
	}
	
	// 最多返回3条记录
	public String createMenu() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// 查询一级菜单
		List<WeixinMenu> list = iWeixinMenuServiceImpl.findByLevelAndCustomer(1, employee.getCustomerName());
		List<WeixinMenu> clist = iWeixinMenuServiceImpl.findByLevelAndCustomer(2, employee.getCustomerName());
		JSONArray json = new JSONArray();
		for (int i = 0; i < list.size() && i < 3; i++) { // 只获取前三条
			JSONObject obj = new JSONObject();
			int id = list.get(i).getId();
			List<WeixinMenu> isList = new ArrayList<WeixinMenu>();
			for (int j = 0; j < clist.size(); j++) {
				if (Integer.valueOf(clist.get(j).getSubId()) == id) {
					isList.add(clist.get(j));
				}
			}
			obj.put("level2", WeixinMenuVo.convert(isList));
			obj.put("level1", new JSONObject(new WeixinMenuVo(list.get(i))));
			json.put(obj);
		} 
		out.print(json.toString());
		return NONE;
	}
	
	public String lastMenu() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		StringBuffer stringBuffer = new StringBuffer();
		
		String subId = request.getParameter("subId");
		//String name = request.getParameter("name");
		stringBuffer.append("<select name=\"subId\" id=\"subId\">");
		stringBuffer.append("<option value=''>无</option>");
		List<WeixinMenu> weixins = iWeixinMenuServiceImpl.findByType("click");
		for(WeixinMenu weixin : weixins){
			if(StringUtils.isNotBlank(subId) && subId.equals(weixin.getId().toString())){
				stringBuffer.append("<option value='"+weixin.getId()+"' selected=\"selected\">"+  weixin.getName() +"</option>");
				continue;
			} 
			stringBuffer.append("<option value='"+weixin.getId()+"'>"+weixin.getName()+"</option>");
		}
		stringBuffer.append("</select>");
		response.getWriter().print(stringBuffer.toString());
		return NONE;
	}
	
	public String lastKeyMenu() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		StringBuffer stringBuffer = new StringBuffer();
		
		String subId = request.getParameter("id");
		//String name = request.getParameter("name");
		stringBuffer.append("<select name=\"subId\" id=\"subId\">");
		stringBuffer.append("<option value=''>无</option>");
		List<WeixinMenu> weixins = iWeixinMenuServiceImpl.findByType("searchKey");
		for(WeixinMenu weixin : weixins){
			String id = weixin.getId() + "";
			if (subId != null) {
				String[] s1 = subId.split("/");
				 if (s1.length > 1) {
			    	  String t = s1[(s1.length - 2)];
			    	  if(id.equals(t)){
							stringBuffer.append("<option value='"+weixin.getId()+"' selected=\"selected\">"+  weixin.getName() +"</option>");
							continue;
						} 
			      }
			}
			stringBuffer.append("<option value='"+weixin.getId()+"'>"+weixin.getName()+"</option>");
		}
		stringBuffer.append("</select>");
		response.getWriter().print(stringBuffer.toString());
		return NONE;
	}
	
	public String getLastName(String id) throws Exception {
		if (StringUtils.isNotBlank(id)) {
			WeixinMenu weixin = iWeixinMenuServiceImpl.view(id);
			if (weixin != null) {
				return weixin.getName();
			}
		}
		return id;
	}
	
	public String init() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		StringBuffer stringBuffer = new StringBuffer();
		
		String subId = request.getParameter("id");
		//String name = request.getParameter("name");
		stringBuffer.append("<select name=\"subId\" id=\"subId\">");
		stringBuffer.append("<option value=''>全部</option>");
		List<WeixinMenu> weixins = iWeixinMenuServiceImpl.findByType("click");
		for(WeixinMenu weixin : weixins){
			if(StringUtils.isNotBlank(subId) && subId.equals(weixin.getId().toString())){
				stringBuffer.append("<option value='"+weixin.getId()+"' selected=\"selected\">"+  weixin.getName() +"</option>");
				continue;
			} 
			stringBuffer.append("<option value='"+weixin.getId()+"'>"+weixin.getName()+"</option>");
		}
		stringBuffer.append("</select>");
		response.getWriter().print(stringBuffer.toString());
		return NONE;
	}

	@Override
	public CRUDService<WeixinMenu> getAutowiredService() {
		return iWeixinMenuServiceImpl;
	}
	
	public String menuSys() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		response.setCharacterEncoding("utf-8");
		
		String operation = request.getParameter("operation");
		String plat = request.getParameter("plat");
		String customer = employee.getCustomerName();
		String returnStr = "";
		if (operation.equals("create")) {
			returnStr = menuOperation(customer, plat);
		} else if (operation.equals("get")) {
			String menuGetUrl = wxMenuGet + "?access_token=" + getTokenByHost();
			returnStr = getMethod(menuGetUrl);
		} else if (operation.equals("delete")) {
			String menuDeleteUrl = wxMenuDelete + "?access_token=" + getTokenByHost();
			returnStr = getMethod(menuDeleteUrl);
		} 
		this.setAjaxInputStream(returnStr);
		return AJAX;
	}
	
	private String menuOperation(String customer, String plat) {
		String hostUrl = "";
		if ("weixin".equals(plat)) {
			hostUrl = wxMenuCreate + "?access_token=" + getTokenByHost();
		} else if ("yixin".equals(plat)) {
			hostUrl = yxMenuCreate + "?access_token=" + getYxTokenByHost();
		}
		StringBuffer sb = new StringBuffer();
		try {
			SSLSocketFactory ssf = getSSL();
			URL url = new URL(hostUrl);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setSSLSocketFactory(ssf);
			connection.setConnectTimeout(30000);
			connection.setReadTimeout(30000);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-type", "text/xml;charset=utf-8");  
			connection.setDoOutput(true);
			OutputStreamWriter outStrm = new OutputStreamWriter(connection.getOutputStream(), "UTF8"); 
			String json = createJson(customer, plat); // 组织JSON ， 从数据库获取
			outStrm.write(json);
			outStrm.flush();
			outStrm.close();
			InputStream content = (InputStream) connection.getInputStream();
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(content, "UTF-8"));
				char buffer[] = new char[4096];
				int len;
				while ((len = br.read(buffer)) > 0)
					sb.append(new String(buffer, 0, len));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			logger.info("WEIXIN CREATE MENU ERROR!");
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	private String createJson(String customer, String plat) throws JSONException {
		if ("weixin".equals(plat)) {
			return createWxJson(customer);
		} else if ("yixin".equals(plat)) {
			return createYxJson(customer);
		}
		return "";
	}
	
	private String createWxJson(String customer) throws JSONException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		JSONStringer js = new JSONStringer();
		JSONArray button = new JSONArray();
		JSONArray subButton = new JSONArray();

		//String name = iCustomerDAO.findById(Long.valueOf(customer)).getShotName(); 
		List<WeixinMenu> list = iWeixinMenuServiceImpl.findByLevelAndCustomer(1, employee.getCustomerName());
		List<WeixinMenu> clist = iWeixinMenuServiceImpl.findByLevelAndCustomer(2, employee.getCustomerName());

		for (int i = 0; i < list.size() && i < 3; i++) {
			WeixinMenu mainMenu = list.get(i);
			boolean isHavedChild = false;
			for (WeixinMenu subMenu : clist) {
				if (subMenu.getSubId().equals(mainMenu.getId() + "")) {
					isHavedChild = true;
					subButton.put(new JSONObject().put("type", "click").put("name", subMenu.getName()).put("key", subMenu.getName()));
				}
			}
			if (isHavedChild) {
				button.put(new JSONObject().put("name", mainMenu.getName()).put("sub_button", subButton));
			} else {
				button.put(new JSONObject().put("type", "click").put("name", mainMenu.getName()).put("key", mainMenu.getName()));
			}
			subButton = new JSONArray();
			logger.info(button.toString());
		}
		js.object().key("button").value(button).endObject();
		logger.info(js.toString());
		return js.toString();
	}
	
	private String createYxJson(String customer) throws JSONException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		JSONStringer js = new JSONStringer();
		JSONArray button = new JSONArray();
		JSONArray subButton = new JSONArray();

		//String name = iCustomerDAO.findById(Long.valueOf(customer)).getShotName(); 
		List<WeixinMenu> list = iWeixinMenuServiceImpl.findByLevelAndCustomer(1, employee.getCustomerName());
		List<WeixinMenu> clist = iWeixinMenuServiceImpl.findByLevelAndCustomer(2, employee.getCustomerName());

		for (int i = 0; i < list.size() && i < 3; i++) {
			WeixinMenu mainMenu = list.get(i);
			boolean isHavedChild = false;
			for (WeixinMenu subMenu : clist) {
				if (subMenu.getSubId().equals(mainMenu.getId() + "")) {
					isHavedChild = true;
					subButton.put(new JSONObject().put("type", "view").put("name", subMenu.getName()).put("url", subMenu.getUrl()));
				}
			}
			if (isHavedChild) {
				button.put(new JSONObject().put("name", mainMenu.getName()).put("sub_button", subButton));
			} else {
				button.put(new JSONObject().put("type", "view").put("name", mainMenu.getName()).put("url", mainMenu.getUrl()));
			}
			subButton = new JSONArray();
			logger.info(button.toString());
		}
		js.object().key("button").value(button).endObject();
		logger.info(js.toString());
		return js.toString();
	}
	
	private String getTokenByHost() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		Customer customer = iCustomerDAO.findByShotName(employee.getCustomerName(), null).get(0);
		String appId = customer.getAppId();
		String secret = customer.getSecret();
		String host = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + secret;
		String access_token = getMethod(host); // GET ACCESS_TOKEN
		try {
			JSONObject json = new JSONObject(access_token);
			access_token = json.getString("access_token");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info(access_token);
		logger.info("WEIXIN GET TOKEN END!");
		return access_token;
	}
	
	private String getYxTokenByHost() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		Customer customer = iCustomerDAO.findByShotName(employee.getCustomerName(), null).get(0);
		String yxAppId = customer.getYxAppId();
		String yxSecret = customer.getYxSecret();
		String yxHost = "https://api.yixin.im/cgi-bin/token?grant_type=client_credential&appid=" + yxAppId + "&secret=" + yxSecret;
		String access_token = getMethod(yxHost); // GET ACCESS_TOKEN
		try {
			JSONObject json = new JSONObject(access_token);
			access_token = json.getString("access_token");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info(access_token);
		logger.info("WEIXIN GET TOKEN END!");
		return access_token;
	}
	
	private String getMethod(String hostUrl) {
		logger.info(hostUrl);
		StringBuffer sb = new StringBuffer();
		try {
			SSLSocketFactory ssf = getSSL();
			URL url = new URL(hostUrl);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setSSLSocketFactory(ssf);
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(30000);
			connection.setReadTimeout(30000);
			InputStream content = (InputStream) connection.getInputStream();
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(content, "UTF-8"));
				char buffer[] = new char[4096];
				int len;
				while ((len = br.read(buffer)) > 0)
					sb.append(new String(buffer, 0, len));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			logger.info("WEIXIN GET METHOD ERROR!");
			e.printStackTrace();
		}
		logger.info(sb.toString());
		return sb.toString();
	}
	
	//HTTPS加入SSL验证部分
	private SSLSocketFactory getSSL() throws Exception {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		return ssf;
	}
	
	/**
	 * 异步请求得到所有type=news的记录
	 * @return
	 * @throws IOException 
	 */
	public String selectMenu() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		response.setCharacterEncoding("utf-8");
		String searchType = request.getParameter("type");
		List<WeixinMenu> menus = iWeixinMenuServiceImpl.findByTypeAndCustomer(searchType, employee.getCustomerName());
		if(menus != null && menus.size() > 0){
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("<select name=\"menu\" id=\"menu\">");
			stringBuffer.append("<option value=''>不选择</option>");
			for(WeixinMenu menu : menus){
				stringBuffer.append("<option value='"+menu.getId()+"'>"+menu.getName()+"</option>");
			}
			stringBuffer.append("</select>");
			response.getWriter().print(stringBuffer.toString());
		}
		return this.NONE;
	}
	
	private static String wxMenuCreate = "https://api.weixin.qq.com/cgi-bin/menu/create";
	private static String wxMenuGet = "https://api.weixin.qq.com/cgi-bin/menu/get";
	private static String wxMenuDelete = "https://api.weixin.qq.com/cgi-bin/menu/delete";
	
	private static String yxMenuCreate = "https://api.yixin.im/cgi-bin/menu/create";
}
