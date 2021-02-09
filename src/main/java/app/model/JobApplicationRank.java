package app.model;
import javax.persistence.*;

import lombok.Builder;

@Entity
@Builder
@Table(name = "job_application_rank")
public class JobApplicationRank implements Comparable<JobApplicationRank> {

	@Id
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

	@Override
	public int compareTo(JobApplicationRank o) {
		return Integer.compare(this.rank, o.getRank());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (jobApplicationId ^ (jobApplicationId >>> 32));
		result = prime * result + rank;
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
		JobApplicationRank other = (JobApplicationRank) obj;
		if (jobApplicationId != other.jobApplicationId)
			return false;
		if (rank != other.rank)
			return false;
		return true;
	}
	
}
