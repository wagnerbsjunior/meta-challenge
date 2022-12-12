package meta.com.br.challenge.core.scrapers;

import meta.com.br.challenge.core.models.GitFile;
import meta.com.br.challenge.core.models.GitFiles;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class GitScraper {
    private static final String DEFAULT_TREE_URL = "https://github.com/%s/";

    private Set<String> subGitUrls = new HashSet<>();

    private static GitFiles gitFiles = new GitFiles();
    private static Map<String, String> gitCookies;
    static {
        Map<String, String> gitCookies = new HashMap<>();
        gitCookies.put("birthtime", "915177601");
        gitCookies.put("mature_content", "1");
        GitScraper.gitCookies = Collections.unmodifiableMap(gitCookies);
    }

    private static GitScraper gitScraper = new GitScraper();

    private GitScraper() { }

    public static GitScraper getGitScraper() { return gitScraper; }

    //Motor de varredura de dados em um determinado repositório
    public void scrape_engine(String url, String level) {
        try {
            gitFiles.clearAll();
            Document doc = getGitDocument(String.format(DEFAULT_TREE_URL, level));

            Elements gitElements = doc.select(String.format("a[href*=%s][class*=' ']", level));

            for (Element element : gitElements.select(".js-navigation-open")) {
                String curr_tag = element.attr("href");

                scrape_files(String.format(DEFAULT_TREE_URL, curr_tag), url);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrape_files(String url, String urlPrev) {
        try {
            for (Element element : getGitDocument(url).select(".js-navigation-open")) {
                    String curr_tag = element.attr("href");

                    if (curr_tag.matches(".*\\btree\\b.*") && curr_tag.length() > urlPrev.length()) {
                        scrape_files(String.format(DEFAULT_TREE_URL, curr_tag), url);
                    }

                    if (curr_tag.matches(".*\\bblob\\b.*")) {
                        scrape_content(String.format(DEFAULT_TREE_URL, curr_tag));
                    }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrape_content(String url) {
        try {
            GitFile gitFile = scrapeFile(getGitDocument(url));

            if (!gitFiles.getGitFilesList().contains(gitFile) && !Objects.isNull(gitFile)) {
                gitFiles.addFile(gitFile);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GitFile scrapeFile(Document doc) {
        String name = scrapeName(doc);
        if (Objects.nonNull(name)) {
            String extension = getFileExtension(name);
            String title = name;
            int lines = scrapeLines(doc);
            return new GitFile(name, extension, title, lines);
        } else {
            return null;
        }
    }

    private String scrapeName(Document doc) {
        try {
            return doc.select(".final-path").text();
        } catch (Exception e) {
            return "";
        }
    }

    private String getFileExtension(String fileName) {
        try {
            if (fileName.contains(".")) {
                return fileName.substring(fileName.indexOf(".")+1);
            } else {
                return "";
            }
        } catch (Exception e) {
            System.out.print("Erro ao tratar o arquivo" + fileName);
            return "";
        }
    }

    public Document getGitDocument(String gitUrl) throws IOException {
        return Jsoup.connect(gitUrl)
                .header("Accept-Encoding", "gzip, deflate")
                .ignoreContentType(true)
                .userAgent("Mozilla")
                .cookie("auth", "token")
                .execute()
                .parse();
    }

    private int scrapeLines(Document doc) {
        try {
            String lines = doc.select(".text-mono").text();
            lines = lines.substring(0, lines.indexOf(" lines"));
            return Integer.valueOf(lines);
        } catch (Exception e) {
            return 0;
        }
    }

    public GitFiles getGitFiles() {
        return gitFiles;
    }
}
