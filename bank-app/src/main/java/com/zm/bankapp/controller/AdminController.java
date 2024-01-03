package com.zm.bankapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import com.zm.bankapp.dto.Account;
import com.zm.bankapp.dto.BankTransaction;
import com.zm.bankapp.dto.Customer;
import com.zm.bankapp.dto.User;
import com.zm.bankapp.service.AccountService;
import com.zm.bankapp.service.AccountServiceImpl;
import com.zm.bankapp.service.BankTransactionService;
import com.zm.bankapp.service.BankTransactionServiceImpl;
import com.zm.bankapp.service.CustomerService;
import com.zm.bankapp.service.CustomerServiceImpl;
import com.zm.bankapp.service.UserService;
import com.zm.bankapp.service.UserServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin")
public class AdminController extends HttpServlet{
	private static UserService usersService = new UserServiceImpl();
	private static AccountService accountService = new AccountServiceImpl();
	private static BankTransactionService bankService = new BankTransactionServiceImpl();
	private static CustomerService customerService = new CustomerServiceImpl();
	private static String action = null;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forward = "";
		action = request.getParameter("action");
		PrintWriter out = response.getWriter();
		if (action.equalsIgnoreCase("create")) {
			String name = request.getParameter("name");
			LocalDate dob = LocalDate.parse(request.getParameter("dob"));
			String gender = request.getParameter("gender");
			String mobile = request.getParameter("mobile");
			String email = request.getParameter("email");
			String aadhaar = request.getParameter("aadhaar");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String address = request.getParameter("address");

			Customer customer = new Customer(name, dob, gender, mobile, email, aadhaar, address);
			User user = new User(username, password);
			int rowsInserted = customerService.createAccountAndCust(customer, user);
			if (rowsInserted != 0) {
				response.setContentType("text/html");
				int accNo = accountService.getAccountNoByCustId(customerService.getCustomerIdByAadhaar(aadhaar));
				request.setAttribute("customerName", name);
				request.setAttribute("accountNumber", accNo);
				RequestDispatcher rd = request.getRequestDispatcher("create-account.jsp");
				rd.forward(request, response);
			}
		}

		if (action.equalsIgnoreCase("deposit")) {
			response.setContentType("text/html");
			String accountNo = request.getParameter("accountNumber");
			String amount = request.getParameter("depositAmount");
			Account account = new Account();
			account.setAccountNo(Integer.parseInt(accountNo));
			if (accountService.validateAccountNo(account)) {
				int rows = bankService.deposit(account, Integer.parseInt(amount));
				String balance = account.getBalance().toString();
				if (rows != 0) {
					HttpSession session = request.getSession();
					request.setAttribute("customerName", "Customer Name: "+
					customerService.getCustomerDetailsByAccountNumber(Integer.parseInt(accountNo)).getCustName());
					request.setAttribute("creditedAccount", "Account Number: "+accountNo);
					request.setAttribute("creditedAmount", "Credited Amount: "+amount);
					request.setAttribute("closingbal", "Closing Balance: "+balance);
					RequestDispatcher rd = request.getRequestDispatcher("deposit.jsp");
					rd.forward(request, response);
				}
			} else {
				out.print("<h2>Account Number with " + accountNo + " is not existed" + "</h2>");

			}

		}

		if (action.equalsIgnoreCase("withdraw")) {
			String accountNo = request.getParameter("accountNumber");
			String amount = request.getParameter("withdrawAmount");
			Account account = new Account();
			account.setAccountNo(Integer.parseInt(accountNo));
			if (accountService.validateAccountNo(account)) {
				int rows = bankService.withdraw(account, Integer.parseInt(amount));
				String balance = account.getBalance().toString();
				if (rows != 0) {
					HttpSession session = request.getSession();
					request.setAttribute("customerName", "Customer Name: "+
					customerService.getCustomerDetailsByAccountNumber(Integer.parseInt(accountNo)).getCustName());
					request.setAttribute("account", "Account Number: "+accountNo);
					request.setAttribute("amountWithdrawn", "Amount Withdrwan: "+amount);
					request.setAttribute("closingbal", "Closing Balance: "+balance);
					RequestDispatcher rd = request.getRequestDispatcher("withdraw.jsp");
					rd.forward(request, response);
				} else {
					out.print("<h2>Insufficient funds!!!</h2>");
				}

			} else {
				out.print("<h2>Account Number with " + accountNo + " is not existed" + "</h2>");
			}
		}

		if (action.equalsIgnoreCase("transfer")) {
			String senderAccountNo = request.getParameter("senderAccountNo");
			String receiverAccountNo = request.getParameter("receiverAccountNo");
			String amount = request.getParameter("transferAmount");
			Account sender = new Account();
			sender.setAccountNo(Integer.parseInt(senderAccountNo));
			//System.out.println(sender.getAccountNo());
			Account receiver = new Account();
			receiver.setAccountNo(Integer.parseInt(receiverAccountNo));
			//System.out.println(receiver.getAccountNo());
			if (accountService.validateAccountNo(sender)) {
				if (accountService.validateAccountNo(receiver)) {
					boolean transfer = bankService.transferAmount(sender, receiver, Integer.parseInt(amount));
					if (transfer) {
						HttpSession session = request.getSession();
						Customer from = customerService.getCustomerDetailsByAccountNumber(Integer.parseInt(senderAccountNo));
						request.setAttribute("senderDetails", "Sender Name: "+
								from.getCustName()+", Account Number: "+senderAccountNo
								+", Transferred Amount:"+amount +", Closing Balance: "+accountService.getBalanceByAccountNo(Integer.parseInt(senderAccountNo)));
						
						Customer To = customerService.getCustomerDetailsByAccountNumber(Integer.parseInt(receiverAccountNo));
						request.setAttribute("receiverDetails", "Recipient Name: "+
								To.getCustName()+", Account Number: "+receiverAccountNo
								+", Closing Balance: "+accountService.getBalanceByAccountNo(Integer.parseInt(receiverAccountNo)));
						RequestDispatcher rd = request.getRequestDispatcher("transfer.jsp");
						rd.forward(request, response);
						
					}else {
						out.print("<h2>Transaction failed!!!</h2>");
					}
				}else {
					out.print("<h2>Receiver Account Number with " + receiverAccountNo + " is not existed" + "</h2>");
				}

			} else {
				out.print("<h2>Sender Account Number with " + senderAccountNo + " is not existed" + "</h2>");
			}
		}
		
		if (action.equalsIgnoreCase("checkBalance")) {
			response.setContentType("text/html");
			String accountNo = request.getParameter("accountNumber");
			Account account = new Account();
			account.setAccountNo(Integer.parseInt(accountNo));
			if (accountService.validateAccountNo(account)) {
				double balance = accountService.getAmount(account);
					HttpSession session = request.getSession();
					request.setAttribute("closingbal", "Closing Balance: "+balance);
					RequestDispatcher rd = request.getRequestDispatcher("check-balance.jsp");
					rd.forward(request, response);
				}
			} 
		
		if(action.equalsIgnoreCase("transactionHistory")) {
			Integer accountNo = Integer.valueOf(request.getParameter("account-number"));
			List<BankTransaction> transactionHistory = bankService.getTransactionHistoryByAccountNo(accountNo);
	         request.setAttribute("transList",transactionHistory);
	         response.setContentType("text/html");
			 RequestDispatcher rd = request.getRequestDispatcher("transactionHistoryDisplay.jsp");
			 rd.forward(request, response);
			
			
		}
	}
}
