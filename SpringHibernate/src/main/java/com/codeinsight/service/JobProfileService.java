package com.codeinsight.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import com.codeinsight.bean.UiJobProfile;
import com.codeinsight.dao.JobProfileRepository;
import com.codeinsight.entity.JobProfile;

@Service
public class JobProfileService {
 
	@Autowired
	private JobProfileRepository jobProfileRepository;
	
	public @ResponseBody List<UiJobProfile> getAllJobProfiles() {
		Iterable<JobProfile> jobProfileList = jobProfileRepository.findAll();
		List<UiJobProfile> jobProfileeBeanList = new ArrayList<>();
		
		for (JobProfile jobProfileEntity : jobProfileList) {
			UiJobProfile jobProfileeBean = setUiClassAttributes(jobProfileEntity);
			jobProfileeBeanList.add(jobProfileeBean);
		}
		
		return jobProfileeBeanList;
	}
	
	public UiJobProfile setUiClassAttributes(JobProfile jobProfileEntity) {
		UiJobProfile jobProfileeBean = new UiJobProfile();
		
		jobProfileeBean.setId(jobProfileEntity.getId());
		jobProfileeBean.setJobProfileName(jobProfileEntity.getJobProfileName());
		
		return jobProfileeBean;
	}
}
