import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Импорт заметок из файла (упрощенный)
 */
public class NoteImporter {
    private NoteStorage storage;
    private NoteManager manager;

    public NoteImporter() {
        this.storage = NoteStorage.getInstance();
        this.manager = new NoteManager();
    }

    public boolean importFromTxt(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String title = null;
            String content = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Title: ")) {
                    title = line.substring(7);
                } else if (line.startsWith("Content: ")) {
                    content = line.substring(9);
                } else if (line.equals("---") && title != null && content != null) {
                    manager.createNote(title, content);
                    title = null;
                    content = null;
                }
            }

            System.out.println("Импорт завершен из файла: " + filename);
            return true;
        } catch (IOException e) {
            System.out.println("Ошибка при импорте: " + e.getMessage());
            return false;
        }
    }
}
