package com.zm.bankapp.service;

import com.zm.bankapp.dao.AdminDAO;
import com.zm.bankapp.dao.AdminDAOImpl;
import com.zm.bankapp.dto.Admin;

public class AdminServiceImpl implements AdminService{

	public static AdminDAO dao = null;

	public AdminServiceImpl() {
		dao = new AdminDAOImpl();
	}
	
	@Override
	public Admin getAdminDetailsByUserName(String userName) {
		return dao.getAdminDetailsByUserName(userName);
	}

}
