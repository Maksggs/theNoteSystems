import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Экспорт заметок в файл
 */
public class NoteExporter {
    private NoteStorage storage;

    public NoteExporter() {
        this.storage = NoteStorage.getInstance();
    }

    public boolean exportToTxt(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            List<Note> notes = storage.getAllNotes();

            for (Note note : notes) {
                writer.println("ID: " + note.getId());
                writer.println("Title: " + note.getTitle());
                writer.println("Date: " + note.getDate());
                writer.println("Content: " + note.getContent());
                writer.println("Tags: " + String.join(", ", note.getTags()));
                writer.println("---");
            }

            System.out.println("Экспорт завершен. Файл: " + filename);
            return true;
        } catch (IOException e) {
            System.out.println("Ошибка при экспорте: " + e.getMessage());
            return false;
        }
    }

    public boolean exportToJson(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            List<Note> notes = storage.getAllNotes();

            writer.println("[");
            for (int i = 0; i < notes.size(); i++) {
                Note n = notes.get(i);
                writer.println("  {");
                writer.println("    \"id\": " + n.getId() + ",");
                writer.println("    \"title\": \"" + n.getTitle() + "\",");
                writer.println("    \"content\": \"" + n.getContent() + "\",");
                writer.println("    \"date\": \"" + n.getDate() + "\"");
                writer.println("  }" + (i < notes.size() - 1 ? "," : ""));
            }
            writer.println("]");

            System.out.println("JSON экспорт завершен: " + filename);
            return true;
        } catch (IOException e) {
            System.out.println("Ошибка при экспорте JSON: " + e.getMessage());
            return false;
        }
    }
}
