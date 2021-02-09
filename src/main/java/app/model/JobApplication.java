package app.model;
import javax.persistence.*;

import lombok.Builder;

@Entity
@Builder
@Table(name = "job_application")
public class JobApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "position_applied")
	private String positionApplied;
	
	@Column(name = "aps_name")
	private String apsName;
	
	@Column(name = "notes")
	private String notes;
	
	@Column(name = "job_description")
	private String jobDescription;
	
	@Column(name = "date_applied")
	private long dateApplied;
	
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "status_id")
	private long statusId;
	

	@Override
	public String toString() {
		return "JobApplication [id=" + id + ", companyName=" + companyName + ", positionApplied=" + positionApplied
				+ ", apsName=" + apsName + ", notes=" + notes + ", jobDescription=" + jobDescription + ", dateApplied="
				+ dateApplied + ", userId=" + userId + ", statusId=" + statusId + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPositionApplied() {
		return positionApplied;
	}

	public void setPositionApplied(String positionApplied) {
		this.positionApplied = positionApplied;
	}

	public String getApsName() {
		return apsName;
	}

	public void setApsName(String apsName) {
		this.apsName = apsName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public long getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(long dateApplied) {
		this.dateApplied = dateApplied;
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
	
	public JobApplication() {}

	public JobApplication(long id, String companyName, String positionApplied, String apsName, String notes,
			String jobDescription, long dateApplied, long userId, long statusId) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.positionApplied = positionApplied;
		this.apsName = apsName;
		this.notes = notes;
		this.jobDescription = jobDescription;
		this.dateApplied = dateApplied;
		this.userId = userId;
		this.statusId = statusId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobApplication other = (JobApplication) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
}
