package meta.com.br.challenge.core.comparators;

import meta.com.br.challenge.core.models.GitFile;

import java.util.Comparator;

public class NameComparator implements Comparator<GitFile> {

    @Override
    public int compare(GitFile f1, GitFile f2) { return f1.getName().compareTo(f2.getName()); }
}
