package app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import app.model.JobApplication;
import app.model.JobApplicationRank;

public class JobApplicationRankRepositoryImpl implements JobApplicationRankRepositoryCustom {
	
	@Autowired 
	JobApplicationRankRepository jobApplicationRankRepository;
	
	@Autowired
	JobApplicationRepository jobApplicationRepository;

	@Override
	public Optional<List<JobApplicationRank>> findByUserIdAndStatusId(long userId, long statusId) {
		
		Optional<List<JobApplicationRank>> jobApplicationRankList = Optional.empty();
		List<Long> userJobApplicationIds;
		
		List<JobApplication> jobApplicationsByUserIdAndStatusId = jobApplicationRepository.findByUserIdAndStatusId(userId, statusId);
		
		if(jobApplicationsByUserIdAndStatusId.size() > 0) {
			jobApplicationRankList = Optional.of(new ArrayList<>());
			userJobApplicationIds = new ArrayList<>();
			for (JobApplication filteredJobApplication : jobApplicationsByUserIdAndStatusId) {
				userJobApplicationIds.add(filteredJobApplication.getId());
			}
			jobApplicationRankList = jobApplicationRankRepository.findByJobApplicationIdIn(userJobApplicationIds);
		}
		return jobApplicationRankList;
		
	}
}
