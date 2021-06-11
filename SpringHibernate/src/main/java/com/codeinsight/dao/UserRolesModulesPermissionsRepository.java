package com.codeinsight.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeinsight.entity.UserRole;
import com.codeinsight.entity.UserRolesModulesPermissions;

public interface UserRolesModulesPermissionsRepository extends JpaRepository<UserRolesModulesPermissions, Integer>{
	Iterable<UserRolesModulesPermissions> findAllByUserRole(UserRole userRole);
}
