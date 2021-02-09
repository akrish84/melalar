package app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import app.model.UserJobApplicationStatus;

public interface UserJobApplicationStatusRepository extends CrudRepository<UserJobApplicationStatus, Long> {

	public List<UserJobApplicationStatus> findByUserId(long userId);
	
}
