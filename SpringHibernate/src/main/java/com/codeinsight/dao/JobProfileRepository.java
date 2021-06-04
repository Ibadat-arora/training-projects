package com.codeinsight.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.codeinsight.entity.JobProfile;

@Repository
public interface JobProfileRepository extends CrudRepository<JobProfile, Integer> {

}
