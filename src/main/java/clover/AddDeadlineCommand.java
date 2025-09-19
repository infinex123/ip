package clover;

class AddDeadlineCommand extends Command {
    private final String arg;

    /**
     * Constructs an AddDeadlineCommand with the given argument string.
     *
     * @param arg the argument string containing the description and deadline (with /by)
     */
    public AddDeadlineCommand(String arg) {
        this.arg = arg;
    }

    /**
     * Executes the AddDeadlineCommand to add a new Deadline task.
     *
     * @param tasks   the TaskList where the new task will be added
     * @param ui      the Ui object used to display output to the user
     * @param storage the Storage object used to save tasks
     * @throws DukeException if the argument is invalid or missing required parts
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        if (arg == null || arg.trim().isEmpty()) {
            throw new DukeException("Missing task description!!");
        }
        int at = arg.indexOf("/by");
        if (at < 0) {
            throw new DukeException("Missing '/by'. Eg. deadline return book /by Sunday");
        }

        String description = arg.substring(0, at).trim();
        String byRaw = arg.substring(at + 3).trim();
        if (description.isEmpty()) {
            throw new DukeException("Missing task description!!");
        }
        if (byRaw.isEmpty()) {
            throw new DukeException("Please let me know when it's due after '/by'.");
        }

        java.time.LocalDateTime by = Clover.parseFlexibleDateTime(byRaw);
        Task t = new Deadline(description, by);
        tasks.add(t);
        storage.save(tasks.asList());
        int TasksSize = tasks.size() - 1;
        ui.show("     Got it. I've added this task:");
        ui.show("       " + t);
        ui.show("     Now you have " + TasksSize + " tasks in the list.");
    }
}

