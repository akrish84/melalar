package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.exceptions.BadRequestException;
import app.exceptions.ForbiddenException;
import app.exceptions.ObjectNotFoundException;
import app.log.LogMessageBuilder;
import app.model.JobApplication;
import app.model.authentication.UserDetail;
import app.model.requestresponse.JobApplicationAddRequest;
import app.model.requestresponse.JobApplicationAddResponse;
import app.service.JobApplicationService;

import static app.controller.ActionsConstants.JOB_APPLICATION_ADD;

@RestController
public class JobApplicationController {

	@Autowired
	private JobApplicationService jobApplicationService;


	@PostMapping("/user/jobApplication")
	public ResponseEntity<JobApplicationAddResponse> addJobApplication(@RequestBody JobApplicationAddRequest jobApplicationRequest,
			@AuthenticationPrincipal UserDetail userDetail) {
		
		String errorMessage;
		HttpStatus httpStatus;
		Exception exception = null;
		LogMessageBuilder logMessageBuilder = new LogMessageBuilder(JOB_APPLICATION_ADD, userDetail.getUserId());
		logMessageBuilder.buildRequestStartMessage();
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
			
			String successResponseMessage = logMessageBuilder.buildSuccessMessage("Job Application for company name: " + jobApplication.getCompanyName() + " added.");
			System.out.println(successResponseMessage);
			
			JobApplicationAddResponse response = new JobApplicationAddResponse(successResponseMessage);
			return new ResponseEntity<JobApplicationAddResponse>(response, HttpStatus.OK);
		
		} catch(BadRequestException be) {
			
			errorMessage = logMessageBuilder.buildExceptionMessage(be.getMessage());
			exception = be;
			httpStatus =  HttpStatus.BAD_REQUEST;
			
		} catch(ForbiddenException fe) {
			
			errorMessage = logMessageBuilder.buildExceptionMessage(fe.getMessage());
			exception = fe;
			httpStatus =  HttpStatus.FORBIDDEN;
			
		} catch(ObjectNotFoundException oe) {
			errorMessage = logMessageBuilder.buildExceptionMessage(oe.getMessage());
			exception = oe;
			httpStatus = HttpStatus.NOT_FOUND;
			
		}
		catch (Exception e) {

			errorMessage = logMessageBuilder.buildExceptionMessage("Internal Server Error.");
			exception = e;
			httpStatus =  HttpStatus.INTERNAL_SERVER_ERROR;
			
		}
		
		System.out.println(errorMessage);
		exception.printStackTrace();
		JobApplicationAddResponse response = new JobApplicationAddResponse(errorMessage);
		return new ResponseEntity<JobApplicationAddResponse>(response, httpStatus);
	}
}
