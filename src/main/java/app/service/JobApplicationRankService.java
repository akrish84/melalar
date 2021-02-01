package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.JobApplicationRank;
import app.repository.JobApplicationRankRepository;

@Service
public class JobApplicationRankService {
	
	@Autowired
	private JobApplicationRankRepository jobApplicationRankRepository;
	
	public void addJobApplicationRank(JobApplicationRank jobApplicationRank) {
		jobApplicationRankRepository.save(jobApplicationRank);
	}

	public List<JobApplicationRank> getJobApplicationsRank(List<Long> jobApplicationIds) {
		return jobApplicationRankRepository.findByJobApplicationIdIn(jobApplicationIds);
	}
	
}
