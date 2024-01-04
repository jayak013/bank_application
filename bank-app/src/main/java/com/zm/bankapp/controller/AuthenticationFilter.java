package com.zm.bankapp.controller;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticationFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		if(!(req.getRequestURI().endsWith("welcome.html")||req.getRequestURI().endsWith("welcome")||req.getRequestURI().endsWith("login.jsp"))) {
			if(session.getAttribute("page")==null) res.sendRedirect("welcome.html");
			else chain.doFilter(req, res);
		}else {
			chain.doFilter(req, res);
		}
	}
}
