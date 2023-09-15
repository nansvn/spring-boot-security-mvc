package com.springproject.securitymvc.dao;

import com.springproject.securitymvc.entity.User;

public interface UserDao {
    User findByUserName(String userName);
}
