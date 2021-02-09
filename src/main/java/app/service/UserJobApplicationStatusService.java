package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.UserJobApplicationStatus;
import app.repository.UserJobApplicationStatusRepository;

@Service
public class UserJobApplicationStatusService {

	@Autowired
	private UserJobApplicationStatusRepository userJobApplicationStatusRepository;
	
	public List<UserJobApplicationStatus> getStatusesForUser(long userId) {
		return userJobApplicationStatusRepository.findByUserId(userId);
	}
}
