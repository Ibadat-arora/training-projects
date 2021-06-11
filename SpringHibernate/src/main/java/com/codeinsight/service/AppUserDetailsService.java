package com.codeinsight.service;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codeinsight.bean.UiUser;
import com.codeinsight.dao.UserRepository;
import com.codeinsight.entity.UserEntity;

@Service

public class AppUserDetailsService implements UserDetailsService {

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
				getGrantedAutority(user));
	}
	
	private Collection<GrantedAuthority> getGrantedAutority(UserEntity user){
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		
		if(user.getUserRole().getRoleName().equalsIgnoreCase("Admin")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities ;
	}
	
	public UserEntity save(UiUser userBean) {
		UserEntity userEntity = new UserEntity() ;
		userEntity.setUsername(userBean.getUsername());
		userEntity.setPassword(bcryptEncoder.encode(userBean.getPassword()));
		return userRepository.save(userEntity);
	}
}
