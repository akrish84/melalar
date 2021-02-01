package app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		List<Long> userJobApplicationIds = new ArrayList<>();
		List<JobApplicationRank> jobApplicationsRank;
		List<JobApplication> jobApplicationsByUserIdAndStatusId = jobApplicationRepository.findByUserIdAndStatusId(jobApplication.getUserId(), jobApplication.getStatusId());

		if(jobApplicationsByUserIdAndStatusId.size() > 0) {
			for (JobApplication filteredJobApplication : jobApplicationsByUserIdAndStatusId) {
				userJobApplicationIds.add(filteredJobApplication.getId());
			}
	
			jobApplicationsRank = jobApplicationRankService.getJobApplicationsRank(userJobApplicationIds);
	
			for (JobApplicationRank jobApplicationRank : jobApplicationsRank) {
				maxRank = Math.max(maxRank, jobApplicationRank.getRank());
			}
		}

		currentJobApplicationRank = maxRank + 1;

		JobApplication addedJobApplication = jobApplicationRepository.save(jobApplication);

		JobApplicationRank jobApplicationRank = JobApplicationRank.builder()
				.jobApplicationId(addedJobApplication.getId()).rank(currentJobApplicationRank).build();

		jobApplicationRankService.addJobApplicationRank(jobApplicationRank);

		return addedJobApplication;
	}


}
