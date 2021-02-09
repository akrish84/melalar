package app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import app.model.JobApplicationRank;

public interface JobApplicationRankRepository extends CrudRepository<JobApplicationRank, Long> , JobApplicationRankRepositoryCustom  {
	
	public Optional<JobApplicationRank> findByJobApplicationId(long jobApplicationId);
	
	public  Optional<List<JobApplicationRank>> findByJobApplicationIdIn(List<Long> jobApplicationIds);
	
	public void deleteByJobApplicationId(long jobApplicationId);

}
