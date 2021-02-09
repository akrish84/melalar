package app.service;

import java.util.List;
import java.util.Optional;

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
	
	public Optional<JobApplicationRank> getJobApplicationRankByJobApplicationId(long jobApplicationId){
		return jobApplicationRankRepository.findByJobApplicationId(jobApplicationId);
	}
	
	public Optional<List<JobApplicationRank>> getJobApplicationRankListByUserIdAndStatusId(long userId, long statusId) {
		return jobApplicationRankRepository.findByUserIdAndStatusId(userId, statusId);
	}
	
	public void addJobApplicationRankList(List<JobApplicationRank> jobApplicationRankList) {
		jobApplicationRankRepository.saveAll(jobApplicationRankList);
	}
	
	public void deleteAll(List<JobApplicationRank> jobApplicationRankList) {
		jobApplicationRankRepository.deleteAll(jobApplicationRankList);
	}
	
	public void deleteJobApplicationRank(JobApplicationRank jobApplicationRank) {
		jobApplicationRankRepository.delete(jobApplicationRank);
	}
	
	public void deleteJobApplicationRankByJobApplicationId(long jobApplicationId) {
		jobApplicationRankRepository.deleteByJobApplicationId(jobApplicationId);
	}

}
