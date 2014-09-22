package net.rytong.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.dao.IEmployeeTemplateDAO;
import net.rytong.dao.IParameterDAO;
import net.rytong.entity.Customer;
import net.rytong.entity.Employee;
import net.rytong.entity.EmployeeTemplate;
import net.rytong.entity.Resource;
import net.rytong.entity.Template;
import net.rytong.entity.WeixinKey;
import net.rytong.entity.WeixinMenu;
import net.rytong.impl.CustomerServiceImpl;
import net.rytong.impl.TemplateServiceImpl;
import net.rytong.impl.WeixinKeyServiceImpl;
import net.rytong.impl.WeixinMenuServiceImpl;
import net.rytong.service.IResourceService;
import net.rytong.utils.TimeHelper;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class CustomerAction extends CRUDActionSupport<Customer>{
	private static final long serialVersionUID = 1L;
	@Autowired
	private IParameterDAO iParameterDAO;
	@Autowired
	private CustomerServiceImpl iCustomerService;
	@Autowired
	private IEmployeeTemplateDAO  iEmployeeTemplateDAO;
	@Autowired
	private TemplateServiceImpl templateServiceImpl;
	@Autowired
	private WeixinMenuServiceImpl iWeixinMenuService;
	@Autowired
	private WeixinKeyServiceImpl iWeixinKeyService;
	@Autowired
	IResourceService iResourceService;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String search() throws Exception {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		Customer p = this.getEntity();
		if (StringUtils.isNotBlank(p.getShotName())) {
			filterMap.put("shotName", p.getShotName());
		}
		if (StringUtils.isNotBlank(p.getCompanyName())) {
			filterMap.put("companyName", p.getCompanyName());
		}
		if (StringUtils.isNotBlank(p.getAddress())) {
			filterMap.put("address", p.getAddress());
		}
		if (StringUtils.isNotBlank(p.getContactor())) {
			filterMap.put("contactor", p.getContactor());
		}
		if (StringUtils.isNotBlank(p.getUsername())) {
			filterMap.put("username", p.getUsername());
		}
		this.setFilterMap(filterMap);
		return this.pageList();
	}
	
	@Override
	public void validate() {
	}
	
	public String check() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String companyName = request.getParameter("name");
		List<Customer> customerList = iCustomerService.findByName(companyName);
		if(customerList.size()>0){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().print("该公司名称已经存在!");
		}
		return NONE;
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
	
	public String init() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		StringBuffer stringBuffer = new StringBuffer();
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		stringBuffer.append("<select name=\"customer\" id=\"customer\">");
		stringBuffer.append("<option value=''>全部</option>");
		List<Customer> customers = iCustomerService.findAll();
		for(Customer customer : customers){
			if(StringUtils.isNotBlank(id) && customer.getId().toString().equals(id)){
				stringBuffer.append("<option value='"+customer.getId()+"' selected=\"selected\">"+customer.getCompanyName()+"</option>");
				continue;
			} else if (StringUtils.isNotBlank(name) && customer.getShotName().toString().equals(name)) {
				stringBuffer.append("<option value='"+customer.getId()+"' selected=\"selected\">"+customer.getCompanyName()+"</option>");
				continue;
			}
			stringBuffer.append("<option value='"+customer.getId()+"'>"+customer.getCompanyName()+"</option>");
		}
		stringBuffer.append("</select>");
		response.getWriter().print(stringBuffer.toString());
		return NONE;
	}
	
	public String addHtml() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("employee");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		String html = request.getParameter("content"); //取得编辑器内容
		String templateName = request.getParameter("name");
		String menuId = request.getParameter("menuId");
		String keyId = request.getParameter("keyId");
		//模板路径
		String fillPath = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORHTML","0").get(0).getValue();
		//展示页路径
		String showPath = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORSHOW","0").get(0).getValue();
		PrintWriter out = response.getWriter();
		//替换占位符
		try {
			//TODO 从模板表中取得对应的选中模板信息
			Template template = templateServiceImpl.findTemplateByCode("moban");//参数code
			if(template == null){
				throw new Exception("没找到对应的模板");
			}
			String fileName = template.getName();
			FileInputStream stream = new FileInputStream(fillPath + fileName+".jsp");
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream,  "utf-8"));
			String lineTxt = null;
			StringBuffer sb = new StringBuffer();
			while((lineTxt = bufferedReader.readLine()) != null){
	        	sb.append(lineTxt);
	        }
			String result = sb.toString();
			html = result.replaceAll("####", html);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String name = TimeHelper.getCurrentDetailTime() + "";
		//生成对应的实际模板页面
        File file = new File(fillPath + name + ".jsp");
		if (!file.exists()) {
			file.createNewFile();
			OutputStream fis = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fis, "utf-8");
			osw.write(html);
			osw.flush();
			osw.close();
		}
		
		if (StringUtils.isNotBlank(menuId)) {
			WeixinMenu menu = iWeixinMenuService.view(menuId);
			List<Resource> resources = menu.getResources();
			if (resources != null && resources.size() > 0) {
				Resource r = resources.get(0);
				r.setUrl(name + ".jsp");
				iResourceService.update(r);
			}
		} else if (StringUtils.isNotBlank(keyId)) {
			WeixinKey key = iWeixinKeyService.view(keyId);
			List<Resource> resources = key.getResources();
			if (resources != null && resources.size() > 0) {
				Resource r = resources.get(0);
				r.setUrl(name + ".jsp");
				iResourceService.update(r);
			}
		}
		
		//保存到用户和模板的对应关系
		EmployeeTemplate et = new EmployeeTemplate();
		et.setCode("moban");
		et.setEmployeeId(employee.getId());
		et.setCustomer(employee.getCustomerName());
		et.setName(templateName);
		et.setPageName(showPath + name + ".jsp");
		iEmployeeTemplateDAO.save(et);
		
		out.println(showPath + name + ".jsp");
		out.flush();
		out.close();
		return NONE;
	}
	
	public String getMobanHtml() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("employee");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		//模板路径
		String fillPath = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORHTML","0").get(0).getValue();
		PrintWriter out = response.getWriter();
		String html = "";
		try {
			FileInputStream stream = new FileInputStream("D:\\360yunpan\\environ\\server\\apache-tomcat-6.0.32\\webapps\\manage\\public\\moban\\index-oa");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream,  "utf-8"));
			String lineTxt = null;
			StringBuffer sb = new StringBuffer();
			while((lineTxt = bufferedReader.readLine()) != null){
	        	sb.append(lineTxt);
	        }
			html = sb.toString();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		out.println(html);
		out.flush();
		out.close();
		return NONE;
	}
   //首页对url token的编辑
	public String editorByType() throws JSONException{
		HttpServletRequest request = ServletActionContext.getRequest();
		Customer customer = this.getEntity();
		String type = request.getParameter("type");
		String value = request.getParameter("value");
		if("url".equals(type)){
			customer.setUrl(value);
		}else if("token".equals(type)){
			customer.setToken(value);
		}
		iCustomerService.update(customer);
		JSONObject object = new JSONObject();
		object.put("code", "ok");
		this.setAjaxInputStream(object.toString());
		request.getSession().setAttribute("customer", customer);
		return AJAX;
	}
	//接口设置中保存接口信息
	public String savePort() throws JSONException{
		HttpServletRequest  request = ServletActionContext.getRequest();
		Customer customer = this.getEntity();
		iCustomerService.update(customer);
		request.getSession().setAttribute("customer", customer);
		JSONObject object = new JSONObject();
		object.put("code", "ok");
		this.setAjaxInputStream(object.toString());
		return AJAX;
	}
	
	
	@Override
	public CRUDService<Customer> getAutowiredService() {
		return iCustomerService;
	}
}