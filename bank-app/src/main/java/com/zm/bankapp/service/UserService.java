package com.zm.bankapp.service;

import com.zm.bankapp.dto.User;

public interface UserService {
	User validateUserNameAndPassword(User user);
	User getUserDetailsById(Integer id);
}
