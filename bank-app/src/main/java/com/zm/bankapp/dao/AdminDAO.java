package com.zm.bankapp.dao;

import com.zm.bankapp.dto.User;

public interface AdminDAO {
	User getAdminDetailsByUserName(String userName);
}
