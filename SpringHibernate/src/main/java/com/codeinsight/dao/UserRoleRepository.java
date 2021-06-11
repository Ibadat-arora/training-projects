package com.codeinsight.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.codeinsight.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{

}
