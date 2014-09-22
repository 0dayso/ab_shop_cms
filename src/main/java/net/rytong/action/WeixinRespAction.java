package net.rytong.action;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.dao.ICustomerDAO;
import net.rytong.dao.IParameterDAO;
import net.rytong.entity.Employee;
import net.rytong.entity.WeixinResp;
import net.rytong.impl.WeixinRespServiceImpl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeixinRespAction extends CRUDActionSupport<WeixinResp>{
	private static final long serialVersionUID = 1012161232986360461L;
	@Autowired
	private WeixinRespServiceImpl weixinRespServiceImpl;
	@Autowired
	private ICustomerDAO iCustomerDAO;
	@Autowired
	private IParameterDAO iParameterDAO;
	private File[] upload;
    private String[] uploadFileName;
    private String[] uploadContentType;
    private String picString;

	@Override
	public String execute() throws Exception{
		return SUCCESS;
	}
	
	public String search() throws Exception{
		Map<String, Object> filterMap = new HashMap<String, Object>();
		WeixinResp p = this.getEntity();
		String tCustomer = p.getCustomer();
		if (StringUtils.isNotBlank(tCustomer)) {
			tCustomer = iCustomerDAO.findById(Long.valueOf(p.getCustomer())).getShotName();
		}
		if (StringUtils.isNotBlank(p.getTitle())) {
			filterMap.put("title", p.getTitle());
		}
		if (StringUtils.isNotBlank(p.getType())) {
			filterMap.put("type", p.getType());
		}
		if (StringUtils.isNotBlank(p.getMenuId())) {
			filterMap.put("menuId", p.getMenuId());
		}
		if (StringUtils.isNotBlank(p.getPicName())) {
			filterMap.put("picName", p.getPicName());
		}
		if (StringUtils.isNotBlank(tCustomer)) {
			filterMap.put("customer", tCustomer);
		}
		this.setFilterMap(filterMap);
		return this.pageList();
	}
	
	@Override
	public String add() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
        String fillPath = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATH", employee.getCustomerName()).get(0).getValue();
        StringBuffer pics = new StringBuffer();
        //打印路径看是否存在  
        if(upload != null){  
            File savedir = new File(fillPath) ;  
            if(!savedir.exists()){  
                savedir.mkdirs() ;  
            }  
            for(int i = 0; i < upload.length; i++){  
                File savefile = new File(savedir, uploadFileName[i]) ;  
                FileUtils.copyFile(upload[i], savefile) ; 
                pics.append(uploadFileName[i]).append(",");
            }  
        }  
        if (pics.length() > 0) {
        	pics.deleteCharAt(pics.length() - 1);
        }
		
        WeixinResp p = this.getEntity();
		String tCustomer = p.getCustomer();
		if (StringUtils.isNotBlank(tCustomer)) {
			tCustomer = iCustomerDAO.findById(Long.valueOf(p.getCustomer())).getShotName();
		}
		p.setCustomer(tCustomer);
		return super.add();
	}
	
	@Override
	public String detail() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		String ipAddess = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", employee.getCustomerName()).get(0).getValue();
		WeixinResp p = this.getEntity();
		String pics = p.getPicName();
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotBlank(pics)) {
			String[] s1 = pics.split(",");
			for (String s : s1) {
				sb.append("<img src='").append(ipAddess + "/" + s + "' alt=''/>");
			}
		}
		picString = sb.toString();
		request.setAttribute("weixinResp", p);
		return SUCCESS;
	}
	
	@Override
	public String edit() throws Exception {
		WeixinResp p = this.getEntity();
		String tCustomer = p.getCustomer();
		if (StringUtils.isNotBlank(tCustomer)) {
			tCustomer = iCustomerDAO.findById(Long.valueOf(p.getCustomer())).getShotName();
		}
		p.setCustomer(tCustomer);
		return super.edit();
	}

	@Override
	public CRUDService<WeixinResp> getAutowiredService() {
		return weixinRespServiceImpl;
	}
}
