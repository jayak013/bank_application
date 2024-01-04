package com.zm.bankapp.controller;

import java.io.IOException;
import java.util.List;

import com.zm.bankapp.dto.BankTransaction;
import com.zm.bankapp.service.AccountServiceImpl;
import com.zm.bankapp.service.BankTransactionService;
import com.zm.bankapp.service.BankTransactionServiceImpl;

import jakarta.servlet.RequestDispatcher;
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
			Integer acc = (Integer)session.getAttribute("onlyAcc");
			double bal = accService.getBalanceByAccountNo(acc);
			session.setAttribute("bal", "Account Balance is  " + bal);
			req.getRequestDispatcher("customer-dashboard.jsp").forward(req, resp);
		}if(action.equalsIgnoreCase("tx")) {
			BankTransactionService bankService = new BankTransactionServiceImpl();
			Integer acc = (Integer)session.getAttribute("onlyAcc");
			Integer accountNo = acc;
			List<BankTransaction> transactionHistory = bankService.getTransactionHistoryByAccountNo(accountNo);
	         req.setAttribute("transList",transactionHistory);
	         resp.setContentType("text/html");
			 RequestDispatcher rd = req.getRequestDispatcher("tx-history-customer.jsp");
			 rd.forward(req, resp);
		}
	}
	
}
