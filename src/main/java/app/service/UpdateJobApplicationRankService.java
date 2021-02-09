package app.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.exceptions.BadRequestException;
import app.exceptions.ObjectNotFoundException;
import app.model.JobApplication;
import app.model.JobApplicationRank;
import app.repository.JobApplicationRepository;

@Service
public class UpdateJobApplicationRankService {

	@Autowired
	private JobApplicationService jobApplicationService;

	@Autowired
	private JobApplicationRankService jobApplicationRankService;
	

	@Transactional
	public void updateJobApplicationRank(JobApplication jobApplication, List<JobApplicationRank> jobApplicationRankList) throws ObjectNotFoundException {
		
		Optional<JobApplication> existingJobApplicationInstance;
		Optional<List<JobApplicationRank>> existingJobApplicationRankList;
		
		existingJobApplicationInstance = jobApplicationService.getJobApplication(jobApplication.getId());
		
		if(!existingJobApplicationInstance.isPresent()) {
			throw new ObjectNotFoundException("Job Application Job_App_ID: " + jobApplication.getId() + " does not exist");
		}
		
		existingJobApplicationRankList = jobApplicationRankService.getJobApplicationRankListByUserIdAndStatusId(jobApplication.getUserId(), jobApplication.getStatusId());
		
		validateJobApplicationRank(jobApplication, jobApplicationRankList, existingJobApplicationRankList);
		
		if(jobApplication.getStatusId() != existingJobApplicationInstance.get().getStatusId()) {
			
			existingJobApplicationInstance.get().setStatusId(jobApplication.getStatusId());
			
			jobApplicationService.updateJobApplication(existingJobApplicationInstance.get());
			jobApplicationRankService.deleteJobApplicationRankByJobApplicationId(existingJobApplicationInstance.get().getId());
		}
		
		
		jobApplicationRankService.deleteAll(existingJobApplicationRankList.get());
		jobApplicationRankService.addJobApplicationRankList(jobApplicationRankList);
	}
	
	private void validateJobApplicationRank(JobApplication jobApplication, List<JobApplicationRank> jobApplicationRankList,
			Optional<List<JobApplicationRank>> existingJobApplicationRankList) throws BadRequestException {
		
		Set<Long> existingJobApplicationIdsSet;
		int sizeDifference = 0;
		
		if(existingJobApplicationRankList.isPresent()) {
			sizeDifference = jobApplicationRankList.size() - existingJobApplicationRankList.get().size();
			
			if(sizeDifference != 0 && sizeDifference != 1) {
				throw new BadRequestException("Number of applications to be updated: " + jobApplicationRankList.size() + " does not match number of existing applications: + "
						+ existingJobApplicationRankList.get().size() + " for user: " + jobApplication.getUserId() + " for statusId: " + jobApplication.getStatusId());
			}
			
			existingJobApplicationIdsSet = new HashSet<>();
			
			for(JobApplicationRank jobApplicationRank : existingJobApplicationRankList.get()) {
				existingJobApplicationIdsSet.add(jobApplicationRank.getJobApplicationId());
			}
			
			if(!existingJobApplicationIdsSet.contains(jobApplication.getId())) {
				throw new BadRequestException("Job Application Job_App_ID: " + jobApplication.getId()
						+ " to be added is not present in updated list of job application ranks.");
			}
			
			for(JobApplicationRank jobApplicationRank : jobApplicationRankList) {
				if(jobApplicationRank.getJobApplicationId() != jobApplication.getId() && !existingJobApplicationIdsSet.contains(jobApplicationRank.getJobApplicationId())) {
					throw new BadRequestException("Trying to update multiple job application ranks at the same time.");
				}
			}
			
		} else {
			
			if(jobApplicationRankList.size() > 1) {
				throw new BadRequestException("Number of applications to be updated: " + jobApplicationRankList.size() + " does not match number of existing applications: + "
				+ existingJobApplicationRankList.get().size() + " for user: " + jobApplication.getUserId() + " for statusId: " + jobApplication.getStatusId());
			}
		}
	}
}
