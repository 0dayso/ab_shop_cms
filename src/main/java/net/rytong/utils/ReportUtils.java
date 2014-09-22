package net.rytong.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReportUtils {
	public static List<String[]> pageList(List<String[]> dataList,
			Integer pageIndex) {
		List<String[]> pageList = new ArrayList<String[]>();

		// 设置list长度
		int length = pageIndex * 20;
		if (length > dataList.size()) {
			length = dataList.size();
		}
		// 设置当前索引
		int index = (pageIndex - 1) * 20;
		for (int j = index; j < length; j++) {
			pageList.add(dataList.get(j));
		}
		return pageList;
	}

	public static StringBuffer pageStrBuf(String[] dataInfo,Integer pageIndex){
		StringBuffer strBuf = new StringBuffer();
		int length = pageIndex * 20;
		// 设置当前索引
		int index = (pageIndex - 1) * 20+1;
		if (length > dataInfo.length-1) {
			length = dataInfo.length-1;
		}
		for (int j = index; j <= length; j++) {
			strBuf.append(dataInfo[j]).append(";");
		}
		return strBuf;
	}
	public static String getSubString(String seatClass) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<String> readTxt(File file, String falg,
			Integer beginDate, Integer endDate) {
		List<String> readList = new ArrayList<String>();
		try {
			FileInputStream content = new FileInputStream(file);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					content));
			String s = in.readLine();
			while (s != null) {
				if (!s.startsWith("#") && s.length() > 0
						&& s.split("\\|")[1].substring(0, 2).equals(falg))
					readList.add(s.trim());
					s = in.readLine();
			}
			in.close();
		} catch (Exception e) {
			readList.clear();
		}
		return readList;
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> readXml(File file, String falg,Integer beginDate, Integer endDate) {
		List<String> readList = new ArrayList<String>();
		try {
			FileInputStream content = new FileInputStream(file);
			SAXReader reader = new SAXReader();
		    Document document = reader.read(content); 
			Element root = document.getRootElement();
			List<Element> recordList = root.element("RecordList").elements("Record");
			for (Element element : recordList) {
				String payType = element.attribute("PayType").getText();
				String orderNo = element.attribute("OrderNum").getText();
				String tranTime = element.attribute("TranTime").getText();
				if(payType.equals("01") && falg.equals(orderNo.substring(0, 2))){
					readList.add(tranTime+"|"+orderNo+"|"+element.attribute("Amt").getText());
				}
			}
		} catch (Exception e) {
			readList.clear();
		}
		return readList;
	}
}
