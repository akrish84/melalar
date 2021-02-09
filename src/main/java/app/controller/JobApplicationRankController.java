package app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.exceptions.BadRequestException;
import app.exceptions.ObjectNotFoundException;
import app.log.LogMessageBuilder;
import app.exceptions.ForbiddenException;
import app.model.JobApplication;
import app.model.JobApplicationRank;
import app.model.authentication.UserDetail;
import app.model.requestresponse.JobApplicationRankUpdateRequest;
import app.model.requestresponse.JobApplicationRankUpdateResponse;
import app.service.UpdateJobApplicationRankService;

import static app.controller.ActionsConstants.JOB_APPLICATION_RANK_UPDATE;;

@RestController
public class JobApplicationRankController {
	
	@Autowired
	private UpdateJobApplicationRankService updateJobApplicationRankService;
	
	@PutMapping("/jobapplication/rank")
	public ResponseEntity<JobApplicationRankUpdateResponse> updateJobApplicationRank(@RequestBody JobApplicationRankUpdateRequest jobApplicationRankUpdateRequest,
			@AuthenticationPrincipal UserDetail userDetail) {
		
		LogMessageBuilder logMessageBuilder = new LogMessageBuilder(JOB_APPLICATION_RANK_UPDATE, userDetail.getUserId());
		String errorMessage;
		HttpStatus httpStatus;
		Exception exception;
		System.out.println(logMessageBuilder.buildRequestStartMessage());
		try {
			Validator.defaultValueCheck(jobApplicationRankUpdateRequest.getJobApplicationId(), "Job Application Id");
			Validator.defaultValueCheck(jobApplicationRankUpdateRequest.getUserId(), "User Id");
			Validator.defaultValueCheck(jobApplicationRankUpdateRequest.getStatusId(), "Status Id");
			Validator.defaultValueCheck(jobApplicationRankUpdateRequest.getDateApplied(), "Date Applied Id");
			Validator.emptyValueCheck(jobApplicationRankUpdateRequest.getCompanyName(), "Company Name");
			Validator.emptyListCheck(jobApplicationRankUpdateRequest.getSortedJobApplicationIds(), "Sorted Job Application Ids");
			Validator.loggedInUserCheck(jobApplicationRankUpdateRequest.getUserId(), userDetail);

			
			final JobApplication jobApplication = JobApplication.builder()
					.id(jobApplicationRankUpdateRequest.getJobApplicationId())
					.userId(jobApplicationRankUpdateRequest.getUserId())
					.statusId(jobApplicationRankUpdateRequest.getStatusId())
					.build();
			
			int rank = jobApplicationRankUpdateRequest.getSortedJobApplicationIds().size();
			final List<JobApplicationRank> jobApplicationRankList = new ArrayList<>(jobApplicationRankUpdateRequest.getSortedJobApplicationIds().size());
			
			for(Long jobApplicationId : jobApplicationRankUpdateRequest.getSortedJobApplicationIds()) {
				final JobApplicationRank jobApplicationRank = JobApplicationRank.builder().jobApplicationId(jobApplicationId).rank(rank).build();
				jobApplicationRankList.add(jobApplicationRank);
				rank--;
			}
			
			updateJobApplicationRankService.updateJobApplicationRank(jobApplication, jobApplicationRankList);
			
			String successResponseMessage = logMessageBuilder.buildSuccessMessage("Successfully updated rank for JobApplication Job_App_Id: " + jobApplication.getId()); 
			System.out.println(successResponseMessage);
			
			JobApplicationRankUpdateResponse response = new JobApplicationRankUpdateResponse(successResponseMessage);
			return new ResponseEntity<JobApplicationRankUpdateResponse>(response, HttpStatus.OK);
			
		} catch(BadRequestException be) {
			
			errorMessage = logMessageBuilder.buildExceptionMessage(be.getMessage());
			exception = be;
			httpStatus = HttpStatus.BAD_REQUEST;
			
		} catch(ForbiddenException fe) {
			
			errorMessage = logMessageBuilder.buildExceptionMessage(fe.getMessage());
			exception = fe;
			httpStatus = HttpStatus.FORBIDDEN;
			
		} catch(ObjectNotFoundException oe) {
			
			errorMessage = logMessageBuilder.buildExceptionMessage(oe.getMessage());
			exception = oe;
			httpStatus =  HttpStatus.NOT_FOUND;
		}
		catch (Exception e) {
			
			errorMessage = logMessageBuilder.buildExceptionMessage("Internal Server Error.");
			exception = e;
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			
		}
		System.out.println(errorMessage);
		exception.printStackTrace();
		JobApplicationRankUpdateResponse response = new JobApplicationRankUpdateResponse(errorMessage);
		return new ResponseEntity<JobApplicationRankUpdateResponse>(response, httpStatus);
	}

}
