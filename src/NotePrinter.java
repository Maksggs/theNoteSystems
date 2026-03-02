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
 
        import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Статистика по заметкам
 */
public class NoteStatistics {
    private NoteStorage storage;

    public NoteStatistics() {
        this.storage = NoteStorage.getInstance();
    }

    public void printStatistics() {
        List<Note> notes = storage.getAllNotes();
        long activeCount = notes.stream().filter(n -> !n.isArchived()).count();
        long archivedCount = notes.stream().filter(Note::isArchived).count();

        Set<String> allTags = new HashSet<>();
        notes.forEach(n -> allTags.addAll(n.getTags()));

        System.out.println("\n=== СТАТИСТИКА ===");
        System.out.println("Всего заметок: " + notes.size());
        System.out.println("  Активных: " + activeCount);
        System.out.println("  В архиве: " + archivedCount);
        System.out.println("Уникальных тегов: " + allTags.size());
    }
}
 
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
 
        import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Установка напоминаний на заметки
 */
public class NoteReminder {
    private NoteStorage storage;
    private Map<Integer, Timer> reminders;

    public NoteReminder() {
        this.storage = NoteStorage.getInstance();
        this.reminders = new HashMap<>();
    }

    public void setReminder(int noteId, LocalDateTime reminderTime) {
        storage.findNoteById(noteId).ifPresentOrElse(note -> {
            // Отмена предыдущего напоминания, если было
            if (reminders.containsKey(noteId)) {
                reminders.get(noteId).cancel();
            }

            Timer timer = new Timer();
            long delay = java.time.Duration.between(LocalDateTime.now(), reminderTime).toMillis();

            if (delay > 0) {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("\n🔔 НАПОМИНАНИЕ: " + note.getTitle());
                    }
                }, delay);

                reminders.put(noteId, timer);
                System.out.println("Напоминание установлено на заметку " + noteId);
            } else {
                System.out.println("Время напоминания должно быть в будущем");
            }
        }, () -> System.out.println("Заметка не найдена"));
    }
}
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
 
        import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Архивирование старых заметок
 */
public class NoteArchiver {
    private NoteStorage storage;

    public NoteArchiver() {
        this.storage = NoteStorage.getInstance();
    }

    public void archiveOldNotes(int daysOld) {
        LocalDateTime threshold = LocalDateTime.now().minus(daysOld, ChronoUnit.DAYS);
        List<Note> notesToArchive = storage.getAllNotes().stream()
                .filter(n -> !n.isArchived())
                .filter(n -> n.getDate().isBefore(threshold))
                .toList();

        for (Note note : notesToArchive) {
            note.setArchived(true);
            System.out.println("Заметка " + note.getId() + " перемещена в архив (старше " + daysOld + " дней)");
        }

        if (notesToArchive.isEmpty()) {
            System.out.println("Нет заметок для архивации");
        }
    }

    public List<Note> getArchivedNotes() {
        return storage.getAllNotes().stream()
                .filter(Note::isArchived)
                .toList();
    }
}
