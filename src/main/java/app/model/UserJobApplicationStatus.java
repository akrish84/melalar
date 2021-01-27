package app.model;
import javax.persistence.*;

@Entity
@Table(name = "user_job_application_status")
public class UserJobApplicationStatus {
	
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "status_id")
	private long statusId;

	@Override
	public String toString() {
		return "UserJobApplicationStatus [userId=" + userId + ", statusId=" + statusId + "]";
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	public UserJobApplicationStatus() {}

	public UserJobApplicationStatus(long userId, long statusId) {
		super();
		this.userId = userId;
		this.statusId = statusId;
	}
}
