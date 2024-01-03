package com.zm.bankapp.service;

import com.zm.bankapp.dao.AccountDAO;
import com.zm.bankapp.dao.AccountDAOImpl;
import com.zm.bankapp.dto.Account;

public class AccountServiceImpl implements AccountService{
	public static AccountDAO dao = null;

	public AccountServiceImpl() {
		dao = new AccountDAOImpl();
	}
	
	@Override
	public int getAccountNoByCustId(Integer id) {
		return dao.getAccountNoByCustomerId(id);
	}

	@Override
	public boolean validateAccountNo(Account account) {
		return dao.validateAccountNo(account);
	}

	@Override
	public double getAmount(Account account) {
		return dao.getAmount(account);
	}

	@Override
	public double getBalanceByAccountNo(Integer accountNo) {
		return dao.getBalanceByAccountNo(accountNo);
	}


}
