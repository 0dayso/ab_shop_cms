package net.rytong.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.entity.Employee;
import net.rytong.entity.FunctionRight;
import net.rytong.entity.Role;
import net.rytong.impl.FunctionRightServiceImpl;
import net.rytong.utils.FlightUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class PermissionAction extends CRUDActionSupport<FunctionRight> {
	private static final long serialVersionUID = 5207197603748720772L;
	@Autowired
	private FunctionRightServiceImpl iFunctionRightService;
	
	@Override
	public String execute() throws Exception{
		return SUCCESS;
	}
	
	public String search() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> filterMap = new HashMap<String, Object>();
		FunctionRight p = this.getEntity();
		if (StringUtils.isNotBlank(p.getPath())) {
			filterMap.put("path", p.getPath());
		}
		if (StringUtils.isNotBlank(p.getName())) {
			filterMap.put("name", p.getName());
		}
		if (StringUtils.isNotBlank(p.getDescription())) {
			filterMap.put("description", p.getDescription());
		}
		if (p.getIsValid() != null) {
			filterMap.put("isValid", p.getIsValid());
		}
		if (p.getLevel() != null) {
			filterMap.put("level", p.getLevel());
		}
		this.setFilterMap(filterMap);
		request.setAttribute("functionList", this.pageList());
		return this.pageList();
	}
	
	@Override
	public String add() throws Exception{
		String projectName = FlightUtil.getProperties("project_service.properties", "project_name");
		FunctionRight functionRight = this.getEntity();
		//一级添加
		if(menuValue == 1 || menuValue.equals(1)){
			functionRight.setCode(projectName+FlightUtil.getCode());
			functionRight.setName(name1);
			functionRight.setPath(path1);
			functionRight.setDescription(description1);
			functionRight.setFunctionRight(null);
			functionRight.setLevel(1);
			functionRight.setIsValid(1);
			functionRight.setSort(sort1);
			functionRight.setIsNew(isNew1);
			iFunctionRightService.functionRightAdd(functionRight);
			return super.add();
		}
		//二级添加
		if(menuValue == 2 || menuValue.equals(2)){
			functionRight.setName(name2);
			functionRight.setPath(path2);
			functionRight.setDescription(description2);
			FunctionRight function = iFunctionRightService.findById(Long.parseLong(functionRight1.substring(0, functionRight1.indexOf(",")).trim()));
			functionRight.setCode(function.getCode());
			functionRight.setFunctionRight(function);
			functionRight.setLevel(2);
			functionRight.setIsValid(1);
			iFunctionRightService.functionRightAdd(functionRight);
			return super.add();
		}
		if(menuValue == 3 || menuValue.equals(3)){
			functionRight.setName(name3);
			functionRight.setPath(path3);
			functionRight.setDescription(description3);
			FunctionRight function = iFunctionRightService.findById(Long.parseLong(functionRight2.substring(0, functionRight2.indexOf(",")).trim()));
			functionRight.setCode(function.getCode());
			functionRight.setFunctionRight(function);
			functionRight.setLevel(level3);
			functionRight.setIsValid(1);
			iFunctionRightService.functionRightAdd(functionRight);
			return super.add();
		}
		return INPUT;
	}
	
	@Override
	public String delete() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		FunctionRight functionRight = this.getEntity();
		List<FunctionRight> functionRightList = iFunctionRightService.findByFunctionRight(functionRight);
		if(functionRightList.size() > 0 || functionRight.getRoles().size()>0){
			String msg = "对不起，不能删除!";
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(msg);
		} else{
			iFunctionRightService.deletefunctionRight(functionRight);
			//更新菜单
			refreshRight();
		}
		return NONE;
	}
	
	@Override
	public String detail() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		FunctionRight functionRight = this.getEntity();
		request.setAttribute("functionRight", functionRight);
		return super.detail();
	}
	
	@Override
	public String edit() throws Exception{
		FunctionRight functionRight = this.getEntity();
		//当处于最顶层的节点时是没有父节点
		if(functionRight.getFunctionRight() != null){
			this.setValid(functionRight.getFunctionRightId(), functionRight.getIsValid());
		}
		
		FunctionRight parentFunction = null;
		if(parentId != null && Long.parseLong(parentId) > 0){
			parentFunction = iFunctionRightService.findById(Long.parseLong(parentId));
		} 
		if(parentFunction!=null){
			functionRight.setFunctionRight(parentFunction);
		}
		//更新菜单
		refreshRight();
		return super.edit();
	}
	
	private void refreshRight() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().removeAttribute("functionRightList");
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		List<FunctionRight> functionRightList = new ArrayList<FunctionRight>();
		if(employee != null){
			if(!employee.getRole().equals("admin")){
				List<Role> roleUsers = employee.getRoles(); //得到用户所拥有的角色
				for (Role role : roleUsers){
					List<FunctionRight> functionRole = role.getFunctionRights();
					for (FunctionRight functionRight : functionRole) { //从角色中得到权限
						if(functionRight.getIsValid().equals(1)){
							functionRightList.add(functionRight);
						}
					}
				}
			}else if(employee.getRole().equals("admin") && employee.getRoles().size() == 0){
				functionRightList = iFunctionRightService.findByProperty("isValid", 1);
			}
		}
		request.getSession().setAttribute("functionRightList", functionRightList);
		request.getSession().setAttribute("employee", employee);
	}
	
	private void setValid(Long rightId, int isValid){
		List<FunctionRight> sonList = iFunctionRightService.findParentFunctionRight(rightId);
		if(sonList!=null){//如果有修改子节点激活状态
			for(FunctionRight fr : sonList){
				setValid(fr.getFunctionRightId(), isValid);
				fr.setIsValid(isValid);
				iFunctionRightService.functionRightEdit(fr);
			}
		}
	}

	@Override
	public CRUDService<FunctionRight> getAutowiredService() {
		return iFunctionRightService;
	}
	
	private String name1;
	private String path1;
	private String description1;
	private String name2;
	private String path2;
	private String description2;
	private String name3;
	private String path3;
	private String description3;
	private Integer level3;
	private Integer sort1;
	private Integer isNew1;
	private Integer sort2;
	private Integer isNew2;
	private Integer sort3;
	private Integer isNew3;
	private String parentId;
	
	private Integer menuValue;//菜单级
	private String functionRight1;//二级
	private String functionRight2;//三级
	
	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getPath1() {
		return path1;
	}

	public void setPath1(String path1) {
		this.path1 = path1;
	}

	public String getDescription1() {
		return description1;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getPath2() {
		return path2;
	}

	public void setPath2(String path2) {
		this.path2 = path2;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getPath3() {
		return path3;
	}

	public void setPath3(String path3) {
		this.path3 = path3;
	}

	public String getDescription3() {
		return description3;
	}

	public void setDescription3(String description3) {
		this.description3 = description3;
	}

	public Integer getLevel3() {
		return level3;
	}

	public void setLevel3(Integer level3) {
		this.level3 = level3;
	}

	public Integer getMenuValue() {
		return menuValue;
	}

	public void setMenuValue(Integer menuValue) {
		this.menuValue = menuValue;
	}

	public String getFunctionRight1() {
		return functionRight1;
	}

	public void setFunctionRight1(String functionRight1) {
		this.functionRight1 = functionRight1;
	}

	public String getFunctionRight2() {
		return functionRight2;
	}

	public void setFunctionRight2(String functionRight2) {
		this.functionRight2 = functionRight2;
	}

	public Integer getSort1() {
		return sort1;
	}

	public void setSort1(Integer sort1) {
		this.sort1 = sort1;
	}

	public Integer getIsNew1() {
		return isNew1;
	}

	public void setIsNew1(Integer isNew1) {
		this.isNew1 = isNew1;
	}

	public Integer getSort2() {
		return sort2;
	}

	public void setSort2(Integer sort2) {
		this.sort2 = sort2;
	}

	public Integer getIsNew2() {
		return isNew2;
	}

	public void setIsNew2(Integer isNew2) {
		this.isNew2 = isNew2;
	}

	public Integer getSort3() {
		return sort3;
	}

	public void setSort3(Integer sort3) {
		this.sort3 = sort3;
	}

	public Integer getIsNew3() {
		return isNew3;
	}

	public void setIsNew3(Integer isNew3) {
		this.isNew3 = isNew3;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
