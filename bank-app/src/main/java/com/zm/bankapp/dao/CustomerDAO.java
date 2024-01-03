package com.zm.bankapp.dao;

import com.zm.bankapp.dto.Customer;
import com.zm.bankapp.dto.User;

public interface CustomerDAO {
	int saveCustomer(Customer customer);
	int getCustomerIdByAadhaar(String aadhaar);
	Customer getCustomerDetailsByUserName(String userName);
	Customer getCustomerByAccountNumber(Integer accountNumber);
}
