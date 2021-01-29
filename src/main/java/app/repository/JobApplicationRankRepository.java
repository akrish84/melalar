package app.repository;

import org.springframework.data.repository.CrudRepository;

import app.model.JobApplicationRank;

public interface JobApplicationRankRepository extends CrudRepository<JobApplicationRank, Long> {

}
