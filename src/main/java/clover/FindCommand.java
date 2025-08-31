package clover;

class FindCommand extends Command {
    private final String keyword;
    FindCommand(String keyword) {
        this.keyword = keyword == null ? "" : keyword.trim();
    }

    @Override boolean isExit() {
        return false;
    }

    @Override void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (keyword.isEmpty()) throw new DukeException("Please type in the following format: find <keyword>");
        String q = keyword.toLowerCase();

        ui.show("     Here are the matching tasks in your list:");
        int shown = 0;
        for (Task t : tasks.asList()) {
            if (t.getDescription().toLowerCase().contains(q)) {
                shown++;
                ui.show("     " + shown + "." + t);
            }
        }
        if (shown == 0) ui.show("     (no matches)");
    }
}
