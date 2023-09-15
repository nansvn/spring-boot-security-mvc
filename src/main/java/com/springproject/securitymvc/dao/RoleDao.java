package com.springproject.securitymvc.dao;

import com.springproject.securitymvc.entity.Role;

public interface RoleDao {
	public Role findRoleByName(String theRoleName);
	
}
