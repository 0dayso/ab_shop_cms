package net.rytong.action;

import javax.servlet.http.HttpServletRequest;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.entity.Employee;
import net.rytong.entity.WeixinUser;
import net.rytong.impl.WeixinUserServiceImpl;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("weixinUserAction")
@Scope("prototype")
public class WeixinUserAction extends CRUDActionSupport<WeixinUser> {
	private static final long serialVersionUID = 1012161232986360461L;
	@Autowired
	private WeixinUserServiceImpl iWeixinUserService;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	@Override
	public void addOtherFilter() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		if (!"admin".equals(employee.getName())) {
			this.getFilterMap().put("customer", employee.getCustomerName());
		}
		super.addOtherFilter();
	}

	@Override
	public CRUDService<WeixinUser> getAutowiredService() {
		return iWeixinUserService;
	}
}
