package app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.exceptions.ObjectNotFoundException;
import app.model.JobApplication;
import app.model.JobApplicationRank;
import app.repository.JobApplicationRepository;

@Service
public class JobApplicationService {

	@Autowired
	private JobApplicationRepository jobApplicationRepository;

	@Autowired
	private JobApplicationRankService jobApplicationRankService;

	/**
	 * Saves job application in the DataSource. 
	 * Saves JobApplicationRank in the DataSource.
	 * Job Application Rank is calculated as current max Job Application rank with requested statusId + 1
	 * 
	 * @param jobApplication
	 * @return Saved JobApplication instance
	 */
	@Transactional
	public JobApplication addJobApplication(JobApplication jobApplication) {

		int maxRank = 0;
		int currentJobApplicationRank = 0;
		
		

		Optional<List<JobApplicationRank>> jobApplicationRankList = jobApplicationRankService.getJobApplicationRankListByUserIdAndStatusId(jobApplication.getUserId(), jobApplication.getStatusId());

		for (JobApplicationRank jobApplicationRank : jobApplicationRankList.get()) {
			maxRank = Math.max(maxRank, jobApplicationRank.getRank());
		}

		currentJobApplicationRank = maxRank + 1;
		
		JobApplication addedJobApplication = jobApplicationRepository.save(jobApplication);

		JobApplicationRank jobApplicationRank = JobApplicationRank.builder()
				.jobApplicationId(addedJobApplication.getId()).rank(currentJobApplicationRank).build();

		jobApplicationRankService.addJobApplicationRank(jobApplicationRank);

		return addedJobApplication;
	}
	
	public void updateJobApplication(JobApplication jobApplication) {
		jobApplicationRepository.save(jobApplication);
	}

	public Optional<JobApplication> getJobApplication(long jobApplicationId){
		return jobApplicationRepository.findById(jobApplicationId);
	}
}
