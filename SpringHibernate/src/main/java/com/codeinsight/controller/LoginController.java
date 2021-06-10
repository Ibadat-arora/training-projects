package com.codeinsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.codeinsight.bean.LoginRequest;
import com.codeinsight.bean.LoginResponse;
import com.codeinsight.bean.UiUser;
import com.codeinsight.security.JwtTokenUtility;
import com.codeinsight.service.AppUserDetailsService;

@RestController
@CrossOrigin
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtility jwtTokenUtil;

	@Autowired
	private AppUserDetailsService jwtUserDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public @ResponseBody LoginResponse createAuthenticationToken(@RequestBody LoginRequest jwtRequest) throws Exception {

		authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

		final UserDetails userDetails = jwtUserDetailsService
				.loadUserByUsername(jwtRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return new LoginResponse(token);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> saveUser(@RequestBody UiUser userBean) throws Exception {
		return ResponseEntity.ok(jwtUserDetailsService.save(userBean));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
