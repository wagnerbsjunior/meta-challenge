package meta.com.br.challenge.app;

import meta.com.br.challenge.core.scrapers.GitScraper;
import meta.com.br.challenge.core.utils.GitFilesToJson;

public class ScraperAppMain {

    private static GitScraper gitScraper = GitScraper.getGitScraper();

    public static void main(String[] args) {
        try {
            gitScraper.scrape_engine("", "devdojobr/devdojo-microservices");
            System.out.println(new GitFilesToJson().getObjectAsJson(gitScraper.getGitFiles()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
