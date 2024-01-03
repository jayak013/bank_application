package com.zm.bankapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.zm.bankapp.config.ConnectionFactory;
import com.zm.bankapp.dto.Account;
import com.zm.bankapp.dto.Customer;

public class AccountDAOImpl implements AccountDAO{

	public static Connection con = null;
	public static PreparedStatement pst = null;

	public AccountDAOImpl() {
		try {
			con = ConnectionFactory.establishConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public int getAccountNoByCustomerId(Integer id) {
		int accountNo = 0;
		String query = "select account_no from account where cust_id = ?";
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) accountNo=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountNo;
	}


	@Override
	public int createAccount(Customer customer) {
		int rows = 0;
		String query = "INSERT INTO account(created_on,balance, cust_id) VALUES ( ?, ?, ?)";
		try {
			pst = con.prepareStatement(query);
			pst.setDate(1, Date.valueOf(LocalDate.now()));
			pst.setInt(2, 0);
			pst.setInt(3, customer.getCustId());
			rows = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}



	@Override
	public boolean validateAccountNo(Account account) {
		String sql = "select account_no from bank_app.account where account_no = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, account.getAccountNo());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.err.println("Account Number is not Existed");
		}
		return false;
	}

	@Override
	public double getAmount(Account account) {
		double balance = 0;
		String select = "select balance from bank_app.account where account_no = " + account.getAccountNo();
		try {
			pst = con.prepareStatement(select);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				account.setBalance(rs.getDouble(1));
			}
			balance = account.getBalance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return balance;
	}
	
	@Override
	public double getBalanceByAccountNo(Integer accountNo) {
		String query = "select balance from bank_app.account where account_no = ?";
		double checkBalance = 0;
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, accountNo);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				checkBalance= rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkBalance;
	}

}
