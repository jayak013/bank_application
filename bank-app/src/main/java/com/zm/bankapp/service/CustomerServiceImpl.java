package com.zm.bankapp.service;

import com.zm.bankapp.dao.AccountDAO;
import com.zm.bankapp.dao.AccountDAOImpl;
import com.zm.bankapp.dao.CustomerDAO;
import com.zm.bankapp.dao.CustomerDAOImpl;
import com.zm.bankapp.dao.UserDAO;
import com.zm.bankapp.dao.UserDAOImpl;
import com.zm.bankapp.dto.Customer;
import com.zm.bankapp.dto.User;

public class CustomerServiceImpl implements CustomerService{

	
	public static CustomerDAO customerDao = null;
	public static UserDAO userDao = null;
	public static AccountDAO accountDao = null;

	public CustomerServiceImpl() {
		customerDao = new CustomerDAOImpl();
		userDao = new UserDAOImpl();
		accountDao = new AccountDAOImpl();
	}
	
	@Override
	public int getCustomerIdByAadhaar(String Aadhaar) {
		return customerDao.getCustomerIdByAadhaar(Aadhaar);
	}

	@Override
	public Customer getCustomerDetailsByAccountNumber(Integer accountNumber) {
		return customerDao.getCustomerByAccountNumber(accountNumber);
	}

	@Override
	public int createAccountAndCust(Customer customer, User user) {
		int row1 = customerDao.saveCustomer(customer);
		// int custId = dao.getCustomerIdByAadhaar(customer.getCustName());
		int custId = customerDao.getCustomerIdByAadhaar(customer.getAadhaarNo());
		customer.setCustId(custId);
		int row2 = accountDao.createAccount(customer);
		int row3 = userDao.saveUserCredentials(user, customer.getCustId());
		return row1 + row2;
	}

	@Override
	public Customer getCustomerByUserName(String userName) {
		return customerDao.getCustomerByUserName(userName);
	}



}
