import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Хранилище всех заметок (синглтон)
 */
public class NoteStorage {
    private static NoteStorage instance;
    private List<Note> notes;
    private int nextId;

    private NoteStorage() {
        notes = new ArrayList<>();
        nextId = 1;
    }

    public static NoteStorage getInstance() {
        if (instance == null) {
            instance = new NoteStorage();
        }
        return instance;
    }

    public List<Note> getAllNotes() { return notes; }

    public void addNote(Note note) { notes.add(note); }

    public void removeNote(int id) {
        notes.removeIf(note -> note.getId() == id);
    }

    public Optional<Note> findNoteById(int id) {
        return notes.stream().filter(n -> n.getId() == id).findFirst();
    }

    public int getNextId() { return nextId++; }
}
