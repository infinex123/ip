package clover;

/**
 * Represents a command to delete a task from the task list.
 */
class DeleteCommand extends Command {
    private final String arg;

    /**
     * Constructs a {@code DeleteCommand} with the given argument string.
     *
     * @param arg the argument string containing the index of the task to delete
     */
    public DeleteCommand(String arg) {
        this.arg = arg;
    }

    /**
     * Executes the DeleteCommand to remove a task from the task list.
     *
     * @param tasks   the TaskList containing the tasks
     * @param ui      the Ui object used to display output to the user
     * @param storage the Storage object used to save tasks persistently
     * @throws DukeException if the argument is invalid or the index is out of range
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(arg.trim());
            Task t = tasks.remove(index);
            ui.show("Okay, I've removed this task:");
            ui.show("   " + t);
            ui.show("Now you have " + (tasks.size() - 1) + " tasks in the list.");
            storage.save(tasks.asList());
        } catch (Exception e) {
            throw new DukeException("Invalid index for delete.");
        }
    }
}
