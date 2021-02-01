package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.model.JobApplication;
import app.requestresponsemodel.JobApplicationRequest;
import app.requestresponsemodel.JobApplicationResponse;
import app.service.JobApplicationService;

@RestController
public class JobApplicationController {

	@Autowired
	private JobApplicationService jobApplicationService;

	@PostMapping("/jobapplication")
	public ResponseEntity<JobApplicationResponse> addJob(@RequestBody JobApplicationRequest jobApplicationRequest) {
		try {
			Validator validator = new Validator();
			validator.addNullCheck(jobApplicationRequest.getUserId(), "User Id cannot be null")
					.addEmptyCheck(jobApplicationRequest.getCompanyName(), "Company Name cannot be empty")
					.addDefaultValueCheck(jobApplicationRequest.getStatusId(), "Status Id Cannot be 0")
					.addDefaultValueCheck(jobApplicationRequest.getDateApplied(), "Date Applied can not be empty")
					.addLoggedInUserCheck(jobApplicationRequest.getUserId(),
							"Unauthorized action, user should be logged in");

			if (!validator.isValid()) {
				return new ResponseEntity<JobApplicationResponse>(
						new JobApplicationResponse(validator.getErrorMessage()), HttpStatus.BAD_REQUEST);
			}

			JobApplication jobApplication = JobApplication.builder()
					.companyName(jobApplicationRequest.getCompanyName())
					.positionApplied(jobApplicationRequest.getPositionApplied())
					.apsName(jobApplicationRequest.getApsName())
					.notes(jobApplicationRequest.getNotes())
					.jobDescription(jobApplicationRequest.getJobDescription())
					.dateApplied(jobApplicationRequest.getDateApplied())
					.userId(jobApplicationRequest.getUserId())
					.statusId(jobApplicationRequest.getStatusId())
					.build();

			jobApplication = jobApplicationService.addJobApplication(jobApplication);
			
			String successResponseMessage = "Job Application added successfully for user: " + jobApplicationRequest.getUserId();
			JobApplicationResponse response = new JobApplicationResponse(successResponseMessage);
			
			System.out.println(successResponseMessage);
			
			return new ResponseEntity<JobApplicationResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = "Failed to add job application";
			if (jobApplicationRequest != null) {
				errorMessage = "Failed to add job application for: " + jobApplicationRequest.getUserId();
			}
			System.out.println(errorMessage);
			
			JobApplicationResponse response = new JobApplicationResponse(errorMessage);
			return new ResponseEntity<JobApplicationResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
