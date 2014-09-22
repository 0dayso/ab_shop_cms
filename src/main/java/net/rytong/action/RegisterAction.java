package net.rytong.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
import net.rytong.service.IRoleService;
import net.rytong.service.IUserService;
import net.rytong.utils.FlightUtil;
import net.rytong.utils.TimeHelper;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")
@Component
public class RegisterAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IUserService iUserService;
	@Autowired
	private IFunctionRightService iFunctionRightService;
	@Autowired
	private ICustomerService iCustomerService;
	@Autowired
	private IRoleService RoleServiceImpl;
	@Autowired
	private IParameterService iParameterService;
	
	private String login;
	private String name;
	private String sex;
	private String password;
	private String address;
	private String phone;
	private String email;
	private String captcha;//验证码
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	/**
	 * 1. 根据用户名login 来生成一个商户名称
	 * 2. 保存EMPLOYEE 并将角色设置为common 普通用户
	 */
	public String execute() throws Exception{
		int random = new Random().nextInt(90) + 10; 
		Long timer = TimeHelper.getCurrentTime();
		String times = timer.toString().substring(2);
		String shotName = "wx" + times + random ;
		Customer customer = new Customer();
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		customer.setToken(uuid); //随机生成一个token
		customer.setYxToken(uuid); 
		List<Parameter> parameters = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IP", "0");
		if(parameters != null && parameters.size() > 0){
			customer.setUrl(parameters.get(0).getValue() + "weixin/" + shotName); 
			customer.setYxUrl(parameters.get(0).getValue() + "yixin/" + shotName); 
		}else{
			customer.setUrl("");
			customer.setYxUrl("");
		}
		customer.setShotName(shotName);
		customer.setUsername(login);
		iCustomerService.save(customer);
		Employee employee = new Employee();
		employee.setLogin(login);
		employee.setCustomerName(shotName);
		employee.setCustomer(customer);
		employee.setRole("common");
		employee.setName(name);
		employee.setEmail(email);
		employee.setPhone(phone);
		employee.setPin(FlightUtil.makeMD5(password));
		employee.setAddress(address);
		List<Role> roles = RoleServiceImpl.findByName("common");
		employee.setRoles(roles); //授予普通用户角色
		iUserService.userReg(employee);//注册
		
		//保存session中 
		HttpServletRequest request = ServletActionContext.getRequest();
		String projectName = (String) request.getSession().getAttribute("projectName");
		List<FunctionRight> functionRightList = new ArrayList<FunctionRight>();
		Role role = roles.get(0);
		List<FunctionRight> functionRole = role.getFunctionRights();
		//从角色中得到权限
		for (FunctionRight functionRight : functionRole) {
			if(projectName.equals("VAS")){
				if(functionRight.getIsValid() == 1 && functionRight.getCode().substring(0,3).equalsIgnoreCase(projectName)){
					functionRightList.add(functionRight);
				}
			}else{
				if(functionRight.getIsValid() == 1){
					functionRightList.add(functionRight);
				}
			}
		}
		request.getSession().setAttribute("customer", customer);
		request.getSession().setAttribute("employee", employee);
		request.getSession().setAttribute("functionRightList", functionRightList);
		return SUCCESS;
	}
	
	@Override
	public void validate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String rightVerifyCode = (String) request.getSession().getAttribute("imgCode");
		if(rightVerifyCode == null || rightVerifyCode.equals("")){
			this.addFieldError("verifyCode", "验证码生成已过期，请刷新重新输入！");
		}
		if (rightVerifyCode != null && !rightVerifyCode.equals(captcha)) {
			this.addFieldError("verifyCode", "验证码输入错误，请重新输入！");
		}
	}
}
