package app.controller;

import static app.controller.ActionsConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.exceptions.BadRequestException;
import app.exceptions.ForbiddenException;
import app.exceptions.ObjectNotFoundException;
import app.log.LogMessageBuilder;
import app.model.UserJobApplicationStatus;
import app.model.authentication.UserDetail;
import app.model.requestresponse.UserJobApplicationStatusAddRequest;
import app.model.requestresponse.UserJobApplicationStatusAddResponse;
import app.model.requestresponse.UserJobApplicationStatusResponse;
import app.model.requestresponse.UserJobApplicationStatusUpdateRequest;
import app.model.requestresponse.UserJobApplicationStatusUpdateResponse;
import app.service.UserJobApplicationStatusService;

@RestController
public class JobStatusController {

	@Autowired
	private UserJobApplicationStatusService userJobApplicationStatusService;
	
	@GetMapping("/jobStatuses")
	public @ResponseBody ResponseEntity<List<UserJobApplicationStatusResponse>> getStatuses(@RequestParam long userId, 
			@AuthenticationPrincipal UserDetail userDetail){
		
		Validator.defaultValueCheck(userId, "userID");
		Validator.loggedInUserCheck(userId, userDetail);
		
		List<UserJobApplicationStatus> userJobApplicationStatuses = userJobApplicationStatusService.getStatusesForUser(userId);
		List<UserJobApplicationStatusResponse> userJobApplicationStatusResponses =  new ArrayList<UserJobApplicationStatusResponse>();
		
		for(UserJobApplicationStatus userJobApplicationStatus: userJobApplicationStatuses) {
			userJobApplicationStatusResponses.add(UserJobApplicationStatusResponse.builder()
			.id(userJobApplicationStatus.getId())
			.statusName(userJobApplicationStatus.getStatusName())
			.build());
		}
		
		return new ResponseEntity<List<UserJobApplicationStatusResponse>>(userJobApplicationStatusResponses, HttpStatus.OK);	
	}
	
//	public @ResponseBody ResponseEntity<List<JSONObject>> getStatuses(@RequestParam long userId, 
//			@AuthenticationPrincipal UserDetail userDetail){
//		
//		Validator.defaultValueCheck(userId, "userID");
//		Validator.loggedInUserCheck(userId, userDetail);
//		
//		List<UserJobApplicationStatus> userJobApplicationStatuses = userJobApplicationStatusService.getStatusesForUser(userId);
//		List<JSONObject> entities = new ArrayList<JSONObject>();
//		
//		ObjectMapper objectMapper = new ObjectMapper();
//		
//		for(UserJobApplicationStatus userJobApplicationStatus: userJobApplicationStatuses) {
//			
//			entities.add(objectMapper.writeValueAsString(userJobApplicationStatus));
//		}
//		
//		return new ResponseEntity<List<JSONObject>>(entities, HttpStatus.OK);	
//	}
	
	
	@PostMapping("/jobStatuses")
	public @ResponseBody ResponseEntity<UserJobApplicationStatusAddResponse> addJobStatus(@RequestBody UserJobApplicationStatusAddRequest userJobApplicationStatusAddRequest,
			@AuthenticationPrincipal UserDetail userDetail) {
		
		String errorMessage;
		HttpStatus httpStatus;
		Exception exception = null;
		LogMessageBuilder logMessageBuilder = new LogMessageBuilder(USER_JOB_APPLICATION_STATUS_ADD, userDetail.getUserId());
		logMessageBuilder.buildRequestStartMessage();
		try {
			Validator.defaultValueCheck(userJobApplicationStatusAddRequest.getUserId(), "User Id");
			Validator.emptyValueCheck(userJobApplicationStatusAddRequest.getStatusName(), "Job Status Name");
			Validator.loggedInUserCheck(userJobApplicationStatusAddRequest.getUserId(), userDetail);

			
			UserJobApplicationStatus userJobApplicationStatus = UserJobApplicationStatus.builder()
																.userId(userJobApplicationStatusAddRequest.getUserId())
																.statusName(userJobApplicationStatusAddRequest.getStatusName()).build();
			
			userJobApplicationStatus = userJobApplicationStatusService.addUserJobApplicationStatus(userJobApplicationStatus);

			String successResponseMessage = logMessageBuilder.buildSuccessMessage("User Job Application Status " + userJobApplicationStatus.getStatusName() + " added.");
			System.out.println(successResponseMessage);

			UserJobApplicationStatusAddResponse response = UserJobApplicationStatusAddResponse.builder()
															.jobStatus(Optional.of(userJobApplicationStatus))
															.build();
			return new ResponseEntity<UserJobApplicationStatusAddResponse>(response, HttpStatus.OK);
		
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
			
		} catch (Exception e) {

			errorMessage = logMessageBuilder.buildExceptionMessage("Internal Server Error.");
			exception = e;
			httpStatus =  HttpStatus.INTERNAL_SERVER_ERROR;
			
		}
		
		System.out.println(errorMessage);
		exception.printStackTrace();
		
		return new ResponseEntity<UserJobApplicationStatusAddResponse>(
				UserJobApplicationStatusAddResponse.builder().message(Optional.of(errorMessage)).build(), httpStatus);
	}
	
	@PutMapping("/jobStatuses")
	public @ResponseBody ResponseEntity<UserJobApplicationStatusUpdateResponse> updateJobStatus(@RequestBody UserJobApplicationStatusUpdateRequest userJobApplicationStatusUpdateRequest,
			@AuthenticationPrincipal UserDetail userDetail) {
		
		String errorMessage;
		HttpStatus httpStatus;
		Exception exception = null;
		LogMessageBuilder logMessageBuilder = new LogMessageBuilder(USER_JOB_APPLICATION_STATUS_UPDATE, userDetail.getUserId());
		logMessageBuilder.buildRequestStartMessage();
		try {
			Validator.defaultValueCheck(userJobApplicationStatusUpdateRequest.getUserId(), "User Id");
			Validator.defaultValueCheck(userJobApplicationStatusUpdateRequest.getId(), "Job Status Id");
			Validator.emptyValueCheck(userJobApplicationStatusUpdateRequest.getStatusName(), "Job Status Name");
			Validator.defaultValueCheck(userJobApplicationStatusUpdateRequest.getRank(), "Job Status Rank");
			Validator.loggedInUserCheck(userJobApplicationStatusUpdateRequest.getUserId(), userDetail);

			
			UserJobApplicationStatus userJobApplicationStatus = UserJobApplicationStatus.builder()
																.id(userJobApplicationStatusUpdateRequest.getId())
																.userId(userJobApplicationStatusUpdateRequest.getUserId())
																.rank(userJobApplicationStatusUpdateRequest.getRank())
																.statusName(userJobApplicationStatusUpdateRequest.getStatusName()).build();
			
			userJobApplicationStatusService.updateUserJobApplicationStatus(userJobApplicationStatus);

			String successResponseMessage = logMessageBuilder.buildSuccessMessage("User Job Application Status " + userJobApplicationStatus.getStatusName() + " updated.");
			System.out.println(successResponseMessage);

			UserJobApplicationStatusUpdateResponse response = UserJobApplicationStatusUpdateResponse.builder()
																.jobStatus(Optional.of(userJobApplicationStatus))
																.build();

			return new ResponseEntity<UserJobApplicationStatusUpdateResponse>(response, HttpStatus.OK);
		
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
			
		} catch (Exception e) {

			errorMessage = logMessageBuilder.buildExceptionMessage("Internal Server Error.");
			exception = e;
			httpStatus =  HttpStatus.INTERNAL_SERVER_ERROR;
			
		}
		
		System.out.println(errorMessage);
		exception.printStackTrace();
		
		return new ResponseEntity<UserJobApplicationStatusUpdateResponse>(
				UserJobApplicationStatusUpdateResponse.builder().message(Optional.of(errorMessage)).build(),
				httpStatus);
	}
	
}
