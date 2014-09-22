package net.rytong.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rytong.dao.IParameterDAO;
import net.rytong.dao.ISubclauesDAO;
import net.rytong.dao.IZanVisitDAO;
import net.rytong.entity.Subclaues;
import net.rytong.entity.ZanVisit;
import net.rytong.vo.SubclauesVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 进入子栏目详情
 * @author 20120628
 *
 */
public class WxSubclausServlet extends HttpServlet {
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
		IParameterDAO iParameterDAO = (IParameterDAO) springContext.getBean("parameterDAO");
		IZanVisitDAO zanVisitDAO = (IZanVisitDAO)springContext.getBean("zanVisitDAO");
		String ip = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "IP", "0").get(0).getValue();
		String preDetailLink = ip + "manage/WxSubclaus?id=";
		String preZanLink = ip + "manage/WxZan?id=";
		String ipUser = getIpAddr(request);
		String subClauseId = request.getParameter("id");
		Subclaues sub = subClauesDAO.findById(Long.parseLong(subClauseId));
		
		if(sub != null){
			SubclauesVo subVo = new SubclauesVo(sub);
			subVo.setLinkUrl(preDetailLink + subVo.getId());
			subVo.setContent(preZanLink + subVo.getId());
			List<ZanVisit> list = zanVisitDAO.getZans(ipUser, subVo.getCode());
			if (list.size() > 0) {
				subVo.setCode("0");
			}
			request.setAttribute("title", subVo.getTitle());
			request.setAttribute("subclaues", subVo);
			//请求转发
			request.getRequestDispatcher("/system/wxpublic/wxsub.jsp").forward(request, response);
		} else {
			request.setAttribute("state", "fail");
			//请求转发
			request.getRequestDispatcher("/wxpublic").forward(request, response);
		}
		
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
