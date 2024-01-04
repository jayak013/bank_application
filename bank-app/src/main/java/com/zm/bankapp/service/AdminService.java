package com.zm.bankapp.service;

import com.zm.bankapp.dto.Admin;

public interface AdminService {
	Admin getAdminDetailsByUserName(String userName);
}
