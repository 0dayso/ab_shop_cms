package net.rytong.action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.entity.Customer;
import net.rytong.entity.LotteryIssue;
import net.rytong.impl.LotteryIssueServiceImpl;
import net.rytong.utils.TimeHelper;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class IssueAction extends CRUDActionSupport<LotteryIssue> {
	private static final long serialVersionUID = 1L;
	@Autowired
	private LotteryIssueServiceImpl lotteryIssueService;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String search() throws Exception {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		LotteryIssue p = this.getEntity();
		this.setFilterMap(filterMap);
		return this.pageList();
	}
	
	public String getAvail() throws Exception {
		List<LotteryIssue> list = lotteryIssueService.findAvailIssue();
		JSONObject obj = new JSONObject();
		if (list != null && list.size() > 0) {
			LotteryIssue iss = list.get(0);
			obj.put("issue", iss.getIssue());
		}
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}
	
	@Override
	public String add() throws Exception {
		LotteryIssue p = this.getEntity();
		p.setTime(TimeHelper.getCurrentTime());
		return super.add();
	} 
	
	public String init() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		StringBuffer stringBuffer = new StringBuffer();
		
		String issueValue = request.getParameter("issue");
		stringBuffer.append("<select name=\"issue\" id=\"issue\">");
		List<LotteryIssue> issues = lotteryIssueService.findAll();
		for(LotteryIssue issue : issues){
			if(StringUtils.isNotBlank(issueValue) && issue.getIssue().equals(issueValue)){
				stringBuffer.append("<option value='"+issue.getIssue()+"' selected=\"selected\">"+issue.getIssue()+"</option>");
				continue;
			} 
			stringBuffer.append("<option value='"+issue.getIssue()+"'>"+issue.getIssue()+"</option>");
		}
		stringBuffer.append("</select>");
		response.getWriter().print(stringBuffer.toString());
		return NONE;
	}
	
	@Override
	public String detail() throws Exception {
		return super.detail();
	}
	
	@Override
	public String edit() throws Exception {
		LotteryIssue LotteryIssue = this.getEntity();
		return super.edit();
	}
	
	public String view() throws Exception {
		return this.VIEW;
	}
	
	@Override
	public void validate() {
	}

	@Override
	public CRUDService<LotteryIssue> getAutowiredService() {
		return lotteryIssueService;
	}
}
