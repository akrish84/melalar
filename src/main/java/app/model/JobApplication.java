package app.model;
import javax.persistence.*;

@Entity
@Table(name = "job_application")
public class JobApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	@Column(name = "resume_id")
	private long resumeId;

	@Override
	public String toString() {
		return "JobApplication [id=" + id + ", companyName=" + companyName + ", positionApplied=" + positionApplied
				+ ", apsName=" + apsName + ", notes=" + notes + ", jobDescription=" + jobDescription + ", dateApplied="
				+ dateApplied + ", userId=" + userId + ", statusId=" + statusId + ", resumeId=" + resumeId + "]";
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

	public long getResumeId() {
		return resumeId;
	}

	public void setResumeId(long resumeId) {
		this.resumeId = resumeId;
	}
	
	public JobApplication() {}

	public JobApplication(long id, String companyName, String positionApplied, String apsName, String notes,
			String jobDescription, long dateApplied, long userId, long statusId, long resumeId) {
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
		this.resumeId = resumeId;
	}
	
	
}
