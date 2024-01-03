package com.zm.bankapp.service;

import java.util.List;

import com.zm.bankapp.dto.Account;
import com.zm.bankapp.dto.BankTransaction;

public interface AccountService {
	int getAccountNoByCustId(Integer id);
	boolean validateAccountNo(Account account);
	double getAmount(Account account);
}
