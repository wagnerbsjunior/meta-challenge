package meta.com.br.challenge.core.models;

import meta.com.br.challenge.core.comparators.ExtensionComparator;
import meta.com.br.challenge.core.comparators.NameComparator;

import java.util.ArrayList;

public class GitFiles {
    private ArrayList<GitFile> gitFiles;

    public GitFiles() { gitFiles = new ArrayList<>(); }

    public void addFile(GitFile gitFile) { gitFiles.add(gitFile); }

    public GitFile getByPosition(int i) {
        return gitFiles.get(i);
    }

    public ArrayList<GitFile> getGitFilesList() {
        return gitFiles;
    }

    public void sortNameAscending() { gitFiles.sort(new NameComparator()); }

    //Ordena os arquivos por tipo de extensão
    public void sortExtensionAscending() { gitFiles.sort(new ExtensionComparator()); }

    public int countFiles() { return gitFiles.size(); }

    public int countByExtension(String extension) {
       int count = 0;
       this.sortExtensionAscending();
        for (GitFile gitFile : gitFiles) {
            if (gitFile.getExtension().matches(extension) ) count ++;
        }
        return count;
    }

    public void clearAll() {
        gitFiles.clear();
    }
}
