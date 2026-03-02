import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * История изменений заметок (упрощенная версия)
 */
public class NoteHistory {
    private Map<Integer, List<String>> history;

    public NoteHistory() {
        this.history = new HashMap<>();
    }

    public void saveSnapshot(Note note, String action) {
        history.computeIfAbsent(note.getId(), k -> new ArrayList<>())
                .add(action + " | " + note.getTitle() + " | " + java.time.LocalDateTime.now());
    }

    public void printHistory(int noteId) {
        if (history.containsKey(noteId)) {
            System.out.println("\n=== ИСТОРИЯ ЗАМЕТКИ " + noteId + " ===");
            history.get(noteId).forEach(System.out::println);
        } else {
            System.out.println("История для заметки " + noteId + " не найдена");
        }
    }
}
