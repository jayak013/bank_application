package com.zm.bankapp.dao;

import com.zm.bankapp.dto.User;

public interface UserDAO {
	User getUserType(User user);
	int saveUserCredentials(User user, Integer custId);
}
