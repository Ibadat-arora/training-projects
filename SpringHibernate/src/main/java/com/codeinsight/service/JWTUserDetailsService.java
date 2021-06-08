package com.codeinsight.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codeinsight.bean.UiUser;
import com.codeinsight.dao.UserRepository;
import com.codeinsight.entity.UserEntity;

@Service

public class JWTUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	public UserEntity save(UiUser userBean) {
		UserEntity userEntity = new UserEntity() ;
		userEntity.setUsername(userBean.getUsername());
		userEntity.setPassword(bcryptEncoder.encode(userBean.getPassword()));
		return userRepository.save(userEntity);
	}
}
