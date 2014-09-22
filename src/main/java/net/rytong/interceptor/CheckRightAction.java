package net.rytong.interceptor;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rytong.entity.Employee;
import net.rytong.entity.FunctionRight;
import net.rytong.entity.Role;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class CheckRightAction extends ActionSupport {
	private static final long serialVersionUID = -3666556887669029043L;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		String path = request.getParameter("path");
		boolean hasRight = this.hasRight(employee, path);
		if(hasRight){
			response.setContentType("text/html; charset=utf-8"); 
			response.setCharacterEncoding("utf-8");    
			PrintWriter out = response.getWriter();
			out.print("0");
			out.flush();
			out.close();
		}else{
			response.setContentType("text/html; charset=utf-8"); 
			response.setCharacterEncoding("utf-8");    
			PrintWriter out = response.getWriter();
			out.print("1");
			out.flush();
			out.close();
		}
		return NONE;
	}
	
	public boolean hasRight(Employee employee, String actionPath) {
		List<Role> roles = employee.getRoles();
		for(Role role : roles){
			for (FunctionRight functionRight : role.getFunctionRights()) {
				if(functionRight.getPath().equals(actionPath)){
					return true;
				}
			}
		}
		
		if(employee.getRole().equals("admin")){
			return true;
		}
		return true;
	}
}
