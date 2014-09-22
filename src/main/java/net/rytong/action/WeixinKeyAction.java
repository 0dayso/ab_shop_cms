package net.rytong.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.dao.ICustomerDAO;
import net.rytong.entity.Employee;
import net.rytong.entity.Resource;
import net.rytong.entity.Visits;
import net.rytong.entity.WeixinKey;
import net.rytong.impl.VisitsServiceImpl;
import net.rytong.impl.WeixinKeyServiceImpl;
import net.rytong.service.IParameterService;
import net.rytong.service.IResourceService;
import net.rytong.utils.TimeHelper;
import net.rytong.utils.ZipUtil;
import net.rytong.vo.ResourceVo;
import net.rytong.vo.TipResultVo;
import net.rytong.vo.WeixinKeyVo;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeixinKeyAction extends CRUDActionSupport<WeixinKey> {
	private static final long serialVersionUID = 1012161232986360461L;
	@Autowired
	private WeixinKeyServiceImpl iWeixinKeyServiceImpl;
	@Autowired
	private IResourceService iResourceService;
	@Autowired
	private IParameterService iParameterService;
	@Autowired
	private VisitsServiceImpl iVisitsServiceImpl;
	@Autowired
	private ICustomerDAO iCustomerDAO;
	private File fileInput;
	private String fileInputContentType;
	private String fileInputFileName;
	private String eventType;
	private String textContent;
	private String resourceId;

	@Override
	public String execute() throws Exception{
		return SUCCESS;
	}
	
	public String search() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		Map<String, Object> filterMap = new HashMap<String, Object>();
		WeixinKey p = this.getEntity();
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
	
	public void keyResponse() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String event = request.getParameter("event");
		String customer = employee.getCustomerName();
		String content = request.getParameter("content");
		WeixinKeyVo vo = null;
		addVisitRecord(employee, "1", StringUtils.isBlank(content) ? "subscribe" : content);
		
		if ("subscribe".equals(event)) {
			vo = subScribe(customer);
		} else if ("key".equals(event)) {
			if ("0".equals(content) || "?".equals(content) || "？".equals(content)) {
				vo = subScribe(customer);
			} else {
				String path = (String)request.getSession().getAttribute("currentPath");
				if (StringUtils.isBlank(path)) { 
					vo = firstVisit(content, employee.getCustomerName());
				} else {
					WeixinKey key = findKeyByCurrentPath(path, customer, content);
					if (key != null && key.getLeave() == 1) {
						request.getSession().setAttribute("currentPath", key.getSubId());
					} 
					vo = base(key, customer);
				}
				if (vo == null) {
					vo = autoAnswer(customer);
				}
			}
		}
		addVisitRecord(employee, "0", StringUtils.join(vo.getResourceId(), ","));
		JSONObject obj = new JSONObject(vo);
		out.print(obj);
	}
	
	private void addVisitRecord(Employee wUser, String type, String content) {
		Visits visit = new Visits();
		visit.setInfoType(type);
		visit.setInputInfo(content);
		visit.setUserName(wUser.getName());
		visit.setCustomer(wUser.getCustomerName());
		visit.setVisitTime(String.valueOf(TimeHelper.getCurrentTime()));
		iVisitsServiceImpl.save(visit);
	}

	private WeixinKey findKeyByCurrentPath(String path, String customer, String content) {
		List<WeixinKey> list = iWeixinKeyServiceImpl.findkeysAndCustomer(customer);
		if (list != null && list.size() > 0) {
			for (WeixinKey key : list) {
				if(key.getSubId().equals(path + "/" + key.getId())) {
					String[] words = key.getKey().split("、");
					for (String word : words) {
						if (StringUtils.isNotBlank(word) && word.contains(content)) {
							return key;
						}
					}
				}
			}
		}
		return null;
	}

	private WeixinKeyVo firstVisit(String content, String customer) {
		WeixinKeyVo result = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		List<WeixinKey> list = iWeixinKeyServiceImpl.findkeysAndCustomer(customer);
		if (list != null && list.size() > 0) { // 解词
			for (int i = 0; i < list.size(); i++) {
				WeixinKey key = list.get(i);
				String[] subId = key.getSubId().split("/");
				if (subId.length == 1) {
					String[] words = key.getKey().split("、");
					for (String word : words) {
						if (StringUtils.isNotBlank(word) && word.contains(content)) {
							result = base(key, customer);
							if (key != null && key.getLeave() == 1) {
								request.getSession().setAttribute("currentPath", key.getSubId());
							}
							return result;
						}
					}
				}
			}
		} 
		if (result == null) {
			result = autoAnswer(customer);
		}
		return result;
	}

	private WeixinKeyVo autoAnswer(String customer) {
		WeixinKeyVo result = null;
		List<WeixinKey> list = iWeixinKeyServiceImpl.findByNameAndCustomer("AutoAnswer", customer);
		if (list != null && list.size() == 1) {
			result = base(list.get(0), customer);
		} else {
			result = base(null, customer);
		}
		return result;
	}

	private WeixinKeyVo subScribe(String customer) {
		WeixinKeyVo result = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		List<WeixinKey> list = iWeixinKeyServiceImpl.findByNameAndCustomer("Attention", employee.getCustomerName());
		if (list != null && list.size() == 1) {
			result = base(list.get(0), customer);
		} else {
			result = base(null, customer);
		}
		request.getSession().setAttribute("currentPath", "");
		return result;
	}
	
	private WeixinKeyVo base(WeixinKey obj, String customer) {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		String showPath = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORSHOW", "0").get(0).getValue();
		WeixinKeyVo vo = null;
		
		if (obj != null) {
			String type = obj.getType();
			vo = new WeixinKeyVo();
			if ("text".equals(type)) {
				List<Resource> resources = obj.getResources();
				if (resources != null && resources.size() > 0) {
					Resource r = obj.getResources().get(0);
					vo.setType("text");
					String[] ids = new String[]{r.getId() + ""};
					vo.setResourceId(ids);
					vo.setContent(r.getContent());
				}
			} else if ("image".equals(obj.getType())) {
				Resource r = obj.getResources().get(0);
				vo.setType("image");
				String[] links = new String[] {ip + employee.getCustomerName() + "/" + r.getPicName()};
				vo.setPicLink(links);
				String[] ids = new String[]{r.getId() + ""};
				vo.setResourceId(ids);
			} else if ("news".equals(obj.getType())) {
				List<Resource> list = obj.getResources();
				vo.setType("news");
				int size = list.size();
				String[] titles = new String[size];
				String[] links = new String[size];
				String[] contents = new String[size];
				String[] ids = new String[size];
				String[] urls = new String[size];
				
				for (int i = 0; i < list.size(); i++) {
					Resource r = obj.getResources().get(i);
					titles[i] = r.getTitle();
					String name = r.getPicName();
					if (StringUtils.isNotBlank(name)) {
						links[i] = name.startsWith("http:") ? name : (ip + employee.getCustomerName() + "/" + name);
					} else {
						links[i] = "";
					}
					contents[i] = r.getContent();
					ids[i] = r.getId() + "";
					String url = r.getUrl();
					if (StringUtils.isNotBlank(url)) {
						urls[i] = url.startsWith("http:") ? url : (showPath + employee.getCustomerName() + "/" + url);
					} else {
						urls[i] = "";
					}
					
				}
				vo.setTitle(titles);
				vo.setPicLink(links);
				vo.setContents(contents);
				vo.setResourceId(ids);
				vo.setUrl(urls);
			}
		} 
		return vo;
	}
	
	
	// 编辑文本
	public String addText() throws Exception {
		String result = getTipResult();
		try {
			if (StringUtils.isNotBlank(eventType)) {
				if ("Attention".equals(eventType) || "AutoAnswer".equals(eventType)) { 
					addOrUpdateTextAttention(eventType);
				} else if ("KeyWord".equals(eventType)) {
					addTextKeyWord();
				}
			}
		} catch(Exception e) {
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
	
	// 更新文本
	public String updateText() throws Exception {
		String result = getTipResult();
		try {
			if (StringUtils.isNotBlank(eventType)) {
				if ("KeyWord".equals(eventType)) {
					updateTextKeyWord();
				}
			}
		} catch(Exception e) {
			result = getTipResult("error", "error");
		}
		this.setAjaxInputStream(result);
		return AJAX;
	}
	
	private void updateTextKeyWord() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String tName = request.getParameter("tName");
		String tWord = request.getParameter("tWord");
		String tContent = request.getParameter("tContent");
		String selectId = request.getParameter("selectId");
		
		WeixinKey newkey = iWeixinKeyServiceImpl.view(selectId);
		newkey.setName(tName);
		newkey.setKey(tWord);
		newkey.setType("text");
		newkey.getResources().get(0).setContent(tContent);
		iWeixinKeyServiceImpl.update(newkey);
	}

	private void addTextKeyWord() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		String tName = request.getParameter("tName");
		String tWord = request.getParameter("tWord");
		String tContent = request.getParameter("tContent");
		String superId = request.getParameter("superId");
		
		WeixinKey newkey = new WeixinKey();
		newkey.setName(tName);
		newkey.setKey(tWord);
		newkey.setCustomer(employee.getCustomerName());
		newkey.setSort(0);
		newkey.setType("text");//文本回复
		newkey.setState(0);
		newkey.setLeave(0);
		Resource resource = new Resource();
		resource.setTitle("text");
		resource.setType("text"); // 文本
		resource.setCustomer(employee.getCustomerName());
		resource.setContent(tContent);
		List<Resource> resources = new ArrayList<Resource>();
		resources.add(resource);
		iResourceService.add(resource);
		newkey.setResources(resources);
		iWeixinKeyServiceImpl.add(newkey);
		//更新上级关键字
		String sub = newkey.getId() + "";
		if (StringUtils.isNotBlank(superId)) {
			WeixinKey wx = iWeixinKeyServiceImpl.view(superId);
			sub = wx.getSubId() + "/" + newkey.getId();
			wx.setLeave(1);
			iWeixinKeyServiceImpl.update(wx);
		} 
		newkey.setSubId(sub);
		iWeixinKeyServiceImpl.update(newkey);
	}
	
	public String deleteKey() throws Exception {
		String result = getTipResult();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String selectId = request.getParameter("selectId");
			
			if (StringUtils.isNotBlank(selectId)) {
				WeixinKey wx = iWeixinKeyServiceImpl.view(selectId);
				if (wx.getLeave() == 1) {
					result = getTipResult("no", "no");
				} else {
					iWeixinKeyServiceImpl.delete(wx);
				}
			} else {
				result = getTipResult("no", "no");
			}
		} catch(Exception e) {
			result = getTipResult("error", "error");
		}
		this.setAjaxInputStream(result);
		return AJAX;
	}
	
	private void addOrUpdateTextAttention(String event) {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		List<WeixinKey> keys = iWeixinKeyServiceImpl.findByNameAndCustomer(event, employee.getCustomerName());
		if (keys != null && keys.size() > 0) {
			WeixinKey key = keys.get(0);
			key.setType("text");
			Resource resource = new Resource();
			resource.setTitle(event);
			resource.setType("text"); // 文本
			resource.setCustomer(employee.getCustomerName());
			resource.setContent(textContent);
			List<Resource> resources = new ArrayList<Resource>();
			resources.add(resource);
			key.setResources(resources);
			iWeixinKeyServiceImpl.update(key);
		} else {
			WeixinKey newkey = new WeixinKey();
			newkey.setName(event);
			newkey.setKey(event);
			newkey.setCustomer(employee.getCustomerName());
			newkey.setSort(0);
			newkey.setType("text");//文本回复
			newkey.setState(0);
			newkey.setLeave(0);
			Resource resource = new Resource();
			resource.setTitle(event);
			resource.setType("text"); // 文本
			resource.setCustomer(employee.getCustomerName());
			resource.setContent(textContent);
			List<Resource> resources = new ArrayList<Resource>();
			resources.add(resource);
			iResourceService.add(resource);
			newkey.setResources(resources);
			iWeixinKeyServiceImpl.add(newkey);
		}
	}
	
	public String buildKeyTree() throws Exception {
		String result = getTipResult();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			Employee employee = (Employee)request.getSession().getAttribute("employee");
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			List<WeixinKey> tList = iWeixinKeyServiceImpl.findkeysAndCustomer(employee.getCustomerName());
			List<WeixinKeyVo> keyList = new ArrayList<WeixinKeyVo>();
			for (WeixinKey key : tList) {
				WeixinKeyVo vo = new WeixinKeyVo();
				vo.setId(key.getId());
				String subId = key.getSubId();
				String[] subs = subId.split("/");
				if (subs.length == 1) {
					vo.setSubId("0");
				} else {
					vo.setSubId(subs[subs.length - 2]);
				}
				vo.setName(key.getName());
				vo.setKey(key.getKey());
				vo.setType(key.getType());
				keyList.add(vo);
			}
			JSONObject obj = new JSONObject();
			obj.put("code", "ok");
			obj.put("list", keyList);
			result = obj.toString();
		} catch(Exception e) {
			result = getTipResult("error", "error");
		}
		this.setAjaxInputStream(result);
		return AJAX;
	}
	
	public String getKeyDetail() throws Exception {
		String result = getTipResult();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			Employee employee = (Employee)request.getSession().getAttribute("employee");
			String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
			String showPath = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORSHOW", "0").get(0).getValue();
			response.setCharacterEncoding("utf-8");
			WeixinKey key = this.getEntity();
			
			WeixinKeyVo vo = new WeixinKeyVo();
			vo.setId(key.getId());
			String subId = key.getSubId();
			String[] subs = subId.split("/");
			if (subs.length == 1) {
				vo.setSubId("0");
			} else {
				vo.setSubId(subs[subs.length - 2]);
			}
			vo.setName(key.getName());
			vo.setKey(key.getKey());
			vo.setType(key.getType());
			if ("text".equals(key.getType())) {
				vo.setContent(key.getResources().get(0).getContent());
			} else if ("image".equals(key.getType())) {
				Resource r = key.getResources().get(0);
				vo.setType("image");
				String[] links = new String[] {ip + employee.getCustomerName() + File.separator + r.getPicName()};
				vo.setPicLink(links);
				String[] ids = new String[]{r.getId() + ""};
				vo.setResourceId(ids);
			} else if ("news".equals(key.getType())) {
				List<Resource> list = key.getResources();
				vo.setType("news");
				int size = list.size();
				String[] titles = new String[size];
				String[] links = new String[size];
				String[] contents = new String[size];
				String[] ids = new String[size];
				String[] urls = new String[size];
				
				for (int i = 0; i < list.size(); i++) {
					Resource r = key.getResources().get(i);
					titles[i] = r.getTitle();
					String name = r.getPicName();
					if (StringUtils.isNotBlank(name)) {
						links[i] = name.startsWith("http:") ? name : (ip + employee.getCustomerName() + "/" + name);
					} else {
						links[i] = "";
					}
					contents[i] = r.getContent();
					ids[i] = r.getId() + "";
					String url = r.getUrl();
					if (StringUtils.isNotBlank(url)) {
						urls[i] = url.startsWith("http:") ? url : (showPath + employee.getCustomerName() + "/" + url);
					} else {
						urls[i] = "";
					}
				}
				vo.setTitle(titles);
				vo.setPicLink(links);
				vo.setContents(contents);
				vo.setResourceId(ids);
				vo.setUrl(urls);
			}
			JSONObject obj = new JSONObject(vo);
			obj.put("code", "ok");
			result = obj.toString();
		} catch(Exception e) {
			result = getTipResult("error", "error");
		}
		this.setAjaxInputStream(result);
		return AJAX;
	}

	// 编辑图片
	public String addImage() throws Exception {
		String result = getTipResult();
		try {
			if (StringUtils.isNotBlank(eventType)) { 
				if ("Attention".equals(eventType) || "AutoAnswer".equals(eventType)) { 
					addOrUpdateImageAttention(eventType);
				} else if("KeyWord".equals(eventType)) {
					addImageKeyWord();
				}
			}
		} catch(Exception e) {
			result = getTipResult("error", "error");
		}
		this.setAjaxInputStream(result);
		return AJAX;
	}
	
	private void addOrUpdateImageAttention(String event) {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		List<WeixinKey> keys = iWeixinKeyServiceImpl.findByNameAndCustomer(event, employee.getCustomerName()); //是否配置了关注信息
		if (keys != null && keys.size() > 0) { // 存在文本
			WeixinKey key = keys.get(0);
			Resource resource = iResourceService.view(resourceId);
			List<Resource> resources = new ArrayList<Resource>();
			resources.add(resource);
			key.setResources(resources);
			key.setType("image"); //设置格式
			iWeixinKeyServiceImpl.update(key);
		} else { //添加图片资源
			WeixinKey newkey = new WeixinKey();
			newkey.setName(event);
			newkey.setKey(event);
			newkey.setType("image");
			newkey.setCustomer(employee.getCustomerName());
			newkey.setSort(0);
			newkey.setLeave(0);
			newkey.setState(0);
			Resource resource = iResourceService.view(resourceId);
			List<Resource> resources = new ArrayList<Resource>();
			resources.add(resource);
			newkey.setResources(resources);
			iWeixinKeyServiceImpl.add(newkey);
		}
	}
	
	// 更新图片
	public String updateImage() throws Exception {
		String result = getTipResult();
		try {
			if (StringUtils.isNotBlank(eventType)) {
				if ("KeyWord".equals(eventType)) {
					updateImageKeyWord();
				}
			}
		} catch(Exception e) {
			result = getTipResult("error", "error");
		}
		this.setAjaxInputStream(result);
		return AJAX;
	}
	
	private void updateImageKeyWord() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String tName = request.getParameter("tName");
		String tWord = request.getParameter("tWord");
		String resourceId = request.getParameter("resourceId");
		String selectId = request.getParameter("selectId");
		
		WeixinKey newkey = iWeixinKeyServiceImpl.view(selectId);
		newkey.setName(tName);
		newkey.setKey(tWord);
		newkey.setType("image");
		Resource resource = iResourceService.view(resourceId);
		List<Resource> resources = new ArrayList<Resource>();
		resources.add(resource);
		newkey.setResources(resources);
		iWeixinKeyServiceImpl.update(newkey);
	}

	private void addImageKeyWord() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		String tName = request.getParameter("tName");
		String tWord = request.getParameter("tWord");
		String resourceId = request.getParameter("resourceId");
		String superId = request.getParameter("superId");
		
		WeixinKey newkey = new WeixinKey();
		newkey.setName(tName);
		newkey.setKey(tWord);
		newkey.setCustomer(employee.getCustomerName());
		newkey.setSort(0);
		newkey.setLeave(0);
		newkey.setType("image");//文本回复
		newkey.setState(0);
		Resource resource = iResourceService.view(resourceId);
		List<Resource> resources = new ArrayList<Resource>();
		resources.add(resource);
		newkey.setResources(resources);
		iWeixinKeyServiceImpl.add(newkey);
		//更新上级关键字
		String sub = newkey.getId() + "";
		if (StringUtils.isNotBlank(superId)) {
			WeixinKey wx = iWeixinKeyServiceImpl.view(superId);
			sub = wx.getSubId() + "/" + newkey.getId();
		} 
		newkey.setSubId(sub);
		iWeixinKeyServiceImpl.update(newkey);
	}

	public String getText() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		WeixinKeyVo vo = new WeixinKeyVo();
		
		try {
			Employee employee = (Employee)request.getSession().getAttribute("employee");
			String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
			List<WeixinKey> keys = iWeixinKeyServiceImpl.findByNameAndCustomer(eventType, employee.getCustomerName()); //事件类型
			String showPath = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORSHOW", "0").get(0).getValue();
			if (keys != null && keys.size() > 0) {
				WeixinKey key = keys.get(0);
				vo.setId(key.getId());
				vo.setName(key.getName());
				vo.setKey(key.getKey());
				vo.setType(key.getType());
				if ("text".equals(key.getType())) {
					vo.setContent(key.getResources().get(0).getContent());
				} else if ("image".equals(key.getType())) {
					Resource r = key.getResources().get(0);
					vo.setType("image");
					String[] links = new String[] {ip + r.getPicName()};
					vo.setPicLink(links);
					String[] ids = new String[]{r.getId() + ""};
					vo.setResourceId(ids);
				} else if ("news".equals(key.getType())) {
					List<Resource> list = key.getResources();
					vo.setType("news");
					int size = list.size();
					String[] titles = new String[size];
					String[] links = new String[size];
					String[] contents = new String[size];
					String[] ids = new String[size];
					String[] urls = new String[size];
					
					for (int i = 0; i < list.size(); i++) {
						Resource r = key.getResources().get(i);
						titles[i] = r.getTitle();
						String name = r.getPicName();
						if (StringUtils.isNotBlank(name)) {
							links[i] = name.startsWith("http:") ? name : (ip + name);
						} else {
							links[i] = "";
						}
						contents[i] = r.getContent();
						ids[i] = r.getId() + "";
						String url = r.getUrl();
						if (StringUtils.isNotBlank(url)) {
							urls[i] = url.startsWith("http:") ? url : (showPath + url);
						} else {
							urls[i] = "";
						}
						
					}
					vo.setTitle(titles);
					vo.setPicLink(links);
					vo.setContents(contents);
					vo.setResourceId(ids);
					vo.setUrl(urls);
				}
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject(vo);
		response.getWriter().print(obj.toString());
		return NONE;
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
	
	// 上传ZIP资源
	public String uploadZIP() throws Exception {
		String result = getTipResult();
		try {
			String pagePath = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORHTML", "0").get(0).getValue();
			String showPage = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORSHOW", "0").get(0).getValue();
			String time = TimeHelper.getCurrentDetailTime() + "";
			String newFileName = time + "_" + fileInputFileName;
			String separator = System.getProperties().getProperty("file.separator");
			String filePath = pagePath + time + separator + newFileName;
			File direcFile = new File(pagePath + time);
			if (!direcFile.exists()) {
				direcFile.mkdir();
			}
			fileInput.renameTo(new File(filePath));
			Boolean bool = ZipUtil.releaseZipToFile(filePath, pagePath + time);
			if (bool) {
				result = getTipResult("ok", showPage + time + "/index.html");
			} else {
				result = getTipResult("error", "error");
			}
		} catch(Exception e) {
			result = getTipResult("error", "error");
		}
		this.setAjaxInputStream(result);
		return AJAX;
	}
	
	// 获取图片资源
	public String getImage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		HttpServletResponse response = ServletActionContext.getResponse();
		String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// 查询所有图片资源
		List<Resource> list = iResourceService.findByTypeAndCustomer("image", employee.getCustomerName());
		List<ResourceVo> listImage = new ArrayList<ResourceVo>();
		JSONArray json = new JSONArray();
		if (list != null && list.size() > 0) {
			for (Resource r : list) {
				listImage.add(new ResourceVo(r));
			}
			JSONObject obj = new JSONObject();
			obj.put("list", listImage);
			obj.put("path", ip + employee.getCustomerName() + "/");
			json.put(obj);
		}
		out.print(json.toString());
		return NONE;
	}
	
	// 获取图文资源
	public String getNews() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(getNewsString());
		return NONE;
	}
	
	// 添加单条图文资源
	public String addSingleNews() throws Exception {
		try {
			addSingleNewsKeyWord();
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(getNewsString());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	private void addSingleNewsKeyWord() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String tTitle = request.getParameter("tTitle");
		String tContent = request.getParameter("tContent");
		String resourceId = request.getParameter("resourceId");
		
		Resource resource = iResourceService.view(resourceId);
		resource.setTitle(tTitle);
		resource.setType("news");
		resource.setContent(tContent);
		iResourceService.update(resource);
	}

	private String getNewsString() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		List<Resource> list = iResourceService.findByTypeAndCustomer("news", employee.getCustomerName());
		List<ResourceVo> listImage = new ArrayList<ResourceVo>();
		JSONArray json = new JSONArray();
		if (list != null && list.size() > 0) {
			for (Resource r : list) {
				listImage.add(new ResourceVo(r));
			}
			JSONObject obj = new JSONObject();
			obj.put("list", listImage);
			obj.put("path", ip + employee.getCustomerName() + "/");
			json.put(obj);
		}
		return json.toString();
	}
	
	// 更新图文
	public String updateNews() throws Exception { 
		String result = getTipResult();
		try {
			if (StringUtils.isNotBlank(eventType)) {
				if ("KeyWord".equals(eventType)) {
					updateNewsKeyWord();
				}
			}
		} catch(Exception e) {
			result = getTipResult("error", "error");
		}
		this.setAjaxInputStream(result);
		return AJAX;
	}
	
	private void updateNewsKeyWord() { 
		HttpServletRequest request = ServletActionContext.getRequest();
		String tName = request.getParameter("tName");
		String tWord = request.getParameter("tWord");
		String resourceId = request.getParameter("resourceId");
		String selectId = request.getParameter("selectId");
		
		WeixinKey newkey = iWeixinKeyServiceImpl.view(selectId);
		newkey.setName(tName);
		newkey.setKey(tWord);
		newkey.setType("news");
		Resource resource = iResourceService.view(resourceId);
		List<Resource> resources = new ArrayList<Resource>();
		resources.add(resource);
		newkey.setResources(resources);
		iWeixinKeyServiceImpl.update(newkey);
	}
	
	// 添加图文
	public String addNews() throws Exception {
		String result = getTipResult();
		try {
			if (StringUtils.isNotBlank(eventType)) { 
				if ("Attention".equals(eventType) || "AutoAnswer".equals(eventType)) { 
					addOrUpdateNewsAttention(eventType);
				} else if("KeyWord".equals(eventType)) {
					addNewsKeyWord();
				}
			}
		} catch(Exception e) {
			result = getTipResult("error", "error");
		}
		this.setAjaxInputStream(result);
		return AJAX;
	}
	
	private void addOrUpdateNewsAttention(String event) {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		List<WeixinKey> keys = iWeixinKeyServiceImpl.findByNameAndCustomer(event, employee.getCustomerName()); //是否配置了关注信息
		if (keys != null && keys.size() > 0) { // 存在文本
			WeixinKey key = keys.get(0);
			Resource resource = iResourceService.view(resourceId);
			List<Resource> resources = new ArrayList<Resource>();
			resources.add(resource);
			key.setResources(resources);
			key.setType("news"); 
			iWeixinKeyServiceImpl.update(key);
		} else { 
			WeixinKey newkey = new WeixinKey();
			newkey.setName(event);
			newkey.setKey(event);
			newkey.setType("news");
			newkey.setCustomer(employee.getCustomerName());
			newkey.setSort(0);
			newkey.setState(0);
			newkey.setLeave(0);
			Resource resource = iResourceService.view(resourceId);
			List<Resource> resources = new ArrayList<Resource>();
			resources.add(resource);
			newkey.setResources(resources);
			iWeixinKeyServiceImpl.add(newkey);
		}
	}
	
	private void addNewsKeyWord() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		String tName = request.getParameter("tName");
		String tWord = request.getParameter("tWord");
		String resourceId = request.getParameter("resourceId");
		String superId = request.getParameter("superId");
		
		WeixinKey newkey = new WeixinKey();
		newkey.setName(tName);
		newkey.setKey(tWord);
		newkey.setCustomer(employee.getCustomerName());
		newkey.setSort(0);
		newkey.setLeave(0);
		newkey.setType("news");//文本回复
		newkey.setState(0);
		Resource resource = iResourceService.view(resourceId);
		List<Resource> resources = new ArrayList<Resource>();
		resources.add(resource);
		newkey.setResources(resources);
		iWeixinKeyServiceImpl.add(newkey);
		//更新上级关键字
		String sub = newkey.getId() + "";
		if (StringUtils.isNotBlank(superId)) {
			WeixinKey wx = iWeixinKeyServiceImpl.view(superId);
			sub = wx.getSubId() + "/" + newkey.getId();
		} 
		newkey.setSubId(sub);
		iWeixinKeyServiceImpl.update(newkey);
	}

	
	@Override
	public String edit() throws Exception {
		WeixinKey p = this.getEntity();
		String tCustomer = p.getCustomer();
		if (StringUtils.isNotBlank(p.getCustomer())) {
			tCustomer = iCustomerDAO.findById(Long.valueOf(p.getCustomer())).getShotName();
		}
		p.setCustomer(tCustomer);
		return super.edit();
	}
	
	@Override
	public String detail() throws Exception {
		WeixinKey p = this.getEntity();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("WeixinKey", p);
		return super.detail();
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
		List<WeixinKey> weixins = iWeixinKeyServiceImpl.findByType("click");
		for(WeixinKey weixin : weixins){
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
		List<WeixinKey> weixins = iWeixinKeyServiceImpl.findByType("searchKey");
		for(WeixinKey weixin : weixins){
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
			WeixinKey weixin = iWeixinKeyServiceImpl.view(id);
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
		List<WeixinKey> weixins = iWeixinKeyServiceImpl.findByType("click");
		for(WeixinKey weixin : weixins){
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

	/**
	 * 异步查询type相对应的关键词
	 * @return
	 * @throws IOException
	 */
	public String selectKey() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		response.setCharacterEncoding("utf-8");
		String searchType = request.getParameter("type");
		if(StringUtils.isNotBlank(searchType)){
			List<WeixinKey> weixins  = iWeixinKeyServiceImpl.findByTypeAndCustomer(searchType, employee.getCustomerName());
			if(weixins != null && weixins.size() > 0){
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append("<select name=\"key\" id=\"key\">");
				stringBuffer.append("<option value=''>不选择</option>");
				for(WeixinKey key : weixins){
					if("Attention".equalsIgnoreCase(key.getName())){
						stringBuffer.append("<option value='"+key.getId()+"'>"+"被添加自动回复"+"</option>");
					}else if("AutoAnswer".equalsIgnoreCase(key.getName())){
						stringBuffer.append("<option value='"+key.getId()+"'>"+"关键词自动回复"+"</option>");
					}else{
						stringBuffer.append("<option value='"+key.getId()+"'>"+key.getName()+"</option>");
					}
				}
				stringBuffer.append("</select>");
				response.getWriter().print(stringBuffer.toString());
			}
		}
		return this.NONE;
	}
	
	
	@Override
	public CRUDService<WeixinKey> getAutowiredService() {
		return iWeixinKeyServiceImpl;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
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

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
}
