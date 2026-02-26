import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Установка напоминаний на заметки
 */
public class NoteReminder {
    private NoteStorage storage;
    private Map<Integer, Timer> reminders;

    public NoteReminder() {
        this.storage = NoteStorage.getInstance();
        this.reminders = new HashMap<>();
    }

    public void setReminder(int noteId, LocalDateTime reminderTime) {
        storage.findNoteById(noteId).ifPresentOrElse(note -> {
            // Отмена предыдущего напоминания, если было
            if (reminders.containsKey(noteId)) {
                reminders.get(noteId).cancel();
            }

            Timer timer = new Timer();
            long delay = java.time.Duration.between(LocalDateTime.now(), reminderTime).toMillis();

            if (delay > 0) {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("\n🔔 НАПОМИНАНИЕ: " + note.getTitle());
                    }
                }, delay);

                reminders.put(noteId, timer);
                System.out.println("Напоминание установлено на заметку " + noteId);
            } else {
                System.out.println("Время напоминания должно быть в будущем");
            }
        }, () -> System.out.println("Заметка не найдена"));
    }
}