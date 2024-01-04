package com.zm.bankapp.dao;

import com.zm.bankapp.dto.Admin;

public interface AdminDAO {
	
	Admin getAdminDetailsByUserName(String userName);
}
