package com.codeinsight.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.codeinsight.bean.LoginRequest;

@RestController
public class LoginController {
	@PostMapping(value = "/login")
	public Boolean authenticate(@RequestBody LoginRequest loginRequest) {
		Boolean isLoginCredentialsCorrect = false;
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();

		if (email.equalsIgnoreCase("ibadat@thecodeinsight.com") && password.equals("thecodeinsight")) {
			System.out.println(loginRequest);
			isLoginCredentialsCorrect = true;
		}

		return isLoginCredentialsCorrect;
	}
}
