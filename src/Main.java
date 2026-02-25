import java.time.LocalDateTime;

/**
 * Главный класс для демонстрации работы системы заметок
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== СИСТЕМА ЗАМЕТОК ===\n");

        // Создаем менеджер и несколько заметок
        NoteManager manager = new NoteManager();

        Note note1 = manager.createNote("Покупки", "Купить молоко, хлеб, яйца");
        Note note2 = manager.createNote("Идеи для проекта", "Добавить экспорт в JSON");
        Note note3 = manager.createNote("Встреча", "Встреча с командой в пятницу");

        // Добавляем теги
        NoteTagger tagger = new NoteTagger();
        tagger.addTagToNote(note1.getId(), "личное");
        tagger.addTagToNote(note1.getId(), "покупки");
        tagger.addTagToNote(note2.getId(), "работа");
        tagger.addTagToNote(note2.getId(), "проект");

        // Выводим все заметки
        NotePrinter printer = new NotePrinter();
        printer.printAllNotes();

        // Фильтр по тегам
        NoteFilter filter = new NoteFilter();
        filter.printFilteredByTag("работа");

        // Сортировка
        NoteSorter sorter = new NoteSorter();
        sorter.printSortedByDate();

        // Статистика
        NoteStatistics stats = new NoteStatistics();
        stats.printStatistics();

        // Поиск
        NoteSearch search = new NoteSearch();
        search.printSearchResults("проект");

        // Редактирование
        NoteEditor editor = new NoteEditor();
        editor.editNote(note1.getId(), "Список покупок", "Купить молоко, хлеб, яйца, сыр");

        // Экспорт
        NoteExporter exporter = new NoteExporter();
        exporter.exportToTxt("notes_backup.txt");

        // Напоминание
        NoteReminder reminder = new NoteReminder();
        reminder.setReminder(note3.getId(), LocalDateTime.now().plusSeconds(10));

        // Архивирование
        NoteArchiver archiver = new NoteArchiver();
        archiver.archiveOldNotes(30); // Архивируем заметки старше 30 дней
    }
}