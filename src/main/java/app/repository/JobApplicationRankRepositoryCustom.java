package app.repository;

import java.util.List;
import java.util.Optional;

import app.model.JobApplicationRank;

public interface JobApplicationRankRepositoryCustom {
	public Optional<List<JobApplicationRank>> findByUserIdAndStatusId(long userId, long statusId);
}
