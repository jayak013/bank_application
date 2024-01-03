package com.zm.bankapp.service;

import com.zm.bankapp.dao.UserDAO;
import com.zm.bankapp.dao.UserDAOImpl;
import com.zm.bankapp.dto.User;

public class UserServiceImpl implements UserService{

	public static UserDAO dao = null;

	public UserServiceImpl() {
		dao = new UserDAOImpl();
	}
	@Override
	public User validateUserNameAndPassword(User user) {
		return dao.getUserType(user);
	}

	@Override
	public User getUserDetailsById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}



}
