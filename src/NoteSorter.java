import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сортировка заметок
 */
public class NoteSorter {
    private NoteStorage storage;

    public NoteSorter() {
        this.storage = NoteStorage.getInstance();
    }

    public List<Note> sortByDate(boolean ascending) {
        Comparator<Note> comparator = Comparator.comparing(Note::getDate);
        if (!ascending) {
            comparator = comparator.reversed();
        }

        return storage.getAllNotes().stream()
                .filter(n -> !n.isArchived())
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public List<Note> sortByTitle(boolean ascending) {
        Comparator<Note> comparator = Comparator.comparing(Note::getTitle);
        if (!ascending) {
            comparator = comparator.reversed();
        }

        return storage.getAllNotes().stream()
                .filter(n -> !n.isArchived())
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public void printSortedByDate() {
        System.out.println("\n=== СОРТИРОВКА ПО ДАТЕ ===");
        sortByDate(true).forEach(n ->
                System.out.println(n.getId() + ": " + n.getTitle() + " (" + n.getDate() + ")"));
    }
}
