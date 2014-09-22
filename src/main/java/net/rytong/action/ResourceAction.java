package net.rytong.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.dao.IParameterDAO;
import net.rytong.entity.Article;
import net.rytong.entity.Employee;
import net.rytong.entity.Page;
import net.rytong.entity.Resource;
import net.rytong.impl.ArticleServiceImpl;
import net.rytong.impl.PageServiceImpl;
import net.rytong.impl.ResourceServiceImpl;
import net.rytong.service.IParameterService;
import net.rytong.utils.FileUtil;
import net.rytong.utils.TimeHelper;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class ResourceAction extends CRUDActionSupport<Resource> {
	private static final long serialVersionUID = 5207197603748720772L;
	@Autowired
	private ResourceServiceImpl resourceServiceImpl;
	@Autowired
	private PageServiceImpl pageService;
	@Autowired
	private ArticleServiceImpl articleService;
	@Autowired
	private IParameterDAO iParameterDAO;
	private String picUrl;
	@Autowired
	private IParameterService iParameterService;
	@Override
	public String execute() throws Exception{
		return SUCCESS;
	}

	@Override
	public CRUDService<Resource> getAutowiredService() {
		return resourceServiceImpl;
	}
	private String id;
	private File imagefile;
	private String imagefileFileName;
	private String imagefileContentType;
	
	private String resType; //素材类型
	
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
	public String getImagefileContentType() {
		return imagefileContentType;
	}
	public void setImagefileContentType(String imagefileContentType) {
		this.imagefileContentType = imagefileContentType;
	}
	public String getImagefileFileName() {
		return imagefileFileName;
	}
	public void setImagefileFileName(String imagefileFileName) {
		this.imagefileFileName = imagefileFileName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String detail() throws Exception {
		Resource resource = this.getEntity();
		HttpServletRequest request = ServletActionContext.getRequest(); 
		request.setAttribute("data", resourceServiceImpl.view(resource.getId().toString()));
		return super.detail();
	}

	@Override
	public void addOtherFilter() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		if (!"admin".equals(employee.getName())) {
			this.getFilterMap().put("customer", employee.getCustomerName());
		}
		this.setPicUrl(iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue());
		super.addOtherFilter();
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	public String getInfos() throws Exception {
		String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		HttpServletRequest request = ServletActionContext.getRequest();
//		String type = request.getParameter("type");
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		// 查询一级菜单
		List<Resource> list = null;
		JSONArray json = new JSONArray();
		try{
			list = resourceServiceImpl.findByTypeAndCustomer(resType,employee.getCustomerName());
		}catch(Exception e){
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			JSONObject obj = new JSONObject();
			Resource res = list.get(i);
			obj.put("id", res.getId().toString());
			obj.put("picUrl",ip + employee.getCustomerName() + "/" + res.getPicName());
			obj.put("title", res.getTitle());
			json.put(obj);
		} 
		System.out.println(json.toString());
		this.setAjaxInputStream(json.toString());
		return AJAX;
	}
	
	public String getNewsById() throws Exception {
		String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		String id = request.getParameter("id");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		JSONArray json = new JSONArray();
		JSONObject obj = new JSONObject();
		Resource res = resourceServiceImpl.view(id);
		obj.put("id",res.getId().toString());
		obj.put("title", res.getTitle());
		obj.put("picUrl",ip + employee.getCustomerName() + "/" + res.getPicName());
		obj.put("content", res.getContent());
		obj.put("type",res.getType());
		json.put(obj);
		out.print(json.toString());
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
    		String url = ip + employee.getCustomerName() + "/" + newFileName;
            JSONObject json = new JSONObject();
            json.put("status", "success");
            json.put("url", url);
            json.put("name",newFileName);
            if("image".equals(resType)){ //当素材类型为图片时，要将其保存在数据库中
            	Resource resource = new Resource();
            	resource.setCustomer(employee.getCustomerName());
            	resource.setPicName(newFileName);
            	resource.setType(resType);
            	resource.setContent(newFileName);
            	resourceServiceImpl.add(resource);
            }
            
            HttpServletResponse response = ServletActionContext.getResponse();
    		response.setCharacterEncoding("utf-8");
    		System.out.println("json=====>"+json.toString());
    		response.getWriter().print(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NONE;
    }
	
	public String uploadPageImage(){
        HttpServletRequest request = ServletActionContext.getRequest();
        Employee employee = (Employee)request.getSession().getAttribute("employee");
        String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		String pageId = request.getParameter("pageId");
		Page page = pageService.view(pageId);
		
        try {
    		String newFileName = saveFile();
    		String url = ip + employee.getCustomerName() + "/" + newFileName;
    		page.setBgImage(url);
    		pageService.update(page);
    		updatePage(page);
            JSONObject json = new JSONObject();
            json.put("status", "success");
            json.put("url", url);
            json.put("name",newFileName);
            HttpServletResponse response = ServletActionContext.getResponse();
    		response.setCharacterEncoding("utf-8");
    		System.out.println("json=====>"+json.toString());
    		response.getWriter().print(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NONE;
    }
	
	public String uploadPageBannerImage(){
        HttpServletRequest request = ServletActionContext.getRequest();
        Employee employee = (Employee)request.getSession().getAttribute("employee");
        String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		String pageId = request.getParameter("pageId");
		Page page = pageService.view(pageId);
		
        try {
    		String newFileName = saveFile();
    		String url = ip + employee.getCustomerName() + "/" + newFileName;
    		page.setBannerImage(url);
    		pageService.update(page);
    		updatePage(page);
            JSONObject json = new JSONObject();
            json.put("status", "success");
            json.put("url", url);
            json.put("name",newFileName);
            HttpServletResponse response = ServletActionContext.getResponse();
    		response.setCharacterEncoding("utf-8");
    		System.out.println("json=====>"+json.toString());
    		response.getWriter().print(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NONE;
    }
	
	private void updatePage(Page page) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String fillPath = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORHTML","0").get(0).getValue();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		List<Article> articles = articleService.findByPageAndCustomer(page.getId(), employee.getCustomerName());
		List<Page> pages = pageService.findByLevelAndCustomer(employee.getCustomerName(), 1);
		FileUtil.updatePage(pages.get(0).getUrl(), fillPath + "index.html", fillPath + "article.html", fillPath + page.getName(), page, articles);
	}
	
	private String saveFile() {
		String newFileName = "";
        String imgUrl = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IMAGE_PATH", "0").get(0).getValue();
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
	
	//编辑单图文
	public String editResource() throws Exception{
		Resource res = this.getEntity();
		resourceServiceImpl.update(res);
		String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		JSONObject obj = new JSONObject();
		obj.put("code", "ok");
		obj.put("id",res.getId().toString());
		obj.put("title", res.getTitle());
		obj.put("picUrl",ip + employee.getCustomerName() + "/" + res.getPicName());
		obj.put("content", res.getContent());
		obj.put("type",res.getType());
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}
	//添加单图文
	public String saveResource() throws Exception{
		Resource res = this.getEntity();
		resourceServiceImpl.add(res);
		String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		JSONObject obj = new JSONObject();
		obj.put("code", "ok");
		obj.put("id",res.getId().toString());
		obj.put("title", res.getTitle());
		obj.put("picUrl",ip + employee.getCustomerName() + "/" + res.getPicName());
		obj.put("content", res.getContent());
		obj.put("type",res.getType());
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}
	//TODO
	public String counts() throws JSONException{
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		List<Object[]> list = resourceServiceImpl.countsByCustomer(employee.getCustomerName());
		
		JSONObject jsonObject = new JSONObject();
//		String[] types = {"text","news","image","vedio"}; //素材类型
	    for(Object[] obj : list){
	    	jsonObject.put(obj[0].toString(), obj[1].toString());
	    }	
	    System.out.println(jsonObject.toString());
	    this.setAjaxInputStream(jsonObject.toString());
		return AJAX;
	}
	
	class vo{
		String typeName;
		Long count; 
		
	}
	
	private static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}
	
	private String saveImgCoupon(String cn,String cd) {
		String newFileName = "";
        String imgUrl = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IMAGE_PATH", "0").get(0).getValue();
        File pathFile = new File(imgUrl +"coupon");
        if(!pathFile.exists()){
        	pathFile.mkdir();
        }
        try {
        	File file = this.getImagefile();
    		newFileName =  cn + "coupon_" + cd +".jpg";
    		String spath = imgUrl +"coupon/" + File.separator + newFileName;
    		File files =new File(spath);
    		if ( files.exists() ) {
    			files.delete();
    		}
    		file.renameTo(new File(spath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFileName;
	}
	
	public String uploadCouponBannerImage(){
		String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		String code = request.getParameter("code");
		try {
    		String newFileName = saveImgCoupon(employee.getCustomerName(),code);
    		String url = ip + "coupon/" + newFileName;
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
}
