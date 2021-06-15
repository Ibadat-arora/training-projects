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

import com.codeinsight.bean.UiEmployee;
import com.codeinsight.bean.UiUser;
import com.codeinsight.dao.UserRepository;
import com.codeinsight.dao.UserRolesModulesPermissionsRepository;
import com.codeinsight.entity.Employee;
import com.codeinsight.entity.UserEntity;
import com.codeinsight.entity.UserRolesModulesPermissions;

import antlr.collections.List;

@Service

public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private UserRolesModulesPermissionsRepository userRolesModulesPermissionsRepository ;
	
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
		Iterable<UserRolesModulesPermissions> userRolesModulesPermissionsList = userRolesModulesPermissionsRepository
				.findAllByUserRoleId(user.getUserRole().getId());
		
		for (UserRolesModulesPermissions userRolesModulesPermissionsEntity : userRolesModulesPermissionsList) {
			String moduleName = userRolesModulesPermissionsEntity.getModule().getModuleName() ;
			String permissionName = userRolesModulesPermissionsEntity.getPermission().getPermissionName();
			
			authorities.add(new SimpleGrantedAuthority(moduleName + '_' + permissionName));
		}
		
		return authorities ;
	}
	
	public UserEntity save(UiUser userBean) {
		UserEntity userEntity = new UserEntity() ;
		userEntity.setUsername(userBean.getUsername());
		userEntity.setPassword(bcryptEncoder.encode(userBean.getPassword()));
		return userRepository.save(userEntity);
	}
}
