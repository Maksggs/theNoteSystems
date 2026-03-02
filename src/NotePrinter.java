import java.util.List;
/**
 * Вывод заметок в консоль
 */
public class NotePrinter {
    private NoteStorage storage;

    public NotePrinter() {
        this.storage = NoteStorage.getInstance();
    }

    public void printAllNotes() {
        List<Note> notes = storage.getAllNotes();
        if (notes.isEmpty()) {
            System.out.println("Нет заметок для отображения");
            return;
        }

        System.out.println("\n=== ВСЕ ЗАМЕТКИ ===");
        for (Note note : notes) {
            printNote(note);
        }
    }

    public void printNote(Note note) {
        System.out.println("ID: " + note.getId());
        System.out.println("Заголовок: " + note.getTitle());
        System.out.println("Дата: " + note.getDate());
        System.out.println("Содержание: " + note.getContent());
        System.out.println("Теги: " + note.getTags());
        System.out.println("Архивировано: " + note.isArchived());
        System.out.println("-------------------");
    }
}
