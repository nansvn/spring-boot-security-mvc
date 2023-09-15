package com.springproject.securitymvc.service;

import com.springproject.securitymvc.entity.User;
import com.springproject.securitymvc.user.RegisterUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	User findByUserName(String userName);
	void save(RegisterUser registerUser);
}
