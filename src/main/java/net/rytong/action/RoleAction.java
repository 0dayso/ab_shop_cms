package net.rytong.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.entity.Employee;
import net.rytong.entity.FunctionRight;
import net.rytong.entity.Role;
import net.rytong.impl.RoleServiceImpl;
import net.rytong.service.IFunctionRightService;
import net.rytong.utils.DateUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class RoleAction extends CRUDActionSupport<Role>{
	private static final long serialVersionUID = -8640000725707600343L;
	@Autowired
	private RoleServiceImpl iRoleService;
	@Autowired
	private IFunctionRightService iFunctionRightService;
	private String beginTime;
	private String endTime;
	
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	@Override
	public String execute() throws Exception{
		return SUCCESS;
	}
	
	public String search() throws Exception{
		Map<String, Object> filterMap = new HashMap<String, Object>();
		Role p = this.getEntity();
		if (StringUtils.isNotBlank(p.getName())) {
			filterMap.put("name", p.getName());
		}
		if (StringUtils.isNotBlank(p.getDepartName())) {
			filterMap.put("departName", p.getDepartName());
		}
		if (StringUtils.isNotBlank(beginTime)) {
			filterMap.put("beginTime", DateUtils.dateToInteger(beginTime));
		}
		if (StringUtils.isNotBlank(endTime)) {
			filterMap.put("pageIndex", DateUtils.dateToInteger(endTime));
		}
		this.setFilterMap(filterMap);
		return this.pageList();
	}
	
	@Override
	public String add() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] functionIds = request.getParameterValues("functionIds");
		List<FunctionRight> functionRights = new ArrayList<FunctionRight>();
		if(functionIds!= null){
			for (String functionRightId : functionIds) {
				FunctionRight functionRight = iFunctionRightService.findById(Long.parseLong(functionRightId));
				functionRights.add(functionRight);
			}
		}
		Role p = this.getEntity();
		p.setCreatedDate(DateUtils.getRYTDate().intValue());
		p.setIsValid(1);
		p.setFunctionRights(functionRights);
		return super.add();
	}
	
	public String view() throws Exception {
		List<FunctionRight> functionList = iFunctionRightService.findByLevelAndIsVaild(1, 1); //得到一级节点
		//定义一map集合，将一级节点下的其他子节点进行组装
		TreeMap<Long, List<FunctionRight>> childFunctionMap = new TreeMap<Long, List<FunctionRight>>();
		Iterator<FunctionRight> it = functionList.iterator();
		while (it.hasNext()) {
			Iterator<FunctionRight> it1 = ((FunctionRight) it.next()).getFunctionRights().iterator();
			while (it1.hasNext()) {
				FunctionRight functionRight = (FunctionRight) it1.next();
				List<FunctionRight> childFunctionList = iFunctionRightService.findByProperty("functionRight.functionRightId",
								functionRight.getFunctionRightId());
				childFunctionMap.put(functionRight.getFunctionRightId(),childFunctionList);
			}
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("functionList", functionList);
		request.setAttribute("childFunctionMap", childFunctionMap);
		return this.VIEW;
	}
	
	@Override
	public String delete() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Role role = this.getEntity();
		Set<Employee> employeeSet = role.getEmployees();
		if(employeeSet.size() > 0){
			String msg = "对不起，不能删除!";
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(msg);
		}else{
			iRoleService.delRole(role);
		}
		return NONE;
	}
	
	@Override
	public String detail() throws Exception {
		Role role = this.getEntity();
		List<FunctionRight> functionList = iFunctionRightService.findByLevelAndIsVaild(1, 1); //得到一级节点
		//定义一map集合，将一级节点下的其他子节点进行组装
		TreeMap<Long, List<FunctionRight>> childFunctionMap = new TreeMap<Long, List<FunctionRight>>();
		for (FunctionRight function : functionList) {
			for (FunctionRight functionRight : function.getFunctionRights()) {
				List<FunctionRight> childFunctionList = iFunctionRightService.findByProperty("functionRight.functionRightId",
						functionRight.getFunctionRightId());
				childFunctionMap.put(functionRight.getFunctionRightId(),childFunctionList);
			}
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("functionList", functionList);
		request.setAttribute("childFunctionMap", childFunctionMap);
		request.setAttribute("role", role);
		return super.detail();
	}
	
	@Override
	public String edit() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<FunctionRight> functionRights = new ArrayList<FunctionRight>();
		String[] functionIds = request.getParameterValues("functionIds");
		if(functionIds != null){
			for (String functionRightId : functionIds) {
				FunctionRight functionRight = iFunctionRightService.findById(Long.parseLong(functionRightId));
				functionRights.add(functionRight);
			}			
		}
		Role role = this.getEntity();
		role.setFunctionRights(functionRights);
		iRoleService.editRole(role);
		return super.edit();
	}
	
	public String checkName() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");
		name=net.rytong.utils.EscapeUtil.unescape(name);
		if(!iRoleService.isRoleExist(name)){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().print("该角色名称已经存在!");
		}
		return NONE;
	}
	
	public  String formatDateOrTime(Object value) {
		if (value != null) {
			String temp = value.toString();
			if (temp.length() == 14) {// 20100105161858
				return temp.substring(0, 4) + "-" + temp.subSequence(4, 6)
						+ "-" + temp.substring(6, 8) + " "
						+ temp.substring(8, 10) + ":" + temp.substring(10, 12)
						+ ":" + temp.substring(12, 14);
			} else if (temp.length() == 8) {// 20091223
				return temp.substring(0, 4) + "-" + temp.subSequence(4, 6)
						+ "-" + temp.substring(6, 8);
			} else if (temp.length() == 6) {// 153218
				return temp.substring(0, 2) + ":" + temp.substring(2, 4) + ":"
						+ temp.substring(4, 6);
			} else if (temp.length() == 4) {// 153218
				return temp.substring(0, 2) + ":" + temp.substring(2, 4);
			}
		}
		return null;
	}
	
	public  String initRole() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("employee");
		List<Role> roleUsers = employee.getRoles();
		if(roleUsers == null || roleUsers.size() == 0){
			roleUsers = iRoleService.roleList();
		}
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<select name=\"roleId\" id=\"roleId\">");
		stringBuffer.append("<option value=''>全部</option>");
		for(Role role : roleUsers){
			stringBuffer.append("<option value='"+role.getId()+"'>"+role.getName()+"</option>");
		}
		stringBuffer.append("</select>");
		response.getWriter().print(stringBuffer.toString());
		return NONE;
	}
	
	@Override
	public CRUDService<Role> getAutowiredService() {
		return iRoleService;
	}
}
