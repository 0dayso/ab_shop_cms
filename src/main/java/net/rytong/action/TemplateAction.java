package net.rytong.action;

import javax.servlet.http.HttpServletRequest;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.entity.Template;
import net.rytong.impl.TemplateServiceImpl;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class TemplateAction extends CRUDActionSupport<Template> {
	private static final long serialVersionUID = 5207197603748720772L;
	@Autowired
	private TemplateServiceImpl templateServiceImpl;
	
	@Override
	public String execute() throws Exception{
		return SUCCESS;
	}

	@Override
	public CRUDService<Template> getAutowiredService() {
		return templateServiceImpl;
	}

	@Override
	public String detail() throws Exception {
		Template template = this.getEntity();
		HttpServletRequest request = ServletActionContext.getRequest(); 
		request.setAttribute("tempInfo", templateServiceImpl.view(template.getId().toString()));
		return super.detail();
	}
	
	
	
}
