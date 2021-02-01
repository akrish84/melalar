package app.model;
import javax.persistence.*;

@Entity
@Table(name = "user_job_application_status")
public class UserJobApplicationStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "status_id")
	private long statusId;

	@Override
	public String toString() {
		return "UserJobApplicationStatus [id=" + id + ", userId=" + userId + ", statusId=" + statusId + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
