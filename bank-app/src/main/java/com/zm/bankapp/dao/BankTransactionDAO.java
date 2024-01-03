package com.zm.bankapp.dao;

import java.util.List;

import com.zm.bankapp.dto.Account;
import com.zm.bankapp.dto.BankTransaction;

public interface BankTransactionDAO {
	int deposit(Account account, Integer amount);
	int withdraw(Account account, Integer amount);
	int saveTransaction(Account account, Integer amount, String txType);
	boolean transferAmount(Account sender, Account receiver, Integer amount);
	int saveTxFlow(Integer senderTxId, Integer receiverTxId);
	List<BankTransaction> getTransactionHistory(Integer accountNum);
}
