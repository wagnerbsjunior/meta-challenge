package meta.com.br.challenge.core.utils;

import com.google.gson.Gson;
import meta.com.br.challenge.core.models.GitFiles;
import meta.com.br.challenge.core.pojo.GitFilePojo;

import java.util.ArrayList;

public class GitFilesToJson {

    private int totalFiles = 0;
    private int totalLines = 0;

    private ArrayList<GitFilePojo> gitFilesGrouped;

    public GitFilesToJson() {
        this.gitFilesGrouped = new ArrayList<>();
    }

    public String getObjectAsJson(GitFiles gitFiles) {
        Gson gson = new Gson();
        return gson.toJson(sortAndGroup(gitFiles));
    }

    //Retorna os dados dos arquivos, de modo ordenado e agrupagdo
    public ArrayList<GitFilePojo> sortAndGroup(GitFiles gitFiles) {
        //Ordena lista de arquivos
        gitFiles.sortExtensionAscending();

        String prevExt = "";

        for (int i = 0; i < gitFiles.countFiles(); i++) {
            //Atualiza contagem dos totalizadores
            sumCount(gitFiles.getByPosition(i).getLines());

            if (!prevExt.equals(gitFiles.getByPosition(i).getExtension())) {
                prevExt = gitFiles.getByPosition(i).getExtension();
                GitFilePojo gitFile = new GitFilePojo(prevExt, totalFiles, totalLines);

                //Zera contagem
                startCount();

                this.gitFilesGrouped.add(gitFile);
            }
        }

        return this.gitFilesGrouped;
    }

    private void startCount() {
        totalFiles = 0;
        totalLines = 0;
    }

    private void sumCount(int lines) {
        totalFiles ++;
        totalLines = totalLines + lines;
    }
}
