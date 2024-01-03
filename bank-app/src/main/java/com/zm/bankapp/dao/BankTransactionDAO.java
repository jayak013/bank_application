package com.zm.bankapp.dao;

import com.zm.bankapp.dto.Account;

public interface BankTransactionDAO {
	int deposit(Account account, Integer amount);
	int withdraw(Account account, Integer amount);
	int saveTransaction(Account account, Integer amount, String txType);
	boolean transferAmount(Account sender, Account receiver, Integer amount);
	int saveTxFlow(Integer senderTxId, Integer receiverTxId);
}
