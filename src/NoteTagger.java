import java.util.List;
import java.util.Optional;

/**
 * Добавление тегов к заметкам
 */
public class NoteTagger {
    private NoteStorage storage;

    public NoteTagger() {
        this.storage = NoteStorage.getInstance();
    }

    public boolean addTagToNote(int noteId, String tag) {
        Optional<Note> noteOpt = storage.findNoteById(noteId);

        if (noteOpt.isPresent()) {
            Note note = noteOpt.get();
            if (!note.getTags().contains(tag)) {
                note.getTags().add(tag);
                System.out.println("Тег '" + tag + "' добавлен к заметке " + noteId);
            } else {
                System.out.println("Тег '" + tag + "' уже существует");
            }
            return true;
        }
        System.out.println("Заметка не найдена");
        return false;
    }

    public boolean removeTagFromNote(int noteId, String tag) {
        Optional<Note> noteOpt = storage.findNoteById(noteId);

        if (noteOpt.isPresent()) {
            Note note = noteOpt.get();
            boolean removed = note.getTags().remove(tag);
            if (removed) {
                System.out.println("Тег '" + tag + "' удален из заметки " + noteId);
            }
            return removed;
        }
        return false;
    }
}
 
        import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтрация заметок по тегам
 */
public class NoteFilter {
    private NoteStorage storage;

    public NoteFilter() {
        this.storage = NoteStorage.getInstance();
    }

    public List<Note> filterByTag(String tag) {
        return storage.getAllNotes().stream()
                .filter(n -> !n.isArchived())
                .filter(n -> n.getTags().contains(tag))
                .collect(Collectors.toList());
    }

    public void printFilteredByTag(String tag) {
        List<Note> filtered = filterByTag(tag);
        System.out.println("\n=== ЗАМЕТКИ С ТЕГОМ '" + tag + "' ===");
        filtered.forEach(n ->
                System.out.println(n.getId() + ": " + n.getTitle()));
    }
}
