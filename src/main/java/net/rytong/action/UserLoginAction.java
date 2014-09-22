package net.rytong.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.rytong.entity.Customer;
import net.rytong.entity.Employee;
import net.rytong.entity.FunctionRight;
import net.rytong.entity.Parameter;
import net.rytong.entity.Role;
import net.rytong.service.ICustomerService;
import net.rytong.service.IFunctionRightService;
import net.rytong.service.IParameterService;
import net.rytong.service.IUserService;
import net.rytong.utils.FlightUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")
@Component
public class UserLoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IUserService iUserService;
	@Autowired
	private IFunctionRightService iFunctionRightService;
	@Autowired
	private ICustomerService iCustomerService;
	@Autowired
	private IParameterService iParameterService;
	
	private String userName;
	private String passWord;
	private String verifyCode;
	private String msg;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String projectName = (String) request.getSession().getAttribute("projectName");
		List<FunctionRight> functionRights = iFunctionRightService.findByProperty("isValid", 1);
		//根据用户名和密码查找用户
		Customer customer = null;
		List<Employee> employeeList = iUserService.userLogin(userName, FlightUtil.makeMD5(passWord));
		List<FunctionRight> functionRightList = new ArrayList<FunctionRight>();
		if(employeeList.size() > 0){
			Employee employee = employeeList.get(0);
			if(employee.getRole() == null || employee.equals("") || !employee.getRole().equals("admin") ){
				//得到用户所拥有的角色
				List<Role> roleUsers = employee.getRoles();
				Map<Long, FunctionRight> map = new HashMap<Long, FunctionRight>();
				for (Role role : roleUsers){
					List<FunctionRight> functionRole = role.getFunctionRights();
					//从角色中得到权限
					for (FunctionRight functionRight : functionRole) {
						if(functionRight.getIsValid() == 1){
							map.put(functionRight.getFunctionRightId(), functionRight);
						}
					}
				}
				for (FunctionRight right : functionRights) {
					if (map.containsKey(right.getFunctionRightId())) {
						functionRightList.add(right);
					}
				}
			}else if(employee.getRole().equals("admin") && employee.getRoles().size() == 0){
				functionRightList = functionRights;
			}
			customer = employee.getCustomer();
			if(customer == null){  //适应由code对应的关联关系
				List<Customer> customers = iCustomerService.findByShotName(employee.getCustomerName());
				if(customers != null && customers.size() > 0){
					customer = customers.get(0);
				}
			}
			if(customer != null){
				if(StringUtils.isBlank(customer.getUrl())){
					List<Parameter> parameters = iParameterService.getParameterByCode("PORT");
					String shotName = customer.getShotName();
					if(parameters != null && parameters.size() > 0){
						customer.setUrl(parameters.get(0).getValue()+shotName); 
					}
				}
				if(StringUtils.isBlank(customer.getToken())){
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					customer.setToken(uuid);
				}
				iCustomerService.update(customer);
			}
			
			request.getSession().setAttribute("customer", customer);
			request.getSession().setAttribute("employee", employee);
			request.getSession().setAttribute("functionRightList", functionRightList);
			return SUCCESS;
		}
		this.addFieldError("msg", "用户名或密码有误，请重新输入!");
		return INPUT;
	}
	
	@Override
	public void validate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String rightVerifyCode = (String) request.getSession().getAttribute("imgCode");
		if(rightVerifyCode == null || rightVerifyCode.equals("")){
			this.addFieldError("verifyCode", "验证码生成已过期，请刷新重新输入！");
		}
		if (rightVerifyCode != null && !rightVerifyCode.equals(verifyCode)) {
			this.addFieldError("verifyCode", "验证码输入错误，请重新输入！");
		}
	}
}
