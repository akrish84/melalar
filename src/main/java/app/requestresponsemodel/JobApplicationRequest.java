package app.requestresponsemodel;

public class JobApplicationRequest {

	private long userId;
	private String companyName;
	private String positionApplied;
	private String apsName;
	private String notes;
	private String jobDescription;
	private long dateApplied;
	private long statusId;
	
	@Override
	public String toString() {
		return "PostJobApplicationPayload [userId=" + userId + ", companyName=" + companyName + ", positionApplied="
				+ positionApplied + ", apsName=" + apsName + ", notes=" + notes + ", jobDescription=" + jobDescription
				+ ", dateApplied=" + dateApplied + ", statusId=" + statusId + "]";
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public long getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(long dateApplied) {
		this.dateApplied = dateApplied;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}
}
