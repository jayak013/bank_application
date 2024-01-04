package com.zm.bankapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zm.bankapp.config.ConnectionFactory;
import com.zm.bankapp.dto.Admin;

public class AdminDAOImpl implements AdminDAO{
	public static Connection con = null;
	public static PreparedStatement pst = null;

	public AdminDAOImpl() {
		try {
			con = ConnectionFactory.establishConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Admin getAdminDetailsByUserName(String userName) {
		Admin a = null;
		String query = "select * from admin where admin_id=(select admin_id from bank_app.user where user_id=?)";
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, userName.toUpperCase());
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				int adminId = rs.getInt(1);
				String adminName = rs.getString(2);
				String email = rs.getString(3);
				a = new Admin(adminId,adminName,email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

}
