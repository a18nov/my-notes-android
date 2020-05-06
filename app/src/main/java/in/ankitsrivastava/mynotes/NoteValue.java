package in.ankitsrivastava.mynotes;

import java.io.Serializable;

public class NoteValue implements Serializable {
    private int id;
    private String title;
    private String content;

    public NoteValue(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public NoteValue() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
