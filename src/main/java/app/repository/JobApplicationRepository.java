package app.repository;

import org.springframework.data.repository.CrudRepository;

import app.model.JobApplication;

public interface JobApplicationRepository extends CrudRepository<JobApplication, Long> {

}
