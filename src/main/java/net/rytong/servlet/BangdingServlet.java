package net.rytong.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rytong.dao.IWeixinUserDAO;
import net.rytong.entity.WeixinUser;
import net.rytong.utils.AccessToHttp;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class BangdingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		
		WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		IWeixinUserDAO iWeixinUserDAO = (IWeixinUserDAO) springContext.getBean("weixinUserDAO");
		String weixinNo = req.getParameter("weixinNo");
		List<WeixinUser>  numbers = iWeixinUserDAO.findByWeixinNo(weixinNo, null);
		String cardType = req.getParameter("cardType");
		String bangdingTrue = req.getParameter("bdTrue");
		
		if (numbers != null && numbers.size() > 0) {
			WeixinUser user = numbers.get(0);
			if(user.getUserName() =="1" || "1".equals(user.getUserName())){
				req.getRequestDispatcher("/system/hxbank/balance.jsp").forward(req, res);
				return;
			}else {
				if(bangdingTrue =="true" || "true".equals(bangdingTrue)){
				if ("10".equals(cardType)) {
					user.setPin("1");
				} else {
					user.setAddress("1");
				}
				user.setUserName("1");
				iWeixinUserDAO.save(user);
				req.setAttribute("weixinNo", weixinNo);
				req.setAttribute("bundResult", "success");
				req.setAttribute("certNo", req.getParameter("certNo"));
				req.setAttribute("cardNo", req.getParameter("cardNo"));
				req.getRequestDispatcher("/system/hxbank/success.jsp").forward(req, res);
				return;
				}else {
					req.setAttribute("weixinNo", weixinNo);
					req.getRequestDispatcher("/system/hxbank/index.jsp").forward(req, res);
					return;
				}
			} 
		}
	}
	
	public String bangDing(Map<String, String> map) throws Exception {
		HttpMethod method = null;
	//	HttpClient client = null;
	//	System.out.println(map.get("ip") + ":"  + map.get("port"));
	//	client = AccessToHttp.getHttpClient1(map.get("ip"), Integer.valueOf(map.get("port")));
		
		NameValuePair[] vars = { 
				new NameValuePair("SID", "-1"),  
				new NameValuePair("trxId", "mbwec001_wechat_relateBiz-wechatRelateOp") , 
				new NameValuePair("checkSession", "false"),
				new NameValuePair("sessionType", "normal"),
				new NameValuePair("customerId", map.get("weixinNo")),
				new NameValuePair("bsnCode", ""),
				new NameValuePair("charset", "UTF-8"),
				new NameValuePair("certType", map.get("certType")),
				
				new NameValuePair("certNo", map.get("certNo")),
				new NameValuePair("cardType", map.get("cardType")),
				new NameValuePair("cardNo", map.get("cardNo")),
				new NameValuePair("wechatID",  map.get("weixinNo")),
				new NameValuePair("accountPassword",  map.get("accountPassword"))};
		method = AccessToHttp.getPostEncodingMethod("/mobileApp/bizInvokerCS.do", vars, "UTF-8");
//		client.executeMethod(method);
		String str = method.getResponseBodyAsString();
		
		
		if (str != null && str.length() > 0) {
			String[] strs = str.split("\\|");
			if (strs.length == 5) {
				if (strs[0].equals("0000")) {
					return "0000";
				} else {
					return strs[1];
				}
			}
		}
		
		return null;
	}	
	
	public String searchMoney(Map<String, String> map) throws Exception {
		HttpMethod method = null;
		HttpClient client = null;
		client = AccessToHttp.getHttpClient1("66.0.34.34", 9085);
		
		NameValuePair[] vars = { 
				new NameValuePair("SID", "-1"),  
				new NameValuePair("trxId", "wechatCreditCardBiz-DebitCardBalanceQueryOp") , 
				new NameValuePair("checkSession", "false"),
				new NameValuePair("sessionType", "normal"),
				new NameValuePair("customerId", map.get("weixinNo")),
				new NameValuePair("bsnCode", map.get("")),
				new NameValuePair("wechatID", map.get("weixinNo"))};
		method = AccessToHttp.getPostEncodingMethod("/mobileApp/bizInvokerCS.do", vars, "UTF-8");
		client.executeMethod(method);
		String str = method.getResponseBodyAsString();
		if (str != null && str.length() > 0) {
			String[] strs = str.split("|");
			if (strs.length == 4) {
				if (strs[0].equals("0000")) {
					return strs[2];
				}
			}
		}
		
		return null;
	}	
	
	public String unbound(Map<String, String> map) throws Exception {
		HttpMethod method = null;
		HttpClient client = null;
		client = AccessToHttp.getHttpClient1("66.0.34.34", 9085);
		
		NameValuePair[] vars = { 
				new NameValuePair("SID", "-1"),  
				new NameValuePair("trxId", "mbwec001_wechat_relateBiz-wecharcarddeleteOp") , 
				new NameValuePair("checkSession", "false"),
				new NameValuePair("sessionType", "normal"),
//				new NameValuePair("customerId", map.get("weixinNo")),
				new NameValuePair("customerId", "222"),
				new NameValuePair("bsnCode", map.get("")),
//				new NameValuePair("wechatID", map.get("weixinNo"))
				new NameValuePair("cardType", "10"),
				new NameValuePair("cardNo", "6222830000002856"),
				new NameValuePair("wechatID", "222")};
		method = AccessToHttp.getPostEncodingMethod("/mobileApp/bizInvokerCS.do", vars, "UTF-8");
		client.executeMethod(method);
		String str = method.getResponseBodyAsString();
		if (str != null && str.length() > 0) {
			String[] strs = str.split("|");
			if (strs.length == 4) {
				if (strs[0].equals("0000")) {
					return strs[2];
				}
			}
		}
		
		return null;
	}	
}
