package net.rytong.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckLoginFilter implements Filter {

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		String uri = request.getRequestURI();
		String target = uri.substring(uri.indexOf("/", 1));
		HttpSession session = request.getSession(false);
		if (target.contains(".action") || target.contains("/register.jsp") || target.contains("main")
			|| target.equals("/")
			|| target.equals("/index.jsp") || target.equals("/noRight.jsp")
			|| target.equals("/login.jsp") || target.equals("/error.jsp")) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else if (session != null && session.getAttribute("employee") != null) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}

	public void destroy() {
	}


	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
