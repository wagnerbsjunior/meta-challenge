package meta.com.br.challenge.api.service;

import meta.com.br.challenge.api.model.entity.RepoGitAnalytics;
import meta.com.br.challenge.api.model.repository.RepoGitAnalyticsRepository;
import meta.com.br.challenge.core.scrapers.GitScraper;
import meta.com.br.challenge.core.utils.GitFilesToJson;
import org.springframework.stereotype.Service;

@Service
public class RepositoryGitService {

    private static GitScraper gitScraper = GitScraper.getGitScraper();

    private final RepoGitAnalyticsRepository repository;

    public RepositoryGitService(RepoGitAnalyticsRepository repository) {
        this.repository = repository;
    }

    public String getAnalytics(String repository) {
        gitScraper.scrape_engine("", repository);
        String response = new GitFilesToJson().getObjectAsJson(gitScraper.getGitFiles());

        RepoGitAnalytics repoData = new RepoGitAnalytics();
        repoData.setRepositorio(repository);

        this.repository.save(repoData);

        return response;
    }
}
