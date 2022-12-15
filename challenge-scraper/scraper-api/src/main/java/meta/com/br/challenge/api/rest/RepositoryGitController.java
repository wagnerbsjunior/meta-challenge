package meta.com.br.challenge.api.rest;

import meta.com.br.challenge.api.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scraper")
public class RepositoryGitController {

    private final RequestService service;

    @Autowired
    public RepositoryGitController(RequestService service) {
        this.service = service;
    }

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public String countFiles(@RequestParam(name = "repository") String repository) {
        return this.service.getAnalytics(repository);
    }
}
