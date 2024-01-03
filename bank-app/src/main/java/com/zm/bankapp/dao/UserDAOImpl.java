package com.zm.bankapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zm.bankapp.config.ConnectionFactory;
import com.zm.bankapp.dto.User;

public class UserDAOImpl implements UserDAO{
	
	public static Connection con = null;
	public static PreparedStatement pst = null;

	public UserDAOImpl() {
		try {
			con = ConnectionFactory.establishConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUserType(User user) {
		String query = "select user_type from bank_app.user where user_id = ? and password = ?";
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, user.getUserId().toUpperCase());
			pst.setString(2, user.getPassword().toUpperCase());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				user.setUserType(rs.getString(1));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int saveUserCredentials(User user, Integer custId) {
		int rows = 0;
		String query = "INSERT INTO bank_app.user(user_id, password, user_type, cust_id) VALUES ( ?, ?, ?, ?)";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, user.getUserId().toUpperCase());
			pst.setString(2, user.getPassword().toUpperCase());
			pst.setString(3, "CUSTOMER");
			pst.setInt(4, custId);
			rows = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

}
