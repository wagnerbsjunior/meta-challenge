package meta.com.br.challenge.api.rest;

import meta.com.br.challenge.api.service.RepositoryGitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scraper")
@CrossOrigin("http://localhost:4200")
public class RepositoryGitController {

    private final RepositoryGitService service;

    @Autowired
    public RepositoryGitController(RepositoryGitService service) {
        this.service = service;
    }

    @GetMapping
    public String countFiles(@RequestParam(name = "repository") String repository) {
        return this.service.getAnalytics(repository);
    }
}
