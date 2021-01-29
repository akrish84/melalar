package app.service;

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

}
