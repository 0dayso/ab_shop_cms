package net.rytong.action;

import net.rytong.core.BaseAction;
import net.rytong.service.IReportService;
import net.rytong.utils.TimeHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 图表
 * @author wuweihua
 *　
 */
@Scope("prototype")
@Component
public class ChartReportAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private IReportService iReportService;
	
	private String day;
	private String month;
	private String year;
	private String yearBeg;
	private String yearEnd;
	private int period;
	private int type;
	
	private long beginTime;
	private long endTime;
	
	public String count() throws Exception {
		String result = iReportService.report(beginTime, endTime,type,period);
		this.setAjaxInputStream(result);
		return AJAX;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getYearBeg() {
		return yearBeg;
	}

	public void setYearBeg(String yearBeg) {
		this.yearBeg = yearBeg;
	}

	public String getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(String yearEnd) {
		this.yearEnd = yearEnd;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public void validate() {
		switch (period) {
		case 1:
			beginTime = Long.valueOf((day.replace("-", "") + "000000"));
			endTime = Long.valueOf((day.replace("-", "") + "235959"));
			break;
		case 2:
			beginTime = Long.valueOf((month.replace("-", "") + "01000000"));
			endTime = Long.valueOf(TimeHelper.getDateTimeOfMonthEnd(Integer.valueOf(month.replace("-", ""))));
			break;
		case 3:
			beginTime = Long.valueOf((year + "0101000000"));
			endTime = Long.valueOf((year + "1231235959"));
			break;
		case 4:
			beginTime = Long.valueOf((yearBeg + "0101000000"));
			endTime = Long.valueOf((yearEnd + "1231235959"));
			break;
		}
	}
}
