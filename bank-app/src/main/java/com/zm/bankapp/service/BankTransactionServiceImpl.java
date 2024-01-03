package com.zm.bankapp.service;

import java.util.List;

import com.zm.bankapp.dao.BankTransactionDAO;
import com.zm.bankapp.dao.BankTransactionDAOImpl;
import com.zm.bankapp.dto.Account;
import com.zm.bankapp.dto.BankTransaction;

public class BankTransactionServiceImpl implements BankTransactionService{

	public static BankTransactionDAO dao = null;

	public BankTransactionServiceImpl() {
		dao = new BankTransactionDAOImpl();
	}
	
	@Override
	public int deposit(Account account, Integer amount) {
		return dao.deposit(account, amount);
	}

	@Override
	public int withdraw(Account account, Integer amount) {
		return dao.withdraw(account, amount);
	}

	@Override
	public int saveTransaction(Account account, Integer amount, String txType) {
		return dao.saveTransaction(account, amount, txType);
	}

	@Override
	public boolean transferAmount(Account sender, Account receiver, Integer amount) {
		return dao.transferAmount(sender, receiver, amount);
	}

	@Override
	public int saveTxFlow(Integer senderTxId, Integer receiverTxId) {
		return dao.saveTxFlow(senderTxId, receiverTxId);
	}
	
	@Override
	public List<BankTransaction> getTransactionHistoryByAccountNo(Integer accountNum) {
		// TODO Auto-generated method stub
		return dao.getTransactionHistory(accountNum);
	}

}
