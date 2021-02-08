package app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.model.UserJobApplicationStatus;
import app.model.authentication.UserDetail;
import app.model.requestresponse.UserJobApplicationStatusResponse;
import app.service.UserJobApplicationStatusService;

@RestController
public class JobStatusController {

	@Autowired
	private UserJobApplicationStatusService userJobApplicationStatusService;
	
	@GetMapping("/jobStatus")
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
}
