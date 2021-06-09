package com.codeinsight.bean;

import java.io.Serializable;

public class LoginResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwtToken;

	public LoginResponse(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getToken() {
		return this.jwtToken;
	}
}