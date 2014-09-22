package net.rytong.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.rytong.action.RegisterAction;
import net.rytong.action.UserAction;
import net.rytong.action.UserExitAction;
import net.rytong.action.UserLoginAction;
import net.rytong.entity.Employee;
import net.rytong.entity.FunctionRight;
import net.rytong.entity.Role;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorizationInterceptor extends AbstractInterceptor{
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Object action = actionInvocation.getAction();
		String actionName = actionInvocation.getInvocationContext().getName();
		Employee employee = (Employee)ServletActionContext.getRequest().getSession().getAttribute("employee");
		if (action instanceof UserLoginAction) {
			return actionInvocation.invoke();
		}else if(action instanceof RegisterAction){
			return actionInvocation.invoke();
		}else if (action instanceof UserAction) {
			return actionInvocation.invoke();
		}else if(action instanceof UserExitAction){
			return actionInvocation.invoke();
		}else if(actionName.contains("Init")){
			return actionInvocation.invoke();
		}else if(action instanceof CheckRightAction){
			return actionInvocation.invoke();
		}else if(actionName.contains("msgsales") || actionName.contains("msgSales") || actionName.contains("Detail") || actionName.contains("preOrderEdit") || actionName.contains("Report") || actionName.contains("lottery") || actionName.contains("subGame") || actionName.contains("game")){
			return actionInvocation.invoke();
		}
		if(employee == null || "".equals(employee)){
			return "login";
		}
		if(validateRole(employee, request)){
			return actionInvocation.invoke();
		} else{
			return "sorry";
		}
	}

	public boolean validateRole(Employee employee,HttpServletRequest request){
		//取得请求的path
		String path = request.getServletPath();
		List<Role> roles = employee.getRoles();
		for (Role role : roles) {
			List<FunctionRight> functionRights = role.getFunctionRights();
			for (FunctionRight functionRight : functionRights) {
				if(functionRight.getPath().equals(path.substring(1,path.length()))){
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
