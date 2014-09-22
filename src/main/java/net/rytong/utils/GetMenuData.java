package net.rytong.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rytong.dao.IFunctionRightDAO;
import net.rytong.entity.FunctionRight;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class GetMenuData extends HttpServlet {

	private static final long serialVersionUID = -8864665586053835846L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parentIdStr=request.getParameter("parentId");
		Long parentId = 0L;
		if(parentIdStr!=null&&parentIdStr.length()>0){
			parentId=Long.parseLong(parentIdStr);
		}
 		String functionName = request.getParameter("functionName");
 		String function = request.getParameter("js");
 		Integer level = Integer.parseInt(request.getParameter("level"));
 		
 		WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		IFunctionRightDAO iFunctionRightDAO = (IFunctionRightDAO)ac.getBean("functionRightDAO");
 	
 		List<FunctionRight> functionList = null;
 		if(parentId == 0l || parentId.equals(0)){
 			functionList = iFunctionRightDAO.findByLevel(1, null);
 		}else{
			functionList = iFunctionRightDAO.findByParentAndLevel(parentId, level);
 		}
 		
 		StringBuffer stringBuffer = new StringBuffer();
 		stringBuffer.append("<select name=\"" + functionName + "\"id=\"" + functionName + "\" onchange=\"" + function + "\">");
 		for (FunctionRight functions : functionList) {
 			stringBuffer.append("<option value=\"" + functions.getFunctionRightId() + "\">" + functions.getName()
 						+ "</option>");
		}
		stringBuffer.append("</select>");
		response.setContentType("text/html; charset=utf-8"); 
		response.setCharacterEncoding("utf-8");  
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		out.print(stringBuffer);
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
