package app.model.requestresponse;

import java.util.List;

import app.model.JobApplication;

public class JobApplicationGetResponse {

	private List<JobApplication> jobApplications;

	public List<JobApplication> getJobApplications() {
		return jobApplications;
	}

	public void setJobApplications(List<JobApplication> jobApplications) {
		this.jobApplications = jobApplications;
	}
}
