package meta.com.br.challenge.api.model.repository;

import meta.com.br.challenge.api.model.entity.RepoGitAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoGitAnalyticsRepository extends JpaRepository<RepoGitAnalytics, Integer> {

}
