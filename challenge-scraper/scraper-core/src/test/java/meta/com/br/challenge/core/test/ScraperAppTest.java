package meta.com.br.challenge.core.test;

import meta.com.br.challenge.core.scrapers.GitScraper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScraperAppTest {

    private static GitScraper gitScraper = GitScraper.getGitScraper();

    @Test
    @DisplayName("Simple scraping test")
    void testSimpleScraping() {
        gitScraper.clearFiles();
        gitScraper.scrape_engine("", "wagnerbsjunior/wagnerbsjunior");
        assertEquals("yml", gitScraper.getGitFiles().getByPosition(0).getExtension(),
                "Scraper simple execution GET should work");
    }

    @Test
    @DisplayName("Simple count file test")
    void testCountFileScraping() {
        gitScraper.clearFiles();
        gitScraper.scrape_engine("", "wagnerbsjunior/wagnerbsjunior");
        assertEquals(1, gitScraper.getGitFiles().countFiles(),
                "Scraper simple file count should work");
    }

}
