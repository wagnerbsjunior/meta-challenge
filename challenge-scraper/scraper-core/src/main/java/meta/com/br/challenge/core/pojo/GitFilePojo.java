package meta.com.br.challenge.core.pojo;

public class GitFilePojo {

    private String extension;
    private int count;
    private int lines;

    public GitFilePojo(String extension,
                       int count,
                       int lines) {
        this.extension = extension;
        this.count = count;
        this.lines = lines;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }
}
