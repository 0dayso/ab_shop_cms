<%@ page language="java" import="java.util.*,net.rytong.utils.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <%
    	Properties projectProperties = FlightUtil.getProperties("project_service.properties");
    	
   		String projectTitle = null;
   		String loginPng = null;
   		String logoPng = null;
   		String logoIco = null;
   		
   		projectTitle = projectProperties.getProperty("hu_project_title");
		loginPng = projectProperties.getProperty("hu_project_login");
		logoPng = projectProperties.getProperty("hu_project_logo");
		logoIco = projectProperties.getProperty("hu_project_logoico");
			
   		request.getSession().setAttribute("projectName","WEIXIN");
   		request.getSession().setAttribute("projectTitle",projectTitle);
   		request.getSession().setAttribute("loginPng",loginPng);
   		request.getSession().setAttribute("logoPng",logoPng);
   		request.getSession().setAttribute("logoIco",logoIco);
   		request.getRequestDispatcher("system/login.jsp").forward(request,response);
   	 %>
  </body>
</html>
