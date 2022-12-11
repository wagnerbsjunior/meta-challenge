package meta.com.br.challenge.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScraperApiMain {

    public static void main(String[] args) {
        SpringApplication.run(ScraperApiMain.class, args);
    }
}
