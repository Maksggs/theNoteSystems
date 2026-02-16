import java.time.LocalDateTime;

public class Note {
    private final String content;
    private LocalDateTime reminderTime;

    public Note(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getReminderTime() {
        return reminderTime; // Метод getReminderTime() остается, если он вам нужен
    }

    public void setReminder(LocalDateTime reminderTime) {
        this.reminderTime = reminderTime;
    }
}
