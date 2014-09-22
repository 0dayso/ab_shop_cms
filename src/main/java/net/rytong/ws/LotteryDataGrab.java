package net.rytong.ws;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.rytong.dao.IParameterDAO;
import net.rytong.entity.Parameter;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LotteryDataGrab {
	@Autowired
	private IParameterDAO iParameterDAO;
	@Autowired
	private LotteryDataUpdate lotteryDataUpdate;
	public static int FLAG = 0;
	public static String issue;
	public static String issueKey;
	
//	public static void main(String args[]) throws Exception {
//		String s = URLDecoder.decode("%C4%D0%BF%C6", "gb2312");
//		String p = URLEncoder.encode("浙江省义乌市城中西路136号", "gb2312");
//		String key = URLEncoder.encode("F9082F1AB63FCF0ACC154A4FD11C9193A91F0F30", "gb2312");
//		String c = "http://api.map.baidu.com/geocoder?address=" + p + "&output=json" + "&key=" + key;
//		int pageNum = 30;
//		String url1 = "http://caipiao.taobao.com/lottery/ajax/get_united_list.htm?page=1&issue=&lotteryType=DLT&playType=&commission_rate=-1&confidential=1&united_fee=0-0&is_not_full=1&creator=&sort_obj=process&sort=desc&change_sort=false&lowAmount=0&highAmount=0&from=&onsale=&t=1403187025128";
//		for (int i = 1; i <= pageNum; i++) {
//			String k = url1.replace("{PAGE}", i + "");
//		LotteryDataGrab t = new LotteryDataGrab();
//			String res = t.httpClient(url1);
//			parseJson(res);
//		}
//			JSONObject ob = new JSONObject(res);
//			final String total = ob.getString("totalPage");
//			System.out.println(total);
//			JSONArray issueList = ob.getJSONArray("issueList");
//			if (issueList != null && issueList.length() > 0) {
//				for (int i = 0; i < issueList.length(); i++) {
//					JSONObject o = issueList.getJSONObject(i);
//					final String onsale = o.getString("onsale");
//					if (onsale == "true") {
//						System.out.println(o.getString("issue"));
//						break;
//					}
//				}
//			}
//	}
	
	public synchronized void synchroGrabData() throws JSONException, IOException {
		if (FLAG == 0) {
			final String url1 = "http://caipiao.taobao.com/lottery/ajax/get_united_list.htm?page={PAGE}&issue=&lotteryType=DLT&playType=&commission_rate=-1&confidential=1&united_fee=0-0&is_not_full=1&creator=&sort_obj=process&sort=desc&change_sort=false&lowAmount=0&highAmount=0&from=&onsale=&t=1403187025128";
			String k = url1.replace("{PAGE}", 1 + "");
			String res = httpClient(k);
			writeNull(); //清空文件
			int total = parseJson(res, true);
			
			if (total > 1) {
				for (int i = 2; i <= total; i++) {
					String k1 = url1.replace("{PAGE}", i + "");
					String res1 = httpClient(k1);
					parseJson(res1, false);
				}
			}
		}
		setFlag(1);
		lotteryDataUpdate.synchroBetLotterys(LotteryDataGrab.issue);
	}
	
	public synchronized static void setFlag (int flag) {
		FLAG = flag;
	}
	
	public int parseJson(String s, boolean flag) throws JSONException, IOException {
		int result = 1;
		
		if (s != null) {
			JSONObject ob = new JSONObject(s);
			JSONArray schemes = ob.getJSONArray("schemes");
			
			if (flag) {
				final int total = ob.getInt("totalPage");
				result = total;
				System.out.println(total);
				JSONArray issueList = ob.getJSONArray("issueList");
				if (issueList != null && issueList.length() > 0) {
					for (int i = 0; i < issueList.length(); i++) {
						JSONObject o = issueList.getJSONObject(i);
						final String onsale = o.getString("onsale");
						if (onsale == "true") {
							issue = o.getString("issue");
							System.out.println(issue);
							break;
						}
					}
				}
			}
			
			if (issue != null) {
				Map<String, String> map = lotteryDataUpdate.getIssueInfoLotterys("1", issue);
				issueKey = map.get(issue);
				
				if (issueKey != null) {
					if (schemes != null && schemes.length() > 0) {
						for (int i = 0; i < schemes.length(); i++) {
							JSONObject o = schemes.getJSONObject(i);
							final String united_id = o.getString("id");
							int init = 1;
							final String url1 = "http://caipiao.taobao.com/lottery/order/united_order_detail_szc.htm?_tb_token_=eb7ae74e83511&page={PAGE}&tb_united_id={ID}&lottery_type_id=8&issue_id=" + issueKey + "&db_type=0&is_history=false";
							String k = url1.replace("{PAGE}", init + "");
							k = k.replace("{ID}", united_id);
							String res = httpClient(k);
							int total = mParseHTML(res, true, united_id);
							
							if (total > 1) {
								for (int p = 2; p < total; p++) {
									String k1 = url1.replace("{PAGE}", p + "");
									k1 = k1.replace("{ID}", united_id);
									String res1 = httpClient(k1);
									mParseHTML(res1, false, united_id);
								}
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	public void write(String fileName, String content) {   
        try {   
            FileWriter writer = new FileWriter(fileName, true);   
            writer.write(content);   
            writer.close();   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
    }   
	
	public void writeNull() {   
        try {   
            FileWriter writer = new FileWriter("D:\\lottery.txt");   
            writer.write("");   
            writer.close();   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
    }
	
	public int mParseHTML(String htmldoc, boolean f, String id) throws IOException {
		int result = 1;
		
		try {
			StringBuffer sb = new StringBuffer();
			Document doc = Jsoup.parse(htmldoc);
			Elements contents = doc.getElementsByClass("order-detail");
			if (contents != null && contents.size() > 1) {
				for (int i = 1; i < contents.size(); i++) {
					Element content = contents.get(i);
					Elements trs = content.getElementsByTag("tr");
					if (trs != null && trs.size() > 0) {
						for (int j = 0; j < trs.size(); j++) {
							Element tr = trs.get(j);
							String text = tr.getElementsByClass("td2").get(0).text().trim();
							int sp = text.indexOf(":");
							String mStr = text.substring(0, sp).trim();
							
							String num = "1";
							try {
								Elements rowspans = tr.getElementsByAttribute("rowspan");
								if (rowspans != null && rowspans.size() > 0) {
									num =rowspans.get(0).text().trim().replace("倍", "");
								}
							} catch(Exception e) {;}
							sb.append(mStr + "_" + id + "_" + num).append("\n");
						}
					}
				}
			}
			if (sb.length() > 0) {
				String filePath = "D:\\lottery.txt";
				List<Parameter> list = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "LOTTERY_FILE_PATH", "0");
				if (list != null && list.size() > 0) {
					String s = list.get(0).getValue();
					if (StringUtils.isBlank(s)) {
						filePath = s;
					}
				}
				write(filePath, sb.toString());
			}
			if (f) {
				Element page = doc.getElementsByClass("page-info").get(0);
				String a = page.text().split("/")[1].toString();
				result = Integer.valueOf(a);
			}
		} catch (Exception ex) {
			System.out.println("ww：" + ex.toString());

		}
		return result;
	}

	public String httpClient(String c) {
		String response = null;
		// 实例化HttpClient
		HttpClient client = new HttpClient();
		// 设置连接超时为5秒
		client.getHttpConnectionManager().getParams()
				.setConnectionTimeout(5000);
		// 设置读取超时为5秒
		client.getHttpConnectionManager().getParams().setSoTimeout(5000);
		// 用目标地址 实例一个GET方法
		GetMethod getMethod = new GetMethod(c);
		// 设置GET方法读取超时为5秒
		getMethod.getParams().setSoTimeout(5000);
		try {
			// 执行GET方法
			client.executeMethod(getMethod);
			// 将PGET返回的数据以字符串的形式存储
			InputStream resStream = getMethod.getResponseBodyAsStream();  
	        BufferedReader br = new BufferedReader(new InputStreamReader(resStream));  
	        StringBuffer resBuffer = new StringBuffer();  
	        String resTemp = "";  
	        while((resTemp = br.readLine()) != null){  
	            resBuffer.append(resTemp);  
	        }  
	        response = resBuffer.toString();  
			// 将POST返回的数据以流的形式读入，再把输入流流至一个buff缓冲字节数组
			// StreamTool类是我自己写的一个工具类，其内容将在下文附出
			// byte[] buff =
			// StreamTool.readInputStream(getMethod.getResponseBodyAsStream());
			// 将返回的内容格式化为String存在html中
			// String html = new String(buff);
			// 关闭连接
			getMethod.releaseConnection();
			// 打印字符串
			//System.out.println(response);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	public synchronized void getHistoryLottery() throws JSONException, IOException {
		String[] years = {"2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014"};
		final String moban = "http://baidu.lecai.com/lottery/draw/list/1?d={YEAR}-01-01";
		Map<String, String> map = new HashMap<String, String>();
		for (String year : years) {
			String k = moban.replace("{YEAR}", year);
			String res = httpClient(k);
			map.putAll(mParseHTML(res));
		}
		lotteryDataUpdate.synchroHistoryLotterys(map);
	}
	
	public Map<String, String> mParseHTML(String htmldoc) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
	
		try {
			Document doc = Jsoup.parse(htmldoc);
			Element content = doc.getElementById("draw_list");
			Elements bgcolor1 = content.getElementsByClass("bgcolor1");
			
			for (int i = 0; i < bgcolor1.size(); i++) {
				Element bg = bgcolor1.get(i);
				StringBuffer a = new StringBuffer("");
				Elements ball1s = bg.getElementsByClass("ball_1");
				String issue = bg.getElementsByTag("a").get(0).text().trim();
				for (int j = 0; j < ball1s.size(); j++) {
					a.append(ball1s.get(j).text().trim()).append(",");
				}
				map.put(issue, a.deleteCharAt(a.length() - 1).toString());
			}
			
			bgcolor1 = content.getElementsByClass("bgcolor2");
			for (int i = 0; i < bgcolor1.size(); i++) {
				Element bg = bgcolor1.get(i);
				StringBuffer a = new StringBuffer("");
				Elements ball1s = bg.getElementsByClass("ball_1");
				String issue = bg.getElementsByTag("a").get(0).text().trim();
				for (int j = 0; j < ball1s.size(); j++) {
					a.append(ball1s.get(j).text().trim()).append(",");
				}
				map.put(issue, a.deleteCharAt(a.length() - 1).toString());
			}
			
		} catch (Exception ex) {
			System.out.println("ww：" + ex.toString());

		}
		return map;
	}

}
