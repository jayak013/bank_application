package com.zm.bankapp.dao;

import com.zm.bankapp.dto.Account;

public class BankTransactionDAOImpl implements BankTransactionDAO{

	@Override
	public int deposit(Account account, Integer amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int withdraw(Account account, Integer amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveTransaction(Account account, Integer amount, String txType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean transferAmount(Account sender, Account receiver, Integer amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveTxFlow(Integer senderTxId, Integer receiverTxId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
