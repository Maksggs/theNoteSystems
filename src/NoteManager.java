/**
 * Управление заметками: создание и удаление
 */
public class NoteManager {
    private NoteStorage storage;

    public NoteManager() {
        this.storage = NoteStorage.getInstance();
    }

    public Note createNote(String title, String content) {
        int id = storage.getNextId();
        Note note = new Note(id, title, content);
        storage.addNote(note);
        System.out.println("Заметка создана с ID: " + id);
        return note;
    }

    public boolean deleteNote(int id) {
        if (storage.findNoteById(id).isPresent()) {
            storage.removeNote(id);
            System.out.println("Заметка с ID " + id + " удалена");
            return true;
        } else {
            System.out.println("Заметка с ID " + id + " не найдена");
            return false;
        }
    }
}

