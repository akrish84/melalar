package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.exceptions.ObjectNotFoundException;
import app.model.UserJobApplicationStatus;
import app.repository.UserJobApplicationStatusRepository;

@Service
public class UserJobApplicationStatusService {

	@Autowired
	private UserJobApplicationStatusRepository userJobApplicationStatusRepository;
	
	public List<UserJobApplicationStatus> getStatusesForUser(long userId) {
		return userJobApplicationStatusRepository.findByUserId(userId);
	}
	
	public UserJobApplicationStatus addUserJobApplicationStatus(UserJobApplicationStatus userJobApplicationStatus ) {
		
		List<UserJobApplicationStatus> existingUserJobApplicationStatusList = getStatusesForUser(userJobApplicationStatus.getUserId());
		int maxRank = 1;
		
		for(UserJobApplicationStatus existingUserJobApplicationStatus : existingUserJobApplicationStatusList) {
			maxRank = Math.max(maxRank, existingUserJobApplicationStatus.getRank());
		}
		
		maxRank+=1;
		userJobApplicationStatus.setRank(maxRank);
		
		return userJobApplicationStatusRepository.save(userJobApplicationStatus);
	}
	
	public void updateUserJobApplicationStatus(UserJobApplicationStatus userJobApplicationStatus ) {
		
		List<UserJobApplicationStatus> existingUserJobApplicationStatusList = getStatusesForUser(userJobApplicationStatus.getUserId());
		boolean rankChanged = false;
		boolean updateRequestJobStatusFound = false;
		
		for(UserJobApplicationStatus existingUserJobApplicationStatus : existingUserJobApplicationStatusList) {
			if(existingUserJobApplicationStatus.getId() == userJobApplicationStatus.getId()) {
				updateRequestJobStatusFound = true;
				if(userJobApplicationStatus.getRank() != existingUserJobApplicationStatus.getRank()) {
					rankChanged = true;
				}
			}
			if(rankChanged) {
				existingUserJobApplicationStatus.setRank(existingUserJobApplicationStatus.getRank()+1);
			}
		}
		
		if(!updateRequestJobStatusFound) {
			throw new ObjectNotFoundException("UserJobApplicationStatus with id: " +  userJobApplicationStatus.getId() +" Not found");
		}
		
		if(rankChanged) {
			userJobApplicationStatusRepository.saveAll(existingUserJobApplicationStatusList);
		} else {
			userJobApplicationStatusRepository.save(userJobApplicationStatus);
		}

	}
}
