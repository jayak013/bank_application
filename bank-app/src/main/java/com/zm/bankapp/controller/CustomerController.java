package com.zm.bankapp.controller;

import java.io.IOException;
import java.math.BigDecimal;

import com.zm.bankapp.service.AccountServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/customer")
public class CustomerController extends HttpServlet{
	private static final String String = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		if(action.equalsIgnoreCase("check")) {
			AccountServiceImpl accService = new AccountServiceImpl();
			String acc = (String)session.getAttribute("custAcc");
			String s = new String(acc);
			double bal = accService.getBalanceByAccountNo(Integer.parseInt(s));
			req.setAttribute("bal", "Account Balance is  " + bal);
			req.getRequestDispatcher("customer-dashboard.jsp").forward(req, resp);
		}if(action.equalsIgnoreCase("tx")) {
			
		}
	}
}
