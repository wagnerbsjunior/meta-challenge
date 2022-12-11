package meta.com.br.challenge.core.models;

public class GitFile extends FileObject {
    private String title;
    private int lines;

    public GitFile(String name, String extension, String title, int lines) {
        super(name, extension);
        this.title = title;
        this.lines = lines;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }
}
