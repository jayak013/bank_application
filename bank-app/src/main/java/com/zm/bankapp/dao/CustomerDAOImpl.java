package com.zm.bankapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zm.bankapp.config.ConnectionFactory;
import com.zm.bankapp.dto.Customer;

public class CustomerDAOImpl implements CustomerDAO{
	
	public static Connection con = null;
	public static PreparedStatement pst = null;

	public CustomerDAOImpl() {
		try {
			con = ConnectionFactory.establishConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int saveCustomer(Customer customer) {
		int rows = 0;
		String query = "INSERT INTO bank_app.customer(" + "cust_name, gender, mobile, email, aadhaar_no, address, dob)"
				+ "	VALUES ( ?, ?, ?, ?, ?, ?, ?);";
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, customer.getCustName().toUpperCase());
			pst.setString(2, customer.getGender().toUpperCase());
			pst.setString(3, customer.getMobile());
			pst.setString(4, customer.getEmail().toUpperCase());
			pst.setString(5, customer.getAadhaarNo());
			pst.setString(6, customer.getAddress().toUpperCase());
			pst.setDate(7, Date.valueOf(customer.getDob()));

			rows = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int getCustomerIdByAadhaar(String aadhaar) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Customer getCustomerDetailsByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomerByAccountNumber(Integer accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
