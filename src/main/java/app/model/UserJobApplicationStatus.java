package app.model;
import javax.persistence.*;

import lombok.Builder;

@Entity
@Builder
@Table(name = "user_job_application_status")
public class UserJobApplicationStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "status_name")
	private String statusName;
	
	@Column(name = "rank")
	private int rank;


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
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}

	public UserJobApplicationStatus() {}

	public UserJobApplicationStatus(long id, long userId, String statusName, int rank) {
		super();
		this.id = id;
		this.userId = userId;
		this.statusName = statusName;
		this.rank = rank;
	}
}
