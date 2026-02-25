import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий заметку
 */
public class Note {
    private int id;
    private String title;
    private String content;
    private LocalDateTime date;
    private List<String> tags;
    private boolean archived;

    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = LocalDateTime.now();
        this.tags = new ArrayList<>();
        this.archived = false;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    public boolean isArchived() { return archived; }
    public void setArchived(boolean archived) { this.archived = archived; }
}