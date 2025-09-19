package clover;

/**
 * Represents a command to add a new {@link Event} task.
 */
class AddEventCommand extends Command {
    private final String arg;

    /**
     * Constructs an {@code AddEventCommand} with the given argument string.
     *
     * @param arg the argument string containing the description, start, and end times
     */
    public AddEventCommand(String arg) {
        this.arg = arg;
    }

    /**
     * Executes the AddEventCommand to add a new Event task.
     *
     * @param tasks   the TaskList where the new task will be added
     * @param ui      the Ui object used to display output to the user
     * @param storage the Storage object used to save tasks
     * @throws DukeException if the argument is invalid or missing required parts
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String s = arg == null ? "" : arg.trim();
        if (s.isEmpty()) {
            throw new DukeException("clover.Event format: event <desc> /from <start> /to <end>");
        }

        int f = s.indexOf("/from");
        int t = s.indexOf("/to");
        if (f < 0 || t < 0 || t <= f) {
            throw new DukeException("Need both '/from' and '/to'. Example: event meetup /from Mon 2pm /to 4pm");
        }

        String desc = s.substring(0, f).trim();
        String fromRaw = s.substring(f + 5, t);
        String toRaw = s.substring(t + 3).trim();

        if (desc.isEmpty()) {
            throw new DukeException("clover.Event needs a description before '/from'.");
        }
        if (fromRaw.isEmpty()) {
            throw new DukeException("Provide a start time after '/from'.");
        }
        if (toRaw.isEmpty()) {
            throw new DukeException("Provide an end time after '/to'.");
        }

        var from = Clover.parseFlexibleDateTime(fromRaw);
        var to = Clover.parseFlexibleDateTime(toRaw);

        Task task = new Event(desc, from, to);
        tasks.add(task);
        storage.save(tasks.asList());
        int TasksSize = tasks.size() - 1;
        ui.show("     Got it. I've added this task:");
        ui.show("       " + task);
        ui.show("     Now you have " + TasksSize + " tasks in the list.");
    }
}
