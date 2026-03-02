import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Статистика по заметкам
 */
public class NoteStatistics {
    private NoteStorage storage;

    public NoteStatistics() {
        this.storage = NoteStorage.getInstance();
    }

    public void printStatistics() {
        List<Note> notes = storage.getAllNotes();
        long activeCount = notes.stream().filter(n -> !n.isArchived()).count();
        long archivedCount = notes.stream().filter(Note::isArchived).count();

        Set<String> allTags = new HashSet<>();
        notes.forEach(n -> allTags.addAll(n.getTags()));

        System.out.println("\n=== СТАТИСТИКА ===");
        System.out.println("Всего заметок: " + notes.size());
        System.out.println("  Активных: " + activeCount);
        System.out.println("  В архиве: " + archivedCount);
        System.out.println("Уникальных тегов: " + allTags.size());
    }
}
