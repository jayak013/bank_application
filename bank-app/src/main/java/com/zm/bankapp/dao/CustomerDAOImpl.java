package com.zm.bankapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		int id = 0;
		String query = "select cust_id from customer where aadhaar_no=?";
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, aadhaar);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) id=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public Customer getCustomerDetailsByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomerByAccountNumber(Integer accountNumber) {
		Customer c = null;
		String query = "select * from customer where cust_id=(select cust_id from account where account_no=?)";
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, accountNumber);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				int custId = rs.getInt(1);
				String custName = rs.getString(2);
				String gender = rs.getString(3);
				String mobile = rs.getString(4);
				String email = rs.getString(5);
				String aadhaar = rs.getString(6);
				String address = rs.getString(7);
				Date dob = rs.getDate(8);
				c = new Customer(custId, custName, dob.toLocalDate(), gender, mobile, email, aadhaar, address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public Customer getCustomerByUserName(String userName) {
		Customer c = null;
		String query = "select * from customer where cust_id=(select cust_id from user where user_id=?)";
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, userName.toUpperCase());
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				int custId = rs.getInt(1);
				String custName = rs.getString(2);
				String gender = rs.getString(3);
				String mobile = rs.getString(4);
				String email = rs.getString(5);
				String aadhaar = rs.getString(6);
				String address = rs.getString(7);
				Date dob = rs.getDate(8);
				c = new Customer(custId, custName, dob.toLocalDate(), gender, mobile, email, aadhaar, address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

}
