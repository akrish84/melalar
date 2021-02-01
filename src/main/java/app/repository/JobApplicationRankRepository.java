package app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import app.model.JobApplicationRank;

public interface JobApplicationRankRepository extends CrudRepository<JobApplicationRank, Long> {
	
	public List<JobApplicationRank> findByJobApplicationIdIn(List<Long> jobApplicationId);

}
