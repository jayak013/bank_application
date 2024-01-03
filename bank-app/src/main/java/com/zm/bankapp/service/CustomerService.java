package com.zm.bankapp.service;

import com.zm.bankapp.dto.Customer;
import com.zm.bankapp.dto.User;

public interface CustomerService {
	int getCustomerIdByAadhaar(String Aadhaar);
	Customer getCustomerDetailsByAccountNumber(Integer accountNumber);
	int createAccountAndCust(Customer customer, User user);
	
}
