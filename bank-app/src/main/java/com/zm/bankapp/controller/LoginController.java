package com.zm.bankapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.zm.bankapp.dto.Admin;
import com.zm.bankapp.dto.Customer;
import com.zm.bankapp.dto.User;
import com.zm.bankapp.service.AccountService;
import com.zm.bankapp.service.AccountServiceImpl;
import com.zm.bankapp.service.AdminService;
import com.zm.bankapp.service.AdminServiceImpl;
import com.zm.bankapp.service.CustomerService;
import com.zm.bankapp.service.CustomerServiceImpl;
import com.zm.bankapp.service.UserService;
import com.zm.bankapp.service.UserServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

	@WebServlet("/login")
	public class LoginController extends HttpServlet {
		private static UserService userService = null;
		private static AccountService accountService = null;
		private static CustomerService customerService = null;

		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			String username = request.getParameter("username");
			session.setAttribute("user", username);
			String password = request.getParameter("password");
			User user = new User(username, password);
			String userType = userService.validateUserNameAndPassword(user).getUserType();
			if (userType!=null) {	
				if (userType.equalsIgnoreCase("admin")) {
					AdminService adminService = new AdminServiceImpl();
					Admin a = adminService.getAdminDetailsByUserName(username);
					session.setAttribute("adminName", "Admin Name: "+a.getAdminName());
					session.setAttribute("adminEmail", "Email: "+a.getEmail());
					
					RequestDispatcher rd = request.getRequestDispatcher("admin-dashboard.jsp");
					
					rd.forward(request, response);
				} else {
					CustomerService custService = new CustomerServiceImpl();
					AccountService accService = new AccountServiceImpl();
					Customer c = custService.getCustomerByUserName(username.toUpperCase());
					session.setAttribute("custName", "Customer Name: "+c.getCustName());
					session.setAttribute("onlyAcc", accService.getAccountNoByCustId(c.getCustId()));
					session.setAttribute("custAcc", "Account Number: "+accService.getAccountNoByCustId(c.getCustId()));
					session.setAttribute("custMobile", "Mobile: "+c.getMobile());
					RequestDispatcher rd = request.getRequestDispatcher("customer-dashboard.jsp");
					rd.forward(request, response);
				}
			} else {
				request.setAttribute("error", "Invalid Credentials...!");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp?login=customer");
				rd.forward(request, response);
			}
		}

		@Override
		public void init(ServletConfig config) throws ServletException {
			userService = new UserServiceImpl();
			accountService = new AccountServiceImpl();
			customerService = new CustomerServiceImpl();
			
		}
	}
