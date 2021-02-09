package app.model.requestresponse;

import java.util.List;

public class JobApplicationRankUpdateRequest extends JobApplicationRequest {
	
	private long jobApplicationId;
	private long newStatusId;
	private long userId;
	private List<Long> sortedJobApplicationIds;

	public long getJobApplicationId() {
		return jobApplicationId;
	}
	public void setJobApplicationId(long jobApplicationId) {
		this.jobApplicationId = jobApplicationId;
	}
	public long getNewStatusId() {
		return newStatusId;
	}
	public void setNewStatusId(long newStatusId) {
		this.newStatusId = newStatusId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getUserId() {
		return userId;
	}
	public List<Long> getSortedJobApplicationIds() {
		return sortedJobApplicationIds;
	}
	public void setSortedJobApplicationIds(List<Long> sortedJobApplicationIds) {
		this.sortedJobApplicationIds = sortedJobApplicationIds;
	}

}
