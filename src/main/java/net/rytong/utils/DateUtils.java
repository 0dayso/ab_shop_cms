package net.rytong.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static Date parse(String s) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateFormat.parse(s);
		} catch (ParseException e) {
			System.out.println("传入的日期字符串格式不对，请确保为yyyy-MM-dd！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 是否闰年
	 * 
	 * @param CurYear
	 *            年度
	 * @return True - 是闰年； false - 平年
	 */
	public static boolean isLeapYear(int CurYear) {
		boolean _bIsLeap = false;
		// 判定平年闰年
		if (((CurYear % 4 == 0) && (CurYear % 100 != 0))
				|| (CurYear % 400 == 0)) {
			_bIsLeap = true;
		} else {
			_bIsLeap = false;
		}
		return _bIsLeap;
	}

	// 计算多少天前的时间

	private static int before(int curYear, int curMonth, int curDay, int i) {
		int _iCurYear = curYear;
		int _iCurMonth = curMonth;
		int _iCurDay = curDay;
		int _iDays = i;
		_iDays = -_iDays;
		// 平年月的天数
		int _aNormMonthDays[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
				30, 31 };
		// 闰年月的天数
		int _aLeapMonthDays[] = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31,
				30, 31 };
		int[] _aMonthDays = new int[13];
		// 计算下个年度的月份
		while (_iDays != 0) {
			// 是否在当前月中
			int _iDescDays = _iCurDay - 1;
			if (_iDays > _iDescDays) {
				_iDays = _iDays - _iDescDays - 1;
				// 设定上月的年月日
				if (_iCurMonth > 1) {
					_iCurMonth--;
				} else {
					_iCurMonth = 12;
					_iCurYear--;
				}
				// 是否闰年
				if (isLeapYear(_iCurYear)) {
					_aMonthDays = _aLeapMonthDays;
				} else {
					_aMonthDays = _aNormMonthDays;
				}
				// 上月的月末日期
				_iCurDay = _aMonthDays[_iCurMonth];
			} else {
				_iCurDay = _iCurDay - _iDays;
				_iDays = 0;
			}
		}

		return _iCurYear * 10000 + _iCurMonth * 100 + _iCurDay;
	}

	// 计算多少天后的时间
	private static int after(int curYear, int curMonth, int curDay, int i) {
		int _iCurYear = curYear;
		int _iCurMonth = curMonth;
		int _iCurDay = curDay;
		int _iDays = i;
		// 平年月的天数
		int _aNormMonthDays[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
				30, 31 };
		// 闰年月的天数
		int _aLeapMonthDays[] = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31,
				30, 31 };
		int[] _aMonthDays = new int[13];
		// 计算下个年度的月份
		while (_iDays != 0) {
			// 是否闰年
			if (isLeapYear(_iCurYear)) {
				_aMonthDays = _aLeapMonthDays;
			} else {
				_aMonthDays = _aNormMonthDays;
			}

			// 是否在当前月中
			int _iDescDays = _aMonthDays[_iCurMonth] - _iCurDay;
			if (_iDays > _iDescDays) {
				_iDays = _iDays - _iDescDays - 1;
				// 设定下月的年月日
				_iCurDay = 1;
				if (_iCurMonth < 12) {
					_iCurMonth++;
				} else {
					_iCurMonth = 1;
					_iCurYear++;
				}
			} else {
				_iCurDay = _iCurDay + _iDays;
				_iDays = 0;
			}
		}

		return _iCurYear * 10000 + _iCurMonth * 100 + _iCurDay;
	}

	/**
	 * 返回指定天数后的日期
	 * 
	 * @param curDate
	 *            时间起点 YYYYMMDD
	 * @param i
	 *            数天前 (Days < 0); Days 数天后 (Days > 0)
	 * @return 数天后的日期 YYYYMMDD
	 */
	public static int getDateLater(int curDate, int i) {
		int _iCurYear = curDate / 10000; // 获得年度
		int _iCurMonth = (curDate - _iCurYear * 10000) / 100; // 获得月份
		int _iCurDay = (curDate) % 100; // 获得日期
		int _iNextDate = 0;
		if (i >= 0) {
			_iNextDate = after(_iCurYear, _iCurMonth, _iCurDay, i);
		} else {
			_iNextDate = before(_iCurYear, _iCurMonth, _iCurDay, i);
		}

		return _iNextDate;

	}

	public static Integer dateToInteger(String dateStr) {
		if (dateStr != null) {
			dateStr = dateStr.trim();
			if (dateStr.length() == 10) {// 2010-01-25
				StringBuffer sb = new StringBuffer();
				String[] splitStr = dateStr.split("-");
				for (int i = 0; i < splitStr.length; i++) {
					sb.append(splitStr[i]);
				}
				return Integer.parseInt(sb.toString());
			} else if (dateStr.length() == 8) {// 13:23:18
				StringBuffer sb = new StringBuffer();
				String[] splitStr = dateStr.split(":");
				for (int i = 0; i < splitStr.length; i++) {
					sb.append(splitStr[i]);
				}
				return Integer.parseInt(sb.toString());
			} else if (dateStr.length() == 19) {// 2010-01-25 13:23:18
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < dateStr.length(); i++) {
					String eachChar = dateStr.substring(i, i + 1);
					if ("1234567890".indexOf(eachChar) != -1) {
						sb.append(eachChar);
					}
				}
				return Integer.parseInt(sb.toString());
			}

		}
		return null;
	}

	public static Long formatLongDate(String dateStr){
		if(dateStr != null && !"".equals(dateStr)){
			if(dateStr.length() == 19){
				return Long.parseLong(dateStr.substring(0, 10).replaceAll("-", "")+dateStr.substring(11,19).replaceAll(":", ""));
			}
			return null;
		}
		return null;
	}

	public static Long getDateLastLong(String dateStr) {
		if (dateStr != null) {
			dateStr = dateStr.trim();
			if (dateStr.length() >= 10) {// 2010-01-25
				dateStr = dateStr.substring(0, 10) + " 23:59:59";
				return dateToLong(dateStr);
			}
		}
		return null;
	}

	// return 20100807000000格式
	public static Long dateToLong(String dateStr) {// 2010-07-01T00:00:00+08:00
		if (dateStr != null) {
			dateStr = dateStr.trim();
			if (dateStr.length() == 10) {// 2010-01-25
				StringBuffer sb = new StringBuffer();
				String[] splitStr = dateStr.split("-");
				for (int i = 0; i < splitStr.length; i++) {
					sb.append(splitStr[i]);
				}
				return Long.parseLong(sb.toString() + "000000");
			} else if (dateStr.length() == 8) {// 13:23:18
				StringBuffer sb = new StringBuffer();
				String[] splitStr = dateStr.split(":");
				for (int i = 0; i < splitStr.length; i++) {
					sb.append(splitStr[i]);
				}
				return Long.parseLong(sb.toString());
			} else if (dateStr.length() == 19) {// 2010-01-25 13:23:18
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < dateStr.length(); i++) {
					String eachChar = dateStr.substring(i, i + 1);
					if ("1234567890".indexOf(eachChar) != -1) {
						sb.append(eachChar);
					}
				}
				return Long.parseLong(sb.toString());
			} else if (dateStr.length() == 25) {
				return Long.parseLong(dateStr.substring(0, 19).replace("T", "")
						.replaceAll("-", "").replaceAll(":", ""));
			}

		}
		return null;
	}

	public static String formatDateOrTime(Object value) {
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
			} else if (temp.length() == 12) {
				return temp.substring(0, 4) + "-" + temp.subSequence(4, 6)
						+ "-" + temp.substring(6, 8) + " "
						+ temp.substring(8, 10) + ":" + temp.substring(10, 12);
			}
		}
		return value + "";
	}

	public static Long getRYTDateTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return Long.parseLong(sdf.format(date));
	}

	public static Long getRYTTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		return Long.parseLong(sdf.format(date));
	}

	public static Long getRYTDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return Long.parseLong(sdf.format(date));
	}

	public static String getRYTDate(Long date) {
		if(date != null){
			String str = date.toString();
			return str.substring(0, 4) + "-" + str.substring(4, 6) + "-"
					+ str.substring(6, 8);
		}
		return "";
	}

	public static String formatDate(String date) {
		return date.replace("-", "/");
	}
}
