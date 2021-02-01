package app.model;

import javax.persistence.*;

@Entity
@Table(name = "job_application_status")
public class JobApplicationStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "status_name")
	private String statusName;

	@Override
	public String toString() {
		return "JobApplicationStatus [id=" + id + ", statusName=" + statusName + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public JobApplicationStatus() {}
	
	public JobApplicationStatus(long id, String statusName) {
		super();
		this.id = id;
		this.statusName = statusName;
	}
}
