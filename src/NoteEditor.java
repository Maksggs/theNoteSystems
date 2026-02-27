import java.util.Optional;

/**
 * Редактирование существующих заметок
 */
public class NoteEditor {
    private NoteStorage storage;

    public NoteEditor() {
        this.storage = NoteStorage.getInstance();
    }

    public boolean editNote(int id, String newTitle, String newContent) {
        Optional<Note> noteOpt = storage.findNoteById(id);

        if (noteOpt.isPresent()) {
            Note note = noteOpt.get();
            if (newTitle != null && !newTitle.isEmpty()) {
                note.setTitle(newTitle);
            }
            if (newContent != null && !newContent.isEmpty()) {
                note.setContent(newContent);
            }
            System.out.println("Заметка с ID " + id + " обновлена");
            return true;
        } else {
            System.out.println("Заметка с ID " + id + " не найдена");
            return false;
        }
    }
}
//czbfn
