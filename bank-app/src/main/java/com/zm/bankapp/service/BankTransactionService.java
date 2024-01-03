package com.zm.bankapp.service;

import com.zm.bankapp.dto.Account;

public interface BankTransactionService {
	int deposit(Account account, Integer amount);
	int withdraw(Account account, Integer amount);
	int saveTransaction(Account account, Integer amount, String txType);
	boolean transferAmount(Account sender, Account receiver, Integer amount);
	int saveTxFlow(Integer senderTxId, Integer receiverTxId);
}
