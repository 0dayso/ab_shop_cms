package net.rytong.action;

import java.util.HashMap;
import java.util.Map;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.entity.LotteryBetNumber;
import net.rytong.entity.LotteryBuyNumber;
import net.rytong.impl.LotteryBuyNumberServiceImpl;
import net.rytong.ws.LotteryDataGrab;
import net.rytong.ws.LotteryDataUpdate;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class LotteryBuyAction extends CRUDActionSupport<LotteryBuyNumber>{
	private static final long serialVersionUID = 1L;
	@Autowired
	private LotteryBuyNumberServiceImpl iLotteryBuyNumberService;
	@Autowired
	private LotteryDataUpdate lotteryDataUpdate;
	
	@Override
	public String search() throws Exception {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		LotteryBuyNumber p = this.getEntity();
		this.setFilterMap(filterMap);
		return this.pageList();
	}
	
	public String updateNum() throws Exception {
		JSONObject obj = new JSONObject();
		LotteryBuyNumber p = this.getEntity();
		if (StringUtils.isNotBlank(p.getIssue())) {
			int[] result = lotteryDataUpdate.setBuyLotterys("1", p.getIssue());
			obj.put("oneNum", result[0] + "");
			obj.put("delNum", result[1] + "");
			obj.put("upNum", result[0] - result[1]);
		} else {
			obj.put("oneNum", "-1");
			obj.put("upName", "-1");
			obj.put("delNum", "-1");
		}
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}
	
	@Override
	public void validate() {
	}
	
	@Override
	public CRUDService<LotteryBuyNumber> getAutowiredService() {
		return iLotteryBuyNumberService;
	}
}