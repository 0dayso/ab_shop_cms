package net.rytong.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.entity.Customer;
import net.rytong.entity.Employee;
import net.rytong.entity.Role;
import net.rytong.impl.EmployeeServiceImpl;
import net.rytong.service.ICustomerService;
import net.rytong.service.IRoleService;
import net.rytong.utils.FlightUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class EmployeeAction extends CRUDActionSupport<Employee> {
	private static final long serialVersionUID = 1L;
	private List<Role> roleList;
	private String signStartTime;
	private String roleId;
	private Role roleEntity;
	private String signEndTime;
	@Autowired
	private EmployeeServiceImpl employeeService;
	@Autowired
	private IRoleService iRoleService;
	@Autowired
	private ICustomerService iCustomerService;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String search() throws Exception {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		Employee p = this.getEntity();
		
		if (StringUtils.isNotBlank(p.getLogin())) {
			filterMap.put("login", p.getLogin());
		}
		if (StringUtils.isNotBlank(p.getName())) {
			filterMap.put("name", p.getName());
		}
		if (StringUtils.isNotBlank(p.getIdType())) {
			filterMap.put("idType", p.getIdType());
		}
		if (StringUtils.isNotBlank(p.getIdNo())) {
			filterMap.put("idNo", p.getIdNo());
		}
		if (StringUtils.isNotBlank(p.getGender())) {
			filterMap.put("gender", p.getGender());
		}
		if (StringUtils.isNotBlank(p.getMobile())) {
			filterMap.put("mobile", p.getMobile());
		}
		if (StringUtils.isNotBlank(p.getArea())) {
			filterMap.put("area", p.getArea());
		}
		if (StringUtils.isNotBlank(p.getAddress())) {
			filterMap.put("address", p.getAddress());
		}
		if (StringUtils.isNotBlank(p.getEmail())) {
			filterMap.put("email", p.getEmail());
		}
		if (StringUtils.isNotBlank(p.getPostcode())) {
			filterMap.put("postcode", p.getPostcode());
		}
		if (StringUtils.isNotBlank(signStartTime)) {
			filterMap.put("signStartTime", signStartTime);
		}
		if (StringUtils.isNotBlank(signEndTime)) {
			filterMap.put("signEndTime", signEndTime);
		}
		this.setFilterMap(filterMap);
		return this.pageList();
	}
	
	@Override
	public String add() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String customerId = (String)request.getAttribute("customer");
		Customer customer = iCustomerService.findById(Long.parseLong(customerId));
		List<Role> roleList = new ArrayList<Role>();
		Employee employee = this.getEntity();
		Role role = iRoleService.findById(Long.parseLong(roleId));
		roleList.add(role);
		employee.setPin(FlightUtil.makeMD5(employee.getPin()));
		employee.setCustomerName(customer.getShotName());
		employee.setRole(role.getName());
		employee.setRoles(roleList);
		return super.add();
	}
	
	@Override
	public String detail() throws Exception {
		roleList = iRoleService.roleList();
		Employee employee = this.getEntity();
		if(employee.getRoles().size() > 0){
			roleEntity = employee.getRoles().iterator().next();
		}else{
			roleEntity = null;
		}
		return super.detail();
	}
	
	@Override
	public String edit() throws Exception {
		Employee employee = this.getEntity();
		if(!"admin".equals(employee.getLogin())){
			List<Role> roleSet = new ArrayList<Role>();
			Role role = iRoleService.findById(Long.valueOf(roleId));
			roleSet.add(role);
			employee.setRole(role.getName());
			employee.setRoles(roleSet);
		}
		return super.edit();
	}
	
	public String isLogin() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String login = request.getParameter("login");
		if(employeeService.findByLogin(login).size()>0){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().print("该登录名已经存在!");
		}
		return NONE;
	}
	
	public String view() throws Exception {
		roleList = iRoleService.roleList();
		return this.VIEW;
	}
	
	public String getSignStartTime() {
		return signStartTime;
	}

	public void setSignStartTime(String signStartTime) {
		this.signStartTime = signStartTime;
	}

	public String getSignEndTime() {
		return signEndTime;
	}

	public void setSignEndTime(String signEndTime) {
		this.signEndTime = signEndTime;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public Role getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(Role roleEntity) {
		this.roleEntity = roleEntity;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public void validate() {
	}

	@Override
	public CRUDService<Employee> getAutowiredService() {
		return employeeService;
	}
}
