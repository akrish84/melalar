package app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import app.model.JobApplication;

public interface JobApplicationRepository extends CrudRepository<JobApplication, Long> {
	
	public List<JobApplication> findByUserId(long userId);
	public List<JobApplication> findByUserIdAndStatusId(long userId, long statusId);

}
