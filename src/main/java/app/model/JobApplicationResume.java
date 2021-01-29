package app.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class JobApplicationResume {

	@Id
	private long jobApplicationId;
	private long resumeId;
	
	public long getJobApplicationId() {
		return jobApplicationId;
	}
	public void setJobApplicationId(long jobApplicationId) {
		this.jobApplicationId = jobApplicationId;
	}
	public long getResumeId() {
		return resumeId;
	}
	public void setResumeId(long resumeId) {
		this.resumeId = resumeId;
	}
}
