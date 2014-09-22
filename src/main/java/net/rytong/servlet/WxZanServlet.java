package net.rytong.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rytong.dao.ISubclauesDAO;
import net.rytong.dao.IZanVisitDAO;
import net.rytong.entity.Subclaues;
import net.rytong.entity.ZanVisit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 进入子栏目详情
 * @author 20120628
 *
 */
public class WxZanServlet extends HttpServlet {
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
		ISubclauesDAO subClauesDAO = (ISubclauesDAO)springContext.getBean("subclauesDAO");
		IZanVisitDAO zanVisitDAO = (IZanVisitDAO)springContext.getBean("zanVisitDAO");
		String subClauseId = request.getParameter("id");
		Subclaues sub = subClauesDAO.findById(Long.parseLong(subClauseId));
		String ip = getIpAddr(request);
		
		if(sub != null){
			//把子栏目内容放到request中
			sub.setZan(sub.getZan() + 1);
			subClauesDAO.update(sub);
			
			ZanVisit visit = new ZanVisit();
			visit.setIp(ip);
			visit.setSubCode(sub.getCode());
			zanVisitDAO.save(visit);
			request.setAttribute("subclaues", sub);
		}else{
			request.setAttribute("state", "fail");
		}
		//请求转发
		request.getRequestDispatcher("/WxSubclaus?id=" + subClauseId).forward(request, response);
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
