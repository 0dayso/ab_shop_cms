package net.rytong.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
@Scope("prototype")
public class UserExitAction extends ActionSupport{
	private static final long serialVersionUID = 702993411775558670L;
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().removeAttribute("employee");
		request.getSession().removeAttribute("functionRightList");
		request.getSession().removeAttribute("projectTitle");
		request.getSession().removeAttribute("loginPng");
		request.getSession().removeAttribute("logoPng");
		request.getSession().removeAttribute("logoIco");
		request.getSession().removeAttribute("projectName");
		return SUCCESS;
	}
}
