public class NoteEditor {
    /**
     * Метод для редактирования текстового содержимого заметки
     * @param oldNote — текст текущей заметки, которую нужно изменить
     * @param newNote — новый текст
     */
    public void editNote(String oldNote, String newNote) {
        // Здесь могла бы быть логика поиска в базе данных или списке
        System.out.println("Заметка '" + oldNote + "' успешно отредактирована.");
        System.out.println("Новое содержимое: " + newNote);
    }

    /**
     * Метод для переименования заголовка заметки (опционально)
     */
    public void renameNote(String oldTitle, String newTitle) {
        System.out.println("Заголовок изменен с '" + oldTitle + "' на '" + newTitle + "'");
    }
}
