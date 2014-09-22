package net.rytong.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rytong.dao.IConferenceDAO;
import net.rytong.dao.IParameterDAO;
import net.rytong.dao.ISubclauesDAO;
import net.rytong.dao.ITopDAO;
import net.rytong.dao.IZanVisitDAO;
import net.rytong.entity.Conference;
import net.rytong.entity.Subclaues;
import net.rytong.entity.ZanVisit;
import net.rytong.vo.SubclauesVo;
import net.rytong.vo.TopVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 
 * @author zhou_dong
 *	会议概要的接口
 */
public class WxPublicServlet extends HttpServlet {
	private static final long serialVersionUID = -2388152467479022418L;
	protected Logger logger = LoggerFactory.getLogger(getClass().getName());
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		//加载spring的web容器		
		WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		IConferenceDAO conferenceDAO = (IConferenceDAO) springContext.getBean("conferenceDAO");
		ITopDAO topDAO = (ITopDAO) springContext.getBean("topDAO");
		ISubclauesDAO subDAO = (ISubclauesDAO) springContext.getBean("subclauesDAO");
		IParameterDAO iParameterDAO = (IParameterDAO) springContext.getBean("parameterDAO");
		IZanVisitDAO zanVisitDAO = (IZanVisitDAO)springContext.getBean("zanVisitDAO");
		String ip = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "IP", "0").get(0).getValue();
		String preDetailLink = ip + "manage/WxSubclaus?id=";
		String preZanLink = ip + "manage/WxZan?id=";
		String ipUser = getIpAddr(request);
		
		//通过用户的会议的状态查询到会议 0为开启 1为关闭
		List<Conference> conferenceList = conferenceDAO.findByProperty("state", 1);
		List<Conference> rConferenceList = new ArrayList<Conference>();
		Conference con = null;
		if (conferenceList.size() > 0) {
			con = conferenceList.get(0);
			rConferenceList.add(con);
			
			//把会议记录放到request中
			request.setAttribute("conferenceList", rConferenceList);
			request.setAttribute("title", con.getTitle());
			
			List<TopVo> tops = TopVo.convert(topDAO.findByConferenceId(con.getId(), null));
			
			for (TopVo top : tops) {
				List<Subclaues> subs = subDAO.findByTopId(Long.valueOf(top.getId()), null);
				List<SubclauesVo> subVos = SubclauesVo.convert(subs);
				
				for (SubclauesVo subVo : subVos) {
					subVo.setLinkUrl(preDetailLink + subVo.getId());
					subVo.setDescribe(preZanLink + subVo.getId());
					List<ZanVisit> list = zanVisitDAO.getZans(ipUser, subVo.getCode());
					if (list.size() > 0) {
						subVo.setCode("0");
					}
				}
				TopVo.addSubclauses(top, subVos);
			}
			request.setAttribute("tops", tops);
		}
		
		//请求转发
		request.getRequestDispatcher("/system/wxpublic/wxpublic.jsp").forward(request, response);
	}
	
	//获取客户端请求真实IP
    public String getIpAddr(HttpServletRequest request) {      
    	String ip = request.getHeader("x-forwarded-for");      
    	if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
    		ip = request.getHeader("Proxy-Client-IP");      
    	}      
    	if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
    		ip = request.getHeader("WL-Proxy-Client-IP");      
    	}      
    	if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
    		ip = request.getRemoteAddr();      
    	}      
    	return ip;      
    } 
}
