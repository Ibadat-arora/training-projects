package com.codeinsight.bean;

import java.util.Date;

public class UiEmployee {
	private Integer id;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private Date dateOfJoining;
	private Integer jobProfileId;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Integer getJobProfileId() {
		return jobProfileId;
	}

	public void setJobProfileId(Integer jobProfileId) {
		this.jobProfileId = jobProfileId;
	}
}
