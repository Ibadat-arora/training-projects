package com.codeinsight.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JobProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id ;
	private String jobProfileName = "";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJobProfileName() {
		return jobProfileName;
	}

	public void setJobProfileName(String jobProfileName) {
		this.jobProfileName = jobProfileName;
	}
}
