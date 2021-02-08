package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.exceptions.BadRequestException;
import app.exceptions.UnauthorizedException;
import app.model.JobApplication;
import app.model.JobApplicationRank;
import app.model.authentication.UserDetail;
import app.model.requestresponse.JobApplicationAddRequest;
import app.model.requestresponse.JobApplicationAddResponse;
import app.model.requestresponse.JobApplicationUpdateRequest;
import app.model.requestresponse.JobApplicationUpdateResponse;
import app.service.JobApplicationService;

@RestController
public class JobApplicationController {

	@Autowired
	private JobApplicationService jobApplicationService;

	@PostMapping("/jobApplication")
	public ResponseEntity<JobApplicationAddResponse> addJob(@RequestBody JobApplicationAddRequest jobApplicationRequest,
			@AuthenticationPrincipal UserDetail userDetail) {
		try {
			Validator.defaultValueCheck(jobApplicationRequest.getUserId(), "User Id");
			Validator.defaultValueCheck(jobApplicationRequest.getStatusId(), "Status Id");
			Validator.defaultValueCheck(jobApplicationRequest.getDateApplied(), "Date Applied Id");
			Validator.emptyValueCheck(jobApplicationRequest.getCompanyName(), "Company Name");
			Validator.loggedInUserCheck(jobApplicationRequest.getUserId(), userDetail);

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
			
			String successResponseMessage = "Job Application added successfully for user: "
					+ jobApplicationRequest.getUserId() + " application name: "
					+ jobApplicationRequest.getCompanyName();
			JobApplicationAddResponse response = new JobApplicationAddResponse(successResponseMessage);
			
			System.out.println(successResponseMessage);
			
			return new ResponseEntity<JobApplicationAddResponse>(response, HttpStatus.OK);
		} catch(BadRequestException be) {
			// add loggedin user id to message
			String errorMessage = "Failed to add job application: " + be.getMessage();
			System.out.println(errorMessage);
			be.printStackTrace();
			JobApplicationAddResponse response = new JobApplicationAddResponse(errorMessage);
			return new ResponseEntity<JobApplicationAddResponse>(response, HttpStatus.BAD_REQUEST);
		} catch(UnauthorizedException ue) {
			// add loggedin user id to message
			String errorMessage = "Failed to add job application: " + ue.getMessage();
			System.out.println(errorMessage);
			ue.printStackTrace();
			JobApplicationAddResponse response = new JobApplicationAddResponse(errorMessage);
			return new ResponseEntity<JobApplicationAddResponse>(response, HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			// add loggedin user id to message
			String errorMessage = "Failed to add job application: " + e.getMessage();
			System.out.println(errorMessage);
			e.printStackTrace();
			JobApplicationAddResponse response = new JobApplicationAddResponse(errorMessage);
			return new ResponseEntity<JobApplicationAddResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/jobapplication")
	public ResponseEntity<JobApplicationUpdateResponse> updateJob(@RequestBody JobApplicationUpdateRequest jobApplicationUpdateRequest,
			@AuthenticationPrincipal UserDetail userDetail) {
		try {
			
			Validator.defaultValueCheck(jobApplicationUpdateRequest.getUserId(), "User Id");
			Validator.defaultValueCheck(jobApplicationUpdateRequest.getStatusId(), "Status Id");
			Validator.defaultValueCheck(jobApplicationUpdateRequest.getDateApplied(), "Date Applied Id");
			Validator.defaultValueCheck(jobApplicationUpdateRequest.getRank(), "Rank Id");
			Validator.emptyValueCheck(jobApplicationUpdateRequest.getCompanyName(), "Company Name");
			Validator.loggedInUserCheck(jobApplicationUpdateRequest.getUserId(), userDetail);

			
			JobApplication jobApplication = JobApplication.builder()
					.companyName(jobApplicationUpdateRequest.getCompanyName())
					.positionApplied(jobApplicationUpdateRequest.getPositionApplied())
					.apsName(jobApplicationUpdateRequest.getApsName())
					.notes(jobApplicationUpdateRequest.getNotes())
					.jobDescription(jobApplicationUpdateRequest.getJobDescription())
					.dateApplied(jobApplicationUpdateRequest.getDateApplied())
					.userId(jobApplicationUpdateRequest.getUserId())
					.statusId(jobApplicationUpdateRequest.getStatusId())
					.build();
			
			JobApplicationRank jobApplicationRank = JobApplicationRank.builder()
					.jobApplicationId(jobApplicationUpdateRequest.getId()).build();
			
			
			
			String successResponseMessage = "Job Application updated successfully for user: "
					+ jobApplicationUpdateRequest.getUserId() + " application name: "
					+ jobApplicationUpdateRequest.getCompanyName();
			System.out.println(successResponseMessage);
			
			JobApplicationUpdateResponse response = new JobApplicationUpdateResponse(successResponseMessage);
			return new ResponseEntity<JobApplicationUpdateResponse>(response, HttpStatus.OK);
		} catch(BadRequestException be) {
			// add loggedin user id to message
			String errorMessage = "Failed to update job application: " + be.getMessage();
			System.out.println(errorMessage);
			be.printStackTrace();
			JobApplicationUpdateResponse response = new JobApplicationUpdateResponse(errorMessage);
			return new ResponseEntity<JobApplicationUpdateResponse>(response, HttpStatus.BAD_REQUEST);
		} catch(UnauthorizedException ue) {
			// add loggedin user id to message
			String errorMessage = "Failed to update job application: " + ue.getMessage();
			System.out.println(errorMessage);
			ue.printStackTrace();
			JobApplicationUpdateResponse response = new JobApplicationUpdateResponse(errorMessage);
			return new ResponseEntity<JobApplicationUpdateResponse>(response, HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			// add loggedin user id to message
			String errorMessage = "Failed to update job application: " + e.getMessage();
			System.out.println(errorMessage);
			e.printStackTrace();
			JobApplicationUpdateResponse response = new JobApplicationUpdateResponse(errorMessage);
			return new ResponseEntity<JobApplicationUpdateResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
