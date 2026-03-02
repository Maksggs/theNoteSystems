import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Архивирование старых заметок
 */
public class NoteArchiver {
    private NoteStorage storage;

    public NoteArchiver() {
        this.storage = NoteStorage.getInstance();
    }

    public void archiveOldNotes(int daysOld) {
        LocalDateTime threshold = LocalDateTime.now().minus(daysOld, ChronoUnit.DAYS);
        List<Note> notesToArchive = storage.getAllNotes().stream()
                .filter(n -> !n.isArchived())
                .filter(n -> n.getDate().isBefore(threshold))
                .toList();

        for (Note note : notesToArchive) {
            note.setArchived(true);
            System.out.println("Заметка " + note.getId() + " перемещена в архив (старше " + daysOld + " дней)");
        }

        if (notesToArchive.isEmpty()) {
            System.out.println("Нет заметок для архивации");
        }
    }

    public List<Note> getArchivedNotes() {
        return storage.getAllNotes().stream()
                .filter(Note::isArchived)
                .toList();
    }
}
