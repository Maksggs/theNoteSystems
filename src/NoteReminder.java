import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class NoteReminder {

    public void setReminder(Note note, LocalDateTime time) {
        note.setReminder(time);
        long delay = time.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli() - System.currentTimeMillis();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Напоминание о заметке: " + note.getContent());
            }
        }, delay);
    }
}