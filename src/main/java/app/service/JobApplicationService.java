package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.JobApplication;
import app.repository.JobApplicationRepository;

@Service
public class JobApplicationService {
	
	@Autowired
	private JobApplicationRepository jobApplicationRepository;
	
	public JobApplication addJobApplication(JobApplication jobApplication) {
		jobApplication = jobApplicationRepository.save(jobApplication);
		return jobApplication;
	}
	
	

}
