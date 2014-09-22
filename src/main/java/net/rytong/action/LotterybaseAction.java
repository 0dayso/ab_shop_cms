package net.rytong.action;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.dao.ILotteryIssueDAO;
import net.rytong.entity.LotteryBaseNumber;
import net.rytong.entity.LotteryIssue;
import net.rytong.impl.LotteryBaseNumberServiceImpl;
import net.rytong.ws.LotteryDataUpdate;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class LotterybaseAction extends CRUDActionSupport<LotteryBaseNumber> {
	private static final long serialVersionUID = 1L;
	@Autowired
	private LotteryBaseNumberServiceImpl LotteryBaseNumberService;
	@Autowired
	private ILotteryIssueDAO iLotteryIssueDAO;
	@Autowired
	private LotteryDataUpdate lotteryDataUpdate;
	private String fiterNmuber;
	private String containNumber;
	private String issue;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String search() throws Exception {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		LotteryBaseNumber p = this.getEntity();
		if (StringUtils.isNotBlank(p.getType())) {
			filterMap.put("type", p.getType());
		}
		this.setFilterMap(filterMap);
		return this.pageList();
	}
	
	public String change() throws Exception {
		JSONObject obj = new JSONObject();
		LotteryIssue ii = iLotteryIssueDAO.findByIssue(issue);
		// 将已有的过滤好过滤掉
		String str = subNum(fiterNmuber, ii.getrHavedFilter());
		String str1 = subNum(containNumber, ii.getrWaitedFilter());
		int result = filter(str, str1);
		updateIssue(1);
		obj.put("num", result);
		obj.put("ok", "ok");
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}
	
	private int filter(String a1, String a2) {
		int result = 0;
		String sql = "update t_base_lottery_number set t_type = '-1' where t_type = '0' ";
		if (StringUtils.isNotBlank(a1) || StringUtils.isNotBlank(a2)) {
			if (StringUtils.isNotBlank(a1)) { 
				sql = sql + " and (";
				String[] fs = a1.split(",");
				for (int i = 0; i < fs.length; i++) {
					sql = sql + " t_number like '%" +  fs[i] + "%' ";
					if (i != fs.length - 1) {
						sql = sql + " or ";
					}
				}
				sql = sql + " ) ";
			}
			if (StringUtils.isNotBlank(a2)) { 
				sql = sql + " and t_number not like '%" +  a2 + "%' ";
			}
			result = lotteryDataUpdate.updateLotteryBases(sql);
		} 
		return result;
	}
	
	private String updateIssue(int i) {
		String res = "";
		if (StringUtils.isNotBlank(issue) && i == 1) {
			LotteryIssue ii = iLotteryIssueDAO.findByIssue(issue);
			if (StringUtils.isNotBlank(fiterNmuber)) {
				String str = ii.getrHavedFilter();
				String result = "";
				if (StringUtils.isNotBlank(str)) {
					result = result + str;
					String[] f2 = fiterNmuber.split(",");
					for (String f : f2) {
						if (!str.contains(f)) {
							result = result + "," + f;
						}
					}
				} else {
					result = fiterNmuber;
				}
				ii.setrHavedFilter(result);
			}
			if (StringUtils.isNotBlank(containNumber)) {
				String str = ii.getrWaitedFilter();
				String result = "";
				if (StringUtils.isNotBlank(str)) {
					result = result + str;
					String[] f2 = containNumber.split(",");
					for (String f : f2) {
						if (!str.contains(f)) {
							result = result + "," + f;
						}
					}
				} else {
					result = containNumber;
				}
				ii.setrWaitedFilter(result);
			}
			iLotteryIssueDAO.update(ii);
		} else if (StringUtils.isNotBlank(fiterNmuber) && StringUtils.isNotBlank(issue) && i == 2) {
			LotteryIssue ii = iLotteryIssueDAO.findByIssue(issue);
			if (StringUtils.isNotBlank(fiterNmuber)) {
				String str = ii.getrHavedFilter();
				if (StringUtils.isNotBlank(str)) {
					res = subNum(str, fiterNmuber);
					ii.setrHavedFilter(res);
				}
			}
			if (StringUtils.isNotBlank(containNumber)) {
				String str = ii.getrWaitedFilter();
				if (StringUtils.isNotBlank(str)) {
					ii.setrWaitedFilter(subNum(str, containNumber));
				}
			}
			iLotteryIssueDAO.update(ii);
		}
		return res;
	}
	
	private String subNum(String fk1, String fk2) {
		String[] f1 = fk1.split(",");
		String[] f2 = fk2.split(",");
		List<String> f11 = Arrays.asList(f1);
		StringBuffer sb = new StringBuffer("");
		
		Map<String, String> map = new HashMap<String,String>();
		
		for (String str : f2) {
			map.put(str, str);
		}
		
		for (int i = 0; i < f11.size(); i++) {
			if (!map.containsKey(f11.get(i))) {
				sb.append(f11.get(i)).append(",");
			}
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	public String revert() throws Exception {
		String sql = "update t_base_lottery_number set t_type = '0' where t_type = '-1' ";
		JSONObject obj = new JSONObject();
		if (StringUtils.isNotBlank(fiterNmuber) || StringUtils.isNotBlank(containNumber)) {
			if (StringUtils.isNotBlank(fiterNmuber)) { 
				sql = sql + " and (";
				String[] fs = fiterNmuber.split(",");
				for (int i = 0; i < fs.length; i++) {
					sql = sql + " t_number like '%" +  fs[i] + "%' ";
					if (i != fs.length - 1) {
						sql = sql + " or ";
					}
				}
				sql = sql + " ) ";
			}
			if (StringUtils.isNotBlank(containNumber)) { 
				sql = sql + " and t_number not like '%" +  containNumber + "%' ";
			}
			int result = lotteryDataUpdate.updateLotteryBases(sql);
			obj.put("num", result);
		} else {
			obj.put("num", "0");
		}
		String res = updateIssue(2);
		// 关闭部分号码
		if (StringUtils.isNotBlank(res)) {
			filter(res, "");
		}
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
		LotteryBaseNumber LotteryBaseNumber = this.getEntity();
		return super.edit();
	}
	
	public String view() throws Exception {
		return this.VIEW;
	}
	
	@Override
	public void validate() {
	}

	@Override
	public CRUDService<LotteryBaseNumber> getAutowiredService() {
		return LotteryBaseNumberService;
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

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}
	
}
