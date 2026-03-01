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