package meta.com.br.challenge.core.utils;

import com.google.gson.Gson;
import meta.com.br.challenge.core.models.GitFiles;
import meta.com.br.challenge.core.pojo.GitFilePojo;

import java.util.ArrayList;

public class GitFilesToJson {

    private int totalFiles = 0;
    private int totalLines = 0;

    public String getObjectAsJson(GitFiles gitFiles) {
        Gson gson = new Gson();
        return gson.toJson(sortAndGroup(gitFiles));
    }

    public ArrayList<GitFilePojo> sortAndGroup(GitFiles gitFiles) {
        gitFiles.sortExtensionAscending();

        ArrayList<GitFilePojo> gitFilesGrouped = new ArrayList<>();

        String prevExt = gitFiles.getByPosition(0).getExtension();

        for (int i = 0; i < gitFiles.countFiles(); i++) {

            if (!prevExt.equals(gitFiles.getByPosition(i).getExtension())) {
                GitFilePojo gitFile = new GitFilePojo(prevExt, totalFiles, totalLines);
                prevExt = gitFiles.getByPosition(i).getExtension();

                startNewCount();

                gitFilesGrouped.add(gitFile);
            } else {
                totalFiles ++;
                totalLines = totalLines + gitFiles.getByPosition(i).getLines();
            }
        }

        return gitFilesGrouped;
    }

    private void startNewCount() {
        totalFiles = 0;
        totalLines = 0;
    }
}
