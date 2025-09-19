package clover;

/**
 * Represents a command to find tasks in the task list that match a given keyword.
 */
class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the specified keyword.
     *
     * @param keyword the search keyword used to match tasks
     */
    FindCommand(String keyword) {
        this.keyword = keyword == null ? "" : keyword.trim();
    }

    /**
     * Returns whether this command exits the application.
     *
     * @return {@code false} since this command does not exit the app
     */
    @Override
    boolean isExit() {
        return false;
    }

    /**
     * Executes the FindCommand by searching for tasks containing the keyword
     * (case-insensitive) in their descriptions, and displays the results.
     *
     * @param tasks   the TaskList to search within
     * @param ui      the Ui object used to display results to the user
     * @param storage the Storage (unused in this command)
     * @throws DukeException if the keyword is missing or empty
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (keyword.isEmpty()) {
            throw new DukeException("Please type in the following format: find <keyword>");
        }
        String q = keyword.toLowerCase();

        ui.show("     Here are the matching tasks in your list:");
        int shown = 0;
        for (Task t : tasks.asList()) {
            if (t.getDescription().toLowerCase().contains(q)) {
                shown++;
                ui.show("     " + shown + "." + t);
            }
        }
        if (shown == 0) {
            ui.show("     (no matches)");
        }
    }
}

