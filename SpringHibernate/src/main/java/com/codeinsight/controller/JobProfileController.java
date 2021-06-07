package com.codeinsight.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.codeinsight.service.JobProfileService;
import com.codeinsight.bean.UiJobProfile;

@RestController
public class JobProfileController {
	
	@Autowired
	private JobProfileService jobProfileService;

	@GetMapping("/jobProfile")
	public @ResponseBody List<UiJobProfile> getAllJobProfiles() {
		List<UiJobProfile> jobProfileBeanList = jobProfileService.getAllJobProfiles();

		return jobProfileBeanList;
	}
}
