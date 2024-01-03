package com.zm.bankapp.dao;

import java.util.List;

import com.zm.bankapp.dto.Account;
import com.zm.bankapp.dto.BankTransaction;
import com.zm.bankapp.dto.Customer;
import com.zm.bankapp.dto.User;

public interface AccountDAO {
	
	int getAccountNoByCustomerId(Integer id);
	int createAccount(Customer customer);
	boolean validateAccountNo(Account account);
	double getAmount(Account account);
	double getBalanceByAccountNo(Integer accountNo);
}
