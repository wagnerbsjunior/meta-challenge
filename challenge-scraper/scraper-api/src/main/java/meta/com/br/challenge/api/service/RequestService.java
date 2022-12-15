package meta.com.br.challenge.api.service;

import meta.com.br.challenge.api.model.entity.File;
import meta.com.br.challenge.api.model.entity.Request;
import meta.com.br.challenge.api.model.repository.RequestRepository;
import meta.com.br.challenge.core.models.GitFile;
import meta.com.br.challenge.core.models.GitFiles;
import meta.com.br.challenge.core.scrapers.GitScraper;
import meta.com.br.challenge.core.utils.GitFilesToJson;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RequestService {

    private static GitScraper gitScraper = GitScraper.getGitScraper();

    private final RequestRepository repository;

    public RequestService(RequestRepository repository) {
        this.repository = repository;
    }

    //Retorna o resumo analítico do repositório
    public String getAnalytics(String repository) {
        //Primeiro, buscar dados em banco
        Request request = this.findRequest(repository);

        if (Objects.nonNull(request)) {
            gitScraper.clearFiles();
            request.getFiles().forEach(record -> {
                GitFile file = new GitFile(
                    record.getName(),
                    record.getExtension(),
                    record.getTitle(),
                    record.getLines());
                gitScraper.addGitFile(file);
            });
        } else {
            //Não encontrando ou conforme alguma condicao para atualização, varrer repositorio
            this.searchDataAndSave(repository);
        }
        return new GitFilesToJson().getObjectAsJson(gitScraper.getGitFiles());
    }

    //Nova pesquisa e grava resultados
    private void searchDataAndSave(String repository) {
        gitScraper.scrape_engine("", repository);
        this.saveData(repository);
    }

    private void saveData(String repository){
        Request request = new Request();
        request.setRepositorio(repository);

        if (!gitScraper.getGitFiles().getGitFilesList().isEmpty()) {
            this.setFiles(gitScraper.getGitFiles(), request);
        }
        this.repository.save(request);
    }

    private Request findRequest(String repository) {
        return this.repository.findRequest(repository);
    }

    private void setFiles(GitFiles gitFiles, Request request) {
        List<File> files = new ArrayList<>();

        gitFiles.getGitFilesList().forEach(file -> {
            File dataFile = new File();
            dataFile.setName(file.getName());
            dataFile.setTitle(file.getTitle());
            dataFile.setExtension(file.getExtension());
            dataFile.setLines(file.getLines());
            dataFile.setRequest(request);
            files.add(dataFile);
        });

        request.setFiles(files);
    }

}
