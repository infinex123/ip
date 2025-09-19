package clover;

/**
 * Represents a command to mark or unmark a task in the task list.
 */
class MarkCommand extends Command {
    private final String arg;
    private final boolean mark;

    /**
     * Constructs a {@code MarkCommand} with the given index argument and action.
     *
     * @param arg  the argument string containing the index of the task
     * @param mark {@code true} to mark the task as done, {@code false} to unmark it
     */
    public MarkCommand(String arg, boolean mark) {
        this.arg = arg;
        this.mark = mark;
    }

    /**
     * Executes the MarkCommand to mark or unmark a task in the task list.
     *
     * @param tasks   the TaskList containing the tasks
     * @param ui      the Ui object used to display output to the user
     * @param storage the Storage object used to save tasks persistently
     * @throws DukeException if the index is invalid or cannot be parsed
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(arg.trim());
            Task task = tasks.get(index);
            if (mark) {
                task.markDone();
                ui.show(" Nice! I've marked this task as done:");
            } else {
                task.markUndone();
                ui.show(" OK, I've marked this task as not done yet:");
            }
            ui.show("   " + task);
            storage.save(tasks.asList());
        } catch (Exception e) {
            throw new DukeException("Invalid index for mark/unmark.");
        }
    }
}
