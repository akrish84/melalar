package app.model;
import javax.persistence.*;

@Entity
@Table(name = "job_application_rank")
public class JobApplicationRank {

	@Column(name = "job_application_id")
	private long jobApplicationId;
	
	@Column(name = "rank")
	private int rank;

	@Override
	public String toString() {
		return "JobApplicationRank [jobApplicationId=" + jobApplicationId + ", rank=" + rank + "]";
	}

	public long getJobApplicationId() {
		return jobApplicationId;
	}

	public void setJobApplicationId(long jobApplicationId) {
		this.jobApplicationId = jobApplicationId;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public JobApplicationRank() {}

	public JobApplicationRank(long jobApplicationId, int rank) {
		super();
		this.jobApplicationId = jobApplicationId;
		this.rank = rank;
	}
	
}
