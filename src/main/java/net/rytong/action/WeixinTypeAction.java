package net.rytong.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.entity.WeixinType;
import net.rytong.impl.WeixinTypeServiceImpl;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class WeixinTypeAction extends CRUDActionSupport<WeixinType>{
	private static final long serialVersionUID = 1L;
	@Autowired
	private WeixinTypeServiceImpl WeixinTypeServiceImpl;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	@Override
	public void validate() {
	}
	
	public String check() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String code = request.getParameter("code");
		List<WeixinType> WeixinTypeList = WeixinTypeServiceImpl.findByCode(code);
		if(WeixinTypeList.size()>0){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().print("该类型编码已经存在!");
		}
		return NONE;
	}
	
	public String init() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		StringBuffer stringBuffer = new StringBuffer();
		
		String id = request.getParameter("id");
		String isShowAll = request.getParameter("isShowAll");
		stringBuffer.append("<select name=\"reType\" id=\"reType\">");
		if (StringUtils.isBlank(isShowAll)) {
			stringBuffer.append("<option value=''>全部</option>");
		}
		List<WeixinType> WeixinTypes = WeixinTypeServiceImpl.list();
		for(WeixinType WeixinType : WeixinTypes){
			if(StringUtils.isNotBlank(id) && WeixinType.getId().toString().equals(id)){
				stringBuffer.append("<option value='"+WeixinType.getCode()+"' selected=\"selected\">"+WeixinType.getName()+"</option>");
				continue;
			} 
			stringBuffer.append("<option value='"+WeixinType.getCode()+"'>"+WeixinType.getName()+"</option>");
		}
		stringBuffer.append("</select>");
		response.getWriter().print(stringBuffer.toString());
		return NONE;
	}

	@Override
	public CRUDService<WeixinType> getAutowiredService() {
		return WeixinTypeServiceImpl;
	}
}
