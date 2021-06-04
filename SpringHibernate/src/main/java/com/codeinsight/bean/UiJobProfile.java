package com.codeinsight.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class UiJobProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ManyToOne
	private Integer id = 0;
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
