import java.time.LocalDateTime;

public class NotesApp {
    public static void main(String[] args) {
        Note note = new Note("Купить продукты");
        NoteReminder reminder = new NoteReminder();

        // Установим напоминание на 10 секунд позже
        LocalDateTime reminderTime = LocalDateTime.now().plusSeconds(10);
        reminder.setReminder(note, reminderTime);

        System.out.println("Напоминание установлено для заметки: " + note.getContent() + "; на время: " + reminderTime);
    }
}