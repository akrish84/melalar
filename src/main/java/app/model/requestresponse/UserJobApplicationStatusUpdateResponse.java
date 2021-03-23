package app.model.requestresponse;

import java.util.Optional;

import app.model.UserJobApplicationStatus;
import lombok.Builder;

@Builder
public class UserJobApplicationStatusUpdateResponse {

	private Optional<UserJobApplicationStatus> jobStatus;
	private Optional<String> message;
	
	public Optional<UserJobApplicationStatus> getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(Optional<UserJobApplicationStatus> userJobApplicationStatus) {
		this.jobStatus = userJobApplicationStatus;
	}
	public Optional<String> getMessage() {
		return message;
	}
	public void setMessage(Optional<String> message) {
		this.message = message;
	}
}
