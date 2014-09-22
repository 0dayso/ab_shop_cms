package net.rytong.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rytong.entity.Customer;
import net.rytong.entity.Employee;
import net.rytong.entity.FunctionRight;
import net.rytong.entity.Role;
import net.rytong.service.ICustomerService;
import net.rytong.service.IFunctionRightService;
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
public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IUserService iUserService;
	@Autowired
	private IFunctionRightService iFunctionRightService;
	@Autowired
	private ICustomerService iCustomerService;
	private static final String LOGINSUCCESS = "loginSuccess";
	private static final String LOGINERROR = "loginError";
	private static final String EXIT = "exit";
	
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
	
	@Override
	public String execute() throws Exception{
		return SUCCESS;
	}
	
	public String login() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String projectName = (String) request.getSession().getAttribute("projectName");
		
		List<FunctionRight> functionRights = iFunctionRightService.findByProperty("isValid", 1);
		
		//根据用户名和密码查找用户
		List<Employee> employeeList = iUserService.userLogin(userName, FlightUtil.makeMD5(passWord));
		List<FunctionRight> functionRightList = new ArrayList<FunctionRight>();
		if(employeeList.size() > 0){
			Employee employee = employeeList.get(0);
			if(employee.getRole() == null || employee.equals("") || !employee.getRole().equals("admin") ){
				//得到用户所拥有的角色
				List<Role> roleUsers = employee.getRoles();
				for (Role role : roleUsers){
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
				}
			}else if(employee.getRole().equals("admin") && employee.getRoles().size() == 0){
				functionRightList = functionRights;
			}
			
			if(StringUtils.isNotBlank(employee.getCustomerName())){
//				Customer customer = iCustomerService.findById(employee.getCustomerId());
				if(projectName.equals("VAS")){
					Customer customer = iCustomerService.findByShotName(employee.getCustomerName()).get(0);
					request.getSession().setAttribute("customer", customer);
				}
			}
			request.getSession().setAttribute("employee", employee);
			request.getSession().setAttribute("functionRightList", functionRightList);
			return LOGINSUCCESS;
		}
		this.addFieldError("msg", "用户名或密码有误，请重新输入!");
		return LOGINERROR;
	}
	
	public String editPass() throws Exception{
		Employee emp = iUserService.getEmpById(empId);
		HttpServletResponse response = ServletActionContext.getResponse();
		if(!emp.getPin().equals(FlightUtil.makeMD5(oldPwd))){
			response.setHeader("Pragma","No-cache");
			response.setHeader("Cache-Control","no-cache");
			response.setDateHeader("Expires", 0);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print("1");
		}else{
			if(pwd1 != null && !"".equals(pwd1)){
				response.setHeader("Pragma","No-cache");
				response.setHeader("Cache-Control","no-cache");
				response.setDateHeader("Expires", 0);
				response.setCharacterEncoding("utf-8");
				response.getWriter().print("2");
				emp.setPin(FlightUtil.makeMD5(pwd1));
				iUserService.userEdit(emp);
			}
		}
		return NONE;
	}
	
	public String exit() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().removeAttribute("employee");
		request.getSession().removeAttribute("functionRightList");
		request.getSession().removeAttribute("projectTitle");
		request.getSession().removeAttribute("loginPng");
		request.getSession().removeAttribute("logoPng");
		request.getSession().removeAttribute("logoIco");
		request.getSession().removeAttribute("projectName");
		return EXIT;
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
	
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getPwd1() {
		return pwd1;
	}
	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}
	public String getPwd2() {
		return pwd2;
	}
	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}

	private Long empId;
	private String oldPwd;
	private String pwd1;
	private String pwd2;
}
