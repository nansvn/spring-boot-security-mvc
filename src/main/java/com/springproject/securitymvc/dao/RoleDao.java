package com.springproject.securitymvc.dao;

import com.springproject.securitymvc.entity.Role;

public interface RoleDao {
	Role findRoleByName(String theRoleName);
	
}
