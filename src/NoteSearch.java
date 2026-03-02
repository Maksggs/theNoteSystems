import java.util.List;
import java.util.stream.Collectors;

/**
 * Поиск заметок по ключевым словам
 */
public class NoteSearch {
    private NoteStorage storage;

    public NoteSearch() {
        this.storage = NoteStorage.getInstance();
    }

    public List<Note> searchByKeyword(String keyword) {
        return storage.getAllNotes().stream()
                .filter(note -> !note.isArchived())
                .filter(note ->
                        note.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                                note.getContent().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void printSearchResults(String keyword) {
        List<Note> results = searchByKeyword(keyword);
        System.out.println("Найдено заметок по ключу '" + keyword + "': " + results.size());
        results.forEach(n -> System.out.println("  " + n.getId() + ": " + n.getTitle()));
    }
}

