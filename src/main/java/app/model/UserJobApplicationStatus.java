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
	
	@Column(name = "status_name")
	private String statusName;

	@Override
	public String toString() {
		return "UserJobApplicationStatus [id=" + id + ", userId=" + userId + ", statusName=" + statusName + "]";
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public UserJobApplicationStatus() {}

	public UserJobApplicationStatus(long id, long userId, String statusName) {
		super();
		this.id = id;
		this.userId = userId;
		this.statusName = statusName;
	}
}
