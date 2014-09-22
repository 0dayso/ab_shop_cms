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
import net.rytong.entity.LotteryBetNumber;
import net.rytong.impl.LotteryBetNumberServiceImpl;
import net.rytong.ws.LotteryDataGrab;
import net.rytong.ws.LotteryDataUpdate;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class LotteryAction extends CRUDActionSupport<LotteryBetNumber> {
	private static final long serialVersionUID = 1L;
	@Autowired
	private LotteryBetNumberServiceImpl lotteryBetNumberService;
	@Autowired
	private LotteryDataGrab lotteryDataGrab;
	@Autowired
	private LotteryDataUpdate lotteryDataUpdate;
	private String fiterNmuber;
	private String containNumber;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String search() throws Exception {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		LotteryBetNumber p = this.getEntity();
		filterMap.put("issue", p.getIssue());
		this.setFilterMap(filterMap);
		return this.pageList();
	}
	
	public String init() throws Exception {
		lotteryDataGrab.synchroGrabData();
		return NONE;
	}
	
	public String daoru() throws Exception {
		JSONObject obj = new JSONObject();
		LotteryBetNumber p = this.getEntity();
		if (StringUtils.isNotBlank(p.getIssue())) {
			LotteryDataGrab.setFlag(1);
			int oneNum = lotteryDataUpdate.synchroBetLotterys(p.getIssue());
			int delNum = lotteryDataUpdate.deleteRepeatedLotterys(p.getIssue());
			obj.put("oneNum", oneNum + "");
			obj.put("delNum", delNum + "");
			obj.put("upNum", oneNum - delNum);
		} else {
			obj.put("oneNum", "-1");
			obj.put("upName", "-1");
			obj.put("delNum", "-1");
		}
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}
	
	public String countBall() throws Exception {
		LotteryBetNumber p = this.getEntity();
		int bcs = 36;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 1 ; i <= 35; i++) {
			map.put(i, 0);
		}
		
		Map<String, String> filter = new HashMap<String, String>();
		if (StringUtils.isNotBlank(fiterNmuber)) {
			String[] as = fiterNmuber.split(",");
			for (String a : as) {
				filter.put(a, a);
			}
		}
		
		String cStr = "";
		if (StringUtils.isNotBlank(containNumber)) {
			String[] as = containNumber.split(",");
			Arrays.sort(as);
			
			for (String a : as) {
				cStr = cStr + a + ",";
			}
			cStr = cStr.substring(0, cStr.length() - 1);
		}
		
		int total = 0;
		
		if (StringUtils.isNotBlank(p.getIssue())) {
			int size= 10000;
			for (int i = 0; i < 25; i++) {
				int start = i * size;
				Map<String, String> lotteryMap = lotteryDataUpdate.selectLotterysForUpdate("1", p.getIssue(), start, size);
				
				for(Entry<String, String> entry : lotteryMap.entrySet()) {	
					String value = entry.getValue();
					String bet = value.split("_")[2];
					String[] betStr = bet.split(",");
					String a1 = betStr[0];
					String a2 = betStr[1];
					String a3 = betStr[2];
					String a4 = betStr[3];
					String a5 = betStr[4];
					
					if (filter.containsKey(a1) || filter.containsKey(a2) || filter.containsKey(a3) || filter.containsKey(a4) || filter.containsKey(a5)) {
						continue;
					}
					
					String ca = a1 + "," + a2 + "," + a3 + "," + a4 + "," + a5;
					if (!ca.contains(cStr)) {
						continue;
					}
					
					total++;
					
					for (int k = 0; k < 5; k++) {
						Integer a = Integer.valueOf(betStr[k]) % bcs;
						map.put(a, map.get(a) + 1);
					}
				}
				
				if (lotteryMap.size() != size) {
					break;
				}
			}
		}
		
		String result = "";
		int i = 1;
		JSONObject obj = new JSONObject();
		for(Entry<Integer, Integer> entry : map.entrySet()) {
			String key = entry.getKey() + "";
			if (key.length() == 1) {
				key = "0" + key;
			}
			result += key + " " + entry.getValue() + "次<br>";
			if (i % 7 == 0) { 
				int j = i / 7;
				obj.put("ratec" + j, result);
				result = "";
			}
			i++;
		}
		i = 1;
		ArrayList<Map.Entry<Integer, Integer>> entries= sortMap(map);
		for(Entry<Integer, Integer> entry : entries) {
			String key = entry.getKey() + "";
			if (key.length() == 1) {
				key = "0" + key;
			}
			result += key + " " + entry.getValue() + "次<br>";
			if (i % 7 == 0) { 
				int j = i / 7;
				obj.put("ratef" + j, result);
				result = "";
			}
			i++;
		}
		obj.put("total", total);
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}
	
	public String deleteRepeated() throws Exception {
		LotteryBetNumber p = this.getEntity();
		JSONObject obj = new JSONObject();
		
		if (StringUtils.isNotBlank(p.getIssue())) {
			int size = lotteryDataUpdate.deleteRepeatedLotterys(p.getIssue());
			obj.put("num", size + "");
		} else {
			obj.put("num", "-1");
		}
		
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}
	
	public static ArrayList<Map.Entry<Integer,Integer>> sortMap(Map map){   
		List<Map.Entry<Integer, Integer>> entries = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());   
		Collections.sort(entries, new Comparator<Map.Entry<Integer, Integer>>() {   
			public int compare(Map.Entry<Integer, Integer> obj1 , Map.Entry<Integer, Integer> obj2) {   
				return obj2.getValue() - obj1.getValue();   
			}   
		});   
		return (ArrayList<Entry<Integer, Integer>>) entries;   
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
		LotteryBetNumber LotteryBetNumber = this.getEntity();
		return super.edit();
	}
	
	public String view() throws Exception {
		return this.VIEW;
	}
	
	@Override
	public void validate() {
	}

	@Override
	public CRUDService<LotteryBetNumber> getAutowiredService() {
		return lotteryBetNumberService;
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
