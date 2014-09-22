package net.rytong.action;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.entity.LotteryHistoryNumber;
import net.rytong.impl.LotteryHistoryNumberServiceImpl;
import net.rytong.ws.LotteryDataGrab;
import net.rytong.ws.LotteryDataUpdate;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class LotteryhistoryAction extends CRUDActionSupport<LotteryHistoryNumber> {
	private static final long serialVersionUID = 1L;
	@Autowired
	private LotteryHistoryNumberServiceImpl LotteryHistoryNumberService;
	@Autowired
	private LotteryDataGrab lotteryDataGrab;
	private String fiterNmuber;
	private String containNumber;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String search() throws Exception {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		LotteryHistoryNumber p = this.getEntity();
		if (StringUtils.isNotBlank(p.getIssue())) {
			filterMap.put("issue", p.getIssue());
		}
		this.setFilterMap(filterMap);
		return this.pageList();
	}
	
	public String init() throws Exception {
		lotteryDataGrab.getHistoryLottery();
		
		JSONObject obj = new JSONObject();
		obj.put("ok", "ok");
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}
	
	public String revert() throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("ok", "ok");
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}

	@Override
	public String add() throws Exception {
		return super.add();
	}
	
	@Override
	public String detail() throws Exception {
		return super.detail();
	}
	
	@Override
	public String edit() throws Exception {
		LotteryHistoryNumber LotteryHistoryNumber = this.getEntity();
		return super.edit();
	}
	
	public String view() throws Exception {
		return this.VIEW;
	}
	
	@Override
	public void validate() {
	}

	@Override
	public CRUDService<LotteryHistoryNumber> getAutowiredService() {
		return LotteryHistoryNumberService;
	}

	public String getFiterNmuber() {
		return fiterNmuber;
	}

	public void setFiterNmuber(String fiterNmuber) {
		this.fiterNmuber = fiterNmuber;
	}

	public String getContainNumber() {
		return containNumber;
	}

	public void setContainNumber(String containNumber) {
		this.containNumber = containNumber;
	}
}
