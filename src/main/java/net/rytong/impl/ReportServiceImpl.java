package net.rytong.impl;

import java.util.List;

import net.rytong.dao.IReportDAO;
import net.rytong.service.IReportService;
import net.rytong.vo.UserVisitCountVO;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author wuweihua
 * 
 */
@Component
public class ReportServiceImpl implements IReportService {

	@Autowired
	private IReportDAO iReportAO;

	public String report(Long beginTime, Long endTime, int type, int period)
			throws Exception {
		String result = "";
		switch (type) {
		case 1:
			result = userVistisReport(beginTime, endTime, period);
			break;
		case 2:
			// TODO 关键字访问量
			break;
		case 3:
			// TODO 菜单 访问量
			break;
		}
		return result;
	}

	private String userVistisReport(Long beginTime, Long endTime, int period)
			throws Exception {
		List<UserVisitCountVO> resultList = iReportAO.userVisitsReport(
				beginTime, endTime, period);
		JSONObject jsonObj = new JSONObject();
		String subtitle = "";
		switch (period) {
		case 1:
			subtitle = "按小时统计";
			break;
		case 2:
			subtitle = "按日统计";
			break;
		case 3:
			subtitle = "按月统计";
			break;
		case 4:
			subtitle = "按年统计";
			break;
		}
		jsonObj.put("title", "微信用户访问量统计");
		jsonObj.put("subtitle", subtitle);
		jsonObj.put("xAxisTitle", "日期");
		jsonObj.put("yAxisTitle", "人数");
		JSONArray seriesData = new JSONArray(resultList);
		JSONObject series = new JSONObject();
		series.put("name", "访问量");
		series.put("data", seriesData);
		jsonObj.put("series", series);
		return jsonObj.toString();
	}

}
