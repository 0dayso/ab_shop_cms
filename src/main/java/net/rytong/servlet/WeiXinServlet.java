package net.rytong.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rytong.dao.ICustomerDAO;
import net.rytong.dao.IParameterDAO;
import net.rytong.dao.IVisitsDAO;
import net.rytong.dao.IWeixinKeyDAO;
import net.rytong.dao.IWeixinMenuDAO;
import net.rytong.dao.IWeixinUserDAO;
import net.rytong.entity.Customer;
import net.rytong.entity.Resource;
import net.rytong.entity.Visits;
import net.rytong.entity.WeixinKey;
import net.rytong.entity.WeixinMenu;
import net.rytong.entity.WeixinUser;
import net.rytong.utils.TimeHelper;
import net.rytong.vo.WeixinKeyVo;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class WeiXinServlet extends HttpServlet {
	private static final long serialVersionUID = -2388152467479022418L;
	protected Logger logger = LoggerFactory.getLogger(getClass().getName());
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/xml;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		logger.info("******************************WEIXIN SIGN  BEGIN**********************************");
		try {
			String signature = req.getParameter("signature");
			String timestamp = req.getParameter("timestamp");
			String nonce = req.getParameter("nonce");
			String echostr = req.getParameter("echostr");
			String uri = req.getRequestURI();
			String customer = uri.substring(uri.lastIndexOf("/") + 1);
			WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
			ICustomerDAO iCustomerDAO = (ICustomerDAO) springContext.getBean("customerDAO");
			List<Customer> cList = iCustomerDAO.findByShotName(customer, null);
			String token = cList.get(0).getToken();
			String[] list = { token, timestamp, nonce };
			Arrays.sort(list);

			String signStr = "";
			for (String str : list) {
				signStr += str;
			}

			String resultSha = stringToSha(signStr);
			logger.info(resultSha);
			logger.info(echostr);
			if (resultSha.equals(signature)) {
				logger.info("WEIXIN SIGN END!");
				if (echostr != null && !echostr.equals("")) {// echostr有值则返回，为空则为用户POST请求，根据KEY时间返回报文
					out.write(echostr);
					out.flush();
				} else {
					retrunPost(req,resp);
				}
			} else {
				logger.info("WEIXIN TOKEN SIGN ERROR!");
			}
		} catch (Exception e) {
			logger.info("WEIXIN SIGN ERROR!");
			e.printStackTrace();
		}
	}

	private void retrunPost(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		PrintWriter out = resp.getWriter();
		String uri = req.getRequestURI();
		String customer = uri.substring(uri.lastIndexOf("/") + 1);
		logger.info("******************************WEIXIN POST  BEGIN**********************************");
		try {
			SAXReader sr = new SAXReader();
			Document result = sr.read(req.getInputStream());
			logger.info("******************************XML DATE READ  BEGIN**********************************");
			logger.info(result.asXML());
			WeixinKeyVo vo = null;
			Element root = result.getRootElement();
			String fromUser = root.elementText("FromUserName");
			String localUser = root.elementText("ToUserName");
			String content = root.elementText("Content");
			WeixinUser wUser = addOrUpdateUser(root.elementText("FromUserName"), customer);
			addVisitRecord(wUser, "1", StringUtils.isBlank(content) ? "subscribe" : content);
			if (result.asXML().indexOf("EventKey") > -1) {
				String key = root.elementText("Event");
				if (key.equals("subscribe")) { 
					vo = subScribe(wUser, customer); // 订阅事件
				} else if (key.equals("CLICK")) { //自定义菜单点击事件\
					vo = menuEvent(root.elementText("EventKey"), customer, wUser);
				}
			}else if (result.asXML().indexOf("MsgId") > -1) { // 判断是否是用户发出消息
				if (result.asXML().indexOf("text") > -1) { //用户发出文本消息
					if ("0".equals(content) || "?".equals(content) || "？".equals(content)) {
						vo = subScribe(wUser, customer);
					} else {
						String path = wUser.getRemark(); 
						if (StringUtils.isBlank(path)) {
							vo = firstVisit(wUser, content, customer);
						} else { 
							WeixinKey key = findKeyByCurrentPath(path, customer, content);
							if (key != null && key.getLeave() == 1) {
								setUserPath(wUser, key.getSubId());
							} 
							vo = base(key, customer);
						}
					}
				} 
			}
			if (vo == null) {
				vo = subScribe(wUser, customer);
			}
			String responseXML = convertFromVo(vo, fromUser, localUser);
			addVisitRecord(wUser, "0", StringUtils.join(vo.getResourceId(), ","));
			logger.info(responseXML);
			out.write(responseXML);
			out.flush();
		} catch (Exception e) {
			out.write("");
			out.flush();
			logger.info("WEIXIN POST ERROR!");
			e.printStackTrace();
		}
	}
	
	private void addVisitRecord(WeixinUser wUser, String type, String content) {
		WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		IVisitsDAO iVisitsDAO = (IVisitsDAO) springContext.getBean("visitsDAO");
		Visits visit = new Visits();
		visit.setInfoType(type);
		visit.setInputInfo(content);
		visit.setUserName(wUser.getName());
		visit.setCustomer(wUser.getCustomer());
		visit.setVisitTime(String.valueOf(TimeHelper.getCurrentTime()));
		iVisitsDAO.save(visit);
	}

	private String convertFromVo(WeixinKeyVo vo, String fromUser, String localUser) {
		String type = vo.getType();
		if ("text".equals(type)) {
			return getText(vo, fromUser, localUser);
 		} else if ("image".equals(type)) {
 			return getImage(vo, fromUser, localUser);
 		} else if ("news".equals(type)) {
 			return getNews(vo, fromUser, localUser);
 		}
		return "";
	}

	private String getText(WeixinKeyVo vo, String fromUser, String localUser) {
		WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		IParameterDAO iParameterDAO = (IParameterDAO) springContext.getBean("parameterDAO");
		String ip = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "IP", "0").get(0).getValue();
		StringBuffer returnXML = new StringBuffer();
		String flag = vo.getFlags()[0];
		String content = vo.getContent();
		if (content.contains("#indenyfy#")) { // 余额查询中的身份判断
			if ("1".equals(flag)) {//已绑定
				content = "您的余额：500.01元。点击<a href=\"" + ip + "manage/bangding?weixinNo="+ fromUser +"\">查看详细</a>";
			} else {
				content = content.replaceAll("#indenyfy#", ip + "manage/bangding?weixinNo="+ fromUser);
			}
		}
		returnXML.append("<xml>");
		returnXML.append("<ToUserName><![CDATA[" + fromUser + "]]></ToUserName>");
		returnXML.append("<FromUserName><![CDATA[" + localUser + "]]></FromUserName>");
		returnXML.append("<CreateTime>" + TimeHelper.getCurrentTime() + "</CreateTime>");
		returnXML.append("<MsgType><![CDATA[text]]></MsgType>");
		returnXML.append("<Content><![CDATA[" + content + "]]></Content>");
		returnXML.append("<FuncFlag>0</FuncFlag>");
		returnXML.append("</xml>");
		return returnXML.toString();
	}

	private String getImage(WeixinKeyVo vo, String fromUser, String localUser) {
		return null;
	}
	
	private String getNews(WeixinKeyVo vo, String fromUser, String localUser) {
		StringBuffer returnXML = new StringBuffer();
		returnXML.append("<xml>");
		returnXML.append("<ToUserName><![CDATA[" + fromUser + "]]></ToUserName>");
		returnXML.append("<FromUserName><![CDATA[" + localUser + "]]></FromUserName>");
		returnXML.append("<CreateTime>" + TimeHelper.getCurrentTime() + "</CreateTime>");
		String[] titles = vo.getTitle();
		String[] links = vo.getPicLink();
		String[] contents = vo.getContents();
		String[] urls = vo.getUrl();
		int size = titles.length;
		StringBuffer artStr = new StringBuffer();
		artStr.append("<MsgType><![CDATA[news]]></MsgType>");
		artStr.append("<ArticleCount>" + size + "</ArticleCount>");
		artStr.append("<Articles>");
		
		for (int i = 0; i < size; i++) {
			artStr.append("<item>");
			artStr.append("<Title><![CDATA[" + titles[i] + "]]></Title>");
			artStr.append("<Description><![CDATA[" + contents[i] + "]]></Description>");
			artStr.append("<PicUrl><![CDATA[" + links[i] + "]]></PicUrl>");
			artStr.append("<Url><![CDATA[" + urls[i] + "]]></Url>");
			artStr.append("</item>");
		}
		artStr.append("</Articles>");
		returnXML.append(artStr.toString());
		returnXML.append("<FuncFlag>1</FuncFlag>");
		returnXML.append("</xml>");
		return returnXML.toString();
	}

	private WeixinKeyVo firstVisit(WeixinUser wUser, String content, String customer) {
		WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		IWeixinKeyDAO iWeixinKeyDAO = (IWeixinKeyDAO) springContext.getBean("weixinKeyDAO");
		WeixinKeyVo result = null;
		List<WeixinKey> list = iWeixinKeyDAO.findkeysAndCustomer(customer);
		if (list != null && list.size() > 0) { // 解词
			for (int i = 0; i < list.size(); i++) {
				WeixinKey key = list.get(i);
				String[] subId = key.getSubId().split("/");
				if (subId.length == 1) {
					String[] words = key.getKey().split("、");
					for (String word : words) {
						if (StringUtils.isNotBlank(word) && word.contains(content)) {
							result = base(key, customer);
							if (key != null && key.getLeave() == 1) {
								setUserPath(wUser, key.getSubId());
							}
							return result;
						}
					}
				}
			}
		} 
		if (result == null) {
			result = autoAnswer(customer);
		}
		return result;
	}
	
	private WeixinKey findKeyByCurrentPath(String path, String customer, String content) {
		WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		IWeixinKeyDAO iWeixinKeyDAO = (IWeixinKeyDAO) springContext.getBean("weixinKeyDAO");
		List<WeixinKey> list = iWeixinKeyDAO.findkeysAndCustomer(customer);
		if (list != null && list.size() > 0) {
			for (WeixinKey key : list) {
				if(key.getSubId().equals(path + "/" + key.getId())) {
					String[] words = key.getKey().split("、");
					for (String word : words) {
						if (StringUtils.isNotBlank(word) && word.contains(content)) {
							return key;
						}
					}
				}
			}
		}
		return null;
	}

	private void setUserPath(WeixinUser wUser, String path) {
		WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		IWeixinUserDAO iWeixinUserDAO = (IWeixinUserDAO) springContext.getBean("weixinUserDAO");
		wUser.setRemark(path);
		iWeixinUserDAO.update(wUser);
	}

	private WeixinKeyVo menuEvent(String eventKey, String customer, WeixinUser user) {
		WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		IWeixinMenuDAO iWeixinMenuDAO = (IWeixinMenuDAO) springContext.getBean("weixinMenuDAO");
		List<WeixinMenu> list = iWeixinMenuDAO.findByNameAndCustomer(eventKey, customer);
		if (list.size() > 0) {
			return menu(list.get(0), customer, user);
		}
		return null;
	}

	private WeixinKeyVo subScribe(WeixinUser wUser, String customer) {
		WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		IWeixinKeyDAO iWeixinKeyDAO = (IWeixinKeyDAO) springContext.getBean("weixinKeyDAO");
		WeixinKeyVo result = null;
		List<WeixinKey> list = iWeixinKeyDAO.findByNameAndCustomer("Attention", customer);
		if (list != null && list.size() == 1) {
			result = base(list.get(0), customer);
		} else {
			result = base(null, customer);
		}
		setUserPath(wUser, "");
		return result;
	}
	
	private WeixinKeyVo autoAnswer(String customer) {
		WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		IWeixinKeyDAO iWeixinKeyDAO = (IWeixinKeyDAO) springContext.getBean("weixinKeyDAO");
		WeixinKeyVo result = null;
		List<WeixinKey> list = iWeixinKeyDAO.findByNameAndCustomer("AutoAnswer", customer);
		if (list != null && list.size() == 1) {
			result = base(list.get(0), customer);
		} else {
			result = base(null, customer);
		}
		return result;
	}
	
	private WeixinKeyVo base(WeixinKey obj, String customer) {
		WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		IParameterDAO iParameterDAO = (IParameterDAO) springContext.getBean("parameterDAO");
		String ip = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		String showPath = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "FILLPATHFORSHOW", "0").get(0).getValue();
		WeixinKeyVo vo = null;
		
		if (obj != null) {
			String type = obj.getType();
			vo = new WeixinKeyVo();
			if ("text".equals(type)) {
				List<Resource> resources = obj.getResources();
				if (resources != null && resources.size() > 0) {
					Resource r = obj.getResources().get(0);
					vo.setType("text");
					String[] ids = new String[]{r.getId() + ""};
					vo.setResourceId(ids);
					vo.setContent(r.getContent());
				}
			} else if ("image".equals(obj.getType())) {
				Resource r = obj.getResources().get(0);
				vo.setType("image");
				String[] links = new String[] {ip + customer + "/" + r.getPicName()};
				vo.setPicLink(links);
				String[] ids = new String[]{r.getId() + ""};
				vo.setResourceId(ids);
			} else if ("news".equals(obj.getType())) {
				List<Resource> list = obj.getResources();
				vo.setType("news");
				int size = list.size();
				String[] titles = new String[size];
				String[] links = new String[size];
				String[] contents = new String[size];
				String[] ids = new String[size];
				String[] urls = new String[size];
				
				for (int i = 0; i < list.size(); i++) {
					Resource r = obj.getResources().get(i);
					titles[i] = r.getTitle();
					String name = r.getPicName();
					if (StringUtils.isNotBlank(name)) {
						links[i] = name.startsWith("http:") ? name : (ip + customer + "/" + name);
					} else {
						links[i] = "";
					}
					contents[i] = r.getContent();
					ids[i] = r.getId() + "";
					String url = r.getUrl();
					if (StringUtils.isNotBlank(url)) {
						urls[i] = url.startsWith("http:") ? url : (showPath + customer + "/" + url);
					} else {
						urls[i] = "";
					}
					
				}
				vo.setTitle(titles);
				vo.setPicLink(links);
				vo.setContents(contents);
				vo.setResourceId(ids);
				vo.setUrl(urls);
			}
		} 
		return vo;
	}
	
	private WeixinKeyVo menu(WeixinMenu obj, String customer, WeixinUser user) {
		WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		IParameterDAO iParameterDAO = (IParameterDAO) springContext.getBean("parameterDAO");
		String ip = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		WeixinKeyVo vo = null;
		
		if (obj != null) {
			String type = obj.getType();
			vo = new WeixinKeyVo();
			if ("text".equals(type)) {
				List<Resource> resources = obj.getResources();
				if (resources != null && resources.size() > 0) {
					vo.setType("text");
					vo.setFlags(new String[]{user.getUserName()});
					vo.setContent(resources.get(0).getContent());
				}
			} else if ("image".equals(obj.getType())) {
				Resource r = obj.getResources().get(0);
				vo.setType("image");
				String[] links = new String[] {ip + r.getPicName()};
				vo.setPicLink(links);
				String[] ids = new String[]{r.getId() + ""};
				vo.setResourceId(ids);
			} else if ("news".equals(obj.getType())) {
				List<Resource> list = obj.getResources();
				vo.setType("news");
				int size = list.size();
				String[] titles = new String[size];
				String[] links = new String[size];
				String[] contents = new String[size];
				String[] ids = new String[size];
				String[] urls = new String[size];
				
				for (int i = 0; i < list.size(); i++) {
					Resource r = obj.getResources().get(0);
					titles[i] = r.getTitle();
					links[i] = r.getPicName();
					contents[i] = r.getContent();
					ids[i] = r.getId() + "";
					urls[i] = r.getUrl();
				}
				vo.setTitle(titles);
				vo.setPicLink(links);
				vo.setContents(contents);
				vo.setResourceId(ids);
				vo.setUrl(urls);
			}
		} 
		return vo;
	}

	private WeixinUser addOrUpdateUser(String weixinNo, String customer) {
		WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		IWeixinUserDAO iWeixinUserDAO = (IWeixinUserDAO) springContext.getBean("weixinUserDAO");
		List<WeixinUser> users = iWeixinUserDAO.findByWeixinNoAndCustomer(weixinNo, customer);
		WeixinUser wUser = null;
		if (users != null && users.size() > 0) {
			wUser = users.get(0);
			wUser.setLastLoginTime(TimeHelper.getCurrentTime());
			iWeixinUserDAO.update(wUser);
		} else {
			wUser = new WeixinUser();
			wUser.setWeixinNo(weixinNo);
			wUser.setName(weixinNo);
			wUser.setCustomer(customer);
			wUser.setLastLoginTime(TimeHelper.getCurrentTime());
			iWeixinUserDAO.save(wUser);
		}
		return wUser;
	}

	// SHA1加密
	private String stringToSha(String signStr) {
		byte[] _bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] bytes = signStr.getBytes();
			md.update(bytes);
			_bytes = md.digest();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return byte2hex(_bytes);
	}

	//将二进制转化为16进制字符串
	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs;
    }
}
