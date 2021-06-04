package com.codeinsight.bean;

import java.util.Date;

import com.codeinsight.entity.JobProfile;

public class UiEmployee {
	private Integer id;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private Date dateOfJoining;
	private JobProfile jobProfile;

	public UiEmployee() {
		super();
	}

	public UiEmployee(Integer id, String firstName, String lastName, String address, String email, Date dateOfJoining,
			JobProfile jobProfile) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.dateOfJoining = dateOfJoining;
		this.jobProfile = jobProfile;
	}

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

	public JobProfile getJobProfile() {
		return jobProfile;
	}

	public void setJobProfile(JobProfile jobProfile) {
		this.jobProfile = jobProfile;
	}
}