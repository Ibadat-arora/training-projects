package com.codeinsight.dao;

import org.springframework.data.repository.CrudRepository;

import com.codeinsight.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
	UserEntity findByUsername(String username);
}
